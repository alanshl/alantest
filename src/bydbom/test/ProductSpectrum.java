package bydbom.test;

import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import bydbom.base.BTest;
import bydbom.common.EnvJsonFile;
import bydbom.common.ListViewStyle;
import bydbom.page.MainPage;
import bydbom.page.ProductSpectrumPage;

import org.testng.annotations.BeforeTest;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.AfterTest;

/* Test: ��Ʒ����ά������
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
		  mainPage.mainMenu.hoverMenu("��Ʒ����");
		  Thread.sleep(2000);
		  mainPage.mainMenu.clickMenu("��Ʒ���׹���");
		  Thread.sleep(5000);
		  
		  //edit product spectrum
		  ProductSpectrumPage productSpectrumPage=new ProductSpectrumPage(super.driver);
		  productSpectrumPage.mainDataSection.clickCheckBox(0,ListViewStyle.TREEVIEW);
		  productSpectrumPage.functionSection.clickButton("����༭");
		  Thread.sleep(1000);
		  
		  //add car series
		  productSpectrumPage.functionSection.clickButton("����");
		  Thread.sleep(1000);
		  productSpectrumPage.functionSection.clickChildButton("�����ӽڵ�");
		  Thread.sleep(1000);
		  
		  productSpectrumPage.mainDataSection.openTextBox("nodeCode");
		  Thread.sleep(1000);
		  productSpectrumPage.mainDataSection.inputText("AT-code-"+super.bcf.getTimeStamp());
		  
		  productSpectrumPage.mainDataSection.openTextBox("nodeName");
		  Thread.sleep(1000);
		  productSpectrumPage.mainDataSection.inputText("AT-name-"+super.bcf.getTimeStamp());
		  
		  productSpectrumPage.mainDataSection.openTextBox("description");
		  Thread.sleep(1000);
		  productSpectrumPage.mainDataSection.inputText("AT-description-"+super.bcf.getTimeStamp());
		  
		  //add car type
		  productSpectrumPage.functionSection.clickButton("����");
		  Thread.sleep(1000);
		  productSpectrumPage.functionSection.clickChildButton("�����ӽڵ�");
		  Thread.sleep(1000);

		  productSpectrumPage.mainDataSection.openTextBox("nodeCode");
		  Thread.sleep(1000);
		  productSpectrumPage.mainDataSection.inputText(super.bcf.getTimeStamp().substring(4));
		  
		  productSpectrumPage.mainDataSection.openTextBox("nodeName");
		  Thread.sleep(1000);
		  productSpectrumPage.mainDataSection.inputText("AT-name-"+super.bcf.getTimeStamp());
		  
		  productSpectrumPage.mainDataSection.openTextBox("description");
		  Thread.sleep(1000);
		  productSpectrumPage.mainDataSection.inputText("AT-description-"+super.bcf.getTimeStamp());
		  
		  productSpectrumPage.mainDataSection.openTextBox("fuelType");
		  Thread.sleep(1000);
		  productSpectrumPage.mainDataSection.expandDropdownList();
		  Thread.sleep(1000);
		  productSpectrumPage.mainDataSection.selectOption("ȼ��");
		  
		  productSpectrumPage.mainDataSection.openTextBox("upgrading");
		  Thread.sleep(1000);
		  productSpectrumPage.mainDataSection.inputText("AT-upgrading-"+super.bcf.getTimeStamp());
		  
		  //add expected go-live year
		  productSpectrumPage.functionSection.clickButton("����");
		  Thread.sleep(1000);
		  productSpectrumPage.functionSection.clickChildButton("�����ӽڵ�");
		  Thread.sleep(1000);
		  
		  productSpectrumPage.mainDataSection.openTextBox("nodeCode");
		  Thread.sleep(1000);
		  productSpectrumPage.mainDataSection.inputText("AT-code-"+super.bcf.getTimeStamp());
		  
		  productSpectrumPage.mainDataSection.openTextBox("nodeName");
		  Thread.sleep(1000);
		  productSpectrumPage.mainDataSection.inputText("AT-name-"+super.bcf.getTimeStamp());
		  
		  productSpectrumPage.mainDataSection.openTextBox("description");
		  Thread.sleep(1000);
		  productSpectrumPage.mainDataSection.inputText("AT-description-"+super.bcf.getTimeStamp());
		  
		  productSpectrumPage.mainDataSection.openTextBox("status");
		  Thread.sleep(1000);
		  productSpectrumPage.mainDataSection.expandDropdownList();
		  Thread.sleep(1000);
		  productSpectrumPage.mainDataSection.selectOption("�滮");
		  Thread.sleep(1000);
		  
		  productSpectrumPage.mainDataSection.openTextBox("firstPlant");
		  Thread.sleep(1000);
		  productSpectrumPage.mainDataSection.expandDropdownList();
		  Thread.sleep(1000);
		  productSpectrumPage.mainDataSection.SelectCheckboxOption("����Y");
		  
		  
		  //add power configuration
		  productSpectrumPage.functionSection.clickButton("����");
		  Thread.sleep(1000);
		  productSpectrumPage.functionSection.clickChildButton("�����ӽڵ�");
		  Thread.sleep(1000);
		  
		  productSpectrumPage.mainDataSection.openTextBox("nodeCode");
		  Thread.sleep(1000);
		  productSpectrumPage.mainDataSection.inputText(super.bcf.getTimeStamp().substring(4));
		  
		  productSpectrumPage.mainDataSection.openTextBox("nodeName");
		  Thread.sleep(1000);
		  productSpectrumPage.mainDataSection.inputText("AT-name-"+super.bcf.getTimeStamp());
		  
		  productSpectrumPage.mainDataSection.openTextBox("description");
		  Thread.sleep(1000);
		  productSpectrumPage.mainDataSection.inputText("AT-description-"+super.bcf.getTimeStamp());
		  
		  productSpectrumPage.mainDataSection.openTextBox("saleMarket");
		  Thread.sleep(1000);
		  productSpectrumPage.mainDataSection.inputText("AT-saleMarket-"+super.bcf.getTimeStamp());
		  
		  productSpectrumPage.mainDataSection.openTextBox("dynamicConfig");
		  Thread.sleep(1000);
		  productSpectrumPage.mainDataSection.inputText("AT-dynamicConfig-"+super.bcf.getTimeStamp());
		  
		  productSpectrumPage.mainDataSection.openTextBox("status");
		  Thread.sleep(1000);
		  productSpectrumPage.mainDataSection.expandDropdownList();
		  Thread.sleep(1000);
		  productSpectrumPage.mainDataSection.selectOption("�滮");
		  

		  
		  //add basic configuration
		  productSpectrumPage.functionSection.clickButton("����");
		  Thread.sleep(1000);
		  productSpectrumPage.functionSection.clickChildButton("�����ӽڵ�");
		  Thread.sleep(1000);
		  
		  productSpectrumPage.mainDataSection.openTextBox("nodeCode");
		  Thread.sleep(1000);
		  productSpectrumPage.mainDataSection.inputText("AT-code-"+super.bcf.getTimeStamp());
		  
		  productSpectrumPage.mainDataSection.openTextBox("nodeName");
		  Thread.sleep(1000);
		  productSpectrumPage.mainDataSection.inputText("AT-name-"+super.bcf.getTimeStamp());
		  
		  productSpectrumPage.mainDataSection.openTextBox("description");
		  Thread.sleep(1000);
		  productSpectrumPage.mainDataSection.inputText("AT-description-"+super.bcf.getTimeStamp());
		  
		  productSpectrumPage.mainDataSection.openTextBox("status");
		  Thread.sleep(1000);
		  productSpectrumPage.mainDataSection.expandDropdownList();
		  Thread.sleep(1000);
		  productSpectrumPage.mainDataSection.selectOption("�滮");
		  
		  productSpectrumPage.mainDataSection.openTextBox("configLevel");
		  Thread.sleep(1000);
		  productSpectrumPage.mainDataSection.inputText("AT-configLevel-"+super.bcf.getTimeStamp());
		  
		  productSpectrumPage.mainDataSection.openTextBox("announcementCode");
		  Thread.sleep(1000);
		  productSpectrumPage.mainDataSection.inputText("AT-announcementCode-"+super.bcf.getTimeStamp());
		  
		  productSpectrumPage.functionSection.clickButton("����");
		  Thread.sleep(1000);
		  
		  Assert.assertEquals(true, true);	  
		  		  
	  }
	  catch(Exception e) {
		  e.printStackTrace();
		  Assert.assertEquals(false, true);
	  }
	  
  }
  @BeforeTest
  public void beforeTest() {
  }

  @AfterTest
  public void afterTest() {
	  super.close();
  }

}
