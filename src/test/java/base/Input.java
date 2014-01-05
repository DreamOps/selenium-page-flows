package base;

//File that stores all the data for tests

public interface Input {
		
	// PageBase 
	String baseUrl = "https://cs11.salesforce.com";
	String baseTitle = "salesforce.com - ";
	
	// Login 	
	String loginUrl = "https://test.salesforce.com";
	String loginTitle = "salesforce.com - Customer Secure Login Page";
	String username = "standard@nimbleams.com.selenium";
	String usernameAdmin = "admin@nimbleams.com.selenium";
	String password = "df2013";
	
	// Home
	String homeUrl = baseUrl + "/home/home.jsp";
	Object homeTitle = "salesforce.com - Enterprise Edition";
	
	// Accounts 
	String accountsUrl = baseUrl + "/001/o";
	String orgAccountType = "Organization";
	String indAccountType = "Individual";	
	String accountStatus = "Active";
	String accountFirstName = "Otto";
	String accountLastName = "Test";
	String accountName = "Auto Test Account";
	String accountTodaysAccounts = "Today's Accounts";
	String allAccounts = "All Accounts";

	// AllTabs
	String allTabsUrl = baseUrl + "/home/showAllTabs.jsp";

}
