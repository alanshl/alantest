package com.selenium.test.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;

public class SignInPage {

@FindBy(xpath = "//*[@id=\'page-top\']/div/div/div[1]/div/div/form/div[1]/div[2]/div[2]/input")
protected WebElement txtUsername;
	
@FindBy(xpath = "//*[@id=\'page-top\']/div/div/div[1]/div/div/form/div[1]/div[3]/div[2]/input")
protected WebElement txtPassword;

@FindBy(xpath = "//*[@id=\'page-top\']/div/div/div[1]/div/div/form/div[2]/button/span")
protected WebElement btnSignin;

private WebDriver driver;
public SignInPage(WebDriver objDriver) {
	this.driver=objDriver;
	PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
}
public void inputUsername(String strUser) {
	txtUsername.sendKeys(strUser);
}

public void inputPassword(String strPwd) {
	txtPassword.sendKeys(strPwd);
}

public void clickSignIn() {
	btnSignin.click();
}

public void SignIn(String strUser, String strPwd) {
	this.inputUsername(strUser);
	this.inputPassword(strPwd);
	this.clickSignIn();
}
/*
public boolean isElementDisplay() {
	try {
		
	}
	catch(Exception e) {
		
	}
	finally {
		WebDriverWait wait=new WebDriverWait(driver, 10, 2000);
		wait.until(new ExpectedCondition<Boolean>()){
			public boolean apply() {
				System.out.println("sleep");
				return !txtUsername.isDisplayed();
			}
		}
		
	}
}
*/
}
