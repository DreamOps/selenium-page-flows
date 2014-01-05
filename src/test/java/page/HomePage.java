package page;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import base.PageBase;

public class HomePage extends PageBase {
	
	private final static String UserMenu_ID = "UserNavLabel"; // Example

	// -- WebElements
	
	@FindBy(how = How.ID, using = UserMenu_ID)
    // @CacheLookup
	private WebElement UserMenu;
	
	@FindBy(how = How.PARTIAL_LINK_TEXT, using = "Logout")
	private WebElement Logout;
	
	@FindBy(how = How.ID, using = "phHeaderLogoImage")
	private WebElement HeaderMenu;
	
	@FindBy(how = How.PARTIAL_LINK_TEXT, using = "Home")
	private WebElement HomeTab;

	// -- Constructors
	
	public HomePage(PageBase parent){
		super(parent);
	}
	
	public HomePage(PageBase parent, Boolean navigate){
		super(parent, navigate);
	}

	public HomePage() {
		super();
	}

	// -- Methods

	public LoginPage doLogOut() {
        UserMenu.click();
        Logout.click();
		return null; // new LoginPage(driver);
	}
	
	// Presses 2 keyboard keys simultaneously
	// Selenium Actions didn't seem to work
	// Bug Test for AMS-1836 - See OrderTest
	public HomePage doNimbleShortCutExecute() {
		// set focus to header image
		HomeTab.click();
		
		// Instantiate new java Robot class
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
		
		// Delay is required between shortcut commands
		robot.delay(3000);
		// Press Keys G+A FOR ACCOUNTS
		robot.keyPress(KeyEvent.VK_G);
		robot.keyPress(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_G);
		robot.keyRelease(KeyEvent.VK_A);

		// Press Keys G+E FOR EVENTS
		robot.delay(3000);
		robot.keyPress(KeyEvent.VK_G);
		robot.keyPress(KeyEvent.VK_E);
		robot.keyRelease(KeyEvent.VK_G);
		robot.keyRelease(KeyEvent.VK_E);
		
		// Press Keys G+M FOR MEMBERSHIPS
		robot.delay(3000);
		robot.keyPress(KeyEvent.VK_G);
		robot.keyPress(KeyEvent.VK_M);
		robot.keyRelease(KeyEvent.VK_G);
		robot.keyRelease(KeyEvent.VK_M);
		
		// Press Keys N+A FOR NEW ACCOUNT
		robot.delay(3000);
		robot.keyPress(KeyEvent.VK_N);
		robot.keyPress(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_N);
		robot.keyRelease(KeyEvent.VK_A);
		
		// Press Keys N+O FOR NEW ORDER
		robot.delay(3000);
		robot.keyPress(KeyEvent.VK_N);
		robot.keyPress(KeyEvent.VK_O);
		robot.keyRelease(KeyEvent.VK_N);
		robot.keyRelease(KeyEvent.VK_O);
		robot.delay(3000);
		
		return this;
	}
	
}
