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

public class Level_07_Switch_Page extends BaseTest{
	private WebDriver driver;
	private String firstName, lastName, emailAddress, password;
	
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserCustomerInforPageObject customerInforPage;
	private UserAddressPageObject addressPage;
	private UserMyProductReviewPageObject myProductReviewPage;
	private UserRewardPointPageObject rewardPointPage;
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
	
	@Test 
	public void User_01_Register() { 
		registerPage = homePage.openRegisterPage(); 
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		
		homePage = registerPage.clickToLogoutLink();
		
	}
	
	@Test
	public void User_02_Login() {
		loginPage = homePage.openLoginPage();
		loginPage.inputToEmailTextbox(emailAddress);
		loginPage.inputToPasswordTextbox(password);
		loginPage.clickToLoginButton();
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
		
	}
	
	@Test
	public void User_03_Customer_Infor() {
		customerInforPage = homePage.openMyAccountPage();
		Assert.assertTrue(customerInforPage.isCustomerInforPageDisplayed());
		
	}
	
	@Test
	public void User_04_Switch_Page() {
		
		//Knowledge của Page Object
		//Một Page A khi chuyển qua Page B thfi phải viết 1 hàm (action : open/click...) để mở Page B lên
		
		//Customer Infor -> Address
		addressPage = customerInforPage.openAddressPage(driver);
		//Address -> My product review
		myProductReviewPage = addressPage.openMyProductReviewPage(driver);
		//My product review -> Reward Point
		rewardPointPage = myProductReviewPage.openRewardPointPage(driver);
		//Reward Point -> Address
		addressPage = rewardPointPage.openAddressPage(driver);
		//Address -> Reward Point
		rewardPointPage = addressPage.openRewardPointPage(driver);
		//Reward point -> My product review
		myProductReviewPage = rewardPointPage.openMyProductReviewPage(driver);
		// My product review -> Address
		addressPage = myProductReviewPage.openAddressPage(driver);
		
		customerInforPage = addressPage.openCustomerInforPage(driver);
	}
	
	@Test
	public void User_05_Switch_Role() {
		//Role User -> Role admin
		
		//Role Admin -> Role User
	}
	
	
	@AfterClass 
	public void afterClass() {
		driver.quit();
	}
}
