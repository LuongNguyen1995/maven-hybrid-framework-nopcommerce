package pageObject.liveTechpanda.user;

import org.openqa.selenium.WebDriver;

import pageObject.liveTechpanda.admin.AdminDashboardPageObject;
import pageObject.liveTechpanda.admin.AdminLoginPageObject;

public class PageGeneratorManager {
	
	public static UserHomePageObject getUserHomePage(WebDriver driver) {
		return new UserHomePageObject(driver);
	}

	public static UserLoginPageObject getUserLoginPage (WebDriver driver) {
		return new UserLoginPageObject(driver);
	}
	
	public static UserMyAccountPageObject getUserMyAccountPage (WebDriver driver) {
		return new UserMyAccountPageObject(driver);
	}
	
	public static UserRegisterPageObject getUserRegisterPage (WebDriver driver) {
		return new UserRegisterPageObject(driver);
	}
	
	public static AdminLoginPageObject getAdminLoginPage(WebDriver driver) {
		return new AdminLoginPageObject(driver);
	}

	public static AdminDashboardPageObject getAdminDashboardPage(WebDriver driver) {
		return new AdminDashboardPageObject(driver);
	}
}
