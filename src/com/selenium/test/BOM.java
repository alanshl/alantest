package com.selenium.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BOM {
  @Test(dataProvider = "dp")
  public void f(Integer n, String s) {
	  //WebDriver driver = new org.openqa.selenium.firefox.FirefoxDriver();
	  String path="C:\\software\\chromedriver_win32\\chromedriver.exe";
	  //WebDriver driver= new org.openqa.selenium.chrome.ChromeDriver(path,9515);
	  //new org.openqa.selenium.chrome.
	  //driver.get("http://192.168.1.33:8080/static/index.html#");
	  try {
		  ChromeDriverService service = new ChromeDriverService.Builder()
				  .usingDriverExecutable(new File("C:\\software\\chromedriver_win32\\chromedriver.exe"))
				  .usingAnyFreePort()
				  .build();
		  service.start();
		  
		  WebDriver driver=new RemoteWebDriver(service.getUrl(), DesiredCapabilities.chrome());
		  
		  driver.get("http://192.168.1.61:8080/static/index.html#");
		  driver.manage().window().maximize();
		  
		  Thread.sleep(10000);
		  
		  WebElement txtUser=driver.findElement(By.xpath("//*[@id='username-inputEl']"));
		  txtUser.sendKeys("shenhl");
		  
		  WebElement txtPwd=driver.findElement(By.xpath("//*[@id='password-inputEl']"));
		  txtPwd.sendKeys("123456");
		  
		  WebElement btnLogin=driver.findElement(By.xpath("//*[@id=\"button-1020\"]"));
		  btnLogin.click();
		  
		  Thread.sleep(5000);
		  WebElement mnuPartName=driver.findElement(By.xpath("//*[@id=\"shortcutdataview\"]/ul/div/li[5]"));
		  mnuPartName.click();
		  System.out.println("==Open part name maintanence page");
		  
		  Thread.sleep(2000);
		  //WebElement btnModify=driver.findElement(By.xpath("//*[@id=\"button-1265-btnInnerEl\"]"));
		  //btnModify.click();
		  //List<WebElement> btnList=driver.findElements(By.className("x-btn-inner x-btn-inner-center"));
		  List<WebElement> elementList=driver.findElements(By.xpath("//*[contains(@id,'btnInnerEl')]"));
		  //List<WebElement> btnList=driver.findElements(By.xpath("//*[starts-with(@id,'button-')]"));
		  //WebElement btnModify=driver.findElement(By.linkText("进入编辑"));
		  //btnModify.click();
		  WebElement btnModify;
		  //System.out.println("==size:" + elementList.size());
		  int i;
		  for(i=0;i<elementList.size();i++)
				  {
			  /*
			  		System.out.println(i);
			  		System.out.println(btnList.get(i).getAttribute("id"));
			  		System.out.println(btnList.get(i).getAttribute("Class"));
			  		System.out.println(btnList.get(i).getText());
			  		System.out.println("====");*/
				  	if(elementList.get(i).getText().contains("进入编辑"))
				  	{
				  		btnModify=elementList.get(i);
						btnModify.click();
						System.out.println("==Click modification button");
				  		break;
				  	}
				  }
		  
		  WebElement btnAdd;
		  elementList=driver.findElements(By.xpath("//*[contains(@id,'btnInnerEl')]"));
		  for(i=0;i<elementList.size();i++)
		  {
			  /*
	  		System.out.println(i);
	  		System.out.println(btnList.get(i).getAttribute("id"));
	  		System.out.println(btnList.get(i).getAttribute("Class"));
	  		System.out.println(btnList.get(i).getText());
	  		System.out.println("====");*/
		  	if(elementList.get(i).getText().contains("添加"))
		  	{
		  		btnAdd=elementList.get(i);
		  		btnAdd.click();
				System.out.println("==Click add button");
		  		break;
		  	}
		  }
		  //WebElement btnAdd=driver.findElement(By.xpath("//*[@id=\"button-1267-btnInnerEl\"]"));
		  
		  elementList=driver.findElements(By.xpath("//*[contains(@id,'ext-gen')]"));
		  for(i=0;i<elementList.size();i++)
		  {
			  
	  		System.out.println(i);
	  		System.out.println(elementList.get(i).getAttribute("id"));
	  		System.out.println(elementList.get(i).getAttribute("Class"));
	  		System.out.println(elementList.get(i).getText());
	  		System.out.println("====");
	  		/*
		  	if(elementList.get(i).getText().contains("添加"))
		  	{
		  		btnAdd=elementList.get(i);
		  		btnAdd.click();
				System.out.println("==Click add button");
		  		break;
		  	}*/
		  }
		  
		  WebElement inputGroupID=driver.findElement(By.xpath("//*[@id=\"ext-gen1439\"]/div"));
		  inputGroupID.click();
		  Thread.sleep(1000);
		  
		  WebElement txtGroupID=driver.findElement(By.xpath("//*[@id=\"ext-gen1452\"]"));
		  //String js="arguments[0].style.overflow='display'";
		  txtGroupID.sendKeys("522");
		  System.out.println("==Input part group ID");
		  
		  WebElement inputUnitId=driver.findElement(By.xpath("//*[@id=\"ext-gen1440\"]/div"));
		  inputUnitId.sendKeys("001");
		  System.out.println("==Input part unit ID");
		  
		  WebElement inputPartNameCN=driver.findElement(By.xpath("//*[@id=\"ext-gen1442\"]/div"));
		  inputPartNameCN.sendKeys("零件1");
		  System.out.println("==Input part name in Chinese");
		  
		  WebElement inputPartNameEN=driver.findElement(By.xpath("//*[@id=\"ext-gen1443\"]/div"));
		  inputPartNameEN.sendKeys("part1");
		  System.out.println("==Input part name in English");
		  
		  WebElement btnSave=driver.findElement(By.xpath("//*[@id=\"button-1270-btnInnerEl\"]"));
		  btnSave.click();
		  System.out.println("==Click save button");
		  
		  WebElement btnActive=driver.findElement(By.xpath("//*[@id=\"button-1271-btnInnerEl\"]"));
		  btnActive.click();
		  System.out.println("==Click activation button");
		  
		  //driver.quit();
		  //service.stop();
	  }
	  catch(Exception e)
	  {
		  System.out.print(e);
	  }
			  
  }
  
  @BeforeMethod
  public void beforeMethod() {
	  
  }

  @AfterMethod
  public void afterMethod() {
  }


  @DataProvider
  public Object[][] dp() {
    return new Object[][] {
      new Object[] { 1, "a" },
      //new Object[] { 2, "b" },
    };
  }
  @BeforeTest
  public void beforeTest() {
	  //System.setProperty("webdriver.firefox.bin", "C:/Program Files (x86)/Mozilla Firefox/firefox.exe");
	  //System.setProperty("webdriver.chrome.bin", "C:/Program Files (x86)/Google/Chrome/Application/chrome.exe");
  }

  @AfterTest
  public void afterTest() {
  }

}
