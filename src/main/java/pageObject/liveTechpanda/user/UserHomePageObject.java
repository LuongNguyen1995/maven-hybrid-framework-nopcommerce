package pageObject.liveTechpanda.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.liveTechpanda.user.HomePageUI;

public class UserHomePageObject extends BasePage {

	WebDriver driver;
	public UserHomePageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public UserLoginPageObject clickToLogin() {
		waitForElementClickable(driver, HomePageUI.MY_ACCOUNT_LINK);
		clickToElement(driver, HomePageUI.MY_ACCOUNT_LINK);
		return PageGeneratorManager.getUserLoginPage(driver);
	}

	
}
