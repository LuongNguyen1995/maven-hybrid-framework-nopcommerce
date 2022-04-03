package pageObject.liveTechpanda.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.liveTechpanda.user.RegisterPageUI;

public class UserRegisterPageObject extends BasePage {

	WebDriver driver;
	public UserRegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void inputToFirstName(String valueFirstName) {
		waitForElementVisible(driver, RegisterPageUI.TEXTBOX_BY_TITLE, "firstname");
		sendkeyToElement(driver, RegisterPageUI.TEXTBOX_BY_TITLE, valueFirstName, "firstname");
	}
	
	public void inputToLastName(String valueLastName) {
		waitForElementVisible(driver, RegisterPageUI.TEXTBOX_BY_TITLE, "lastname");
		sendkeyToElement(driver, RegisterPageUI.TEXTBOX_BY_TITLE, valueLastName, "lastname");
	}
	
	public void inputToEmailAddress(String valueEmailAddress) {
		waitForElementVisible(driver, RegisterPageUI.TEXTBOX_BY_TITLE, "email_address");
		sendkeyToElement(driver, RegisterPageUI.TEXTBOX_BY_TITLE, valueEmailAddress, "email_address");
	}
	
	public void inputToPassword(String valuePassword) {
		waitForElementVisible(driver, RegisterPageUI.TEXTBOX_BY_TITLE, "password");
		sendkeyToElement(driver, RegisterPageUI.TEXTBOX_BY_TITLE, valuePassword, "password");
	}

	
	public void inputToConfirmPassword(String valuePassword) {
		waitForElementVisible(driver, RegisterPageUI.TEXTBOX_BY_TITLE, "confirmation");
		sendkeyToElement(driver, RegisterPageUI.TEXTBOX_BY_TITLE, valuePassword, "confirmation");
	}

	public UserMyAccountPageObject clickToRegisterAccount() {
		waitForElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);
		clickToElement(driver, RegisterPageUI.REGISTER_BUTTON);
		return PageGeneratorManager.getUserMyAccountPage(driver);
	}
	

}
