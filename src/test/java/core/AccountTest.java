package core;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import base.TestBase;
import page.AccountPage;
import page.BusinessAccountDetail;
import page.LoginPage;
import page.PersonAccountDetail;

public class AccountTest extends TestBase{
	
	protected AccountPage account = null;
	protected Boolean ranOrgAccount = false;
	protected Boolean ranIndAccount = false;
	
	@Before
	public void setUp() {
		LoginPage login = new LoginPage();
		login.doLogin();
		account = new AccountPage(login,true);
	}
	
	@Test
	public void NewOrganizationTest() {
		ranOrgAccount = true;
		BusinessAccountDetail detailPage = account.doAccountNewOrg(accountName);
		assertNotNull("Expected detail page",detailPage);
	}
	
	@Test
	public void NewIndividualTest() {
		ranIndAccount = true;		
		PersonAccountDetail detailPage = account.doAccountNewInd(accountFirstName,accountLastName);
		assertNotNull("Expected detail page",detailPage);		
	}
	
	@After
	public void tearDown() {
		if (ranOrgAccount) {
			ranOrgAccount = false;
			account.navigateTo(account.getUrl());
			account.doAccountDelete(accountName);	
		}
		if (ranIndAccount) {
			ranIndAccount = false;
			account.navigateTo(account.getUrl());
			account.doAccountDelete(accountFirstName, accountLastName);	
		}
		account.close();	
		account = null;
	}
}
