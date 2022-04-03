package pageUIs.sauceLab;

import org.openqa.selenium.WebDriver;

import pageObject.sauceLab.InventoryPageObject;
import pageObject.sauceLab.LoginPageObject;

public class PageGeneratorManager {
	
	public static LoginPageObject getLoginPage (WebDriver driver) {
		return new LoginPageObject(driver);
	}

	public static InventoryPageObject getInventoryPage (WebDriver driver) {
		return new InventoryPageObject(driver);
	}
	
	
}
