package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Level_01_Register_DRY {
	WebDriver driver;
	String emailAddress;
	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
	
		driver = new FirefoxDriver();
		
		emailAddress = "afc" + generateFakeNumber() + "@mail.vn";
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");
	}
	
	@Test
	public void TC_01_Register_Empty_Data() {
		driver.findElement(By.xpath("//a[text()='Register']")).click();
		driver.findElement(By.id("register-button")).click();
		
		Assert.assertEquals(driver.findElement(By.id("FirstName-error")).getText(),"First name is required.");
		Assert.assertEquals(driver.findElement(By.id("LastName-error")).getText(),"Last name is required.");
		Assert.assertEquals(driver.findElement(By.id("Email-error")).getText(),"Email is required.");
		Assert.assertEquals(driver.findElement(By.id("Password-error")).getText(),"Password is required.");
		Assert.assertEquals(driver.findElement(By.id("ConfirmPassword-error")).getText(),"Password is required.");
	}
	
	@Test
	public void TC_02_Register_Invalid_Email() {
		driver.findElement(By.xpath("//a[text()='Register']")).click();
		
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Automation");
		driver.findElement(By.cssSelector("input#LastName")).sendKeys("FC");
		driver.findElement(By.cssSelector("input#Email")).sendKeys("bgfjkewng@fsdkljgvk");
		driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123456");
		
		driver.findElement(By.id("register-button")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.message-error li")).getText(), "Wrong email");
	}
	
	@Test
	public void TC_03_Register_Success() {
		driver.findElement(By.xpath("//a[text()='Register']")).click();
		
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Automation");
		driver.findElement(By.cssSelector("input#LastName")).sendKeys("FC");
		driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
		driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123456");
		
		driver.findElement(By.id("register-button")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
		
		driver.findElement(By.cssSelector("a.ico-logout")).click();
	}
	
	@Test
	public void TC_04_Register_Existing_Email() {
		driver.findElement(By.xpath("//a[text()='Register']")).click();
		
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Automation");
		driver.findElement(By.cssSelector("input#LastName")).sendKeys("FC");
		driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
		driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123456");
		
		driver.findElement(By.id("register-button")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.message-error li")).getText(), "The specified email already exists");
	}
	
	@Test
	public void TC_05_Register_Password_Less_Than_6_Chars() {
		driver.findElement(By.xpath("//a[text()='Register']")).click();
		
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Automation");
		driver.findElement(By.cssSelector("input#LastName")).sendKeys("FC");
		driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
		driver.findElement(By.cssSelector("input#Password")).sendKeys("123");
		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123");
		
		driver.findElement(By.id("register-button")).click();
		
		//C1 : tách ra 2 dòng rồi merge với nhau
//		String errorMess1 = driver.findElement(By.cssSelector("span#Password-error p")).getText();
//		String errorMess2 = driver.findElement(By.cssSelector("span#Password-error li")).getText();
//		String errorMess = errorMess1 + errorMess2;
//		Assert.assertEquals(errorMess, "Password must meet the following rules:must have at least 6 characters");
		
		//C2 :get trực tiếp value
		Assert.assertEquals(driver.findElement(By.cssSelector("span#Password-error")).getText(), "Password must meet the following rules:\nmust have at least 6 characters");
	}
	
	@Test
	public void TC_06_Register_Invalid_Confirm_Password() {
		driver.findElement(By.xpath("//a[text()='Register']")).click();
		
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Automation");
		driver.findElement(By.cssSelector("input#LastName")).sendKeys("FC");
		driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
		driver.findElement(By.cssSelector("input#Password")).sendKeys("123123");
		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123");
		
		driver.findElement(By.id("register-button")).click();
		
		Assert.assertEquals(driver.findElement(By.id("ConfirmPassword-error")).getText(),"The password and confirmation password do not match.");
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public int generateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
}
