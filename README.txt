/*
*SIMPLE WEB CRAWLER REST SERVICE
*Author Steven Nguyen
*Date 30/04/2018
*/

1. 
	This RESTFUL service is implemented by using Java/Springboot/Crawler4j for multithreads crawler and 
	Jsoup API for the ease of html elements access. 
    
    The project is packaged using maven and should be decompressed before starting from commandline:
	
		C:\workspace\SimpleWebCrawler>mvn spring-boot:run
	
	
2.
	The crawling process behind is purely based on Crawler4j, it requires initial parameters which defines the number 
	of crawling threads and max page depth.
	
	These parameters are listed in the application.properties file.
	
3. 
	Testing can be done via at least 3 ways.
	
		a. Using curl, install curl on window and add curl to path.
	
			C:\>curl http://localhost:8081/api/v1/web-crawler?url=[SOME_INPUT_URL]
	   
		b. Using Chrome, open chrome browers and type in the url in (a).
		
		c. Chrome's plug-in postman, install postman extension and open it from chrome browser 
		   go to "chrome://apps/", click on Postman icon and start using.
		
4.  
	Crawling service can also be run without starting up the springboot service 
    		
		C:\workspace\SimpleWebCrawler>mvn clean -Dtest=MyCrawlerServiceTest#test_crawlMe_should_return_valid_result  test
	   
	
	