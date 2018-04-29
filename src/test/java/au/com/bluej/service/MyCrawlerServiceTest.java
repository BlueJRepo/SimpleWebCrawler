package au.com.bluej.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import au.com.bluej.domain.Node;
import au.com.bluej.exception.MyWebCrawlerException;


@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MyCrawlerServiceTest {

	private static final Logger _log = LoggerFactory.getLogger(MyCrawlerServiceTest.class);
	
	@Autowired
	private MyCrawlerService myCrawlerService;
	
	@Test //testing Validation Aspect 
	public void test_crawlMe_should_throw_MyWebCrawlerException_when_invalid_url_input() {
		Exception expected = null;
		String url = "https://www.netregistry.com.au/images/logo.png";		
		try {
			myCrawlerService.crawlMe(url);
		} catch (Exception e) {
			expected = e;
		}
		if(expected != null) {
			_log.info(expected.getMessage());
		}
		assertTrue(expected instanceof MyWebCrawlerException);
	} 
	
	@Test //compare jsoup result with craw4j result
	public void test_crawlMe_should_return_valid_result() {
		String url = "https://www.netregistry.com.au/domain-names/domain-names-for-sale";
		Node result = null;
		
		try {
			result = myCrawlerService.crawlMe(url); //craw4j getOutGoingUrls
			
			_log.info("===========================================================================");
			 try{
	         	 ObjectMapper objectMapper = new ObjectMapper();
	         	 String json = new String(objectMapper.writeValueAsBytes(result));
	         	_log.info(json);  
			 }catch (JsonProcessingException e) {
				 // TODO Auto-generated catch block
				 _log.info(e.getMessage());
			 }
			_log.info("===========================================================================");
			
		} catch (Exception e) {			
			_log.info(e.getMessage());
		}	
		
		Node cnode = new Node("http://www.netfleet.com.au/buy-domains");		
		assertTrue(result != null && result.getNodes().contains(cnode));
	}
	
	
	@Test
	public void testRetrevieTitleUsingJSOUP() throws IOException {
		Document doc = Jsoup.connect("https://www.facebook.com/netregistry").get();
		String title = null;
		if(!doc.getElementsByTag("title").isEmpty()){
			title = doc.getElementsByTag("title").get(0).text();
			_log.info("title =" + title);
		}
		assertEquals(title,"Netregistry - Home | Facebook");
	}
	
	

}
