package com.nopcommerce.user;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserAddressPageObject;
import pageObjects.nopCommerce.user.UserCustomerInforPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import pageObjects.nopCommerce.user.UserRewardPointPageObject;

public class Level_17_Custom_Close_Driver extends BaseTest{
	private WebDriver driver;
	private String firstName, lastName, emailAddress, password;
	
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserCustomerInforPageObject customerInforPage;
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		System.out.println("Run on "+browserName);
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		
		//Pre-Condition
		firstName = "Automation";
		lastName = "FC";
		emailAddress = "afc" + generateFakeNumber() + "@mail.vn";
		password = "123456";

		log.info("Pre-Condition - Step 01: Navigate to 'Register' Page");
		registerPage = homePage.openRegisterPage(); 
		
		log.info("Pre-Condition - Step 02: Enter to Firstname Textbox with value is '"+firstName+"'");
		registerPage.inputToFirstnameTextbox(firstName);
		
		log.info("Pre-Condition - Step 03: Enter to Lastname Textbox with value is '"+lastName+"'");
		registerPage.inputToLastnameTextbox(lastName);
		
		log.info("Pre-Condition - Step 04: Enter to Email Address Textbox with value is '"+emailAddress+"'");
		registerPage.inputToEmailTextbox(emailAddress);
		
		log.info("Pre-Condition - Step 05: Enter to Password Textbox with value is '"+password+"'");
		registerPage.inputToPasswordTextbox(password);
		
		log.info("Pre-Condition - Step 06: Enter to Confirm Password Textbox with value is '"+password+"'");
		registerPage.inputToConfirmPasswordTextbox(password);
		
		log.info("Pre-Condition - Step 07: Click to Register Button");
		registerPage.clickToRegisterButton();
		
		log.info("Pre-Condition - Step 08: Verify Register success message is displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed...");
		
		log.info("Pre-Condition - Step 09: Click to Log out link");
		homePage = registerPage.clickToLogoutLink();
		
		log.info("Pre-Condition - Step 10: Navigate to Login Page");
		loginPage = homePage.openLoginPage();
		
		log.info("Pre-Condition - Step 11: Enter to Email Address Textbox with value is '"+emailAddress+"'");
		loginPage.inputToEmailTextbox(emailAddress);
		
		log.info("Pre-Condition - Step 12: Enter to Password Textbox with value is '"+password+"'");
		loginPage.inputToPasswordTextbox(password);
		
		log.info("Pre-Condition - Step 13: Click to Login Button");
		loginPage.clickToLoginButton();

	}
	
	@Test 
	public void Search_01_Name() { 

		
	}
	
	@Test 
	public void Search_02_Address() { 
		
	}
	
	
	@AfterClass (alwaysRun= true)
	public void afterClass() {
		closeBrowserAndDriver();
	}
}
