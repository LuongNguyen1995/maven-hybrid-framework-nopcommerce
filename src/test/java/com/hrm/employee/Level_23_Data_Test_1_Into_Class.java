package com.hrm.employee;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import pageObjects.hrm.AddEmployeePO;
import pageObjects.hrm.DashboardPO;
import pageObjects.hrm.EmployeeListPO;
import pageObjects.hrm.LoginPO;
import pageObjects.hrm.MyInfoPO;
import pageObjects.hrm.PageGenerator;
import utilities.DataUtil;

public class Level_23_Data_Test_1_Into_Class extends BaseTest{
	WebDriver driver;
	LoginPO loginPage;
	AddEmployeePO addEmployeePage;
	DashboardPO dashboardPage;
	EmployeeListPO employeeListPage;
	MyInfoPO myInfoPage;
	DataUtil fakeData;
	String employeeID, statusValue;
	String adminUserName, adminPassword, empFirstname, empLastname, empUsername, empPassword, empFullname;
	String editEmpFirstName, editEmpLastName, editEmpGender, editEmpMaritalStatus, editEmpNationality ;
	String editEmpAddStr1, editEmpAddStr2, editEmpCity, editEmpProvince, editEmpZip, editEmpCountry, editEmpHomeTel, editEmpMobile, editEmpWorkTel, editEmpWorkMail, editEmpOtherMail;
	String editNameEmgContact, editRelationshipEmgContact, editHomeTelEmgContact;
	String editNameDependent, editRelationshipDependent, editDOBDependent;
	String avatarFilePath = GlobalConstants.UPLOAD_FILE + "Avatar.jpg";
	
	
	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		log.info("Pre-Condition - Step 01: Open browser '"+ browserName +"' and navigate to '"+ appUrl +"'");
		driver = getBrowserDriver(browserName, appUrl);
		loginPage = PageGenerator.getLoginPage(driver);
		fakeData = DataUtil.getData();
		
		statusValue = "Enabled";
		adminUserName = "Admin";
		adminPassword = "admin123";
		
		empFirstname = fakeData.getFirstName();
		empLastname = fakeData.getLastName();
		empFullname = empFirstname+ " "+ empLastname;
		empUsername = fakeData.getUserName();
		empPassword = fakeData.getPassword();
		editEmpFirstName = fakeData.getEditFirstName();
		editEmpLastName = fakeData.getEditLastName();
		editEmpGender = "Male"; 
		editEmpMaritalStatus = "Single"; 
		editEmpNationality = "Vietnamese";
		
		editEmpAddStr1 ="Dinh Bang";
		editEmpAddStr2 = "Tu Son";
		editEmpCity = "Bac Ninh";
		editEmpProvince = "Viet Nam";
		editEmpZip = "10000";
		editEmpCountry = "Viet Nam";
		editEmpHomeTel = fakeData.getPhoneNumber();
		editEmpMobile = fakeData.getPhoneNumber();
		editEmpWorkTel = fakeData.getPhoneNumber();
		editEmpWorkMail = fakeData.getEmailAddress();
		editEmpOtherMail = fakeData.getEmailAddress();
		
		editNameEmgContact = fakeData.getFirstName();
		editRelationshipEmgContact = "Daddy";
		editHomeTelEmgContact = fakeData.getPhoneNumber();
		
		editNameDependent = fakeData.getFirstName();
		editRelationshipDependent = "Child";
		editDOBDependent = "2022-01-03";
		
		log.info("Pre-Condition - Step 02: Login with Admin role");

		dashboardPage = loginPage.loginToSystem(driver, adminUserName, adminPassword);
	}
	
	@Test 
	public void Employee_01_Add_New_Employee() { 
		log.info("Add_New_01 - Step 01: Open 'Employee List' Page");
		dashboardPage.openSubMenuPage(driver, "PIM", "Employee List");
		employeeListPage = PageGenerator.getEmployeeListPage(driver);
		
		log.info("Add_New_01 - Step 02: Click to 'Add' button");
		employeeListPage.clickToButtonByID(driver, "btnAdd");
		addEmployeePage = PageGenerator.getAddEmployeePage(driver);
		
		log.info("Add_New_01 - Step 03: Enter valid infor to 'First Name' textbox");
		addEmployeePage.enterToTextboxByID(driver, "firstName", empFirstname);
		
		log.info("Add_New_01 - Step 04: Enter valid infor to 'Last Name' textbox");
		addEmployeePage.enterToTextboxByID(driver, "lastName", empLastname);
		
		log.info("Add_New_01 - Step 05: Get value of 'Employee ID'");
		employeeID = addEmployeePage.getTextboxValueByID(driver, "employeeId");
		
		log.info("Add_New_01 - Step 06: Click to 'Create Login Details' checkbox");
		addEmployeePage.clickToCheckboxByLabel(driver, "Create Login Details");
		
		log.info("Add_New_01 - Step 07: Enter valid infor to 'User Name' textbox");
		addEmployeePage.enterToTextboxByID(driver, "user_name", empUsername);
		
		log.info("Add_New_01 - Step 08: Enter valid infor to 'Password' textbox");
		addEmployeePage.enterToTextboxByID(driver, "user_password", empPassword);
		
		log.info("Add_New_01 - Step 09: Enter valid infor to 'Confirm Password' textbox");
		addEmployeePage.enterToTextboxByID(driver, "re_password", empPassword);
		
		log.info("Add_New_01 - Step 10: Select '"+ statusValue +"' value in 'Status' dropdown");
		addEmployeePage.selectItemInDropdownByID(driver, "status", statusValue);
		
		log.info("Add_New_01 - Step 11: Click to 'Save' button");
		addEmployeePage.clickToButtonByID(driver, "btnSave");
		myInfoPage = PageGenerator.getMyInfoPage(driver);
		
		log.info("Add_New_01 - Step 12: Open 'Employee List' Page");
		dashboardPage.openSubMenuPage(driver, "PIM", "Employee List");
		employeeListPage = PageGenerator.getEmployeeListPage(driver);
		
		log.info("Add_New_01 - Step 13: Enter valid infor to 'Employee Name' textbox");
		verifyTrue(employeeListPage.isJQueryAjaxLoadedSuccess(driver));
		employeeListPage.enterToTextboxByID(driver, "empsearch_employee_name_empName", empFullname);
		verifyTrue(employeeListPage.isJQueryAjaxLoadedSuccess(driver));
		
		log.info("Add_New_01 - Step 14: Click to 'Search' button");
		verifyTrue(employeeListPage.isJQueryAjaxLoadedSuccess(driver));
		employeeListPage.clickToButtonByID(driver, "searchBtn");
		
		log.info("Add_New_01 - Step 15: Verify Employee Information displayed at 'Result Table'");
		verifyEquals(employeeListPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "resultTable", "Id", "1"), employeeID);
		verifyEquals(employeeListPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "resultTable", "First (& Middle) Name", "1"), empFirstname);
		verifyEquals(employeeListPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "resultTable", "Last Name", "1"), empLastname);
	}
	
	@Test 
	public void Employee_02_Upload_Avatar() { 
		log.info("Upload_Avatar_01 - Step 01: Login with Employee role");
		loginPage = employeeListPage.logoutToSystem(driver);
		
		dashboardPage = loginPage.loginToSystem(driver, empUsername, empPassword);
		
		log.info("Upload_Avatar_01 - Step 02: Open 'Personal Detail' Page");
		dashboardPage.openMenuPage(driver, "My Info");
		myInfoPage = PageGenerator.getMyInfoPage(driver);
		
		log.info("Upload_Avatar_01 - Step 03: Click to 'Change Photo' image");
		myInfoPage.clickToChangePhotoImage();
		
		log.info("Upload_Avatar_01 - Step 04: Upload new Avatar image");
		myInfoPage.uploadImage(driver, avatarFilePath);
		
		log.info("Upload_Avatar_01 - Step 05: Click to Upload button");
		myInfoPage.clickToButtonByID(driver, "btnSave");
		
		log.info("Upload_Avatar_01 - Step 06: Verify Success message is displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Uploaded"));
		
		log.info("Upload_Avatar_01 - Step 07: Verify new Avatar image is displayed");
		verifyTrue(myInfoPage.isNewAvatarImageDisplayed());
		
	}
	
	@Test 
	public void Employee_03_Personal_Details() { 
		log.info("Personal_Details_03 - Step 01: Open 'Personal Details' tab at Side Bar");
		myInfoPage.openTabAtSideBarByName("Personal Details");
		
		log.info("Personal_Details_03 - Step 02: Verify all fileds at 'Personal Details' tab is disabled");
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "personal_txtEmpFirstName"));
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "personal_txtEmpLastName"));
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "personal_txtEmployeeId"));
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "personal_txtLicenNo"));
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "personal_txtNICNo"));
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "personal_txtSINNo"));
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "personal_optGender_1"));
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "personal_optGender_2"));
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "personal_cmbMarital"));
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "personal_cmbNation"));
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "personal_DOB"));
		
		log.info("Personal_Details_03 - Step 03: Click to Edit at 'Personal Details' form");
		myInfoPage.clickToButtonByID(driver, "btnSave");
		
		log.info("Personal_Details_03 - Step 04: Verify 'Employee ID' textbox is disable");
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "personal_txtEmployeeId"));
		
		log.info("Personal_Details_03 - Step 05: Verify 'Driver's License Number' textbox is disable");
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "personal_txtLicenNo"));
		
		log.info("Personal_Details_03 - Step 06: Verify 'SSN Number' textbox is disable");
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "personal_txtNICNo"));
		
		log.info("Personal_Details_03 - Step 07: Verify 'SIN Number' textbox is disable");
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "personal_txtSINNo"));
		
		log.info("Personal_Details_03 - Step 08: Verify 'Date of Birth' textbox is disable");
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "personal_DOB"));
		
		log.info("Personal_Details_03 - Step 09: Enter new value to 'Frist name' textbox");
		myInfoPage.enterToTextboxByID(driver, "personal_txtEmpFirstName", editEmpFirstName);
		
		log.info("Personal_Details_03 - Step 10: Enter new value to 'Last name' textbox");
		myInfoPage.enterToTextboxByID(driver, "personal_txtEmpLastName", editEmpLastName);
		
		log.info("Personal_Details_03 - Step 11: Select new value to 'Gender' radio button");
		myInfoPage.clickToRadioByLabel(driver, editEmpGender);
		
		log.info("Personal_Details_03 - Step 12: Select new value to 'Martial Status' dropdown");
		myInfoPage.selectItemInDropdownByID(driver, "personal_cmbMarital", editEmpMaritalStatus);
		
		log.info("Personal_Details_03 - Step 13: Select new value to 'Nationality' dropdown");
		myInfoPage.selectItemInDropdownByID(driver, "personal_cmbNation", editEmpNationality);

		log.info("Personal_Details_03 - Step 14: Click to Save button at 'Personal Details' form");
		myInfoPage.clickToButtonByID(driver, "btnSave");
		
		log.info("Personal_Details_03 - Step 15: Verify Success message is displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));
		
		log.info("Personal_Details_03 - Step 16: Verify 'First name' textbox is updated success");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "personal_txtEmpFirstName"),editEmpFirstName);
		
		log.info("Personal_Details_03 - Step 17: Verify 'Last name' textbox is updated success");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "personal_txtEmpLastName"),editEmpLastName);
		
		log.info("Personal_Details_03 - Step 18: Verify 'Gender' radio button is updated success");
		verifyTrue(myInfoPage.isRadioButtonSelectedByLabel(driver, editEmpGender));
		
		log.info("Personal_Details_03 - Step 19: Verify 'Martial Status' dropdown is updated success");
		verifyEquals(myInfoPage.getSelectedValueInDropdownByID(driver, "personal_cmbMarital"),editEmpMaritalStatus);
		
		log.info("Personal_Details_03 - Step 20: Verify 'Nationality' dropdown is updated success");
		verifyEquals(myInfoPage.getSelectedValueInDropdownByID(driver, "personal_cmbNation"),editEmpNationality);

		log.info("Personal_Details_03 - Step 21: Verify 'EmployeeID' textbox value is correct");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "personal_txtEmployeeId"),employeeID);
	}
	
	@Test 
	public void Employee_04_Contact_Details() { 
		log.info("Contact_Details_04 - Step 01: Open 'Contact Details' tab at Side Bar");
		myInfoPage.openTabAtSideBarByName("Contact Details");
		
		log.info("Contact_Details_04 - Step 02: Verify all fileds at 'Contact Details' form is disabled");
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "contact_street1"));
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "contact_street2"));
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "contact_city"));
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "contact_province"));
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "contact_emp_zipcode"));
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "contact_country"));
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "contact_emp_hm_telephone"));
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "contact_emp_mobile"));
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "contact_emp_work_telephone"));
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "contact_emp_work_email"));
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "contact_emp_oth_email"));
		
		log.info("Contact_Details_04 - Step 03: Click to Edit at 'Contact Details' form");
		myInfoPage.clickToButtonByID(driver, "btnSave");
	
		log.info("Contact_Details_04 - Step 04: Enter new value to 'Address Street 1' textbox");
		myInfoPage.enterToTextboxByID(driver, "contact_street1", editEmpAddStr1);
		
		log.info("Contact_Details_04 - Step 05: Enter new value to 'Address Street 2' textbox");
		myInfoPage.enterToTextboxByID(driver, "contact_street2", editEmpAddStr2);

		log.info("Contact_Details_04 - Step 06: Enter new value to 'City' textbox");
		myInfoPage.enterToTextboxByID(driver, "contact_city", editEmpCity);
		
		log.info("Contact_Details_04 - Step 07: Enter new value to 'State/Province' textbox");
		myInfoPage.enterToTextboxByID(driver, "contact_province", editEmpProvince);

		log.info("Contact_Details_04 - Step 08: Enter new value to 'Zip/Postal Code' textbox");
		myInfoPage.enterToTextboxByID(driver, "contact_emp_zipcode", editEmpZip);
		
		log.info("Contact_Details_04 - Step 09: Select new value to 'Country' textbox");
		myInfoPage.selectItemInDropdownByID(driver, "contact_country", editEmpCountry);
		
		log.info("Contact_Details_04 - Step 10: Enter new value to 'Home Telephone' textbox");
		myInfoPage.enterToTextboxByID(driver, "contact_emp_hm_telephone", editEmpHomeTel);
		
		log.info("Contact_Details_04 - Step 11: Enter new value to 'Mobile' textbox");
		myInfoPage.enterToTextboxByID(driver, "contact_emp_mobile", editEmpMobile);

		log.info("Contact_Details_04 - Step 12: Enter new value to 'Work Telephone' textbox");
		myInfoPage.enterToTextboxByID(driver, "contact_emp_work_telephone", editEmpWorkTel);

		log.info("Contact_Details_04 - Step 13: Enter new value to 'Work Email' textbox");
		myInfoPage.enterToTextboxByID(driver, "contact_emp_work_email", editEmpWorkMail);

		log.info("Contact_Details_04 - Step 14: Enter new value to 'Other Email' textbox");
		myInfoPage.enterToTextboxByID(driver, "contact_emp_oth_email", editEmpOtherMail);

		log.info("Contact_Details_04 - Step 15: Click to Save at 'Contact Details' form");
		myInfoPage.clickToButtonByID(driver, "btnSave");
		
		log.info("Contact_Details_04 - Step 16: Verify Success message is displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));
		
		log.info("Contact_Details_04 - Step 17: Verify 'Address Street 1' textbox is updated success");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_street1"),editEmpAddStr1);
		
		log.info("Contact_Details_04 - Step 18: Verify 'Address Street 2' textbox is updated success");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_street2"),editEmpAddStr2);
		
		log.info("Contact_Details_04 - Step 19: Verify 'City' textbox is updated success");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_city"),editEmpCity);
		
		log.info("Contact_Details_04 - Step 20: Verify 'State/Province' textbox is updated success");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_province"),editEmpProvince);
		
		log.info("Contact_Details_04 - Step 21: Verify 'Zip/Postal Code' textbox is updated success");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_emp_zipcode"),editEmpZip);
		
		log.info("Contact_Details_04 - Step 22: Verify 'Country' checkbox is updated success");
		verifyEquals(myInfoPage.getSelectedValueInDropdownByID(driver, "contact_country"),editEmpCountry);
		
		log.info("Contact_Details_04 - Step 23: Verify 'Home Telephone' textbox is updated success");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_emp_hm_telephone"),editEmpHomeTel);
		
		log.info("Contact_Details_04 - Step 24: Verify 'Mobile' textbox is updated success");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_emp_mobile"),editEmpMobile);
		
		log.info("Contact_Details_04 - Step 25: Verify 'Work Telephone' textbox is updated success");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_emp_work_telephone"),editEmpWorkTel);
		
		log.info("Contact_Details_04 - Step 26: Verify 'Work Email' textbox is updated success");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_emp_work_email"),editEmpWorkMail);
		
		log.info("Contact_Details_04 - Step 27: Verify 'Other Email' textbox is updated success");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_emp_oth_email"),editEmpOtherMail);
	}
	
	@Test 
	public void Employee_05_Emergency_Details() { 
		log.info("Emergency_Details_05 - Step 01: Open 'Emergency Contacts' tab at Side Bar");
		myInfoPage.openTabAtSideBarByName("Emergency Contacts");
		
		log.info("Emergency_Details_05 - Step 02: Click to 'Add' Emergency Contacts");
		myInfoPage.clickToButtonByID(driver, "btnAddContact");
		
		log.info("Emergency_Details_05 - Step 03: Enter to 'Name' Emergency Contacts");
		myInfoPage.enterToTextboxByID(driver, "emgcontacts_name", editNameEmgContact);
		
		log.info("Emergency_Details_05 - Step 04: Enter to 'Relationship' Emergency Contacts");
		myInfoPage.enterToTextboxByID(driver, "emgcontacts_relationship", editRelationshipEmgContact);
		
		log.info("Emergency_Details_05 - Step 05: Enter to 'Home Telephone' Emergency Contacts");
		myInfoPage.enterToTextboxByID(driver, "emgcontacts_homePhone", editHomeTelEmgContact);
		
		log.info("Emergency_Details_05 - Step 06: Click to 'Save' Emergency Contacts");
		myInfoPage.clickToButtonByID(driver, "btnSaveEContact");
		
		log.info("Emergency_Details_05 - Step 07: Verify Success message is displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));
		
		log.info("Emergency_Details_05 - Step 08: Verify Emergency Contacts Information displayed at 'Result Table'");
		verifyEquals(myInfoPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "emgcontact_list", "Name", "1"), editNameEmgContact);
		verifyEquals(myInfoPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "emgcontact_list", "Relationship", "1"), editRelationshipEmgContact);
		verifyEquals(myInfoPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "emgcontact_list", "Home Telephone", "1"), editHomeTelEmgContact);
		
	}
	
	@Test 
	public void Employee_06_Assigned_Dependents() { 
		log.info("Assigned_Dependents_06 - Step 01: Open 'Dependent' tab at Side Bar");
		myInfoPage.openTabAtSideBarByName("Dependents");
		
		log.info("Assigned_Dependents_06 - Step 02: Click to 'Add' button in Dependent");
		myInfoPage.clickToButtonByID(driver, "btnAddDependent");
		
		log.info("Assigned_Dependents_06 - Step 03: Enter to 'Name' Dependent");
		myInfoPage.enterToTextboxByID(driver, "dependent_name", editNameDependent);
		
		log.info("Assigned_Dependents_06 - Step 04: Select to 'Relationship' Dependent");
		myInfoPage.selectItemInDropdownByID(driver, "dependent_relationshipType", editRelationshipDependent);
		
		log.info("Assigned_Dependents_06 - Step 05: Enter to 'DOB' Dependent");
		myInfoPage.enterToTextboxByID(driver, "dependent_dateOfBirth", editDOBDependent);
		
		log.info("Assigned_Dependents_06 - Step 06: Click to 'Save' button in Dependent");
		myInfoPage.clickToButtonByID(driver, "btnSaveDependent");
		
		log.info("Assigned_Dependents_06 - Step 07: Verify Success message is displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));
		
		log.info("Assigned_Dependents_06 - Step 08: Verify Dependent Contacts Information displayed at 'Result Table'");
		verifyEquals(myInfoPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dependent_list", "Name", "1"), editNameDependent);
		verifyEquals(myInfoPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dependent_list", "Relationship", "1"), editRelationshipDependent.toLowerCase());
		verifyEquals(myInfoPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dependent_list", "Date of Birth", "1"), editDOBDependent);
		
		
	}
	
	@Test 
	public void Employee_07_Edit_View_Job() { 
		//Verify Job disable -> Login with Admin account -> Edit Job -> Login with personal account -> Verify data updated
	
	}
	
	@Test 
	public void Employee_08_Edit_View_Salary() { 
		//Verify Salary disable -> Login with Admin account -> Edit Salary -> Login with personal account -> Verify data updated

	}
	
	@Test 
	public void Employee_09_Edit_View_Tax() { 
		//Verify Tax disable -> Login with Admin account -> Edit Tax -> Login with personal account -> Verify data updated

	}
	
	@Test 
	public void Employee_10_Qualifications() { 
//		log.info("Qualifications_10 - Step 01: Open 'Qualifications' tab at Side Bar");
//		myInfoPage.openTabAtSideBarByName("Qualifications");
		
		//Add Work Experience : Add ->Edit->Verify Success Message -> Verify Updated Value
		//Add Education
		//Add Skills
		//Add Languages
		//Add License
		//Add Attachments
		
	}
	
	@Test 
	public void Employee_11_Search_Employee() { 

	}
	
	
	@AfterClass (alwaysRun= true)
	public void afterClass() {
		closeBrowserAndDriver();
	}
}
