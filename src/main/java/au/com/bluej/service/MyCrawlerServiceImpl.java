package au.com.bluej.service;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import au.com.bluej.domain.Node;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

@Service("myCrawlerService")
public class MyCrawlerServiceImpl implements MyCrawlerService{
	
	private static final Logger _log = LoggerFactory.getLogger(MyCrawlerServiceImpl.class);

	@Value("${numberOfCrawlers}")
	private int numberOfCrawlers;
	
	@Value("${crawlStorageFolder}")
	private String crawlStorageFolder;
	
	@Value("${maxDepthOfCrawling}")
	private int maxDepthOfCrawling;

	
	@SuppressWarnings("unchecked")
	@Override
	public Node crawlMe(String url) throws Exception {
		url = url.replaceAll("\\/$","");
		//Use crawler4j lib for multi thread support in scrapping web pages.
		CrawlConfig config = new CrawlConfig();		
		Date date = new Date();
		
		//time in milliseconds so config object is different for every http request calling crawlMe
		//which starts another set of "numberOfCrawlers" crawlers.
		//This will address concurrent http user requests hitting the server.
		String time = String.valueOf(date.getTime()); 
		config.setCrawlStorageFolder(crawlStorageFolder.concat("/").concat(time));
		//config.setCrawlStorageFolder(crawlStorageFolder);
		
		/**
		 * Politeness delay in milliseconds (delay between sending two requests to the same host).
		 */
		//config.setPolitenessDelay(1000);
		
		/**
		 * Maximum number of pages to fetch For unlimited number of pages, this
		 * parameter should be set to -1
		 */
		//config.setMaxPagesToFetch(100);
		
		/**
		 * Maximum depth of crawling For unlimited depth this parameter should be
		 * set to -1
		 */
		config.setMaxDepthOfCrawling(maxDepthOfCrawling);
						
		PageFetcher pageFetcher = new PageFetcher(config);
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
		CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);
		controller.addSeed(url);
		
		Map<String, Node> crawlingCollection = new ConcurrentHashMap<String, Node>();
		//Map<String, Node> crawlingCollection = new Hashtable<String, Node>();
		Node node = new Node(url);
		crawlingCollection.put(url, node);
		MyCrawlerFactory factory = new MyCrawlerFactory(crawlingCollection);
		
		//Blocking methods execute synchronously and non-blocking methods execute asynchronously.
		//startNonBlocking requires waitUntilFinish
		
		//controller.startNonBlocking(factory, numberOfCrawlers);
		//controller.waitUntilFinish();
		
		controller.start(factory, numberOfCrawlers);
		
   	 	_log.info("==============" + numberOfCrawlers + " Crawling Threads =============="); 		
		/**
    	 _log.info("==========START WEB CRAWLER RESULT ================================================================="); 
    	 try{
         	 ObjectMapper objectMapper = new ObjectMapper();
         	 result = new String(objectMapper.writeValueAsBytes(node));
         	_log.info(result);  
		 }catch (JsonProcessingException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
		 }
    	 _log.info("==========END WEB CRAWLER RESULT =================================================================");     
        **/  
		return node;
	}
	
	
	
}
