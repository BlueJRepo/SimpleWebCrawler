package au.com.bluej.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import au.com.bluej.domain.Node;
import au.com.bluej.validator.URLValidator;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

public class MyCrawler extends WebCrawler{
	
	private static final Logger _log = LoggerFactory.getLogger(MyCrawler.class);

	private Map<String, Node> crawlingCollection;

	private  Node currentNode;
	
	public MyCrawler(Map<String, Node> crawlingCollection) {
		this.crawlingCollection = crawlingCollection;
	}
	
	@Override
	public void visit(Page page) {
		 if (page.getParseData() instanceof HtmlParseData) {

			 String url = page.getWebURL().getURL();             
			 url = url.replaceAll("\\/$","");                    			 			 
			 HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();            
             String html = htmlParseData.getHtml();
             
             //use jsoup lib for the ease of html parsing and search support api
             Document htmlDocument = Jsoup.parse(html); 
             String title = null;
             if(!htmlDocument.getElementsByTag("title").isEmpty()) {
            	 title = htmlDocument.getElementsByTag("title").get(0).text();
             }
             
             _log.info("crawling: " +url);
             
             //extract custom data             
             currentNode = crawlingCollection.get(url);   
             if(currentNode == null) {               	
            	 currentNode = new Node();
            	 currentNode.setUrl(url);           	             	
             }             
             currentNode.setTitle(title);
             Set<WebURL> links = htmlParseData.getOutgoingUrls();
             if(!links.isEmpty() && currentNode.getNodes().isEmpty()) {

            	 //Update the found node 
            	 
            	 //java 8
            	 links.stream()
            	 		.filter(link -> URLValidator.isWorthCrawlingURL(link.getURL().replaceAll("\\/$","")) && shouldVisit(page, link))
            	 		.filter(link ->  crawlingCollection.get(link.getURL().replaceAll("\\/$","")) == null)
            	 		.forEach(link -> {
            	 			Node myNode = new Node();
            	 			String newUrl = link.getURL().replaceAll("\\/$","");
            	 			myNode.setUrl(newUrl);
            	 			crawlingCollection.put(newUrl, myNode);
            	 			currentNode.getNodes().add(myNode);
            	 		});
            	 
            	 /**
            	  *	             			            		            
	             for(WebURL link : links){
	            	 String newUrl = link.getURL();
	            	 newUrl = newUrl.replaceAll("\\/$",""); 
	            	 if(URLValidator.isWorthCrawlingURL(newUrl) && shouldVisit(page, link)) { 	            		 
	            		 Node myNode = crawlingCollection.get(newUrl); 
	            		 if(myNode == null) {	
	            			 myNode = new Node();
	            			 myNode.setUrl(newUrl);	
	            			 crawlingCollection.put(newUrl, myNode);
	            			 currentNode.getNodes().add(myNode);
	            		 }
	            	 }
	             }	             
	             **/
            	 
             }
		 	 
             crawlingCollection.put(url, currentNode);                        
		 }
		 
	}
 
}
