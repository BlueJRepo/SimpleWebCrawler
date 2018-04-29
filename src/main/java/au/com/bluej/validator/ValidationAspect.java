package au.com.bluej.validator;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import au.com.bluej.exception.MyWebCrawlerException;

@Component
@Aspect
public class ValidationAspect{
	

	@Pointcut("execution(public * au.com.bluej.service.MyCrawlerService.crawlMe(..))")
	public void validateCrawlMeInput() {}
	
	@Before("validateCrawlMeInput()")
	public void validateUrl(JoinPoint joinPoint) throws Exception {

		String href = (String) joinPoint.getArgs()[0];
		
		if(!URLValidator.isWorthCrawlingURL(href)) 		
			throw new MyWebCrawlerException("This URL: " + href + " is NOT worth crawling.");
		
	}
	

	
}
