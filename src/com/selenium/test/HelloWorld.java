package com.selenium.test;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.*;
import com.fasterxml.jackson.core.json.*;

public class HelloWorld {
	
	public static void main(String[] args) {
		//System.setProperty("webdriver.firefox.bin", "C:/Program Files (x86)/Mozilla Firefox/firefox.exe");
		
		//WebDriver driver = new org.openqa.selenium.firefox.FirefoxDriver();


        //Çý¶¯µÄÍøÖ·
        //driver.get("http://www.baidu.com/");
		
		try {
			  
			JsonNode node;
			 ObjectMapper mapper=new ObjectMapper();
			  	
			  	
			  	String jsonpath="C:/Users/alans/git/alantest/src/configuration/";
			  	String jsonname="alantest.json";
			  	System.out.println(jsonpath + jsonname);
			  	
			  	//C:\Users\sheny\git\alantest\src\configuration\
			  	//mapper.writeValue(new File(jsonpath + jsonname), json);
			  	//mapper.readValue("\""+jsonpath + jsonname+"\"", JsonNode.class);
			  	//node=mapper.readTree(new File(jsonpath+jsonname));
			  	node =mapper.readValue(new File(jsonpath+jsonname), JsonNode.class);
			  	
			  	System.out.println(node.get("url").toString().replaceAll("\"", "")); 
			  }
			  catch(Exception e) {
				  System.out.println(e);
				  
			  }

	}
}