package com.qa.demoshop.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.demoshop.base.TestBase;
import com.qa.demoshop.util.TestUtil;

public class LoginPage extends TestBase{
	
	AddBookPage addbook;
	
	//Page Factory - OR:
	@FindBy(xpath="//input[@class='email']")
	WebElement username;
	
	@FindBy(xpath="//input[@class='password']")
	WebElement password;
	
	@FindBy(xpath="//a[text()='Log in']")
	WebElement loginBtn;
	
	@FindBy(xpath="//div[@class='page-title']")
	WebElement verifyPageTitle;
	
	@FindBy(xpath="//input[@class='button-1 login-button']")
	WebElement submitBtn;
	
	@FindBy(xpath="//div[@class='header-links']//a[@class='account']")
	WebElement verifyUser;

	
	public LoginPage(){
		PageFactory.initElements(driver, this);
	}
	
	public AddBookPage ClickLoginBtn(){
		loginBtn.click(); 
		return new AddBookPage();
	}
	
	public String validateLoginPageTitle(){
		return verifyPageTitle.getText();
	}
	public AddBookPage login(String un,String pwd) {
		username.sendKeys(un);
		password.sendKeys(pwd);
		submitBtn.click();	
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		return new AddBookPage();
		
	}
	public String verifyUser() {
		return verifyUser.getText();
	}
}