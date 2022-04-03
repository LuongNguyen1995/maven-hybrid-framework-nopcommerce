package com.saucelab.sort;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObject.sauceLab.InventoryPageObject;
import pageObject.sauceLab.LoginPageObject;
import pageUIs.sauceLab.PageGeneratorManager;

public class Level_21_Sort extends BaseTest{
	private WebDriver driver;
	LoginPageObject loginPage;
	InventoryPageObject inventoryPage;
	
	
	
	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		System.out.println("Run on "+browserName);
		driver = getBrowserDriver(browserName, appUrl);
		
		loginPage = PageGeneratorManager.getLoginPage(driver);
		loginPage.enterToUserNameTextbox("standard_user");
		loginPage.enterToPasswordTextbox("secret_sauce");
		inventoryPage = loginPage.clickToLoginButton();
	}
	
	@Test 
	public void Sort_01_Name() { 
		//Ascending : Tăng dần
		log.info("Sort_01_Name - Step 01: Click to Sort By Name (A to Z)");
		inventoryPage.selectItemInSortDropDown("Name (A to Z)");
		verifyTrue(inventoryPage.isProductNameSortAscending());
		inventoryPage.sleepInSecond(3);
		
		//Descending : Giảm dần
		log.info("Sort_01_Name - Step 02 : Click to Sort By Name (A to Z)");
		inventoryPage.selectItemInSortDropDown("Name (Z to A)");
		verifyTrue(inventoryPage.isProductNameSortDescending());
		inventoryPage.sleepInSecond(3);
	}
	
	@Test 
	public void Sort_02_Price() { 
		log.info("Sort_02_Price - Step 01: Click to Sort By Price (low to high)");
		inventoryPage.selectItemInSortDropDown("Price (low to high)");
		verifyTrue(inventoryPage.isProductPriceSortAscending());
		inventoryPage.sleepInSecond(3);
		
		log.info("Sort_02_Price - Step 01: Click to Sort By Price (high to low)");
		inventoryPage.selectItemInSortDropDown("Price (high to low)");
		verifyTrue(inventoryPage.isProductPriceSortDescending());
		inventoryPage.sleepInSecond(3);
	}
	
	
	@AfterClass (alwaysRun= true)
	public void afterClass() {
		closeBrowserAndDriver();
	}
}
