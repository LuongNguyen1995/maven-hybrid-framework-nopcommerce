package commons;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePageFactory {
	private long longTimeout = 30;
	private long shortTimeout = 5;
	
	protected static BasePage getBasePageObject() {
		return new BasePage();
	}
	
	protected void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}
	
	protected String getPageTitle(WebDriver driver, String pageUrl) {
		return driver.getTitle();
	}
	
	protected String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}
	
	protected String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}
	
	protected void backToPage(WebDriver driver) {
		driver.navigate().back();
	}
	
	protected void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}
	
	protected void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}
	
	protected Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver,longTimeout);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}
	
	protected void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}
	
	protected void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}
	
	protected String getTextAlert(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}
	
	protected void sendkeyToAlert(WebDriver driver,String textValue) {
		waitForAlertPresence(driver).sendKeys(textValue);
	}
	
	protected void switchToWindowByTitle(WebDriver driver, String windowTitle) {
		Set<String> allWindowIds = driver.getWindowHandles();
		for (String id : allWindowIds) { 
			driver.switchTo().window(id);
			String actualTitle = driver.getTitle();
			if (actualTitle.equals(windowTitle)) {
				break;
			}	
		}
	}
	
	protected void switchToWindowByID(WebDriver driver, String windowID) {
		Set<String> allWindowIds = driver.getWindowHandles();
		for (String id : allWindowIds) { 
			if (!id.equals(windowID)) {
				driver.switchTo().window(id);
				break;
			}	
		}
	}
	
	protected void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindowIds = driver.getWindowHandles();
		for (String id : allWindowIds) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
			driver.switchTo().window(parentID);
		}
	}
	
	protected void clickToElement(WebElement element){
		element.click();
	}
	
	protected void sendkeyToElement(WebElement element, String textValue) {
		element.clear();
		element.sendKeys(textValue);
	}
	
	protected String getElementText(WebElement element){
		return element.getText();
	}
	
	protected boolean isElementDisplayed(WebElement element) {
		return element.isDisplayed();
	}
	
	protected void waitForElementVisible(WebDriver driver, WebElement element) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOf(element));
	}
	
	protected void waitForAllElementVisible(WebDriver driver, List<WebElement> elements) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}
	
	protected void waitForElementInvisible(WebDriver driver, WebElement element) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	protected void waitForAllElementInisible(WebDriver driver, List<WebElement> elements) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(elements));
	}
	
	protected void waitForElementClickable(WebDriver driver, WebElement element) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(element));
	}
}
