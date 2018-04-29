package au.com.bluej.controller;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.junit.runners.MethodSorters;


@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WebCrawlControllerTest {

	
	@Autowired
    private WebApplicationContext wac;
	
	private MockMvc mockMvc;
		
	@Value("${rest.api.base.path}")
	private String REST_API_BASE_PATH; 
	
	@Before
    public void setup() { 
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
		
	@Test
	public void test_crawlMe_should_return_200_OK() throws Exception{		
        String url = "https://www.netregistry.com.au";
		ResultMatcher ok  = MockMvcResultMatchers.status().isOk();        
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
        		.get(REST_API_BASE_PATH+"?url="+url)
        		.accept(MediaType.APPLICATION_JSON)
        		.contentType(MediaType.APPLICATION_JSON);
        ResultActions result = this.mockMvc.perform(builder).andExpect(ok);
                
        //result.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void test_crawlMe_should_return_error_bad_request() throws Exception{		
        String url = "https://www.netregistry.com.au/images/logo.png";
		ResultMatcher error  = MockMvcResultMatchers.status().isBadRequest(); 
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
        		.get(REST_API_BASE_PATH+"?url="+url)
        		.accept(MediaType.APPLICATION_JSON)
        		.contentType(MediaType.APPLICATION_JSON);
        ResultActions result = this.mockMvc.perform(builder).andExpect(error);
                
        //result.andDo(MockMvcResultHandlers.print());
	}
 
}
