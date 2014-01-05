package page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import base.Input;
import base.PageBase;

public class AccountPage extends PageBase{
	
	// -- WebElements

     @FindBy(how = How.ID, using = "Account_Tab") 
     @CacheLookup
     private static WebElement AccountsTab;

     @FindBy(how = How.NAME, using = "new")
     @CacheLookup
     private static WebElement NewButton;
     
     @FindBy(how = How.ID, using = "p3") 
     @CacheLookup
     private static WebElement RecordType;

     @FindBy(how = How.ID, using = "name_firstacc2") 
     @CacheLookup
     private static WebElement FirstName;

     @FindBy(how = How.ID, using = "name_lastacc2") 
     @CacheLookup
     private static WebElement LastName;

     @FindBy(how = How.NAME, using = "save")
     @CacheLookup
     private static WebElement Save;

     @FindBy(how = How.ID, using = "acc2")
     @CacheLookup
     private static WebElement AccountName;

     @FindBy(how = How.XPATH, using = "*//label[text()='Status']")
     @CacheLookup
     private static WebElement Status;

     @FindBy(how = How.LINK_TEXT, using = base.Input.accountName)
     @CacheLookup
     private static WebElement AccountEdit;

     @FindBy(how = How.NAME, using = "edit")
     @CacheLookup
     private static WebElement Edit;

     @FindBy(how = How.NAME, using = "delete")
     @CacheLookup
     private static WebElement Delete;

     @FindBy(how = How.ID, using = "fcf")
     @CacheLookup
     private static WebElement View;
     
     @FindBy(how = How.ID, using = "phHeaderLogoImage")
     @CacheLookup
     private static WebElement HeaderLogoImage;
     
 	// -- Constructors
     
 	public AccountPage(PageBase parent, Boolean navigate) {
		super(parent, navigate);
	}
     
          
 	public AccountPage(PageBase parent) {
		super(parent);
	}

 	public AccountPage() {
		super();
	}
 	
 	// -- Overrides 
 	
 	@Override
	public String getUrl() {
		return accountsUrl;
	}
 	
 	// -- Methods

 	public BusinessAccountDetail doAccountNewOrg(String aAccountName) {
 		return (BusinessAccountDetail) doAccountNew(orgAccountType, aAccountName, "", "", accountStatus);
 	}
 	
 	public PersonAccountDetail doAccountNewInd(String aFirstName, String aLastName) {
 		return (PersonAccountDetail) doAccountNew(indAccountType, "", aFirstName, aLastName, accountStatus);

 	}
 	
 	public PageBase doAccountNew(String aAccountType, String aAccountName, 
 			String aFirstName, String aLastName, String aStatus) {
 		NewButton.click();
        
        // Select the Record Type of new record
        RecordType.sendKeys(aAccountType);
        RecordType.submit();

        Boolean isBusiness = aAccountType.equals(Input.orgAccountType);
        		
        if (isBusiness) {
            AccountName.sendKeys(aAccountName);
        }
        else {
            FirstName.sendKeys(aFirstName);
            LastName.sendKeys(aLastName);
        }
                
        Save.click();
        
        if (isBusiness) {
        		return new BusinessAccountDetail(this);
        } else {
        		return new PersonAccountDetail(this);
        }
 	}
 	
 	public PageBase doAccountDelete(String aFirstName, String aLastName) { 		
 		String accountName = aLastName + ", " + aFirstName;
 		doAccountDelete(accountName);
 		return this;
 	}
 	
    public AccountPage doAccountDelete(String aAccountName) {

    		// Load the account page (displays the 'All Accounts' screen)
        doAccountsView(accountTodaysAccounts);

        // Set individual account name equal to name fieldsaccountTodaysAccounts
        AccountEdit = driver.findElement(By.linkText(aAccountName));
        
        // Click individual account name link to edit
        AccountEdit.click();
        
        // Set mouse cursor focus away from the hover links so delete button isn't blocked
        HeaderLogoImage.click();
        
        // Click the delete account button
        Delete.click();
        
        // The dual try-catch blocks handled the SF UI pop-ups.
        // One DeleteBtn.click is often not enough, but two can be too much.
        try {
        		Delete.click();
        	}
        catch(Exception e) {
        		// Delete not in focus, try switch
        }
        
        // Click "Enter" to accept delete modal pop-up
        try {
        		driver.switchTo().alert().accept();
        }
        catch(Exception e) {
        	// At times the delete modal popUp mysteriously disappears, 
        	// so try it again if not found initially.
	        	Delete.click();
	        	driver.switchTo().alert().accept();
        }

        return this;
    }
     
	public AccountPage doAccountsView(String aViewName) {
		
        // Select "All Accounts" view on Accounts home page
        View.click();
        View.sendKeys(aViewName);
        
        // Submit drop down selection
        View.submit();

        return this;
    }     
}
