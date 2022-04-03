package pageObject.sauceLab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.sauceLab.InventoryPageUI;

public class InventoryPageObject extends BasePage{

	WebDriver driver;
	
	public InventoryPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void selectItemInSortDropDown(String itemText) {
		waitForElementClickable(driver, InventoryPageUI.SORT_DROPDOWN);
		selectItemInDefaultDropdown(driver, InventoryPageUI.SORT_DROPDOWN, itemText);
		
	}

	public boolean isProductNameSortAscending() {
		List<WebElement> productNameElements = getListWebElement(driver, InventoryPageUI.PRODUCT_NAME_TEXT);
		
		List<String> productNameText = new ArrayList<String>();
		
		for (WebElement productName : productNameElements) {
			productNameText.add(productName.getText());
		}
		System.out.println("Before sort ascending : ---------");
		for (String product : productNameText) {
			System.out.println(product);
		}
		
		List<String> productNameTextClone = new ArrayList<String>();
		for (String productName : productNameText) {
			productNameTextClone.add(productName);
		}
		
		Collections.sort(productNameTextClone);
		
		System.out.println("After sort ascending : ---------");
		for (String product : productNameTextClone) {
			System.out.println(product);
		}
		return productNameText.equals(productNameTextClone);
	}

	public boolean isProductNameSortDescending() {
		List<WebElement> productNameElements = getListWebElement(driver, InventoryPageUI.PRODUCT_NAME_TEXT);
		
		List<String> productNameText = new ArrayList<String>();
		
		for (WebElement productName : productNameElements) {
			productNameText.add(productName.getText());
		}
		System.out.println("Before sort decsending : ---------");
		for (String product : productNameText) {
			System.out.println(product);
		}
		
		List<String> productNameTextClone = new ArrayList<String>();
		for (String productName : productNameText) {
			productNameTextClone.add(productName);
		}
		
		Collections.sort(productNameTextClone);
		Collections.reverse(productNameTextClone);
		
		System.out.println("After sort decsending : ---------");
		for (String product : productNameTextClone) {
			System.out.println(product);
		}
		return productNameText.equals(productNameTextClone);
	}

	public boolean isProductPriceSortAscending() {
		List<WebElement> productPriceElements = getListWebElement(driver, InventoryPageUI.PRODUCT_PRICE_TEXT);
		
		List<Float> productNamePrice = new ArrayList<Float>();
		
		for (WebElement productPrice : productPriceElements) {
			productNamePrice.add(Float.parseFloat(productPrice.getText().replace("$", "")));
		}
		
		System.out.println("Before sort ascending : ---------");
		for (Float productPrice : productNamePrice) {
			System.out.println(productPrice);
		}
		
		List<Float> productPriceTextClone = new ArrayList<Float>();
		for (Float productPrice : productNamePrice) {
			productPriceTextClone.add(productPrice);
		}
		
		Collections.sort(productPriceTextClone);
		
		System.out.println("After sort ascending : ---------");
		for (Float productPrice : productPriceTextClone) {
			System.out.println(productPrice);
		}
		return productNamePrice.equals(productPriceTextClone);
	}

	public boolean isProductPriceSortDescending() {
		List<WebElement> productPriceElements = getListWebElement(driver, InventoryPageUI.PRODUCT_PRICE_TEXT);
		
		List<Float> productNamePrice = new ArrayList<Float>();
		
		for (WebElement productPrice : productPriceElements) {
			productNamePrice.add(Float.parseFloat(productPrice.getText().replace("$", "")));
		}
		
		System.out.println("Before sort descending : ---------");
		for (Float productPrice : productNamePrice) {
			System.out.println(productPrice);
		}
		
		List<Float> productPriceTextClone = new ArrayList<Float>();
		for (Float productPrice : productNamePrice) {
			productPriceTextClone.add(productPrice);
		}
		
		Collections.sort(productPriceTextClone);
		Collections.reverse(productPriceTextClone);
		System.out.println("After sort descending : ---------");
		for (Float productPrice : productPriceTextClone) {
			System.out.println(productPrice);
		}
		return productNamePrice.equals(productPriceTextClone);
	}
}
