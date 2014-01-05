package base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.PageFactory;

public class PageBase implements Input {

	/**
	 * Documents the system property that is used to inject a different 
	 * webdriver into the tests. Set the property to the name of the 
	 * alternative driver to use ("InternerExplorerDriver", "ChromeDriver", 
	 * and so forth).
	 */
	public final static String WEBDRIVER_DRIVER_NAME = "webdriver.driver.name";
	
	/** 
	 * Documents the system property that indicates the location of the 
	 * ChromeDriver binary, if it is not installed in the default location. 
	 * https://code.google.com/p/selenium/wiki/ChromeDriver
	 */
	public final static String WEBDRIVER_CHROME_DRIVER = "webdriver.chrome.driver";
	
	protected WebDriver driver;
	
	/**
	 * Constructs a PageBase object with the default or injected 
	 * driver, and navigates to the page object's url, 
	 * as returned by getUrl.
	 */
	public PageBase() {
		this(null,true);		
	}
	
	/**
	 * Constructs a PageBase with the driver used by the passed 
	 * parent object, without navigation.
	 * @param parent Prior object in a page flow
	 */
	public PageBase(PageBase parent) {
		this(parent,false);
	}
	
	/**
	 * Constructs a PageBase using the options passed
	 * @param parent Prior object in a page flow, or null
	 * @param navigate True to navigate to the location returned by getUrl
	 */
	public PageBase(PageBase parent, Boolean navigate) {
		if ((parent==null) || (parent.driver == null)) {
			String myDriver = System.getProperty(WEBDRIVER_DRIVER_NAME,"FireFoxDriver");
			// String-based case statements  are Java 1.7
			if (myDriver=="FireFoxDriver")  driver = new FirefoxDriver();
			else if (myDriver=="InternetExplorerDriver")  driver = new InternetExplorerDriver();
			else if (myDriver=="SafariDriver")  driver = new SafariDriver();
			else if (myDriver=="ChromeDriver")  driver = new ChromeDriver();
			else if (myDriver=="HtmlUnitDriver") driver = new HtmlUnitDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
 		} else {
			driver = parent.driver;
		}
		if (navigate) navigateTo(getUrl());
        PageFactory.initElements(this.driver, this);		
	}
	
	// -- Methods to override
	
	/**
	 * Returns the absolute url to open the page. 
	 * @return Absolute url for the page
	 */
    public String getUrl() {
    		return Input.baseUrl;
    }
	
    // -- Optional methods to override 
    
	public java.util.List<String> getAllErrors() {
		throw new java.lang.UnsupportedOperationException();
	}

	public java.util.List<String> getAllPrompts() {
		throw new java.lang.UnsupportedOperationException();
	}
		
	// -- Driver methods 
	
	/**
	 * Navigates browser to the url
	 * @param url Absolute url
	 */
	public PageBase navigateTo(String url) {
		driver.get(url);
		return this;
	}

	/**
	 * Returns title of the current browser page. 
	 * @return Browser page title
	 */
	public String getTitle()  {
		return driver.getTitle();
	}
	
	/**
	 * Returns url of the current browser page. 
	 * @return Browser page url
	 */
	public String getCurrentUrl()  {
		return driver.getCurrentUrl();
	}

	/**
	 * Returns HTML source of the current browser page. 
	 * @return Browser HTML source
	 */
	public String getPageSource()  {
		return driver.getPageSource();
	}
	
	/**
	 * Closes the browser window.
	 */
	public void close() {
		driver.close();
		driver = null;
	}
		
}