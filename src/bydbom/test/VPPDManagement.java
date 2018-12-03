package bydbom.test;

import org.testng.annotations.Test;

import bydbom.base.BTest;
import bydbom.common.EnvJsonFile;
import bydbom.common.ListViewStyle;
import bydbom.page.MainPage;
import bydbom.page.VPPDPage;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

/* Test: VPPD维护
 * 1. start BOM
 * 2. login BOM
 * 3. open VPPD window
 * 4. add node step by step
 */
public class VPPDManagement extends BTest {
  @Test
  public void f() {
	  try {
		  
		  //start BOM
		  super.StartBOM(EnvJsonFile.BASICFILE, "integration");
		  Thread.sleep(5000);
		
		  //login BOM
		  super.LoginBOM();
		  Thread.sleep(5000);
		  
		  //open product structure window
		  MainPage mainPage=new MainPage(super.driver);
		  mainPage.mainMenu.hoverMenu("产品管理");
		  Thread.sleep(2000);
		  mainPage.mainMenu.clickMenu("产品结构模板");
		  Thread.sleep(2000);
		  
		  //select version to lookup VPPD
		  VPPDPage vppdPage=new VPPDPage(super.driver);
		  vppdPage.querySection.openComboxFromQuerySection("版本");
		  Thread.sleep(1000);
		  vppdPage.querySection.selectLastOption();
		  Thread.sleep(1000);
		  vppdPage.querySection.clickButton("查询");
		  Thread.sleep(2000);
		  
		  try {
		  vppdPage.functionSection.clickButton("进入编辑");
		  Thread.sleep(1000);
		  }
		  catch(Exception e) {
			  //if there is no draft version, need to click prompt button then start editting
			  vppdPage.functionSection.clickButton("升版");
			  Thread.sleep(2000);
			  vppdPage.querySection.openComboxFromQuerySection("版本");
			  Thread.sleep(1000);
			  vppdPage.querySection.selectLastOption();
			  Thread.sleep(1000);
			  vppdPage.querySection.clickButton("查询");
			  Thread.sleep(2000);
			  vppdPage.functionSection.clickButton("进入编辑");
			  Thread.sleep(1000);
		  }
		  
		  vppdPage.mainDataSection.clickCheckBox(0,ListViewStyle.GRIDVIEW);
		  
		  //add a node
		  vppdPage.functionSection.clickButton("新增");
		  Thread.sleep(1000);
		  vppdPage.functionSection.clickChildButton("新增子节点");
		  Thread.sleep(1000);
		  
		  //select the newly added node to edit its attributes
		  vppdPage.mainDataSection.clickCheckBox(1, ListViewStyle.GRIDVIEW);
		  Thread.sleep(1000);
		  
		  //add the code
		  int columnId=vppdPage.mainDataSection.getColumnId("BYD");
		  vppdPage.mainDataSection.openTextBox(columnId,1);
		  Thread.sleep(1000);
		  vppdPage.mainDataSection.inputText(super.bcf.getTimeStamp());
		  
		  //add the simple code
		  vppdPage.mainDataSection.openTextBox(columnId+1,1);
		  Thread.sleep(1000);
		  vppdPage.mainDataSection.inputText(super.bcf.getTimeStamp());
		  
		  //add the function position code
		  vppdPage.mainDataSection.openTextBox(columnId+2,1);
		  Thread.sleep(1000);
		  vppdPage.mainDataSection.inputText(super.bcf.getTimeStamp());
		  
		  //add the Chinese description
		  vppdPage.mainDataSection.openTextBox(columnId+3,1);
		  Thread.sleep(1000);
		  vppdPage.mainDataSection.inputText(super.bcf.getTimeStamp());
		  
		  //save the VPPD
		  vppdPage.functionSection.clickButton("保存");
		  Thread.sleep(1000);
		  
		  
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
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
