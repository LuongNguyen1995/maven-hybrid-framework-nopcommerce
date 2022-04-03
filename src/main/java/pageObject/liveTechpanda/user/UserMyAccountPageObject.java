package pageObject.liveTechpanda.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import commons.BasePage;
import pageUIs.liveTechpanda.user.HomePageUI;
import pageUIs.liveTechpanda.user.MyAccountPageUI;

public class UserMyAccountPageObject extends BasePage{

	WebDriver driver;
	public UserMyAccountPageObject(WebDriver driver) {
		this.driver = driver;
	}
	public boolean isVerifyRegisterSuccessfully(String firstName, String lastName, String emailAddress) {
		Assert.assertEquals(getElementText(driver, MyAccountPageUI.TITLE_MY_DASHBOARD), "MY DASHBOARD");
		Assert.assertEquals(getElementText(driver, MyAccountPageUI.SUCCESS_MESSAGE), "Thank you for registering with Main Website Store.");
		Assert.assertEquals(getElementText(driver, MyAccountPageUI.HELLO_USER_DISPLAY), "Hello, "+firstName+" "+lastName+"!");
		
		String contactInformation = getElementText(driver, MyAccountPageUI.CONTACT_INFORMATION_DISPLAY);
		//Verify name 
		Assert.assertTrue(contactInformation.contains(firstName+" "+lastName));
		Assert.assertTrue(contactInformation.contains(emailAddress));
		return true;
	}
	public UserHomePageObject clickToLogOut() {
		waitForElementClickable(driver, MyAccountPageUI.ACCOUNT_LINK);
		clickToElement(driver, MyAccountPageUI.ACCOUNT_LINK);
		waitForElementClickable(driver, MyAccountPageUI.LOG_OUT_LINK);
		clickToElement(driver, MyAccountPageUI.LOG_OUT_LINK);
		waitForElementVisible(driver, HomePageUI.TITLE_THIS_IS_DEMO_SITE);
		return PageGeneratorManager.getUserHomePage(driver);
	}

}
