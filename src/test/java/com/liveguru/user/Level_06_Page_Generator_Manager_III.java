package com.liveguru.user;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.liveGuru.HomePageObject;
import pageObjects.liveGuru.LoginPageObject;
import pageObjects.liveGuru.MyDashboardPageObject;
import pageObjects.liveGuru.RegisterPageObject;

public class Level_06_Page_Generator_Manager_III extends BaseTest{
	private WebDriver driver;
	private HomePageObject homePage;
	private LoginPageObject loginPage;
	private RegisterPageObject registerPage;
	private MyDashboardPageObject myDashboardPage;
	
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		System.out.println("Run on "+browserName);
		driver = getBrowserDriver(browserName);
		

	}
	
	@Test 
	public void User_01_Register_To_System() { 
		
	}
	
	@Test
	public void Login_02_Login_To_System() {
		
	}

	@AfterClass 
	public void afterClass() {
		driver.quit();
	}
}
