package com.nopcommerce.common;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class Common_01_Register_End_User extends BaseTest{
	private WebDriver driver;
	public static String  emailAddress, password;
	private String firstName, lastName;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	
	@Parameters("browser")
	@BeforeTest(description = "Create new common User for all Classes Test")
	public void Register(String browserName) { 
		
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		
		firstName = "Automation";
		lastName = "FC";
		emailAddress = "afc" + generateFakeNumber() + "@mail.vn";
		password = "123456";
		
		
		log.info("Pre-Conditon - Step 01: Navigate to 'Register' Page");
		registerPage = homePage.openRegisterPage(); 
		
		log.info("Pre-Conditon - Step 02: Enter to Firstname Textbox with value is '"+firstName+"'");
		registerPage.inputToFirstnameTextbox(firstName);
		
		log.info("Pre-Conditon - Step 03: Enter to Lastname Textbox with value is '"+lastName+"'");
		registerPage.inputToLastnameTextbox(lastName);
		
		log.info("Pre-Conditon - Step 04: Enter to Email Address Textbox with value is '"+emailAddress+"'");
		registerPage.inputToEmailTextbox(emailAddress);
		
		log.info("Pre-Conditon - Step 05: Enter to Password Textbox with value is '"+password+"'");
		registerPage.inputToPasswordTextbox(password);
		
		log.info("Pre-Conditon - Step 06: Enter to Confirm Password Textbox with value is '"+password+"'");
		registerPage.inputToConfirmPasswordTextbox(password);
		
		log.info("Pre-Conditon - Step 07: Click to Register Button");
		registerPage.clickToRegisterButton();
		
		log.info("Pre-Conditon - Step 08: Verify Register success message is displayed");
		verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		
		log.info("Pre-Conditon - Step 09: Click to Log out link");
		homePage = registerPage.clickToLogoutLink();

		driver.quit();
	}
	
	
}
