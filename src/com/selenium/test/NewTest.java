package com.selenium.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

public class NewTest {
	@BeforeClass
	 public void setUp() {
	   // code that will be invoked when this test is instantiated
	 }
	 
	 @Test(groups = { "fast" })
	 public void aFastTest() {
	   System.out.println("Fast test");
	 }
	 
	 @Test(groups = { "slow" })
	 public void aSlowTest() {
	    System.out.println("Slow test");
	 }


}
