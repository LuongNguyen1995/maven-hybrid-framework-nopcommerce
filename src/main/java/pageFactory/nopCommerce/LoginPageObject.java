package pageFactory.nopCommerce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BasePageFactory;

public class LoginPageObject extends BasePageFactory {
	private WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Page UI
	@FindBy(xpath = "//input[@id='Email']")
	private WebElement emailTextbox;
	
	@FindBy(xpath ="//input[@id='Password']")
	private WebElement passwordTextbox;
	
	@CacheLookup
	@FindBy(xpath ="//button[contains(@class,'login-button')]")
	private WebElement loginButton;
	
	@FindBy(xpath ="//span[@id='Email-error']")
	private WebElement emailErrorMessage;
	
	@FindBy(xpath ="//div[contains(@class,'validation-summary-errors')]")
	private WebElement unsuccessfulErrorMassage;
	
	//Page Object
	public void clickToLoginButton() {
		waitForElementClickable(driver, loginButton);
		clickToElement(loginButton);
	}

	public String getErrorMessageAtEmailTextbox() {
		waitForElementVisible(driver, emailErrorMessage);
		return getElementText(emailErrorMessage);
	}

	public void inputToEmailTextbox(String validEmail) {
		waitForElementVisible(driver, emailTextbox);
		sendkeyToElement(emailTextbox, validEmail);
		
	}

	public String getErrorMessageUnsuccessful() {
		waitForElementVisible(driver, unsuccessfulErrorMassage);
		return getElementText(unsuccessfulErrorMassage);
		
	}

	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, passwordTextbox);
		sendkeyToElement(passwordTextbox, password);
		
	}
	
}
