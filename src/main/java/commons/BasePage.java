package commons;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.hrm.DashboardPO;
import pageObjects.hrm.LoginPO;
import pageObjects.hrm.PageGenerator;
import pageObjects.nopCommerce.admin.AdminLoginPageObject;
import pageObjects.nopCommerce.user.UserAddressPageObject;
import pageObjects.nopCommerce.user.UserCustomerInforPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewPageObject;
import pageObjects.nopCommerce.user.UserRewardPointPageObject;
import pageUIs.hrm.BasePageHRMUI;
import pageUIs.hrm.MyInfoPageUI;
import pageUIs.jQuery.uploadFile.BasePageJQueryUI;
import pageUIs.nopCommerce.user.BasePageUI;
import pageUIs.nopCommerce.user.RegisterPageUI;

public class BasePage {
	
	private long longTimeout = GlobalConstants.LONG_TIMEOUT;
	private long shortTimeout = GlobalConstants.SHORT_TIMEOUT;
	
	public static BasePage getBasePageObject() {
		return new BasePage();
	}
	
	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}
	
	public String getPageTitle(WebDriver driver, String pageUrl) {
		return driver.getTitle();
	}
	
	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}
	
	public String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}
	
	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}
	
	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}
	
	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}
	
	public Set<Cookie> getAllCookies(WebDriver driver) {
		return driver.manage().getCookies();
	}
	
	public void setCookies(WebDriver driver, Set<Cookie> cookies) {
		for (Cookie cookie : cookies) {
			driver.manage().addCookie(cookie);
		}
		sleepInSecond(3);
	}
	
	public Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver,longTimeout);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}
	
	public void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}
	
	public void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}
	
	public String getTextAlert(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}
	
	public void sendkeyToAlert(WebDriver driver,String textValue) {
		waitForAlertPresence(driver).sendKeys(textValue);
	}
	
	public void switchToWindowByTitle(WebDriver driver, String windowTitle) {
		Set<String> allWindowIds = driver.getWindowHandles();
		for (String id : allWindowIds) { 
			driver.switchTo().window(id);
			String actualTitle = driver.getTitle();
			if (actualTitle.equals(windowTitle)) {
				break;
			}	
		}
	}
	
	public void switchToWindowByID(WebDriver driver, String windowID) {
		Set<String> allWindowIds = driver.getWindowHandles();
		for (String id : allWindowIds) { 
			if (!id.equals(windowID)) {
				driver.switchTo().window(id);
				break;
			}	
		}
	}
	
	public void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindowIds = driver.getWindowHandles();
		for (String id : allWindowIds) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
			driver.switchTo().window(parentID);
		}
	}
	

	//locator type : id=/ css=/ xpath=/ name=/class=
	public By getByLocator(String locatorType) {
		By by = null;
		if (locatorType.startsWith("id=") || locatorType.startsWith("ID=") || locatorType.startsWith("Id=")) {
			by = By.id(locatorType.substring(3));
		} else if (locatorType.startsWith("class=") || locatorType.startsWith("CLASS=") || locatorType.startsWith("Class=")) {
			by = By.className(locatorType.substring(6));
		} else if (locatorType.startsWith("xpath=")  || locatorType.startsWith("XPATH=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPath=")) {
			by = By.xpath(locatorType.substring(6));
		} else if (locatorType.startsWith("name=") || locatorType.startsWith("NAME=") || locatorType.startsWith("Name=") ) {
			by = By.name(locatorType.substring(5));
		} else if (locatorType.startsWith("css=") || locatorType.startsWith("CSS=") || locatorType.startsWith("Css=")) {
			by = By.cssSelector(locatorType.substring(4));
		} else {
			throw new RuntimeException("Locator type is not support!");
		}

		return by;
	}
	
	//Nếu như truyền vào locator type là xpath = đúng
	//Truyền vào locator Type # xpath = sai
	public String getDynamicXpath(String locatorType, String... dynamicValue) {
		if(locatorType.startsWith("xpath=")  || locatorType.startsWith("XPATH=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPath=")) {
			locatorType = String.format(locatorType, (Object[]) dynamicValue);
		}
		return locatorType;
	}
	
	public WebElement getWebElement(WebDriver driver, String locatorType){
		return driver.findElement(getByLocator(locatorType));
	}
	
	public List<WebElement> getListWebElement(WebDriver driver, String locatorType) {
		return driver.findElements(getByLocator(locatorType));
	}
	
	public void clickToElement(WebDriver driver, String locatorType){
		if (driver.toString().contains("internet explorer")) {
			clickToElementByJS(driver, locatorType);
			sleepInSecond(2);
		} else {
			getWebElement(driver, locatorType).click();
		}
	}
	
	public void clickToElement(WebDriver driver, String locatorType, String... dynamicValue){
		if (driver.toString().contains("internet explorer")) {
			clickToElementByJS(driver, locatorType, dynamicValue);
			sleepInSecond(2);
		} else {
			getWebElement(driver, getDynamicXpath(locatorType, dynamicValue)).click();
		}
	}
	
	public void sendkeyToElement(WebDriver driver, String locatorType, String textValue) {
		WebElement element = getWebElement(driver, locatorType);
		element.clear();
		element.sendKeys(textValue);
	}
	
	public void sendkeyToElement(WebDriver driver, String locatorType, String textValue, String... dynamicValue) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValue));
		element.clear();
		element.sendKeys(textValue);
	}
	
	public String getElementText(WebDriver driver, String locatorType){
		return getWebElement(driver, locatorType).getText().trim();
	}
	
	public String getElementText(WebDriver driver, String locatorType, String... dynamicValue){
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValue)).getText().trim();
	}
	
	public void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String textItem){
		Select select = new Select(getWebElement(driver, locatorType));
		select.selectByVisibleText(textItem);
	}
	
	public void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String textItem, String... dynamicValue){
		Select select = new Select(getWebElement(driver, getDynamicXpath(locatorType, dynamicValue)));
		select.selectByVisibleText(textItem);
	}
	
	public String getSelectedItemDefaultDropdown(WebDriver driver, String locatorType){
		Select select = new Select(getWebElement(driver, locatorType));
		return select.getFirstSelectedOption().getText();
	}
	
	public String getSelectedItemDefaultDropdown(WebDriver driver, String locatorType, String... dynamicValue){
		Select select = new Select(getWebElement(driver, getDynamicXpath(locatorType, dynamicValue)));
		return select.getFirstSelectedOption().getText();
	}
	
	public boolean isDropdownMultiple(WebDriver driver, String locatorType){
		Select select = new Select(getWebElement(driver, locatorType));
		return select.isMultiple();
	}
	
	public void selectItemInCustomDropdownList(WebDriver driver,String parentXpath, String childXpath, String expectedTextItem) {
		clickToElement(driver,parentXpath);
		sleepInSecond(2);
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childXpath)));
		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedTextItem)) {
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(2);
				item.click();
				break;
			}
		}
	}
	
	public void sleepInSecond (long second) {
		try {
			Thread.sleep( second * 1000);
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
	public String getElementAttribute(WebDriver driver, String locatorType, String attributeName){
		return getWebElement(driver, locatorType).getAttribute(attributeName);
	}
	
	public String getElementAttribute(WebDriver driver, String locatorType, String attributeName, String... dynamicValue){
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValue)).getAttribute(attributeName);
	}
	
	public String getElementCssValue(WebDriver driver, String locatorType, String propertyName){
		return getWebElement(driver, locatorType).getCssValue(propertyName);
	}
	
	public String getHexaColorFromRgba(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}
	
	public int getElementSize(WebDriver driver, String locatorType) {
		return getListWebElement(driver,locatorType).size();
	}
	
	public int getElementSize(WebDriver driver, String locatorType, String... dynamicValue) {
		return getListWebElement(driver,getDynamicXpath(locatorType, dynamicValue)).size();
	}
	
	public void checkToDefaultCheckboxOrRadio(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if (!element.isSelected()) {
			if (driver.toString().contains("internet explorer")) {
				clickToElementByJS(driver, locatorType);
			} else {
				element.click();
			}
		}
	}
	
	public void checkToDefaultCheckboxOrRadio(WebDriver driver, String locatorType, String... dynamicValue) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValue));
		if (!element.isSelected()) {
			if (driver.toString().contains("internet explorer")) {
				clickToElementByJS(driver, getDynamicXpath(locatorType, dynamicValue));
			} else {
				element.click();
			}
		}
	}
	
	public void uncheckToDefaultCheckboxRadio(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if (element.isSelected()) {
			if (driver.toString().contains("internet explorer")) {
				clickToElementByJS(driver, locatorType);
			} else {
				element.click();
			}
		}
	}
	
	public void uncheckToDefaultCheckboxRadio(WebDriver driver, String locatorType, String... dynamicValue) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValue));
		if (driver.toString().contains("internet explorer")) {
			clickToElementByJS(driver, getDynamicXpath(locatorType, dynamicValue));
		} else {
			element.click();
		}
	}
	
	public boolean isElementDisplayed(WebDriver driver, String locatorType) {
		try {
			return getWebElement(driver, locatorType).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public boolean isElementUndisplayed(WebDriver driver, String locator) {
		System.out.println("Start time : "+new Date().toString());
		overrideImplicitTimeout(driver, shortTimeout);
		List<WebElement> elements = getListWebElement(driver, locator);
		overrideImplicitTimeout(driver, longTimeout);
		if (elements.size()==0) {
			return true;
		} else if(elements.size()>0 && !elements.get(0).isDisplayed()) {
			return true;
		}else {
			return false;
		}
	}
	
	public void overrideImplicitTimeout(WebDriver driver, long timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}
	
	public boolean isElementDisplayed(WebDriver driver, String locatorType, String... dynamicValue) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValue)).isDisplayed();
	}
	
	public boolean isElementEnabled(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isEnabled();
	}

	
	public boolean isElementEnabled(WebDriver driver, String locatorType, String... dynamicValue) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValue)).isEnabled();
	}
	
	public boolean isElementSelected(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isSelected();
	}
	
	public boolean isElementSelected(WebDriver driver, String locatorType, String... dynamicValue) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValue)).isSelected();
	}
	
	public void switchToFrameIframe(WebDriver driver, String locatorType) {
		driver.switchTo().frame(getWebElement(driver, locatorType));
	}
	
	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	
	public void hoverMouseToElement(WebDriver driver, String locatorType) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, locatorType)).perform();
	}
	
	public void hoverMouseToElement(WebDriver driver, String locatorType, String... dynamicValue) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, getDynamicXpath(locatorType, dynamicValue))).perform();
	}
	
	public void pressKeyToElement(WebDriver driver, String locatorType, Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver,locatorType), key).perform();
	}
	
	public void pressKeyToElement(WebDriver driver, String locatorType, Keys key, String... dynamicValue) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, getDynamicXpath(locatorType, dynamicValue)), key).perform();
	}
	
	public void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void highlightElement(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, locatorType);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}
	
	public void clickToElementByJS(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locatorType));
	}
	
	public void clickToElementByJS(WebDriver driver, String locatorType, String... dynamicValue) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, getDynamicXpath(locatorType, dynamicValue)));
	}

	public WebElement getShadowDOM(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = (WebElement) jsExecutor.executeScript("return arguments[0].shadowRoot;", getWebElement(driver, locatorType));
		return element;
	}
	
	public void scrollToElement(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locatorType));
	}

	public String getElementValueByJSXpath(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		xpathLocator = xpathLocator.replace("xpath=", "");
		return (String) jsExecutor.executeScript("return $(document.evaluate(\"" + xpathLocator+ "\",document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue).val()");
	}
	
	public void removeAttributeInDOM(WebDriver driver, String locatorType, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locatorType));
	}
	

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}
	
	public boolean isJQueryAjaxLoadedSuccess(WebDriver driver){
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active ==0);");
			}
		};
		return explicitWait.until(jQueryLoad);
	}

	public String getElementValidationMessage(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, locatorType));
	}
	
	
	public boolean isImageLoaded(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, locatorType));
		return status;
	}
	

	public boolean isImageLoaded(WebDriver driver, String locatorType, String... dynamicValue) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, getDynamicXpath(locatorType, dynamicValue)));
		return status;
	}
	
	public void waitForElementVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
	}
	
	public void waitForElementVisible(WebDriver driver, String locatorType, String... dynamicValue) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValue))));
	}
	
	public void waitForAllElementVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));
	}
	
	public void waitForAllElementVisible(WebDriver driver, String locatorType, String... dynamicValue) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicXpath(locatorType, dynamicValue))));
	}
	
	public void waitForElementInvisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
	}
	/*
	* Wait for Element undisplayed in DOM or not in DOM and override implicitTimeout
	* */
	public void waitForElementUndisplayed(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, shortTimeout);
		overrideImplicitTimeout(driver, shortTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
		overrideImplicitTimeout(driver, longTimeout);
	}
	
	
	
	public void waitForElementInvisible(WebDriver driver, String locatorType, String... dynamicValue) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValue))));
	}
	
	public void waitForAllElementInisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, locatorType)));
	}
	
	public void waitForAllElementInisible(WebDriver driver, String locatorType, String... dynamicValue) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, getDynamicXpath(locatorType, dynamicValue))));
	}
	
	public void waitForElementClickable(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
	}
	
	public void waitForElementClickable(WebDriver driver, String locatorType, String... dynamicValue) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locatorType, dynamicValue))));
	}
	
	public void uploadMultipleFiles(WebDriver driver, String... fileNames) {
		String filePath = GlobalConstants.UPLOAD_FILE;
		String fullFileName = "";
		
		for (String file : fileNames) {
			fullFileName = fullFileName + filePath + file + "\n";
		}
		fullFileName = fullFileName.trim();
		getWebElement(driver, BasePageJQueryUI.UPLOAD_FILE).sendKeys(fullFileName);
		
	}
	
	
	
	//Tối ưu ở bài học Level_07_Switch_Page
	public UserAddressPageObject openAddressPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.ADDRESS_LINK);
		clickToElement(driver, BasePageUI.ADDRESS_LINK);
		return PageGeneratorManager.getUserAddressPage(driver);
	}
	
	public UserMyProductReviewPageObject openMyProductReviewPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.MY_PRODUCT_REVIEW_LINK);
		clickToElement(driver, BasePageUI.MY_PRODUCT_REVIEW_LINK);
		return PageGeneratorManager.getUserMyProductReviewPage(driver);
	}
	
	public UserRewardPointPageObject openRewardPointPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.REWARD_POINT_LINK);
		clickToElement(driver, BasePageUI.REWARD_POINT_LINK);
		return PageGeneratorManager.getUserRewardPointPage(driver);
	}
	
	public UserCustomerInforPageObject openCustomerInforPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.CUSTOMER_INFOR_LINK);
		clickToElement(driver, BasePageUI.CUSTOMER_INFOR_LINK);
		return PageGeneratorManager.getUserCustomerInforPage(driver);
	}
	
	//Tối ưu ở bài học Level_09_Dynamic_Locator
	public BasePage openPageAtMyAccountByName(WebDriver driver, String pageName) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA, pageName);
		clickToElement(driver, BasePageUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA, pageName);
		switch (pageName) {
		case "Customer info":
			return PageGeneratorManager.getUserCustomerInforPage(driver);
		case "Addresses":
			return PageGeneratorManager.getUserAddressPage(driver);
		case "My product reviews":
			return PageGeneratorManager.getUserMyProductReviewPage(driver);
		case "Reward points":
			return PageGeneratorManager.getUserRewardPointPage(driver);
			
		default:
			throw new RuntimeException("Invalid page name at My Account area.");
		}
	}
	
	public void openPageAtMyAccountByPageName(WebDriver driver, String pageName) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA, pageName);
		clickToElement(driver, BasePageUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA, pageName);
	}
	
	//Level_08_Switch_Role
	public UserHomePageObject clickToLogoutLinkAtUserPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.LOGOUT_LINK_AT_USER);
		clickToElement(driver, BasePageUI.LOGOUT_LINK_AT_USER);
		return PageGeneratorManager.getUserHomePage(driver);
	}
	
	public AdminLoginPageObject clickToLogoutLinkAtAdminPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.LOGOUT_LINK_AT_ADMIN);
		clickToElement(driver, BasePageUI.LOGOUT_LINK_AT_ADMIN);
		return PageGeneratorManager.getAdminLoginPage(driver);
	}
	
	//Pattern Object : User - Nopcommerce
//	public void enterToTextboxByID(WebDriver driver, String textboxID, String value) {
//		waitForElementVisible(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_ID, textboxID);
//		sendkeyToElement(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_ID, value, textboxID);
//	}
//	
//	public void openHeaderPageByName (WebDriver driver, String textboxID) {
//		waitForElementClickable(driver, BasePageUI.DYNAMIC_PAGE_HEADER, textboxID);
//		clickToElement(driver, BasePageUI.DYNAMIC_PAGE_HEADER, textboxID);
//	}
//	
//	public void clickToRadioButtonByText(WebDriver driver, String radioButtonID) {
//		waitForElementClickable(driver, BasePageUI.DYNAMIC_RADIO_BY_TEXT, radioButtonID);
//		clickToElement(driver, BasePageUI.DYNAMIC_RADIO_BY_TEXT, radioButtonID);
//	}
//
//	public void selectDropdownByName(WebDriver driver, String dropdownName, String itemText) {
//		selectItemInDefaultDropdown(driver, BasePageUI.DYNAMIC_DROPDOWN_BY_NAME, itemText, dropdownName);
//	}
//	
//	public void clickToButtonByText(WebDriver driver, String buttonText) {
//		waitForElementClickable(driver, BasePageUI.DYNAMIC_BUTTON_BY_TEXT, buttonText);
//		clickToElement(driver, BasePageUI.DYNAMIC_BUTTON_BY_TEXT, buttonText);
//	}
	
	//HRM - Menu
	public void openMenuPage(WebDriver driver, String pageName) {
		waitForElementClickable(driver, BasePageHRMUI.MENU_BY_PAGE_NAME, pageName);
		clickToElement(driver, BasePageHRMUI.MENU_BY_PAGE_NAME, pageName);
		
		isJQueryAjaxLoadedSuccess(driver);
		if (driver.toString().contains("internet explorer")) {
			sleepInSecond(3);
		} 
	}
	//HRM - Sub Menu
	public void openSubMenuPage(WebDriver driver, String pageName, String subMenuPageName) {
		waitForElementClickable(driver, BasePageHRMUI.MENU_BY_PAGE_NAME, pageName);
		if (driver.toString().contains("internet explorer")) {
			openPageUrl(driver, getElementAttribute(driver, BasePageHRMUI.MENU_BY_PAGE_NAME, "href", pageName));
		} else {
			clickToElement(driver,BasePageHRMUI.MENU_BY_PAGE_NAME, pageName);
		}
		
		
		waitForElementClickable(driver,BasePageHRMUI.MENU_BY_PAGE_NAME, subMenuPageName);
		if (driver.toString().contains("internet explorer")) {
			openPageUrl(driver, getElementAttribute(driver, BasePageHRMUI.MENU_BY_PAGE_NAME, "href", subMenuPageName));
		} else {
		clickToElement(driver, BasePageHRMUI.MENU_BY_PAGE_NAME, subMenuPageName);
		}
		isJQueryAjaxLoadedSuccess(driver);
		if (driver.toString().contains("internet explorer")) {
			sleepInSecond(3);
		} 
	}
	//HRM - Child Sub Menu
	public void openChildSubMenuPage(WebDriver driver, String pageName, String subMenuPageName, String childSubMenuPageName) {
		waitForElementClickable(driver, BasePageHRMUI.MENU_BY_PAGE_NAME, pageName);
		clickToElement(driver, BasePageHRMUI.MENU_BY_PAGE_NAME, pageName);
		
		waitForElementVisible(driver, BasePageHRMUI.MENU_BY_PAGE_NAME, subMenuPageName);
		hoverMouseToElement(driver, BasePageHRMUI.MENU_BY_PAGE_NAME, subMenuPageName);
		
		waitForElementClickable(driver, BasePageHRMUI.MENU_BY_PAGE_NAME, childSubMenuPageName);
		clickToElement(driver, BasePageHRMUI.MENU_BY_PAGE_NAME, childSubMenuPageName);
		
		isJQueryAjaxLoadedSuccess(driver);
		if (driver.toString().contains("internet explorer")) {
			sleepInSecond(3);
		} 
	}
	
	/**
	 * Click to Button by ID
	 * 
	 * @param driver
	 * @param buttonIDName
	 */
	public void clickToButtonByID(WebDriver driver, String buttonIDName) {
		waitForElementClickable(driver, BasePageHRMUI.BUTTON_BY_ID, buttonIDName);
		clickToElement(driver, BasePageHRMUI.BUTTON_BY_ID, buttonIDName);
		if (driver.toString().contains("internet explorer")) {
			sleepInSecond(3);
		} 
	}
	
	/**
	 * enter to textbox by textbox ID
	 * 
	 * @param driver
	 * @param textboxIDName
	 * @param value
	 */
	public void enterToTextboxByID(WebDriver driver, String textboxIDName, String value) {
		waitForElementVisible(driver, BasePageHRMUI.TEXTBOX_BY_ID, textboxIDName);
		sendkeyToElement(driver, BasePageHRMUI.TEXTBOX_BY_ID, value, textboxIDName);
	}
	
	/**
	 * Get textbox value by textbox id
	 * @author Luong Dinh Nguyen
	 * @param driver
	 * @param textboxIDName
	 * @return attribute value
	 */
	public String getTextboxValueByID(WebDriver driver, String textboxIDName) {
		waitForElementVisible(driver, BasePageHRMUI.TEXTBOX_BY_ID, textboxIDName);
		return getElementAttribute(driver, BasePageHRMUI.TEXTBOX_BY_ID, "value", textboxIDName);
	}
	
	/**
	 * Select Item In Dropdown by ID
	 * @param driver
	 * @param dropdownID
	 * @param valueItem
	 */
	public void selectItemInDropdownByID(WebDriver driver, String dropdownID, String valueItem) {
		waitForElementVisible(driver, BasePageHRMUI.DROPDOWN_BY_ID, dropdownID);
		selectItemInDefaultDropdown(driver, BasePageHRMUI.DROPDOWN_BY_ID, valueItem, dropdownID);
	}
	
	/**
	 * Get value Selected in Dropdown by ID
	 * @param driver
	 * @param dropdownID
	 * @return selected text in dropdown
	 */
	public String getSelectedValueInDropdownByID(WebDriver driver, String dropdownID) {
		waitForElementVisible(driver, BasePageHRMUI.DROPDOWN_BY_ID, dropdownID);
		return getSelectedItemDefaultDropdown(driver, BasePageHRMUI.DROPDOWN_BY_ID, dropdownID);
	}
	
	/**
	 * Click to check box by Label Text
	 * @param driver
	 * @param checkboxLabelName
	 */
	public void clickToCheckboxByLabel(WebDriver driver, String checkboxLabelName) {
		waitForElementClickable(driver, BasePageHRMUI.CHECKBOX_BY_LABEL, checkboxLabelName);
		checkToDefaultCheckboxOrRadio(driver, BasePageHRMUI.CHECKBOX_BY_LABEL, checkboxLabelName);
	}
	
	/**
	 * Click to radio button by Label text
	 * @param driver
	 * @param radioLabelName
	 */
	public void clickToRadioByLabel(WebDriver driver, String radioLabelName) {
		waitForElementClickable(driver, BasePageHRMUI.RADIO_BY_LABEL, radioLabelName);
		checkToDefaultCheckboxOrRadio(driver, BasePageHRMUI.RADIO_BY_LABEL, radioLabelName);
	}
	
	public boolean isCheckboxSelectedByLabel(WebDriver driver, String checkboxLabelName) {
		waitForElementVisible(driver, BasePageHRMUI.CHECKBOX_BY_LABEL, checkboxLabelName);
		return isElementSelected(driver, BasePageHRMUI.CHECKBOX_BY_LABEL, checkboxLabelName);
	}
	
	public boolean isRadioButtonSelectedByLabel(WebDriver driver, String radioLabelName) {
		waitForElementVisible(driver, BasePageHRMUI.RADIO_BY_LABEL, radioLabelName);
		return isElementSelected(driver, BasePageHRMUI.RADIO_BY_LABEL, radioLabelName);
	}
	
	public String getValueInTableIDAtColumnNameAndRowIndex(WebDriver driver, String tableID, String headerName, String rowIndex) {
		int columnIndex = getElementSize(driver, BasePageHRMUI.TABLE_HEADER_BY_ID_AND_NAME, tableID, headerName) +1;
		waitForElementVisible(driver, BasePageHRMUI.TABLE_ROW_BY_COLUMN_INDEX_AND_ROW_INDEX, tableID, rowIndex, String.valueOf(columnIndex) );
		return getElementText(driver, BasePageHRMUI.TABLE_ROW_BY_COLUMN_INDEX_AND_ROW_INDEX, tableID, rowIndex, String.valueOf(columnIndex) );
	}
	
	public LoginPO logoutToSystem(WebDriver driver) {
		waitForElementClickable(driver, BasePageHRMUI.WELCOME_USER_LINK);
		clickToElement(driver, BasePageHRMUI.WELCOME_USER_LINK);
		
		waitForElementClickable(driver, BasePageHRMUI.LOGOUT_LINK);
		clickToElement(driver, BasePageHRMUI.LOGOUT_LINK);
		return PageGenerator.getLoginPage(driver);
	}
	
	public DashboardPO loginToSystem(WebDriver driver, String userName, String password) {
		waitForElementVisible(driver, BasePageHRMUI.USER_LOGIN_TEXTBOX);
		sendkeyToElement(driver, BasePageHRMUI.USER_LOGIN_TEXTBOX, userName);
		sendkeyToElement(driver, BasePageHRMUI.PASSWORD_LOGIN_TEXTBOX, password);
		if (driver.toString().contains("internet explorer")) {
			sleepInSecond(3);
		} 
		clickToElement(driver, BasePageHRMUI.LOGIN_BUTTON);
		return PageGenerator.getDashboardPage(driver);
	}
	
	public void uploadImage(WebDriver driver, String filePath) {
		getWebElement(driver, BasePageHRMUI.UPLOAD_FILE).sendKeys(filePath);
	}
	
	public boolean isSuccessMessageDisplayed(WebDriver driver, String messageValue) {
		waitForElementVisible(driver, BasePageHRMUI.SUCCESS_MASSAGE_VALUE, messageValue);
		return isElementDisplayed(driver, BasePageHRMUI.SUCCESS_MASSAGE_VALUE, messageValue);
	}
	
	public boolean isFieldEnableByID(WebDriver driver, String fieldID) {
		waitForElementVisible(driver, BasePageHRMUI.ANY_FIELD_BY_ID, fieldID);
		return isElementEnabled(driver, BasePageHRMUI.ANY_FIELD_BY_ID, fieldID);
		
	}
}
