package bydbom.test;

import org.testng.annotations.Test;

import bydbom.base.BTest;
import bydbom.common.ChangeOrderCode;
import bydbom.common.ColumnStyle;
import bydbom.common.EnvJsonFile;
import bydbom.common.LabelStyle;
import bydbom.common.ListViewStyle;
import bydbom.common.TableStyle;
import bydbom.common.TextStyle;
import bydbom.page.MainPage;
import bydbom.page.PartApplyOrderPage;

import org.testng.annotations.BeforeTest;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterTest;

/**
 * apply a new part
 * 1. create a part application form
 * 2. fill all the mandatory fields
 * 3. save the form
 * 4. publish the form
 * @author alans
 *
 */
public class PartApply extends BTest{
  @Test
  public void partApplyProcess() {
	  try {
		//start BOM
		  super.StartBOM(EnvJsonFile.BASICFILE, "integration");
		  Thread.sleep(5000);
		  
		  //login BOM
		  super.LoginBOM();
		  Thread.sleep(10000);
		  
		  //open product spectrum window
		  MainPage mainPage=new MainPage(super.driver);
		  mainPage.mainMenu.hoverMenu("�㲿������");
		  Thread.sleep(2000);
		  mainPage.mainMenu.clickMenu("������뵥����");
		  Thread.sleep(5000);
		  
		  //create a new part application order
		  PartApplyOrderPage partApplyOrderPage=new PartApplyOrderPage(super.driver);
		  partApplyOrderPage.button.clickButton("����");
		  Thread.sleep(2000);
		  
		  String labelId=partApplyOrderPage.otherElements.getLabelId(LabelStyle.COMBO, "���뵥����");
		  partApplyOrderPage.option.expandDropdownList(labelId);
		  Thread.sleep(1000);
		  partApplyOrderPage.option.selectOption("�ṹ��");
		  Thread.sleep(1000);
		  partApplyOrderPage.button.clickButton("����");
		  Thread.sleep(2000);
		  
		  partApplyOrderPage.button.clickButton("����༭");
		  Thread.sleep(1000);
		  
		  partApplyOrderPage.button.clickButton("����",1);
		  Thread.sleep(1000);
		  partApplyOrderPage.button.clickChildButton("����-�ܳɼ�");
		  Thread.sleep(1000);
		  
		  //get the part application table Id
		  String partApplicationTableId=partApplyOrderPage.otherElements.getTableId(TableStyle.GRIDVIEW, 1);
		  //define the table Id for pop-up selector, like part name selector, VPPD selector, etc.
		  String popUpSelectorTableId;
		  //define the magnifying glass table Id for those field contains magnifying glass button
		  String magnifyingGlassTableId;
		  
		  
		  //select Chinese name for the part
		  partApplyOrderPage.text.openTextBox(partApplicationTableId, 1, 6);
		  Thread.sleep(1000);
		  partApplyOrderPage.button.clickMagnifyingGlass(TableStyle.materialName2, "", 1, 2);
		  Thread.sleep(1000);
		  popUpSelectorTableId=partApplyOrderPage.otherElements.getTableId(TableStyle.GRIDVIEW, 2);
		  partApplyOrderPage.option.clickCheckBox(popUpSelectorTableId, 1, 1);
		  Thread.sleep(1000);
		  partApplyOrderPage.button.clickButton("ѡ��");
		  Thread.sleep(1000);
		  
		  
		  //select car series code
		  partApplyOrderPage.text.openTextBox(partApplicationTableId, 1, 7);
		  Thread.sleep(1000);
		  magnifyingGlassTableId=partApplyOrderPage.otherElements.getTableId(TableStyle.GANGTRIGGERFIELD, 0);
		  Thread.sleep(1000);
		  partApplyOrderPage.button.clickMagnifyingGlass(TableStyle.GANGTRIGGERFIELD, magnifyingGlassTableId, 1, 2);
		  Thread.sleep(1000);
		  popUpSelectorTableId=partApplyOrderPage.otherElements.getTableId(TableStyle.GRIDVIEW, 2);
		  partApplyOrderPage.option.clickCheckBox(popUpSelectorTableId, 1, 1);
		  Thread.sleep(1000);
		  partApplyOrderPage.button.clickButton("ѡ��");
		  Thread.sleep(1000);
		  
		  
		  //add the additional information code
		  partApplyOrderPage.text.openTextBox(partApplicationTableId, 1, 13);
		  Thread.sleep(1000);
		  partApplyOrderPage.text.inputText(TextStyle.TEXTFIELD, super.bcf.getTimeStamp().substring(4));
		  Thread.sleep(1000);
		  
		  //select the functional position code
		  if(partApplyOrderPage.text.isTextBoxEmpty(partApplicationTableId, 1, 18)){
			  partApplyOrderPage.text.openTextBox(partApplicationTableId, 1, 18);
			  Thread.sleep(1000);
			  magnifyingGlassTableId=partApplyOrderPage.otherElements.getTableId(TableStyle.GANGTRIGGERFIELD, 1);
			  Thread.sleep(1000);
			  partApplyOrderPage.button.clickMagnifyingGlass(TableStyle.GANGTRIGGERFIELD, magnifyingGlassTableId, 1, 2);
			  Thread.sleep(1000);
			  popUpSelectorTableId=partApplyOrderPage.otherElements.getTableId(TableStyle.GRIDVIEW, 2);
			  partApplyOrderPage.option.clickCheckBox(popUpSelectorTableId, 2, 1);
			  Thread.sleep(1000);
			  partApplyOrderPage.button.clickButton("ѡ��");
			  Thread.sleep(2000);
		  }
		  
		  //select suggested source
		  partApplyOrderPage.text.openTextBox(partApplicationTableId, 1, 22);
		  Thread.sleep(1000);
		  partApplyOrderPage.option.expandDropdownList();
		  Thread.sleep(1000);
		  partApplyOrderPage.option.selectOption("�ɹ�");
		  Thread.sleep(1000);
		  partApplyOrderPage.text.openTextBox(partApplicationTableId, 1, 23);
		  Thread.sleep(1000);
		  
		  //save
		  partApplyOrderPage.button.clickButton("����");
		  Thread.sleep(1000);
		  
		  //check if the unsaved icon (green plus) disappears, if yes, the data has been saved
		  assertEquals(partApplyOrderPage.otherElements.isEditFlagDisappeared(ListViewStyle.GRIDVIEW), true);
		  Thread.sleep(1000);
		  
		  //fetch the part number and save the number into jason file, it can be used for further testing, like eBOM
		  String partNum=partApplyOrderPage.text.getValueFromTextBox(TableStyle.GRIDVIEW, partApplicationTableId, 1, 5);
		  Map<String, String> testData=new HashMap<String, String>();
		  testData.put("PartNum",partNum);
		  super.bcf.writeJasonFile(EnvJsonFile.TESTDATA, testData);
		  
		  //close the part application order window and publish the 
		  String title=partApplyOrderPage.otherElements.getWindowTitle();
		  System.out.println(title);
		  partApplyOrderPage.button.clickCloseButton(1);
		  Thread.sleep(1000);
		  
		  //check the part application order and publish it
		  partApplicationTableId=partApplyOrderPage.otherElements.getTableId(TableStyle.GRIDVIEW, 0);
		  String columnId=partApplyOrderPage.otherElements.getColumnId(ColumnStyle.GANTLINKCOLUMN, "���뵥��");
		  int linkCount=partApplyOrderPage.link.getLinkCount(columnId);
		  for(int i=0;i<linkCount;i++) {
			  if(title.contains(partApplyOrderPage.text.getValueFromTextBox(TableStyle.GRIDVIEW, partApplicationTableId, i+1, 3))) {
				  partApplyOrderPage.option.clickCheckBox(partApplicationTableId, i+1, 1);
				  Thread.sleep(1000);
				  partApplyOrderPage.button.clickButton("����");
				  Thread.sleep(1000);
				  break;
			  }
		  }
		  
		  
	  }
	  catch(InterruptedException e) {
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
