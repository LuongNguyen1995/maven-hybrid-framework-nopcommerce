package pageUIs.liveTechpanda.user;

public class MyAccountPageUI {

	//SUCCESS_MESSAGE = Thank you for registering with Main Website Store.
	public static final String SUCCESS_MESSAGE = "xpath=//li[@class='success-msg']//span";
	public static final String TITLE_MY_DASHBOARD = "xpath=//div[@class='page-title']/h1";
	//"Hello, "+firstName+" "+lastName+"!"
	public static final String HELLO_USER_DISPLAY ="xpath=//p[@class='hello']/strong";
	//get infor (contains)
	public static final String CONTACT_INFORMATION_DISPLAY ="xpath=//h3[text()='Contact Information']/parent::div/following-sibling::div/p";
	
	public static final String ACCOUNT_LINK = "xpath=//header//span[text()='Account']";
	public static final String LOG_OUT_LINK = "xpath=//header//a[text()='Log Out']";
}
