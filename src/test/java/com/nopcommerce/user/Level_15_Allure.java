package com.nopcommerce.user;



import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pageObjects.nopCommerce.user.UserCustomerInforPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class Level_15_Allure extends BaseTest{
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
		
		firstName = "Automation";
		lastName = "FC";
		emailAddress = "afc" + generateFakeNumber() + "@mail.vn";
		password = "123456";

		homePage = PageGeneratorManager.getUserHomePage(driver);

	}
	
	@Description("Register to System")
	@Severity(SeverityLevel.NORMAL)
	@Test 
	public void User_01_Register() { 
		registerPage = homePage.openRegisterPage(); 
		
		registerPage.inputToFirstnameTextbox(firstName);
		
		registerPage.inputToLastnameTextbox(lastName);
		
		registerPage.inputToEmailTextbox(emailAddress);
		
		registerPage.inputToPasswordTextbox(password);
		
		registerPage.inputToConfirmPasswordTextbox(password);
		
		registerPage.clickToRegisterButton();
		
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed...");
	}
	
	@Description("Login to System")
	@Severity(SeverityLevel.NORMAL)
	@Test 
	public void User_02_Login() { 
		homePage = registerPage.clickToLogoutLink();
		loginPage = homePage.openLoginPage();
		
		loginPage.inputToEmailTextbox(emailAddress);
		
		loginPage.inputToPasswordTextbox(password);
		
		loginPage.clickToLoginButton();
		
		Assert.assertFalse(homePage.isMyAccountLinkDisplayed());
		
		customerInforPage = homePage.openMyAccountPage();
		
		Assert.assertFalse(customerInforPage.isCustomerInforPageDisplayed());
	}
	
	
	@AfterClass 
	public void afterClass() {
		driver.quit();
	}
}
