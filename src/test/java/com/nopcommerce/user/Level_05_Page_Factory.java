package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import pageFactory.nopCommerce.HomePageObject;
import pageFactory.nopCommerce.LoginPageObject;
import pageFactory.nopCommerce.RegisterPageObject;

public class Level_05_Page_Factory extends BaseTest {
	private WebDriver driver;
	private String firstName, lastName, existingEmail,invalidEmail,notFoundEmail, validPassword,invalidPassword;

	
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = new HomePageObject(driver);
		firstName = "Automation";
		lastName = "FC";
		existingEmail = "afc" + generateFakeNumber() + "@mail.vn";
		invalidEmail = "afc@fsdf@vsd";
		notFoundEmail = "afc" + generateFakeNumber() + "@mail.com";
		validPassword = "123456";
		invalidPassword = "654321";
		
		System.out.println("Pre-condition - Step 01 : Click to Register link");
		homePage.clickToRegisterLink();
		registerPage = new RegisterPageObject(driver);
		
		System.out.println("Pre-condition - Step 02 : Input to require fields");
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(existingEmail);
		registerPage.inputToPasswordTextbox(validPassword);
		registerPage.inputToConfirmPasswordTextbox(validPassword);
		
		System.out.println("Pre-condition - Step 03 : Click to Register link");
		registerPage.clickToRegisterButton();
		
		System.out.println("Pre-condition - Step 04 : Verify success message displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		
		System.out.println("Pre-condition - Step 05 : Click to Logout link");
		registerPage.clickToLogoutLink();
		
		homePage = new HomePageObject(driver);
	}
	
	@Test 
	public void Login_01_Empty_Data() { 
		System.out.println("Login_01 - Step 01 : Click to Login link");
		homePage.clickToLoginLink();
		
		loginPage = new LoginPageObject(driver);
		System.out.println("Login_01 - Step 02 : Click to Login Button");
		loginPage.clickToLoginButton();
		
		System.out.println("Login_01 - Step 03 : Verify error message displayed");
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Please enter your email");
		
	}
	
	@Test
	public void Login_02_Invalid_Email() {
		System.out.println("Login_02 - Step 01 : Click to Login link");
		homePage.clickToLoginLink();
		loginPage = new LoginPageObject(driver);
		loginPage.inputToEmailTextbox(invalidEmail);
		
		System.out.println("Login_02 - Step 02 : Click to Login Button");
		loginPage.clickToLoginButton();
		
		System.out.println("Login_02 - Step 03 : Verify error message displayed");
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Wrong email");
	}
	
	@Test
	public void Login_03_Email_Not_Found() {
		System.out.println("Login_03 - Step 01 : Click to Login link");
		homePage.clickToLoginLink();
		loginPage = new LoginPageObject(driver);
		loginPage.inputToEmailTextbox(notFoundEmail);
		
		System.out.println("Login_03 - Step 02 : Click to Login Button");
		loginPage.clickToLoginButton();
		
		System.out.println("Login_03 - Step 03 : Verify error message displayed");
		Assert.assertEquals(loginPage.getErrorMessageUnsuccessful(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
		
	}
	
	@Test
	public void Login_04_Existing_Email_Empty_Password() {
		System.out.println("Login_04 - Step 01 : Click to Login link");
		homePage.clickToLoginLink();
		loginPage = new LoginPageObject(driver);
		loginPage.inputToEmailTextbox(existingEmail);
		
		System.out.println("Login_04 - Step 02 : Click to Login Button");
		loginPage.clickToLoginButton();
		
		System.out.println("Login_04 - Step 03 : Verify error message displayed");
		Assert.assertEquals(loginPage.getErrorMessageUnsuccessful(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
		
	}
	
	@Test
	public void Login_05_Existing_Email_Incorrect_Password() {
		System.out.println("Login_05 - Step 01 : Click to Login link");
		homePage.clickToLoginLink();
		loginPage = new LoginPageObject(driver);
		System.out.println("Login_05 - Step 02 : Input to require fields");
		loginPage.inputToEmailTextbox(existingEmail);
		loginPage.inputToPasswordTextbox(invalidPassword);
		
		System.out.println("Login_05 - Step 03 : Click to Login Button");
		loginPage.clickToLoginButton();
		
		System.out.println("Login_05 - Step 04 : Verify error message displayed");
		Assert.assertEquals(loginPage.getErrorMessageUnsuccessful(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	
	}
	
	@Test
	public void Login_06_Valid_Email_Password() {
		System.out.println("Login_06 - Step 01 : Click to Login link");
		homePage.clickToLoginLink();
		loginPage = new LoginPageObject(driver);
		System.out.println("Login_06 - Step 02 : Input to require fields");
		loginPage.inputToEmailTextbox(existingEmail);
		loginPage.inputToPasswordTextbox(validPassword);
		
		System.out.println("Login_06 - Step 03 : Click to Login Button");
		loginPage.clickToLoginButton();
		
		homePage = new HomePageObject(driver);
		System.out.println("Login_06 - Step 04 : Verify error message displayed");
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
		
	}
	

	@AfterClass 
	public void afterClass() {
		driver.quit();
	}
	
	public int generateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}

}
