package pageUIs.liveTechpanda.admin;

public class AdminDashboardPageUI {

	public static final String ADMIN_PANEL_TITLE = "xpath=//div[@class='header']//img[@alt='Magento Logo']";
	public static final String TEXTBOX_BY_TITLE = "xpath=//input[@name='%s']";
	
	public static final String SEARCH_BUTTON = "xpath=//table[@class='actions']//span[text()='Search']";
	public static final String INFOR_SEARCH_BY_NAME_AND_EMAIL = "xpath=//td[contains(text(),'%s %s')]/following-sibling::td[contains(text(),'%s')]";
	public static final String CHECKBOX_AT_ROW_EMAIL = "xpath=//td[contains(text(),'%s')]/preceding-sibling::td[contains(@class,'a-center')]/input";
	
	public static final String ACTION_SELECT_DROP_DOWN = "xpath=//select[@id='customerGrid_massaction-select']";
	public static final String SUBMIT_BUTTON = "xpath=//button[@title='Submit']//span[text()='Submit']";
	
	public static final String POPUP_MESSAGE = "xpath=//div[@id='message-popup-window']";
	public static final String CLOSE_POPUP_BUTTON = "xpath=//div[@id='message-popup-window']//span[text()='close']";

	public static final String MESSAGE_CONFIRM_DELETED ="xpath=//li[@class='success-msg']//span[text()='Total of 1 record(s) were deleted.']";
}
