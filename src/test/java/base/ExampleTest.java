package base;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
// import org.openqa.selenium.support.ui.ExpectedCondition;
// import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ExampleTest {
  private String baseUrl;
  private WebDriver driver;
  private ScreenshotHelper screenshotHelper;
  
  @Before
  public void openBrowser() {
	// Usually, this would be set when running Maven, but Google is Google.
    System.setProperty("webdriver.base.url","http://google.com/");
    
	baseUrl = System.getProperty("webdriver.base.url");
    driver = new FirefoxDriver();
    driver.get(baseUrl);
    screenshotHelper = new ScreenshotHelper();
  }
  
  @After
  public void saveScreenshotAndCloseBrowser() throws IOException {
    screenshotHelper.saveScreenshot("screenshot.png");
    driver.quit();
  }
  
@Test
  public void pageTitleBeforeAndAfterl() throws IOException {
    assertEquals("The page title should equal Google at the start of the test.", "Google", driver.getTitle());
    WebElement searchField = driver.findElement(By.name("q"));
    searchField.sendKeys("Nimble AMS");
    searchField.submit();
    assertEquals("The page title should equal Google at the end of the test.", "Google", driver.getTitle());    
    /*
    assertTrue("The page title should start with the search string after the search.",
      (new WebDriverWait(driver, 10)).until(new ExpectedCondition() {
        public Boolean apply(WebDriver d) {
          return d.getTitle().toLowerCase().startsWith("drupal!");
        }
      })
    );
    */
  }
  
  private class ScreenshotHelper {
  
    public void saveScreenshot(String screenshotFileName) throws IOException {
      File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
      FileUtils.copyFile(screenshot, new File(screenshotFileName));
    }
  }
}
