package bydbom.test;

import org.testng.annotations.Test;

import bydbom.base.BTest;
import bydbom.common.EnvJsonFile;
import bydbom.common.LabelStyle;
import bydbom.common.ListViewStyle;
import bydbom.common.TableStyle;
import bydbom.common.TextStyle;
import bydbom.common.TriggerStyle;
import bydbom.page.EBOMPage;
import bydbom.page.MainPage;

import org.testng.annotations.BeforeTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

/**
 * EBOM maintain
 * 1. add part
 * 2. input the mandatory fields including quantity, structure code, assemble workshop, suggested source, development strategy 
 * 3. save
 * @author alans
 *
 */
public class EBOMManagement extends BTest {
  @Test
  public void EBOMAddPart() {
	  try {
		  
		  //start BOM
		  super.StartBOM(EnvJsonFile.BASICFILE, "integration");
		  Thread.sleep(10000);
		  
		  		
		  //login BOM
		  super.LoginBOM();
		  Thread.sleep(10000);
		  
		  //open EBOM window
		  MainPage mainPage=new MainPage(super.driver);
		  mainPage.mainMenu.hoverMenu("BOM����");
		  Thread.sleep(2000);
		  mainPage.mainMenu.hoverMenu("����BOM����");
		  Thread.sleep(2000);
		  mainPage.mainMenu.clickMenu("����BOM����");
		  Thread.sleep(10000);
		  
		  EBOMPage eBomPage=new EBOMPage(super.driver);
		  
		  //select project code and query the bom
		  String labelId=eBomPage.otherElements.getLabelId(LabelStyle.COMBO,"��Ŀ����");
		  super.bcf.readJasonFile(EnvJsonFile.TESTDATA);
		  String prjectCode=super.bcf.getProperty("ProjectCode");
		  eBomPage.option.expandDropdownList(labelId);
		  Thread.sleep(2000);
		  eBomPage.option.selectOption(prjectCode);
		  Thread.sleep(1000);
		  eBomPage.button.clickButton("��ѯ");
		  Thread.sleep(5000);
		  
		  eBomPage.option.clickCheckBox(0,ListViewStyle.GRIDVIEW);
		  
		  //add a part
		  eBomPage.button.clickButton("����༭");
		  Thread.sleep(1000);
		  eBomPage.button.clickButton("����");
		  Thread.sleep(1000);
		  eBomPage.button.clickChildButton("�����ӽڵ�");
		  Thread.sleep(1000);
		  
		  //from the part selector, choose a part
		  String Id;
		  Id=eBomPage.otherElements.getLabelId(LabelStyle.TEXTFIELD, "�����");
		  eBomPage.text.openTextBox(TextStyle.IDININPUT, Id, 1);
		  eBomPage.text.inputText(TextStyle.TEXTFIELD,super.bcf.getProperty("PartNum"));
		  Thread.sleep(1000);
		  eBomPage.button.clickButton("��ѯ",2);
		  Thread.sleep(1000);
		  String PopUpTableId=eBomPage.otherElements.getTableId(TableStyle.GRIDVIEW,2);
		  eBomPage.option.clickCheckBox(PopUpTableId, 1,1);
		  Thread.sleep(1000);
		  eBomPage.button.clickButton("ѡ��");
		  Thread.sleep(1000);
		  eBomPage.button.clickCloseButton(1);
		  Thread.sleep(1000);
		  
		  //input quantity
		  String mainDataTableId=eBomPage.otherElements.getTableId(TableStyle.GRIDVIEW,1);
		  
		  Thread.sleep(1000);
		  eBomPage.text.openTextBox(mainDataTableId, 2, 3);
		  Thread.sleep(1000);
		  eBomPage.text.inputText(TextStyle.NUMBERFIELD, super.bcf.getTimeStamp().substring(4));
		  Thread.sleep(1000);
		  
		  //check if there is functional position code. if no, select functional position code
		  if(eBomPage.text.isTextBoxEmpty(mainDataTableId, 2, 11)) {
			  eBomPage.text.openTextBox(mainDataTableId, 2, 11);
			  Thread.sleep(1000);
			  String MagnifyingGlassTableId=eBomPage.otherElements.getTableId(TableStyle.GANGTRIGGERFIELD, 2);
			  eBomPage.button.clickMagnifyingGlass(TableStyle.GANGTRIGGERFIELD, MagnifyingGlassTableId,1,2);
			  Thread.sleep(1000);
			  PopUpTableId=eBomPage.otherElements.getTableId(TableStyle.GRIDVIEW,2);
			  eBomPage.option.clickCheckBox(PopUpTableId, 2,1);
			  Thread.sleep(1000);
			  eBomPage.button.clickButton("ѡ��");
			  Thread.sleep(2000);
		  }
		  
		  
		  //select the assemble workshop
		  eBomPage.text.openTextBox(mainDataTableId, 2, 12);
		  Thread.sleep(1000);
		  eBomPage.option.expandDropdownList();
		  Thread.sleep(1000);
		  eBomPage.option.selectOption("��װ����");
		  Thread.sleep(1000);
		  
		  //check if there is suggested source. If no, add it
		  if(eBomPage.text.isTextBoxEmpty(mainDataTableId, 2, 23)) {
			  eBomPage.text.openTextBox(mainDataTableId, 2, 23);
			  Thread.sleep(1000);
			  eBomPage.option.expandDropdownList();
			  Thread.sleep(1000);
			  eBomPage.option.selectOption("����-Ϳ");
			  Thread.sleep(1000);
		  }
		  
		  //select development strategy
		  eBomPage.text.openTextBox(mainDataTableId, 2, 29);
		  Thread.sleep(1000);
		  eBomPage.option.expandDropdownList();
		  Thread.sleep(1000);
		  eBomPage.option.selectOption("�¿���");
		  Thread.sleep(2000);
		  eBomPage.text.openTextBox(mainDataTableId, 2, 3);
		  
		  //save
		  eBomPage.button.clickButton("����");
		  Thread.sleep(2000);
		  
		  Assert.assertEquals(eBomPage.otherElements.isEditFlagDisappeared(ListViewStyle.GRIDVIEW), true);
			  	  
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
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
