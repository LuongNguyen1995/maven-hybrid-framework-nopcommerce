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

public class Level_18_Pattern_Object extends BaseTest{
	private WebDriver driver;
	private String firstName, lastName, emailAddress, password, date, month, year;
	
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserCustomerInforPageObject customerInforPage;
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		System.out.println("Run on "+browserName);
		driver = getBrowserDriver(browserName);
		
		firstName = "Automation";
		lastName = "FC";
		emailAddress = "afc" + generateFakeNumber() + "@mail.vn";
		password = "123456";
		date ="1";
		month = "March";
		year = "1995";
		homePage = PageGeneratorManager.getUserHomePage(driver);

	}
	
//	@Test 
//	public void User_01_Register() { 
//		log.info("Register - Step 01: Navigate to 'Register' Page");
//		homePage.openHeaderPageByName(driver, "Register");
//		registerPage = PageGeneratorManager.getUserRegisterPage(driver);
//		
//		log.info("Register - Step 02: Click to 'Male' radio button");
//		registerPage.clickToRadioButtonByText(driver, "Male");
//		
//		
//		log.info("Register - Step 03: Enter to Firstname Textbox with value is '"+firstName+"'");
//		registerPage.enterToTextboxByID(driver, "FirstName",firstName);
//		
//		log.info("Register - Step 04: Enter to Lastname Textbox with value is '"+lastName+"'");
//		registerPage.enterToTextboxByID(driver, "LastName",lastName);
//		
//		//tham số 1 : detect đây là textbox nào dựa vào ID truyền vào
//		//tham số 2 : value để input vào textbox
//		//registerPage.enterToTextboxByID("Email",emailAddress);
//		
//		log.info("Register - Step 05: Enter to Email Address Textbox with value is '"+emailAddress+"'");
//		registerPage.enterToTextboxByID(driver, "Email", emailAddress);
//		
//		log.info("Register - Step 06: Enter to Password Textbox with value is '"+password+"'");
//		registerPage.enterToTextboxByID(driver, "Password",password);
//		
//		log.info("Register - Step 07: Enter to Confirm Password Textbox with value is '"+password+"'");
//		registerPage.enterToTextboxByID(driver, "ConfirmPassword",password);
//		
//		log.info("Register - Step 08: Select item in 'Date of Day' dropdown");
//		registerPage.selectDropdownByName(driver, "DateOfBirthDay", date);
//		
//		log.info("Register - Step 09: Select item in 'Date of Month' dropdown");
//		registerPage.selectDropdownByName(driver, "DateOfBirthMonth", month);
//		
//		log.info("Register - Step 10: Select item in 'Date of Year' dropdown");
//		registerPage.selectDropdownByName(driver, "DateOfBirthYear", year);
//		
//		log.info("Register - Step 11: Click to Register Button");
//		registerPage.clickToButtonByText(driver, "Register");
//		
//		log.info("Register - Step 12: Verify Register success message is displayed");
//		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
//		
//		log.info("Register - Step 13: Click to Log out Link");
//		registerPage.openHeaderPageByName(driver, "Log out");
//		homePage = PageGeneratorManager.getUserHomePage(driver);
//		
//		
//	}
//	
//	@Test 
//	public void User_02_Login() { 
//		log.info("Login - Step 01: Navigate to Login Page");
//		homePage.openHeaderPageByName(driver, "Log in");
//		loginPage = PageGeneratorManager.getUserLoginPage(driver);
//		
//		log.info("Login - Step 02: Enter to Email Address Textbox with value is '"+emailAddress+"'");
//		loginPage.enterToTextboxByID(driver, "Email",emailAddress);
//		
//		log.info("Login - Step 03: Enter to Password Textbox with value is '"+password+"'");
//		loginPage.enterToTextboxByID(driver, "Password",password);
//		
//		log.info("Login - Step 04: Click to Login Button");
//		loginPage.clickToButtonByText(driver, "Log in");
//		homePage = PageGeneratorManager.getUserHomePage(driver);
//		
//		
//		log.info("Login - Step 05: Verify 'My Account' link is displayed");
//		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
//		
//		log.info("Login - Step 06: Navigate to 'My Account' page");
//		customerInforPage = homePage.openMyAccountPage();
//		  
//		log.info("Login - Step 07: Verify 'Customer Infor' page");
//		Assert.assertTrue(customerInforPage.isCustomerInforPageDisplayed());
//	}
//	
	
	@AfterClass (alwaysRun= true)
	public void afterClass() {
		closeBrowserAndDriver();
	}
}
