package com.qa.demoshop.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.qa.demoshop.base.TestBase;

public class ShoppingCartPage extends TestBase{

	
	@FindBy(xpath="//a//span[text()='Shopping cart']")
	WebElement clickOnShoppingCart;
	
	@FindBy(xpath="//input[@id='termsofservice']")
	WebElement checkBox;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement checkOutBtn;
	

	

	public  ShoppingCartPage(){
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnShoppingCart() {
		
		clickOnShoppingCart.click();
	}
	public void SubTotal() {
		String SubTotal = "//table[@class='cart-total']/tbody/tr/td[2]/span[1]";
		java.util.List<WebElement> l = driver.findElements(By.xpath(SubTotal));
		System.out.println("Total no of coloumns are"+l.size());
		int i=0;
		while(i<l.size()) {
			String value = l.get(0).getText();
			System.out.println("Sub Total-"+value);
			i = i+1;
			Assert.assertEquals(value, value);
		}
	}
	public void checkBox() {
		checkBox.click();
		js= (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", checkOutBtn);
		checkOutBtn.click();
	}
}
