package pageObject.liveTechpanda.admin;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import commons.BasePage;
import pageUIs.liveTechpanda.admin.AdminDashboardPageUI;

public class AdminDashboardPageObject extends BasePage{

	WebDriver driver;
	public AdminDashboardPageObject(WebDriver driver) {
		this.driver = driver;
	}
	public void closePopupOnDashboard() {
		waitForElementVisible(driver, AdminDashboardPageUI.POPUP_MESSAGE);
		Assert.assertTrue(isElementDisplayed(driver, AdminDashboardPageUI.POPUP_MESSAGE));
		waitForElementClickable(driver, AdminDashboardPageUI.CLOSE_POPUP_BUTTON);
		clickToElement(driver, AdminDashboardPageUI.CLOSE_POPUP_BUTTON);
	}

	public void inputToSearchByTitle(String titleName, String emailAddress) {
		waitForElementVisible(driver, AdminDashboardPageUI.TEXTBOX_BY_TITLE, titleName);
		sendkeyToElement(driver, AdminDashboardPageUI.TEXTBOX_BY_TITLE, emailAddress, titleName);
		
		waitForElementClickable(driver, AdminDashboardPageUI.SEARCH_BUTTON);
		clickToElement(driver, AdminDashboardPageUI.SEARCH_BUTTON);
		sleepInSecond(5);
	}
	
	public void clickToSelectRowByEmail(String emailAddress) {
		waitForElementClickable(driver, AdminDashboardPageUI.CHECKBOX_AT_ROW_EMAIL, emailAddress);
		clickToElement(driver, AdminDashboardPageUI.CHECKBOX_AT_ROW_EMAIL, emailAddress);
	}
	
	public void clickToDeleteRowSelected() {
		selectItemInDefaultDropdown(driver, AdminDashboardPageUI.ACTION_SELECT_DROP_DOWN, "Delete");
		waitForElementClickable(driver, AdminDashboardPageUI.SUBMIT_BUTTON);
		clickToElement(driver, AdminDashboardPageUI.SUBMIT_BUTTON);
		
		acceptAlert(driver);
		
		//Verify Account Deleted
		Assert.assertTrue(isElementDisplayed(driver, AdminDashboardPageUI.MESSAGE_CONFIRM_DELETED));
	}

}
