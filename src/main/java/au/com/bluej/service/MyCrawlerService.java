package au.com.bluej.service;

import au.com.bluej.domain.Node;

public interface MyCrawlerService {

	Node crawlMe(String url) throws Exception;

}
