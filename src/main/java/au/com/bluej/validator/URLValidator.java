package au.com.bluej.validator;

import java.util.regex.Pattern;

public class URLValidator {

	private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg|png|mp3|mp4|tar|jar|war|ear|zip|gz|svg|rss|webmanifest))$");;
		
	public static boolean isWorthCrawlingURL(String href) {
		if(FILTERS.matcher(href).matches()) return false;
		return true;
	}
}
