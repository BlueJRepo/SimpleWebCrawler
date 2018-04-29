package au.com.bluej.exception;

public class MyWebCrawlerException extends Exception{

	private static final long serialVersionUID = 3502655121080084208L;
	@SuppressWarnings("unused")
	private String errorMessage;
	
	public MyWebCrawlerException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
	
	public MyWebCrawlerException() {
		super();
	}
}
