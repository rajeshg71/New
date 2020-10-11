package com.qa.demoshop.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.qa.demoshop.base.TestBase;
import com.qa.demoshop.util.TestUtil;

public class BillingAddressPage extends TestBase{
	
	@FindBy(xpath="//select[@name='billing_address_id']")
	WebElement dropDown;
	
	@FindBy(xpath="//select[@name='BillingNewAddress.CountryId']")
	WebElement countryDropDown;
	
	
	@FindBy(xpath="//input[@name='BillingNewAddress.City']")
	WebElement cityTextBox;
	
	@FindBy(xpath="//input[@name='BillingNewAddress.Address1']")
	WebElement addressTextBox;
	
	@FindBy(xpath="//input[@name='BillingNewAddress.ZipPostalCode']")
	WebElement zipTextBox;
	
	@FindBy(xpath="//input[@name='BillingNewAddress.PhoneNumber']")
	WebElement mobileTextBox;

	@FindBy(xpath="//input[@onclick='Billing.save()']")
	WebElement continueButton;

	@FindBy(xpath="//input[@onclick='Shipping.save()']")
	WebElement shipcontinueButton;
	
	@FindBy(xpath="//input[@id='shippingoption_1']")
	WebElement nextDayAirCheckBox;
	
	
	@FindBy(xpath="//input[@onclick='ShippingMethod.save()']")
	WebElement shippingMethodSave;
	
	@FindBy(xpath="//input[@onclick='PaymentMethod.save()']")
	WebElement paymentContinueBtn;
	
	@FindBy(xpath="//p[contains(text(),'You will pay by COD')]")
	WebElement codConfirmation;
	
	@FindBy(xpath="//input[@onclick='PaymentInfo.save()']")
	WebElement paymentInfoCntBtn;
	
	@FindBy(xpath="//input[@value='Confirm']")
	WebElement confirmOrderBtn;
	
	@FindBy(xpath="//strong[contains(text(),'Your order has been successfully processed!')]")
	WebElement confirmOrderSuccess;
	
	@FindBy(xpath="//li[contains(text(),'Order number:')]")
	WebElement orderNumber;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement continueOrder;
	
	@FindBy(xpath="//a[@class='ico-logout']")
	WebElement logout;
	
	 WebDriverWait wait=new WebDriverWait(driver, 40);
	 /*Webdriver is to wait untill the element to be clickable*/
	 
	public BillingAddressPage() {
	PageFactory.initElements(driver, this);}
	
	public void dropDownSelect() {
		Select selectDropDown = new Select(dropDown);
		selectDropDown.selectByVisibleText("New Address");
		Select selectCountryDropDown = new Select(countryDropDown);
		selectCountryDropDown.selectByVisibleText("India");
		cityTextBox.sendKeys("Hyd");
		addressTextBox.sendKeys("Telangana");
		zipTextBox.sendKeys("521344");
		mobileTextBox.sendKeys("1234567890");
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)","");
		continueButton.click();
	}
	public void continueToShop() {
		wait.until(ExpectedConditions.elementToBeClickable(shipcontinueButton));
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		js.executeScript("window.scrollBy(0,1000)","");
		shipcontinueButton.click();
		nextDayAirCheckBox.click();
		shippingMethodSave.click();
		System.out.println("Completed shipping method");
	}
	public void paymentMethod() {
		wait.until(ExpectedConditions.elementToBeClickable(paymentContinueBtn));
		paymentContinueBtn.click();
		String cod = codConfirmation.getText();
		Assert.assertEquals(cod, "You will pay by COD");
		System.out.println("Completed Pay by COD");
		paymentInfoCntBtn.click();
	}
	public void confirmOrder() {
		wait.until(ExpectedConditions.elementToBeClickable(confirmOrderBtn));
		confirmOrderBtn.click();
		String orderSuccess = confirmOrderSuccess.getText();
		Assert.assertEquals(orderSuccess, "Your order has been successfully processed!");
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		String orderNo = orderNumber.getText();
		System.out.println(orderNo);
		wait.until(ExpectedConditions.elementToBeClickable(continueOrder));
		continueOrder.click();
	}
	public void logout() {
		logout.click();
		
	}
}
