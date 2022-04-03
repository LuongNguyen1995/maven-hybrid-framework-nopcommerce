package com.nopcommerce.user;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.admin.AdminDashboardPageObject;
import pageObjects.nopCommerce.admin.AdminLoginPageObject;
import pageObjects.nopCommerce.user.UserCustomerInforPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class Level_08_Switch_Role extends BaseTest{
	private WebDriver driver;
	private String firstName, lastName, userEmailAddress, userPassword, adminEmailAddress, adminPassword;
	
	private UserHomePageObject userHomePage;
	private UserRegisterPageObject userRegisterPage;
	private UserLoginPageObject userLoginPage;
	private UserCustomerInforPageObject userCustomerInforPage;
	private AdminLoginPageObject adminLoginPage;
	private AdminDashboardPageObject adminDashboardPage;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		System.out.println("Run on "+browserName);
		driver = getBrowserDriver(browserName);
		
		firstName = "Automation";
		lastName = "FC";
		userEmailAddress = "afc" + generateFakeNumber() + "@mail.vn";
		userPassword = "123456";
		adminEmailAddress = "admin@yourstore.com";
		adminPassword = "admin";
		
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
		//Register first
		userRegisterPage = userHomePage.openRegisterPage(); 
		userRegisterPage.inputToFirstnameTextbox(firstName);
		userRegisterPage.inputToLastnameTextbox(lastName);
		userRegisterPage.inputToEmailTextbox(userEmailAddress);
		userRegisterPage.inputToPasswordTextbox(userPassword);
		userRegisterPage.inputToConfirmPasswordTextbox(userPassword);
		userRegisterPage.clickToRegisterButton();
		Assert.assertEquals(userRegisterPage.getRegisterSuccessMessage(), "Your registration completed");
		
		userHomePage = userRegisterPage.clickToLogoutLink();
	}
	
	
	@Test
	public void Role_01_User_To_Login() {
		userLoginPage = userHomePage.openLoginPage();
		
		//login as User Role
		userHomePage = userLoginPage.loginAsUser(userEmailAddress,userPassword);
		Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());
		
		//HomePage -> CustomerInfor
		userCustomerInforPage = userHomePage.openMyAccountPage();
		
		//Customer Infor click Logout -> Click Logout -> HomePage
		userHomePage = userCustomerInforPage.clickToLogoutLinkAtUserPage(driver);
		
		//User HomePage -> Open Admin Page -> LoginPage (Admin)
		userHomePage.openPageUrl(driver, GlobalConstants.ADMIN_PAGE_URL);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);
		//Login as Admin Role
		adminDashboardPage = adminLoginPage.loginAsAdmin(adminEmailAddress,adminPassword);
		Assert.assertTrue(adminDashboardPage.isDashboardHeaderDisplayed());
		
		//Dashboard Page -> Click logout -> Login Page (Admin)
		adminLoginPage = adminDashboardPage.clickToLogoutLinkAtAdminPage(driver);
		
	}
	
	@Test
	public void Role_02_Admin_To_User() {
		//Login Page (admin) -> Open User Url -> Home Page (User) 
		adminLoginPage.openPageUrl(driver, GlobalConstants.PORTAL_PAGE_URL);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
		
		//HomePage -> Login User
		userLoginPage = userHomePage.openLoginPage();
		//Login as User Role
		userHomePage = userLoginPage.loginAsUser(userEmailAddress,userPassword);
		Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());
	}
	
	
	@AfterClass 
	public void afterClass() {
		driver.quit();
	}
}
