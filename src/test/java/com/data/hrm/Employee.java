package com.data.hrm;

import utilities.DataUtil;

public class Employee {
	private DataUtil fakeData;
	
	
	public Employee(){
		fakeData = DataUtil.getData();
	}
	
	public class Role {
		public static final String ADMIN_USERNAME = "Admin";
		public static final String ADMIN_PASSWORD = "admin123";
	}
	
	public class PersonalDetail {
		public static final String FRISTNAME = "Luong";
		public static final String LASTNAME = "Nguyen";
		public static final String FULLNAME = FRISTNAME + " " + LASTNAME;
		public static final String USERNAME = "luongok123";
		public static final String PASSWORD = "Luong1995";
		
		
		
	}
	
	public class ContactDetail{
		public static final String EDIT_FIRSTNAME = "Luong";
		public static final String EDIT_LASTNAME = "Nguyen";
		
	}
	
	public class Job{
		
	}
	
	public class Salary{
		
	}
	
}
