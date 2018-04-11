package com.selenium.test.page;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.annotations.Test;

public class GoalsPage {
	@FindBy(xpath = "//*[@id=\'use-chickasaw-keyboard\']/div[1]/div[2]/div/ul/a[2]")
	protected WebElement lnkGoals;
		
	@FindBy(xpath = "//*[@id=\'use-chickasaw-keyboard\']/div[1]/div[3]/div/div/div/div[2]/ul/li[1]/ul/a[1]/div[2]")
	protected WebElement lnkManageData;


	private WebDriver driver;
	public GoalsPage(WebDriver objDriver) {
		this.driver=objDriver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	public void clickGoals() {
		lnkGoals.click();
	}
	
	public void clickManageData() {
		lnkManageData.click();
	}
	
}
