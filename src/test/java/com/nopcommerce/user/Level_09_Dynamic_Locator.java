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

public class Level_09_Dynamic_Locator extends BaseTest{
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
	public void User_01_Register_Login() { 
		registerPage = homePage.openRegisterPage(); 
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		
		homePage = registerPage.clickToLogoutLink();
		
		loginPage = homePage.openLoginPage();
		loginPage.inputToEmailTextbox(emailAddress);
		loginPage.inputToPasswordTextbox(password);
		loginPage.clickToLoginButton();
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
		
		customerInforPage = homePage.openMyAccountPage();
		Assert.assertTrue(customerInforPage.isCustomerInforPageDisplayed());
		
	}
	
	@Test
	public void User_02_Switch_Page() {
		
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
	}
	
	@Test
	public void User_03_Dynamic_Page_01() {
		//My product review -> Reward Point
		rewardPointPage = (UserRewardPointPageObject) myProductReviewPage.openPageAtMyAccountByName(driver, "Reward points");
		//Reward Point -> Address
		addressPage = (UserAddressPageObject) rewardPointPage.openPageAtMyAccountByName(driver, "Addresses");
		//Address -> Reward Point
		rewardPointPage = (UserRewardPointPageObject) addressPage.openPageAtMyAccountByName(driver, "Reward points");
		//Reward point -> My product review
		myProductReviewPage = (UserMyProductReviewPageObject) rewardPointPage.openPageAtMyAccountByName(driver, "My product reviews");
		// My product review -> Customer Infor
		customerInforPage =(UserCustomerInforPageObject) myProductReviewPage.openPageAtMyAccountByName(driver, "Customer info");
		
	}
	
	@Test
	public void User_03_Dynamic_Page_02() {
		//Customer Infor -> My product review
		customerInforPage.openPageAtMyAccountByPageName(driver, "My product reviews");
		myProductReviewPage = PageGeneratorManager.getUserMyProductReviewPage(driver);
		//My product review -> Reward Point
		myProductReviewPage.openPageAtMyAccountByPageName(driver, "Reward points");
		rewardPointPage = PageGeneratorManager.getUserRewardPointPage(driver);
		//Reward Point -> Address
		rewardPointPage.openPageAtMyAccountByPageName(driver, "Addresses");
		addressPage = PageGeneratorManager.getUserAddressPage(driver);
		//Address -> Reward Point
		addressPage.openPageAtMyAccountByPageName(driver, "Reward points");
		rewardPointPage = PageGeneratorManager.getUserRewardPointPage(driver);
		//Reward point -> My product review
		rewardPointPage.openPageAtMyAccountByPageName(driver, "My product reviews");
		myProductReviewPage = PageGeneratorManager.getUserMyProductReviewPage(driver);
		// My product review -> Customer Infor
		myProductReviewPage.openPageAtMyAccountByPageName(driver, "Customer info");
		customerInforPage =PageGeneratorManager.getUserCustomerInforPage(driver);
		
	}
	
	
	@AfterClass 
	public void afterClass() {
		driver.quit();
	}
}
