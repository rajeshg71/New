package com.qa.demoshop.testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.demoshop.base.TestBase;
import com.qa.demoshop.pages.AddBookPage;
import com.qa.demoshop.pages.BillingAddressPage;
import com.qa.demoshop.pages.LoginPage;
import com.qa.demoshop.pages.ShoppingCartPage;
import com.qa.demoshop.util.TestUtil;

public class TestCasesExecution extends TestBase{
	LoginPage loginPage;
	AddBookPage addBook;
	ShoppingCartPage shopCart;
	BillingAddressPage billingAdPage;
	
	@BeforeClass
	public void setUp(){
		initialization();
		loginPage = new LoginPage();
		addBook = new AddBookPage();
		shopCart = new ShoppingCartPage();
		billingAdPage = new BillingAddressPage();
	}
	@Test(priority=1)
	public void loginPage() {
		loginPage.ClickLoginBtn();
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "Welcome, Please Sign In!");
		loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		String verifyuser = loginPage.verifyUser();
		Assert.assertEquals(verifyuser, "atest@gmail.com");
		System.out.println(verifyuser);
	}
	@Test(priority=2)
	public void clickOnBook() {
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		addBook.clickOnBook();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		addBook.clickOnBookImg();
		String getbookprice = addBook.getPriceDetails();
		System.out.println(getbookprice);
		addBook.enterQuantity(prop.getProperty("quantity"));
		
	}
	@Test(priority=3)
	public void clickOnAddToCart() {
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		addBook.clickOnAddToCart();
	}
	@Test(priority=4)
	public void validateProduct() {
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		String validateProduct = addBook.validateProductCart();
		System.out.println(validateProduct);
		Assert.assertEquals(validateProduct, "The product has been added to your shopping cart");
	}
	@Test(priority=5)
	public void shopCart() {
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		js.executeScript("window.scrollBy(0,-1000)");
		shopCart.clickOnShoppingCart();
		shopCart.SubTotal();
		shopCart.checkBox();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
	}
	@Test(priority=6)
	public void dropDownAddress() {
		billingAdPage.dropDownSelect();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		billingAdPage.continueToShop();
		billingAdPage.paymentMethod();
		billingAdPage.confirmOrder();
		System.out.println("Completed Order Billing and Shipping");
	}
	@AfterClass
	public void tearDown(){
		billingAdPage.logout();
		System.out.println("Completed Logout");
		driver.quit();
	}

}
