//package com.nopcommerce.user;
//
//
//import java.lang.reflect.Method;
//
//import org.openqa.selenium.WebDriver;
//import org.testng.Assert;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Parameters;
//import org.testng.annotations.Test;
//
//import com.aventstack.extentreports.Status;
//
//import commons.BaseTest;
//import commons.PageGeneratorManager;
//import pageObjects.nopCommerce.user.UserAddressPageObject;
//import pageObjects.nopCommerce.user.UserCustomerInforPageObject;
//import pageObjects.nopCommerce.user.UserHomePageObject;
//import pageObjects.nopCommerce.user.UserLoginPageObject;
//import pageObjects.nopCommerce.user.UserMyProductReviewPageObject;
//import pageObjects.nopCommerce.user.UserRegisterPageObject;
//import pageObjects.nopCommerce.user.UserRewardPointPageObject;
//import reportConfig.ExtentTestManager;
//
//public class Level_15_ExtentV5 extends BaseTest{
//	private WebDriver driver;
//	private String firstName, lastName, emailAddress, password;
//	
//	private UserHomePageObject homePage;
//	private UserRegisterPageObject registerPage;
//	private UserLoginPageObject loginPage;
//	private UserCustomerInforPageObject customerInforPage;
//	@Parameters("browser")
//	@BeforeClass
//	public void beforeClass(String browserName) {
//		System.out.println("Run on "+browserName);
//		driver = getBrowserDriver(browserName);
//		
//		firstName = "Automation";
//		lastName = "FC";
//		emailAddress = "afc" + generateFakeNumber() + "@mail.vn";
//		password = "123456";
//
//		homePage = PageGeneratorManager.getUserHomePage(driver);
//
//	}
//	
//	@Test 
//	public void User_01_Register(Method method) { 
//		ExtentTestManager.startTest(method.getName(), "Register to system with valid Email and Password");
//		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 01: Navigate to 'Register' page");
//		registerPage = homePage.openRegisterPage(); 
//		
//		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 02: Enter to Firstname textbox with value is '"+firstName+"'");
//		registerPage.inputToFirstnameTextbox(firstName);
//		
//		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 03: Enter to Lastname textbox with value is '"+lastName+"'");
//		registerPage.inputToLastnameTextbox(lastName);
//		
//		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 04: Enter to Email Address textbox with value is '"+emailAddress+"'");
//		registerPage.inputToEmailTextbox(emailAddress);
//		
//		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 05: Enter to Password textbox with value is '"+password+"'");
//		registerPage.inputToPasswordTextbox(password);
//		
//		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 06: Enter to Confirm Password textbox with value is '"+password+"'");
//		registerPage.inputToConfirmPasswordTextbox(password);
//		
//		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 07: Click to 'Register' button");
//		registerPage.clickToRegisterButton();
//		
//		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 08: Verify register success message is displayed");
//		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed...");
//	}
//	
//	@Test 
//	public void User_02_Login(Method method) { 
//		ExtentTestManager.startTest(method.getName(), "Login to system successfully");
//		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 01: Navigate to Login page");
//		homePage = registerPage.clickToLogoutLink();
//		loginPage = homePage.openLoginPage();
//		
//		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 02: Enter to Email textbox with value is '"+emailAddress+"'");
//		loginPage.inputToEmailTextbox(emailAddress);
//		
//		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 03: Enter to Password textbox with value is '"+password+"'");
//		loginPage.inputToPasswordTextbox(password);
//		
//		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 04: Click to Login Button");
//		loginPage.clickToLoginButton();
//		
//		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 05: Verify 'My Account' link is displayed");
//		Assert.assertFalse(homePage.isMyAccountLinkDisplayed());
//		
//		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 06: Navigate to 'My Account' page");
//		customerInforPage = homePage.openMyAccountPage();
//		
//		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 07: Verify 'Customer Infor' page is displayed");
//		Assert.assertFalse(customerInforPage.isCustomerInforPageDisplayed());
//	}
//	
//	
//	@AfterClass 
//	public void afterClass() {
//		driver.quit();
//	}
//}
