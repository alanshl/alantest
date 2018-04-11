package com.selenium.test;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.*;

public class HelloWorld {
	
	public static void main(String[] args) {
		System.setProperty("webdriver.firefox.bin", "C:/Program Files (x86)/Mozilla Firefox/firefox.exe");
		
		WebDriver driver = new org.openqa.selenium.firefox.FirefoxDriver();


        //Çý¶¯µÄÍøÖ·
        driver.get("http://www.baidu.com/");
	}

}
