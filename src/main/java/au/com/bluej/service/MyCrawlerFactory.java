package au.com.bluej.service;

import java.util.Map;
import au.com.bluej.domain.Node;
import edu.uci.ics.crawler4j.crawler.CrawlController.WebCrawlerFactory;
import edu.uci.ics.crawler4j.crawler.WebCrawler;

public class MyCrawlerFactory  implements WebCrawlerFactory{
	

	private Map<String, Node> crawlingCollection;
	
	public MyCrawlerFactory(Map<String, Node> crawlingCollection) {this.crawlingCollection = crawlingCollection;}
	
	@Override
	public WebCrawler newInstance() throws Exception {
		return new MyCrawler(crawlingCollection);
	}

}
