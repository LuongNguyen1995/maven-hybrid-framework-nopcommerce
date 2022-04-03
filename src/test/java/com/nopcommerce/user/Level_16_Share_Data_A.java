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

public class Level_16_Share_Data_A extends BaseTest{
	private WebDriver driver;
	private String emailAddress, password;
	
	private UserHomePageObject homePage;
	private UserLoginPageObject loginPage;
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);

		homePage = PageGeneratorManager.getUserHomePage(driver);
		
		emailAddress = Common_01_Register_End_User.emailAddress;
		password = Common_01_Register_End_User.password;
		
		log.info("Login - Step 01: Navigate to Login Page");
		loginPage = homePage.openLoginPage();
		
		log.info("Login - Step 02: Enter to Email Address Textbox with value is '"+emailAddress+"'");
		loginPage.inputToEmailTextbox(emailAddress);
		
		log.info("Login - Step 03: Enter to Password Textbox with value is '"+password+"'");
		loginPage.inputToPasswordTextbox(password);
		
		log.info("Login - Step 04: Click to Login Button");
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
