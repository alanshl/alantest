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
		  Thread.sleep(10000);
		  
		  //open product spectrum window
		  MainPage mainPage=new MainPage(super.driver);
		  mainPage.mainMenu.hoverMenu("��Ʒ����");
		  Thread.sleep(2000);
		  mainPage.mainMenu.clickMenu("��Ʒ���׹���");
		  Thread.sleep(5000);
		  
		  //edit product spectrum
		  ProductSpectrumPage productSpectrumPage=new ProductSpectrumPage(super.driver);
		  productSpectrumPage.option.clickCheckBox(0,ListViewStyle.TREEVIEW);
		  productSpectrumPage.button.clickButton("����༭");
		  Thread.sleep(1000);
		  
		  //add car series
		  productSpectrumPage.button.clickButton("����");
		  Thread.sleep(1000);
		  productSpectrumPage.button.clickChildButton("�����ӽڵ�");
		  Thread.sleep(1000);
		  
		  productSpectrumPage.text.openTextBox("nodeCode");
		  Thread.sleep(1000);
		  productSpectrumPage.text.inputText("AT-code-"+super.bcf.getTimeStamp());
		  
		  productSpectrumPage.text.openTextBox("nodeName");
		  Thread.sleep(1000);
		  productSpectrumPage.text.inputText("AT-name-"+super.bcf.getTimeStamp());
		  
		  productSpectrumPage.text.openTextBox("description");
		  Thread.sleep(1000);
		  productSpectrumPage.text.inputText("AT-description-"+super.bcf.getTimeStamp());
		  
		  //add car type
		  productSpectrumPage.button.clickButton("����");
		  Thread.sleep(1000);
		  productSpectrumPage.button.clickChildButton("�����ӽڵ�");
		  Thread.sleep(1000);

		  productSpectrumPage.text.openTextBox("nodeCode");
		  Thread.sleep(1000);
		  productSpectrumPage.text.inputText(super.bcf.getTimeStamp().substring(4));
		  
		  productSpectrumPage.text.openTextBox("nodeName");
		  Thread.sleep(1000);
		  productSpectrumPage.text.inputText("AT-name-"+super.bcf.getTimeStamp());
		  
		  productSpectrumPage.text.openTextBox("description");
		  Thread.sleep(1000);
		  productSpectrumPage.text.inputText("AT-description-"+super.bcf.getTimeStamp());
		  
		  productSpectrumPage.text.openTextBox("fuelType");
		  Thread.sleep(1000);
		  productSpectrumPage.option.expandDropdownList();
		  Thread.sleep(1000);
		  productSpectrumPage.option.selectOption("ȼ��");
		  
		  productSpectrumPage.text.openTextBox("upgrading");
		  Thread.sleep(1000);
		  productSpectrumPage.text.inputText("AT-upgrading-"+super.bcf.getTimeStamp());
		  
		  //add expected go-live year
		  productSpectrumPage.button.clickButton("����");
		  Thread.sleep(1000);
		  productSpectrumPage.button.clickChildButton("�����ӽڵ�");
		  Thread.sleep(1000);
		  
		  productSpectrumPage.text.openTextBox("nodeCode");
		  Thread.sleep(1000);
		  productSpectrumPage.text.inputText("AT-code-"+super.bcf.getTimeStamp());
		  
		  productSpectrumPage.text.openTextBox("nodeName");
		  Thread.sleep(1000);
		  productSpectrumPage.text.inputText("AT-name-"+super.bcf.getTimeStamp());
		  
		  productSpectrumPage.text.openTextBox("description");
		  Thread.sleep(1000);
		  productSpectrumPage.text.inputText("AT-description-"+super.bcf.getTimeStamp());
		  
		  productSpectrumPage.text.openTextBox("status");
		  Thread.sleep(1000);
		  productSpectrumPage.option.expandDropdownList();
		  Thread.sleep(1000);
		  productSpectrumPage.option.selectOption("�滮");
		  Thread.sleep(1000);
		  
		  productSpectrumPage.text.openTextBox("firstPlant");
		  Thread.sleep(1000);
		  productSpectrumPage.option.expandDropdownList();
		  Thread.sleep(1000);
		  productSpectrumPage.option.SelectAllCheckboxOption();
		  
		  
		  //add power configuration
		  productSpectrumPage.button.clickButton("����");
		  Thread.sleep(1000);
		  productSpectrumPage.button.clickChildButton("�����ӽڵ�");
		  Thread.sleep(1000);
		  
		  productSpectrumPage.text.openTextBox("nodeCode");
		  Thread.sleep(1000);
		  productSpectrumPage.text.inputText(super.bcf.getTimeStamp().substring(4));
		  
		  productSpectrumPage.text.openTextBox("nodeName");
		  Thread.sleep(1000);
		  productSpectrumPage.text.inputText("AT-name-"+super.bcf.getTimeStamp());
		  
		  productSpectrumPage.text.openTextBox("description");
		  Thread.sleep(1000);
		  productSpectrumPage.text.inputText("AT-description-"+super.bcf.getTimeStamp());
		  
		  productSpectrumPage.text.openTextBox("saleMarket");
		  Thread.sleep(1000);
		  productSpectrumPage.text.inputText("AT-saleMarket-"+super.bcf.getTimeStamp());
		  
		  productSpectrumPage.text.openTextBox("dynamicConfig");
		  Thread.sleep(1000);
		  productSpectrumPage.text.inputText("AT-dynamicConfig-"+super.bcf.getTimeStamp());
		  
		  productSpectrumPage.text.openTextBox("status");
		  Thread.sleep(1000);
		  productSpectrumPage.option.expandDropdownList();
		  Thread.sleep(1000);
		  productSpectrumPage.option.selectOption("�滮");
		  

		  
		  //add basic configuration
		  productSpectrumPage.button.clickButton("����");
		  Thread.sleep(1000);
		  productSpectrumPage.button.clickChildButton("�����ӽڵ�");
		  Thread.sleep(1000);
		  
		  productSpectrumPage.text.openTextBox("nodeCode");
		  Thread.sleep(1000);
		  productSpectrumPage.text.inputText("AT-code-"+super.bcf.getTimeStamp());
		  
		  productSpectrumPage.text.openTextBox("nodeName");
		  Thread.sleep(1000);
		  productSpectrumPage.text.inputText("AT-name-"+super.bcf.getTimeStamp());
		  
		  productSpectrumPage.text.openTextBox("description");
		  Thread.sleep(1000);
		  productSpectrumPage.text.inputText("AT-description-"+super.bcf.getTimeStamp());
		  
		  productSpectrumPage.text.openTextBox("status");
		  Thread.sleep(1000);
		  productSpectrumPage.option.expandDropdownList();
		  Thread.sleep(1000);
		  productSpectrumPage.option.selectOption("�滮");
		  
		  productSpectrumPage.text.openTextBox("configLevel");
		  Thread.sleep(1000);
		  productSpectrumPage.text.inputText("AT-configLevel-"+super.bcf.getTimeStamp());
		  
		  productSpectrumPage.text.openTextBox("announcementCode");
		  Thread.sleep(1000);
		  productSpectrumPage.text.inputText("AT-announcementCode-"+super.bcf.getTimeStamp());
		  
		  productSpectrumPage.button.clickButton("����");
		  Thread.sleep(2000);
		  
		  Assert.assertEquals(productSpectrumPage.otherElements.isEditFlagDisappeared(ListViewStyle.TREEVIEW), true);
		  
		  		  
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
