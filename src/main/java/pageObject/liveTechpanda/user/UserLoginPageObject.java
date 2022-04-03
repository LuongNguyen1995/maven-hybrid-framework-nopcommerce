package pageObject.liveTechpanda.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.liveTechpanda.user.LoginPageUI;

public class UserLoginPageObject extends BasePage{

	WebDriver driver;
	public UserLoginPageObject(WebDriver driver) {
		this.driver = driver;
	}
	public UserRegisterPageObject clickToCreateAccount() {
		waitForElementClickable(driver, LoginPageUI.CREAT_AN_ACCOUNT_BUTTON);
		clickToElement(driver, LoginPageUI.CREAT_AN_ACCOUNT_BUTTON);
		return PageGeneratorManager.getUserRegisterPage(driver);
	}
	public void loginAccountRegisted(String emailAddress, String password) {
		waitForElementVisible(driver, LoginPageUI.INPUT_TEXTBOX_BY_TITLE, "email");
		sendkeyToElement(driver, LoginPageUI.INPUT_TEXTBOX_BY_TITLE, emailAddress, "email");
		
		waitForElementVisible(driver, LoginPageUI.INPUT_TEXTBOX_BY_TITLE, "pass");
		sendkeyToElement(driver, LoginPageUI.INPUT_TEXTBOX_BY_TITLE, emailAddress, "pass");
		
		waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		
	}
	
	public boolean isVerifyAccountDeleted() {
		return isElementDisplayed(driver, LoginPageUI.MESSAGE_LOGIN_ERROR);
	}
	
	

}
