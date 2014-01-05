package core;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import base.PageBase;
import base.TestBase;
import page.LoginPage;

public class LoginTest extends TestBase {

	protected LoginPage login;
	
	@Before
	public void setUp() {
		login = new LoginPage();
	}
	
	@After
	public void tearDown() {
		login.close();
		login = null;
	}
	
	@Test
	public void testLoginPass() {
		PageBase myResult = login.doLogin(username, password);
		String myTitle = myResult.getTitle();
		Boolean pass = (homeTitle.equals(myTitle));
		assertTrue("Expected Home Page after successful login but found " + myTitle, pass);
	}
	
	@Test
	public void testLoginDefault() {
		PageBase myResult = login.doLogin();
		String myTitle = myResult.getTitle();
		Boolean pass = (homeTitle.equals(myTitle));
		assertTrue("Expected Home Page after successful login but found " + myTitle, pass);
	}
	
}
