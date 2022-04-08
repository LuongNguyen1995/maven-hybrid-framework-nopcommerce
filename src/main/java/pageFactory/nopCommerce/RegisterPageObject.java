package pageFactory.nopCommerce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BasePageFactory;
import pageUIs.nopCommerce.user.RegisterPageUI;

public class RegisterPageObject extends BasePageFactory{
	private WebDriver driver;

	public RegisterPageObject(WebDriver driver)  {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
		
		//Page UI
		@FindBy(xpath = "//input[@id='FirstName']")
		private WebElement firstNameTextbox;
		
		@FindBy(xpath = "//input[@id='LastName']")
		private WebElement lastNameTextbox;
		
		@FindBy(xpath = "//input[@id='Email']")
		private WebElement emailTextbox;
		
		@FindBy(xpath = "//input[@id='Password']")
		private WebElement passwordTextbox;
		
		@FindBy(xpath = "//input[@id='ConfirmPassword']")
		private WebElement confirmPasswordTextbox;
		
		@FindBy(xpath = "//button[@id='register-button']")
		private WebElement registerButton;
		
		@FindBy(xpath = "//span[@id='FirstName-error']")
		private WebElement firstNameErrorMassage;
		
		@FindBy(xpath = "//span[@id='LastName-error']")
		private WebElement lastNameErrorMassage;
		
		@FindBy(xpath = "//span[@id='Email-error']")
		private WebElement emailErrorMassage;
		
		@FindBy(xpath = "//span[@id='Password-error']")
		private WebElement passwordErrorMassge;
		
		@FindBy(xpath = "//span[@id='ConfirmPassword-error']")
		private WebElement confirmPasswordErrorMassage;
		
		@FindBy(xpath = "//div[contains(@class,'message-error')]//li")
		private WebElement mainRegisterErrorMassage;
		
		@FindBy(xpath = "//div[@class='result']")
		private WebElement successMassage;

		@FindBy(xpath = "//a[@class='ico-logout']")
		private WebElement logoutLink;
	
		//Page Object
		public void clickToRegisterButton() {
			waitForElementClickable(driver, registerButton);
			clickToElement(registerButton);
		}

		public String getErrorMessageAtFirstnameTextbox() {
			waitForElementVisible(driver, firstNameErrorMassage);
			return getElementText(firstNameErrorMassage);
			
		}

		public String getErrorMessageAtLastnameTextbox() {
			waitForElementVisible(driver, lastNameErrorMassage);
			return getElementText(lastNameErrorMassage);
		}

		public String getErrorMessageAtEmailTextbox() {
			waitForElementVisible(driver, emailErrorMassage);
			return getElementText(emailErrorMassage);
		}

		public String getErrorMessageAtPasswordTextbox() {
			waitForElementVisible(driver, passwordErrorMassge);
			return getElementText(passwordErrorMassge);
		}

		public String getErrorMessageAtConfirmPasswordTextbox() {
			waitForElementVisible(driver, confirmPasswordErrorMassage);
			return getElementText(confirmPasswordErrorMassage);
		}

		public void inputToFirstnameTextbox(String firstName) {
			waitForElementVisible(driver, firstNameTextbox);
			sendkeyToElement(firstNameTextbox, firstName);
			
		}

		public void inputToLastnameTextbox(String lastName) {
			waitForElementVisible(driver, lastNameTextbox);
			sendkeyToElement(lastNameTextbox, lastName);
			
		}

		public void inputToEmailTextbox(String emailAddress) {
			waitForElementVisible(driver, emailTextbox);
			sendkeyToElement(emailTextbox, emailAddress);
			
		}

		public void inputToPasswordTextbox(String password) {
			waitForElementVisible(driver, passwordTextbox);
			sendkeyToElement(passwordTextbox, password);
			
		}

		public void inputToConfirmPasswordTextbox(String confirmPassword) {
			waitForElementVisible(driver, confirmPasswordTextbox);
			sendkeyToElement(confirmPasswordTextbox, confirmPassword);
			
		}

		public String getErrorMessageAtMainRegisterTexbox() {
			waitForElementVisible(driver, mainRegisterErrorMassage);
			return getElementText(mainRegisterErrorMassage);
		}

		public String getRegisterSuccessMessage() {
			waitForElementVisible(driver, successMassage);
			return getElementText(successMassage);
		}

		public void clickToLogoutLink() {
			waitForElementClickable(driver, logoutLink);
			clickToElement(logoutLink);
		}
}
