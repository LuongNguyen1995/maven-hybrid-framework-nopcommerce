package com.liveguru.user;


import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import enviromentConfig.Enviroment;

public class Level_24_Multiple_Enviroment extends BaseTest{
	
	Enviroment enviroment;
	
	@Parameters({"browser"})
	@BeforeClass
	public void beforeClass(String browserName) {
		//Get enviroment value from Maven command line
		String prefixPropertiesName = System.getProperty("envMaven");
		System.out.println("Properties file name = " +prefixPropertiesName );
		ConfigFactory.setProperty("envOwner", prefixPropertiesName);
		enviroment = ConfigFactory.create(Enviroment.class);
		driver = getBrowserDriver(browserName, enviroment.applicationUrl());
		
		System.out.println(driver.getCurrentUrl());
	}
	
	@Test 
	public void Employee_01_Add_New_Employee() { 

	}
	
	@Test 
	public void Employee_02_Upload_Avatar() { 
		
	}
	
	@Test 
	public void Employee_03_Personal_Details() { 
	}
	
	@Test 
	public void Employee_04_Contact_Details() { 
	}
	
	@Test 
	public void Employee_05_Emergency_Details() { 
		
	}
	
	@Test 
	public void Employee_06_Assigned_Dependents() { 
		
	}
	
	@Test 
	public void Employee_07_Edit_View_Job() { 
	
	}
	
	@Test 
	public void Employee_08_Edit_View_Salary() { 

	}
	
	@Test 
	public void Employee_09_Edit_View_Tax() { 

	}
	
	@Test 
	public void Employee_10_Qualifications() { 
		
	}
	
	@Test 
	public void Employee_11_Search_Employee() { 

	}
	
	
	@AfterClass (alwaysRun= true)
	public void afterClass() {
		closeBrowserAndDriver();
	}
}
