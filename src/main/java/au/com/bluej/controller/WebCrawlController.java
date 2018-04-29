package au.com.bluej.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import au.com.bluej.domain.Node;
import au.com.bluej.exception.MyWebCrawlerException;
import au.com.bluej.service.MyCrawlerService;

@RestController
@RequestMapping("${rest.api.base.path}")
public class WebCrawlController {

	private static final Logger _log = LoggerFactory.getLogger(WebCrawlController.class);
	
	@Autowired
	private MyCrawlerService myCrawlerService;
	
	
	//eg. GET /api/v1/web-crawler?url=https://www.netregistry.com.au
	@SuppressWarnings("rawtypes")
	@GetMapping
	public ResponseEntity crawlMe(@RequestParam("url") String url){
		Node node = null;
		try {
			node = myCrawlerService.crawlMe(url);	
			return new ResponseEntity<Node>(node, HttpStatus.OK);
		}catch(MyWebCrawlerException me) {
			_log.debug("There is an exception in MyCrawlerService " + me.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(me.getMessage());
		}catch (Exception e) {
			_log.debug("There is an exception in MyCrawlerService " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	
		
	}
	
}
