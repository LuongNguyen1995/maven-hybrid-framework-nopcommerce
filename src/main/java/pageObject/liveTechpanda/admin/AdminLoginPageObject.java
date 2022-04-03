package pageObject.liveTechpanda.admin;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import commons.BasePage;
import pageObject.liveTechpanda.user.PageGeneratorManager;
import pageUIs.liveTechpanda.admin.AdminDashboardPageUI;
import pageUIs.liveTechpanda.admin.AdminLoginPageUI;

public class AdminLoginPageObject extends BasePage{

	WebDriver driver;
	public AdminLoginPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public AdminDashboardPageObject loginAsAdmin(String userAdmin, String passwordAdmin) {
		waitForElementVisible(driver, AdminLoginPageUI.TEXTBOX_BY_ID, "username");
		sendkeyToElement(driver, AdminLoginPageUI.TEXTBOX_BY_ID, userAdmin, "username");
		
		waitForElementVisible(driver, AdminLoginPageUI.TEXTBOX_BY_ID, "login");
		sendkeyToElement(driver, AdminLoginPageUI.TEXTBOX_BY_ID, passwordAdmin, "login");
		
		waitForElementClickable(driver, AdminLoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, AdminLoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getAdminDashboardPage(driver);
	}
	
	public boolean isAdminDashboardPageDisplayed() {
		
//		waitForAlertPresence(driver);
//		Alert alert = driver.switchTo().alert();
//		clickToElement(driver, AdminDashboardPageUI.CLOSE_ALERT);
//		//driver.switchTo().parentFrame();
//		sleepInSecond(3);
//		backToPage(driver);
		
		
		return isElementDisplayed(driver, AdminDashboardPageUI.ADMIN_PANEL_TITLE);
	}

	public boolean isVerifyEmailSearched(String firstName, String lastName, String emailAddress) {
		return isElementDisplayed(driver, AdminDashboardPageUI.INFOR_SEARCH_BY_NAME_AND_EMAIL, firstName, lastName, emailAddress);
	}

}
