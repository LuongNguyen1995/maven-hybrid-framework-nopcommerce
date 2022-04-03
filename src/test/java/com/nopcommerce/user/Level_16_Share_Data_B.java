package com.nopcommerce.user;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.common.Common_01_Register_End_User;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserAddressPageObject;
import pageObjects.nopCommerce.user.UserCustomerInforPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import pageObjects.nopCommerce.user.UserRewardPointPageObject;

public class Level_16_Share_Data_B extends BaseTest{
	private WebDriver driver;
	private String firstName, lastName, emailAddress, password;	
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);

		homePage = PageGeneratorManager.getUserHomePage(driver);
		
		firstName = "Automation";
		lastName = "FC";
		emailAddress = "afc" + generateFakeNumber() + "@mail.vn";
		password = "123456";
		
		log.info("Pre-Condition - Step 01: Navigate to 'Register' Page");
		registerPage = homePage.openRegisterPage(); 
		
		log.info("Pre-Condition - Step 02: Enter to Firstname Textbox with value is '"+firstName+"'");
		registerPage.inputToFirstnameTextbox(firstName);
		
		log.info("Pre-Conditionegister - Step 03: Enter to Lastname Textbox with value is '"+lastName+"'");
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
		verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed...");
		
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
	public void Search_01_Empty_Data() { 
		
	}
	
	@Test 
	public void Search_02_Relative_Product_Name() { 
		
	}
	
	@Test 
	public void Search_03_Absolute_Product_Name() { 
		
	}
	
	@Test 
	public void Search_04_Parent_Category() { 
		
	}
	
	@Test 
	public void Search_05_Incorrect_Manufactorer() { 
		
	}
	
	@Test 
	public void Search_06_Correct_Manufactorer() { 

	}
	
	
	@AfterClass 
	public void afterClass() {
		driver.quit();
	}
}
