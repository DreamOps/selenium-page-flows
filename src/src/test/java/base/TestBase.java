package base;

public class TestBase implements Input {

	private String myBaseUrl = "";

	/**
	 * Documents system property that sets the base url 
	 * for a test run. The base url is typically prepended 
	 * to relative urls for the various pages.
	 */
	public final static String WEBDRIVER_BASE_URL = "webdriver.base.url";
	
	/**
	 * 
	 * @return
	 */
	protected String getBaseUrl() {
		if (myBaseUrl.isEmpty()) myBaseUrl = System.getProperty(WEBDRIVER_BASE_URL,"");
		if (myBaseUrl.isEmpty()) myBaseUrl = baseUrl;
		return myBaseUrl;
	}	
}
