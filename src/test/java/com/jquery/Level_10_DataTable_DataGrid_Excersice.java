package com.jquery;


import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import pageObject.liveTechpanda.admin.AdminDashboardPageObject;
import pageObject.liveTechpanda.admin.AdminLoginPageObject;
import pageObject.liveTechpanda.user.PageGeneratorManager;
import pageObject.liveTechpanda.user.UserHomePageObject;
import pageObject.liveTechpanda.user.UserLoginPageObject;
import pageObject.liveTechpanda.user.UserMyAccountPageObject;
import pageObject.liveTechpanda.user.UserRegisterPageObject;

public class Level_10_DataTable_DataGrid_Excersice extends BaseTest{
	private WebDriver driver;
	private String firstName, lastName, emailAddress, password;
	private String userAdmin, passwordAdmin;
	
	private UserHomePageObject userHomePage;
	private UserLoginPageObject userLoginPage;
	private UserRegisterPageObject userRegisterPage;
	private UserMyAccountPageObject userMyAccountPage;
	
	private AdminLoginPageObject adminLoginPage;
	private AdminDashboardPageObject adminDashboardPage;
	
	List<String> actualAllCountryValue;
	List<String> expectedAllCountryValue;
	
	@Parameters({"browser","url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);

		firstName = "Luong";
		lastName = "Nguyen";
		emailAddress = "luongnguyen" + generateFakeNumber()+"@hotmail.com";
		password = "123456";
		
		userAdmin = "user01";
		passwordAdmin ="guru99com";
		
	}
	
	@Test 
	public void Step_01_Register_Account() { 
		userLoginPage = userHomePage.clickToLogin();
		userRegisterPage = userLoginPage.clickToCreateAccount();
		//input data
		userRegisterPage.inputToFirstName(firstName);
		userRegisterPage.inputToLastName(lastName);
		userRegisterPage.inputToEmailAddress(emailAddress);
		userRegisterPage.inputToPassword(password);
		userRegisterPage.inputToConfirmPassword(password);
		userMyAccountPage = userRegisterPage.clickToRegisterAccount();
		Assert.assertTrue(userMyAccountPage.isVerifyRegisterSuccessfully(firstName, lastName, emailAddress));
		
		//Chuẩn bị trước
		//userHomePage = userMyAccountPage.clickToLogOut();
	}
	
	@Test
	public void Step_02_Verify_Account_At_Admin_Site() {
		userHomePage.openPageUrl(driver, GlobalConstants.ADMIN_PAGE_LIVETECHPANDA_URL);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);
		
		//Login
		adminDashboardPage = adminLoginPage.loginAsAdmin(userAdmin,passwordAdmin);
		Assert.assertTrue(adminLoginPage.isAdminDashboardPageDisplayed());
		adminDashboardPage.closePopupOnDashboard();
		
		adminDashboardPage.inputToSearchByTitle("email",emailAddress);
		Assert.assertTrue(adminLoginPage.isVerifyEmailSearched(firstName,lastName,emailAddress));

	}
	
	@Test
	public void Step_03_Verify_Account_Deleted_At_User_Site() {
		//Delete account
		adminDashboardPage.clickToSelectRowByEmail(emailAddress);
		adminDashboardPage.clickToDeleteRowSelected();
		adminDashboardPage.sleepInSecond(2);
		adminDashboardPage.openPageUrl(driver, GlobalConstants.PORTAL_PAGE_LIVETECHPANDA_URL);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
		
		userLoginPage = userHomePage.clickToLogin();
		userLoginPage.loginAccountRegisted(emailAddress, password);
		Assert.assertTrue(userLoginPage.isVerifyAccountDeleted());
		
		userLoginPage.sleepInSecond(2);
		
	}

	
	@AfterClass 
	public void afterClass() {
		driver.quit();
	}
}
