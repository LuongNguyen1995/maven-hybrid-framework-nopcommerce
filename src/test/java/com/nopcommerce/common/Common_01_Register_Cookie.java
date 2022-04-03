package com.nopcommerce.common;


import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class Common_01_Register_Cookie extends BaseTest{
	private WebDriver driver;
	private String firstName, lastName, emailAddress, password;
	
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	public static Set<Cookie> loggedCookies;
	
	@Parameters("browser")
	@BeforeTest(description = "Create new common User for all Classes Test")
	public void Register(String browserName) { 
		
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		
		firstName = "Automation";
		lastName = "FC";
		emailAddress = "afc" + generateFakeNumber() + "@mail.vn";
		password = "123456";
		
		
		log.info("Register - Step 01: Navigate to 'Register' Page");
		registerPage = homePage.openRegisterPage(); 
		
		log.info("Register - Step 02: Enter to Firstname Textbox with value is '"+firstName+"'");
		registerPage.inputToFirstnameTextbox(firstName);
		
		log.info("Register - Step 03: Enter to Lastname Textbox with value is '"+lastName+"'");
		registerPage.inputToLastnameTextbox(lastName);
		
		log.info("Register - Step 04: Enter to Email Address Textbox with value is '"+emailAddress+"'");
		registerPage.inputToEmailTextbox(emailAddress);
		
		log.info("Register - Step 05: Enter to Password Textbox with value is '"+password+"'");
		registerPage.inputToPasswordTextbox(password);
		
		log.info("Register - Step 06: Enter to Confirm Password Textbox with value is '"+password+"'");
		registerPage.inputToConfirmPasswordTextbox(password);
		
		log.info("Register - Step 07: Click to Register Button");
		registerPage.clickToRegisterButton();
		
		log.info("Register - Step 08: Verify Register success message is displayed");
		verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		
		log.info("Register - Step 09: Click to Log out link");
		homePage = registerPage.clickToLogoutLink();


		log.info("Pre-Condition - Step 10: Navigate to Login Page");
		loginPage = homePage.openLoginPage();
		
		log.info("Pre-Condition - Step 11: Enter to Email Address Textbox with value is '"+emailAddress+"'");
		loginPage.inputToEmailTextbox(emailAddress);
		
		log.info("Pre-Condition - Step 12: Enter to Password Textbox with value is '"+password+"'");
		loginPage.inputToPasswordTextbox(password);
		
		log.info("Pre-Condition - Step 13: Click to Login Button");
		loginPage.clickToLoginButton();
		
		loggedCookies = homePage.getAllCookies(driver);
		for (Cookie cookie : loggedCookies) {
			System.out.println("Cookie at Common Class : "+ cookie);
			
		}
	}
	
	@AfterTest
	public void afterClass() {
		driver.quit();
	}
	
}
