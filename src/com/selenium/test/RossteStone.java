package com.selenium.test;

import org.testng.annotations.Test;

import com.selenium.test.page.GoalsPage;
import com.selenium.test.page.SignInPage;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.*;
import com.fasterxml.jackson.core.json.*;


public class RossteStone {
	
private JsonNode node;
	
  @Test(dataProvider = "dp")
  public void f(Integer n, String s) {
	  
	  WebDriver driver = new org.openqa.selenium.firefox.FirefoxDriver();
	  driver.get(getProperty("url"));
	  //driver.manage().window().maximize();
	  //driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	  
	  
	  try {
		Thread.sleep(10000);
		//WebElement txtUsername=driver.findElement(By.xpath("//*[@id=\'page-top\']/div/div/div[1]/div/div/form/div[1]/div[2]/div[2]/input"));
		String strUsername=getProperty("user");
		  //txtUsername.sendKeys(strUsername);
		String strPwd=getProperty("password");
		SignInPage pgSignIn=new SignInPage(driver);
		pgSignIn.inputUsername(strUsername);
		Thread.sleep(1000);
		pgSignIn.inputPassword(strPwd);
		Thread.sleep(1000);
	    pgSignIn.clickSignIn();
		
		Thread.sleep(10000);
		GoalsPage pgGoals=new GoalsPage(driver);
		pgGoals.clickGoals();
		pgGoals.clickManageData();
		  
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	  
	  
  }
  @BeforeMethod
  public void beforeMethod() {
	  try {
		  
	  
	  User user=new User();
	  user.setname("Alan");
	  user.setEmail("alanshl@163.com");
	  user.setAge(20);
	  SimpleDateFormat dateformat=new SimpleDateFormat("yyyy-mm-dd");
	  
	  user.setBirthday(dateformat.parse("1996-10-01"));
	  
	  ObjectMapper mapper=new ObjectMapper();
	  	String json=mapper.writeValueAsString(user);
	  	System.out.println(json);
	  	
	  	List<User> users=new ArrayList<User>();
	  	users.add(user);
	  	String jsonlist=mapper.writeValueAsString(users);
	  	System.out.println(jsonlist);
	  	
	  	System.out.println(this.getClass().getClassLoader().getResource("").getPath());
	  	String jsonpath=this.getClass().getResource("").getPath() + "configurtion/";
	  	jsonpath="C:\\Users\\HUILINGAlanShen\\eclipse-workspace\\AlanTest\\src\\configuration\\";
	  	String jsonname="alantest.json";
	  	System.out.println(jsonpath + jsonname);
	  	jsonpath="C:/Users/HUILINGAlanShen/eclipse-workspace/AlanTest/src/configuration/";
	  	//mapper.writeValue(new File(jsonpath + jsonname), json);
	  	mapper.readValue("\""+jsonpath + jsonname+"\"", json.getClass());
	  	node=mapper.readTree(new File(jsonpath+jsonname));
	  	
	  	System.out.println(node.get("url").toString().replaceAll("\"", "")); 
	  }
	  catch(Exception e) {
		  System.out.println(e);
		  //test
	  }
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
  @BeforeClass
  public void beforeClass() {
  }

  @AfterClass
  public void afterClass() {
  }

  @BeforeTest
  public void beforeTest() {
	  System.setProperty("webdriver.firefox.bin", "C:/Program Files (x86)/Mozilla Firefox/firefox.exe");
  }

  @AfterTest
  public void afterTest() {
	  
  }

  @BeforeSuite
  public void beforeSuite() {
  }

  @AfterSuite
  public void afterSuite() {
  }
  
  private boolean isElementPresent(WebDriver driver, By by) {
	  boolean result=false;
	  try {
		  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	      driver.findElement(by);
		  result=true;
	  }
	  catch (NoSuchElementException e){
		  result=false;
	  }
	  finally {
		  return result;
	  }
	  
  }
  
  public String getProperty(String name) {
	  return node.get(name).toString().replaceAll("\"", "");
  }

}


