/*
*SIMPLE WEB CRAWLER REST SERVICE
*Author Steven Nguyen
*Date 30/04/2018
*http://localhost:8081/api/v1/web-crawler?url=[SOME_INPUT_URL]
*/

1. 
	This RESTFUL service is implemented by using Java/Springboot/Crawler4j for multithreads crawler and 
	Jsoup API for the ease of html elements access. 
    
	The project is packaged using maven and can be started from commandline:
	
		C:\workspace\SimpleWebCrawler>mvn spring-boot:run
	
	
2.
	The crawling process behind is purely based on Crawler4j, it requires initial parameters which 
	defines the number of crawling threads, and max page depth of crawling.
	
	numberOfCrawlers=5	 
	maxDepthOfCrawling=1 (increase this parameter will increase memory usage)
	
	These parameters are listed in the application.properties file.
	
3. 
	Testing can be done via at least 3 ways.
	
		a. Using curl, install curl on window and add curl to path.
	
			C:\>curl http://localhost:8081/api/v1/web-crawler?url=[SOME_INPUT_URL]
	   
		b. Using Chrome, open chrome browers and type in the url in (a).
		
		c. Chrome's plug-in postman, install postman extension and open it from chrome browser 
		   go to "chrome://apps/", click on Postman icon and start using.
		
4. 
	Shutdown, at least via 2 ways: 
		
		a. Run netstat to find springboot PID  
				C:>netstat -o -a -n | find "8081"
		   	 	Use "Task Manager" on window to end task with the found PID 
		   	 	or use "kill -9 [pid]" on linux.
		
		b. Enable endpoints.shutdown.enabled=true in application.properties so 
		springboot application can be shutdown by sending shutdown request. 
5.  
	Crawling service can also be invoked without starting up the springboot service 
    		
		C:\workspace\SimpleWebCrawler>mvn clean -Dtest=MyCrawlerServiceTest#test_crawlMe_should_return_valid_result  test