package com.facebook.register;



import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObject.facebook.LoginPageObject;
import pageObject.facebook.PageGeneratorManager;

public class Level_13_Element_Undisplayed extends BaseTest{
	private WebDriver driver;
	private LoginPageObject loginPage;
	private String emailRegister;
	@Parameters({"browser","url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		loginPage = PageGeneratorManager.getLoginPage(driver);
		emailRegister = "automationfc@gmail.com";
	}
	
	@Test 
	public void TC_01_Verify_Element_Display() { 
		loginPage.clickToCreateNewAccountButton();
		//Nếu hàm chỉ mong đợi để verify element displayed - kết hợp cả wait + isDisplayed
		//waitForElementVisible
		//isElementDisplayed
		verifyTrue(loginPage.isEmailAddressTextboxDisplayed());
	}
	
	@Test 
	public void TC_02_Verify_Element_Undisplay_In_DOM() { 
		//Nếu hàm mong đợi 1 hàm vừa verify displayed/undisplayed thì không được kết hợp wait
		//waitForElementVisible
		//isElementDisplayed
		
		//Verify True - mong đợi Confirm Email displayed (true)
		loginPage.enterToEmailAddressTextbox(emailRegister);
		loginPage.sleepInSecond(3);
		verifyTrue(loginPage.isConfirmEmailAddressTextboxDisplayed());
		
		//Verify False - mong đợi Confirm Email Undisplayed (false)
		loginPage.enterToEmailAddressTextbox("");
		loginPage.sleepInSecond(3);
		verifyTrue(loginPage.isConfirmEmailAddressTextboxUndisplayed());
	}
	
	@Test 
	public void TC_03_Verify_Element_Undisplay_Not_In_DOM() { 
		loginPage.clickCloseIconAtRegisterForm();
		loginPage.sleepInSecond(3);

		//Khi close cái form Register đi thì Confirm Email không còn trong DOM nữa
		//Nên hàm findElement sẽ bị fail vì không tìm thấy Element
		//1 - Nó sẽ chờ hết timeout của implicitWait : 30s
		//2 - Nó sẽ đánh fail testcase tại đúng step này
		//3 - Không chạy các step còn lại
		
		
		//Verify False - mong đợi Confirm Email Undisplayed (false)
		//isDisplayed : bản chất không kiểm tra 1 element không có trong DOM được
		//verifyFalse(loginPage.isConfirmEmailAddressTextboxDisplayed());
		verifyTrue(loginPage.isConfirmEmailAddressTextboxUndisplayed());
	}
	
	@AfterClass 
	public void afterClass() {
		driver.quit();
	}
}
