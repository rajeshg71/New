package com.qa.demoshop.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.demoshop.base.TestBase;

public class AddBookPage extends TestBase{
	
	@FindBy(xpath="//li[@class='inactive']//a[contains(text(),'Books')]")
	WebElement book;
	
	
	@FindBy(xpath="//h2//a[text()='Computing and Internet']")
	WebElement bookImg;	
	
	@FindBy(xpath="//span[@class='price-value-13']")
	WebElement getPrice; 
		
	@FindBy(xpath="//input[@name='addtocart_13.EnteredQuantity']")
	WebElement enterQuantity;
	
	@FindBy(xpath="//input[@class='button-1 add-to-cart-button']")
	WebElement clickOnAddToCart;
	
	@FindBy(xpath="//p[@class='content']")
	WebElement validateAddProduct;
	
	public AddBookPage(){
		PageFactory.initElements(driver, this);
	}
	public void clickOnBook() {
		book.click();
	}
	public void clickOnBookImg() {
		bookImg.click();
	}
	public String getPriceDetails() {
		return getPrice.getText();
	}
	public void enterQuantity(String qnty) {
		enterQuantity.clear();
		enterQuantity.sendKeys(qnty);
	}
	public void clickOnAddToCart() {
		clickOnAddToCart.click();
	}
	public String validateProductCart() {
		return validateAddProduct.getText();
	}
}
