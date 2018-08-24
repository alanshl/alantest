package bydbom.test;

import org.testng.annotations.Test;

import bydbom.base.BTest;
import bydbom.common.EnvJsonFile;
import bydbom.page.MainPage;
import bydbom.page.ProductSpectrumPage;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

/* Test: 产品型谱维护功能
 * 1. start BOM
 * 2. login BOM
 * 3. open product spectrum window
 * 4. add node step by step
 */
public class ProductSpectrum extends BTest {
  @Test
  public void ProductSpectrumManagement() {
	  try {
		  //start BOM
		  super.StartBOM(EnvJsonFile.BASICFILE, "integration");
		  Thread.sleep(5000);
		  
		  //login BOM
		  super.LoginBOM();
		  Thread.sleep(5000);
		  
		  //open product spectrum window
		  MainPage mainPage=new MainPage(super.driver);
		  mainPage.hoverMenu("产品管理");
		  Thread.sleep(2000);
		  mainPage.clickMenu("产品型谱管理");
		  Thread.sleep(2000);
		  
		  //edit product spectrum
		  ProductSpectrumPage prductSpectrumPage=new ProductSpectrumPage(super.driver);
		  prductSpectrumPage.clickCheckBox(0);
		  prductSpectrumPage.clickButton("进入编辑");
		  Thread.sleep(1000);
		  
		  /*
		  prductSpectrumPage.clickCheckBox(0);
		  Thread.sleep(1000);
		  prductSpectrumPage.clickCheckBox(1);
		  */
		  
		  //open external panel
		  //prductSpectrumPage.clickExternalButton();
		  
		  //add car series
		  prductSpectrumPage.clickButton("新增");
		  Thread.sleep(1000);
		  prductSpectrumPage.clickMenu("新增子节点");
		  Thread.sleep(1000);
		  
		  prductSpectrumPage.openTextBox("nodeCode");
		  Thread.sleep(1000);
		  prductSpectrumPage.inputText("AT-code-"+super.bcf.getTimeStamp());
		  
		  prductSpectrumPage.openTextBox("nodeName");
		  Thread.sleep(1000);
		  prductSpectrumPage.inputText("AT-name-"+super.bcf.getTimeStamp());
		  
		  prductSpectrumPage.openTextBox("description");
		  Thread.sleep(1000);
		  prductSpectrumPage.inputText("AT-description-"+super.bcf.getTimeStamp());
		  
		  //add car type
		  prductSpectrumPage.clickButton("新增");
		  Thread.sleep(1000);
		  prductSpectrumPage.clickMenu("新增子节点");
		  Thread.sleep(1000);

		  prductSpectrumPage.openTextBox("nodeCode");
		  Thread.sleep(1000);
		  prductSpectrumPage.inputText("AT-code-"+super.bcf.getTimeStamp());
		  
		  prductSpectrumPage.openTextBox("nodeName");
		  Thread.sleep(1000);
		  prductSpectrumPage.inputText("AT-name-"+super.bcf.getTimeStamp());
		  
		  prductSpectrumPage.openTextBox("description");
		  Thread.sleep(1000);
		  prductSpectrumPage.inputText("AT-description-"+super.bcf.getTimeStamp());
		  
		  prductSpectrumPage.openTextBox("fuelType");
		  Thread.sleep(1000);
		  prductSpectrumPage.expandDropdownList();
		  Thread.sleep(1000);
		  prductSpectrumPage.selectOption("燃油");
		  
		  prductSpectrumPage.openTextBox("upgrading");
		  Thread.sleep(1000);
		  prductSpectrumPage.inputText("AT-upgrading-"+super.bcf.getTimeStamp());
		  
		  //add expected go-live year
		  prductSpectrumPage.clickButton("新增");
		  Thread.sleep(1000);
		  prductSpectrumPage.clickMenu("新增子节点");
		  Thread.sleep(1000);
		  
		  prductSpectrumPage.openTextBox("nodeCode");
		  Thread.sleep(1000);
		  prductSpectrumPage.inputText("AT-code-"+super.bcf.getTimeStamp());
		  
		  prductSpectrumPage.openTextBox("nodeName");
		  Thread.sleep(1000);
		  prductSpectrumPage.inputText("AT-name-"+super.bcf.getTimeStamp());
		  
		  prductSpectrumPage.openTextBox("description");
		  Thread.sleep(1000);
		  prductSpectrumPage.inputText("AT-description-"+super.bcf.getTimeStamp());
		  
		  prductSpectrumPage.openTextBox("firstPlant.orgName");
		  Thread.sleep(1000);
		  prductSpectrumPage.expandDropdownList();
		  Thread.sleep(1000);
		  prductSpectrumPage.selectOption("深圳Y");
		  
		  prductSpectrumPage.openTextBox("status");
		  Thread.sleep(1000);
		  prductSpectrumPage.expandDropdownList();
		  Thread.sleep(1000);
		  prductSpectrumPage.selectOption("规划");
		  
		  
		  //add power configuration
		  prductSpectrumPage.clickButton("新增");
		  Thread.sleep(1000);
		  prductSpectrumPage.clickMenu("新增子节点");
		  Thread.sleep(1000);
		  
		  prductSpectrumPage.openTextBox("nodeCode");
		  Thread.sleep(1000);
		  prductSpectrumPage.inputText("AT-code-"+super.bcf.getTimeStamp());
		  
		  prductSpectrumPage.openTextBox("nodeName");
		  Thread.sleep(1000);
		  prductSpectrumPage.inputText("AT-name-"+super.bcf.getTimeStamp());
		  
		  prductSpectrumPage.openTextBox("description");
		  Thread.sleep(1000);
		  prductSpectrumPage.inputText("AT-description-"+super.bcf.getTimeStamp());
		  
		  prductSpectrumPage.openTextBox("saleMarket");
		  Thread.sleep(1000);
		  prductSpectrumPage.inputText("AT-saleMarket-"+super.bcf.getTimeStamp());
		  
		  prductSpectrumPage.openTextBox("status");
		  Thread.sleep(1000);
		  prductSpectrumPage.expandDropdownList();
		  Thread.sleep(1000);
		  prductSpectrumPage.selectOption("规划");

		  
		  //add basic configuration
		  prductSpectrumPage.clickButton("新增");
		  Thread.sleep(1000);
		  prductSpectrumPage.clickMenu("新增子节点");
		  Thread.sleep(1000);
		  
		  prductSpectrumPage.openTextBox("nodeCode");
		  Thread.sleep(1000);
		  prductSpectrumPage.inputText("AT-code-"+super.bcf.getTimeStamp());
		  
		  prductSpectrumPage.openTextBox("nodeName");
		  Thread.sleep(1000);
		  prductSpectrumPage.inputText("AT-name-"+super.bcf.getTimeStamp());
		  
		  prductSpectrumPage.openTextBox("description");
		  Thread.sleep(1000);
		  prductSpectrumPage.inputText("AT-description-"+super.bcf.getTimeStamp());
		  
		  prductSpectrumPage.openTextBox("status");
		  Thread.sleep(1000);
		  prductSpectrumPage.expandDropdownList();
		  Thread.sleep(1000);
		  prductSpectrumPage.selectOption("规划");
		  
		  prductSpectrumPage.openTextBox("configLevel");
		  Thread.sleep(1000);
		  prductSpectrumPage.inputText("AT-configLevel-"+super.bcf.getTimeStamp());
		  
		  prductSpectrumPage.openTextBox("announcementCode");
		  Thread.sleep(1000);
		  prductSpectrumPage.inputText("AT-announcementCode-"+super.bcf.getTimeStamp());
		  
		  prductSpectrumPage.clickButton("保存");
		  		  
	  }
	  catch(Exception e) {
		  e.printStackTrace();
	  }
	  
  }
  @BeforeTest
  public void beforeTest() {
  }

  @AfterTest
  public void afterTest() {
  }

}
