package bydbom.test;

import org.testng.annotations.Test;

import bydbom.base.BTest;
import bydbom.common.EnvJsonFile;
import bydbom.page.MainPage;
import bydbom.page.ProductSpectrumPage;

import org.testng.annotations.BeforeTest;
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
		  mainPage.hoverMenu("��Ʒ����");
		  Thread.sleep(2000);
		  mainPage.clickMenu("��Ʒ���׹���");
		  Thread.sleep(2000);
		  
		  //edit product spectrum
		  ProductSpectrumPage prductSpectrumPage=new ProductSpectrumPage(super.driver);
		  prductSpectrumPage.clickCheckBox(0);
		  prductSpectrumPage.clickButton("����༭");
		  Thread.sleep(1000);
		  
		  /*
		  prductSpectrumPage.clickCheckBox(0);
		  Thread.sleep(1000);
		  prductSpectrumPage.clickCheckBox(1);
		  */
		  
		  //open external panel
		  //prductSpectrumPage.clickExternalButton();
		  
		  //add car series
		  prductSpectrumPage.clickButton("����");
		  Thread.sleep(1000);
		  prductSpectrumPage.clickMenu("�����ӽڵ�");
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
		  prductSpectrumPage.clickButton("����");
		  Thread.sleep(1000);
		  prductSpectrumPage.clickMenu("�����ӽڵ�");
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
		  prductSpectrumPage.selectOption("ȼ��");
		  
		  prductSpectrumPage.openTextBox("upgrading");
		  Thread.sleep(1000);
		  prductSpectrumPage.inputText("AT-upgrading-"+super.bcf.getTimeStamp());
		  
		  //add expected go-live year
		  prductSpectrumPage.clickButton("����");
		  Thread.sleep(1000);
		  prductSpectrumPage.clickMenu("�����ӽڵ�");
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
		  prductSpectrumPage.selectOption("����Y");
		  
		  prductSpectrumPage.openTextBox("status");
		  Thread.sleep(1000);
		  prductSpectrumPage.expandDropdownList();
		  Thread.sleep(1000);
		  prductSpectrumPage.selectOption("�滮");
		  
		  
		  //add power configuration
		  prductSpectrumPage.clickButton("����");
		  Thread.sleep(1000);
		  prductSpectrumPage.clickMenu("�����ӽڵ�");
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
		  prductSpectrumPage.selectOption("�滮");

		  
		  //add basic configuration
		  prductSpectrumPage.clickButton("����");
		  Thread.sleep(1000);
		  prductSpectrumPage.clickMenu("�����ӽڵ�");
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
		  prductSpectrumPage.selectOption("�滮");
		  
		  prductSpectrumPage.openTextBox("configLevel");
		  Thread.sleep(1000);
		  prductSpectrumPage.inputText("AT-configLevel-"+super.bcf.getTimeStamp());
		  
		  prductSpectrumPage.openTextBox("announcementCode");
		  Thread.sleep(1000);
		  prductSpectrumPage.inputText("AT-announcementCode-"+super.bcf.getTimeStamp());
		  
		  prductSpectrumPage.clickButton("����");
		  		  
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
