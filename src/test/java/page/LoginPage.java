package page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import base.PageBase;

public class LoginPage extends PageBase {
	
	// -- WebElements
	
	@FindBy(how = How.ID, using = "username")
    @CacheLookup	
	private WebElement Username;
	
	@FindBy(how = How.ID, using = "password")
    @CacheLookup	
	private WebElement Password;
	
	@FindBy(how = How.ID, using = "Login")
    @CacheLookup	
	private WebElement Login;
	
	// -- Constructors
	
	public LoginPage(PageBase parent, Boolean navigate) {
		super(parent, navigate);
	}
	
	public LoginPage (PageBase parent){
		super(parent);
	}
	
	public LoginPage() {
		super();
	}
	
	// -- Overrides
	
	@Override
	public String getUrl() {
		return loginUrl;
	}
	
	// -- Methods
	
	public PageBase doLogin(String aUsername, String aPassword) {
		
		// Login
		this.Username.sendKeys(aUsername);
		this.Password.sendKeys(aPassword);
		Login.click();
		
		// Are we there yet?
		String newTitle = driver.getTitle();	
		if (newTitle.equals(homeTitle)) {
			return new HomePage(this); 
		} else {
			return this;
		}
	}
	
	public HomePage doLogin() {
		return (HomePage) doLogin(username, password);
	}
	
	public HomePage doLoginAdmin() {
		return (HomePage) doLogin(usernameAdmin, password);
	}
	
}