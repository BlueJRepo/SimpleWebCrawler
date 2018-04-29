package au.com.bluej;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 01/05/2018
 * @author Steven Nguyen
 * Qantas Loyalty code challenge - Simple Web Crawler
 * mvn spring-boot:run
 */

@SpringBootApplication
public class SimpleWebCrawler 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(SimpleWebCrawler.class, args);  
    }
}
