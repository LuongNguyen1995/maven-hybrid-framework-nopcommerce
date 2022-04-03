package com.jquery;



import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObject.jQuery.uploadFile.HomePageObject;
import pageObject.jQuery.uploadFile.PageGeneratorManager;

public class Level_11_Upload_Files extends BaseTest{
	private WebDriver driver;
	private HomePageObject homePage;
	String javaFileName = "Java.png";
	String pythonFileName = "Python.png";
	String csharpFileName = "CSharp.png";
	String rubyFileName = "Ruby.png";
	String[] mutipleFileNames = {javaFileName, pythonFileName, csharpFileName, rubyFileName };
	
	@Parameters({"browser","url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		homePage = PageGeneratorManager.getHomePage(driver);
	}
	
	@Test 
	public void Upload_01_One_File_Per_Time() { 
		//Step 1 : Load single file
		homePage.uploadMultipleFiles(driver, javaFileName);
		//Step 2 : Verify single file loaded success
		Assert.assertTrue(homePage.isFileLoadedByName(javaFileName));
		//Step 3 ; Click to Start Button
		homePage.clickToStartButton();
		//Step 4 : Verify single file link uploaded success
		Assert.assertTrue(homePage.isFileLinkUploadedByName(javaFileName));
		//Step 5 : Verify single file image uploaded success
		Assert.assertTrue(homePage.isFileImageUploadedByName(javaFileName));
	}
	
	@Test 
	public void Upload_02_Multiple_Files_Per_Time() { 
		homePage.refreshCurrentPage(driver);
		//Step 1 : Load multiple file
		homePage.uploadMultipleFiles(driver, mutipleFileNames);
		//Step 2 : Verify multiple file loaded success
		//javaFileName, pythonFileName, csharpFileName, rubyFileName
		Assert.assertTrue(homePage.isFileLoadedByName(javaFileName));
		Assert.assertTrue(homePage.isFileLoadedByName(pythonFileName));
		Assert.assertTrue(homePage.isFileLoadedByName(csharpFileName));
		Assert.assertTrue(homePage.isFileLoadedByName(rubyFileName));
		//Step 3 ; Click to Start Button
		homePage.clickToStartButton();
		//Step 4 : Verify multiple file link uploaded success
		Assert.assertTrue(homePage.isFileLinkUploadedByName(javaFileName));
		Assert.assertTrue(homePage.isFileLinkUploadedByName(pythonFileName));
		Assert.assertTrue(homePage.isFileLinkUploadedByName(csharpFileName));
		Assert.assertTrue(homePage.isFileLinkUploadedByName(rubyFileName));
		//Step 5 : Verify multiple file image uploaded success
		Assert.assertTrue(homePage.isFileImageUploadedByName(javaFileName));		
		Assert.assertTrue(homePage.isFileImageUploadedByName(pythonFileName));		
		Assert.assertTrue(homePage.isFileImageUploadedByName(csharpFileName));		
		Assert.assertTrue(homePage.isFileImageUploadedByName(rubyFileName));		
	}
	
	
	@AfterClass 
	public void afterClass() {
		//driver.quit();
	}
}
