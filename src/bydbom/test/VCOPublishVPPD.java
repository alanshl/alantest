package bydbom.test;

import org.testng.annotations.Test;

import bydbom.base.BTest;
import bydbom.common.ChangeOrderCode;
import bydbom.common.EnvJsonFile;
import bydbom.page.MainPage;
import bydbom.page.VCOPage;

import org.testng.annotations.BeforeTest;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class VCOPublishVPPD extends BTest {
  @Test
  public void SubmitVCO() {
	  try
	  {
		  //start BOM
		  super.StartBOM(EnvJsonFile.BASICFILE, "integration");
		  Thread.sleep(10000);
		
		  //login BOM
		  super.LoginBOM();
		  Thread.sleep(10000);
		  
		  //open VCO window
		  MainPage mainPage=new MainPage(super.driver);
		  mainPage.mainMenu.hoverMenu("变更管理");
		  Thread.sleep(2000);
		  mainPage.mainMenu.clickMenu("产品结构变更管理");
		  Thread.sleep(2000);
		  
		  //create a new VCO
		  VCOPage vcoPage=new VCOPage(super.driver);
		  vcoPage.functionSection.clickButton("新增");
		  Thread.sleep(1000);
		  
		  vcoPage.functionSection.clickButton("保存");
		  Thread.sleep(1000);
		  
		  //add the change content
		  vcoPage.changeOrderWindow.clickTabPage("变更内容");
		  Thread.sleep(1000);
		  vcoPage.functionSection.clickButton("整版关联");
		  Thread.sleep(1000);
		  
		  //add approver
		  vcoPage.changeOrderWindow.clickTabPage("流程审批");
		  Thread.sleep(1000);
		  String columnId=vcoPage.changeOrderWindow.getColumnId("审批任务名称");
		  int approverCount=vcoPage.changeOrderWindow.getLinkCount(columnId);
		  
		  columnId=vcoPage.changeOrderWindow.getColumnId("操作候选人");
		  
		  for (int i=0;i<approverCount;i++) {
			  vcoPage.changeOrderWindow.openTextBox(columnId, i);
			  Thread.sleep(1000);
			  vcoPage.changeOrderWindow.clickMagnifyingGlass();
			  Thread.sleep(1000);
			  super.bcf.readJasonFile(EnvJsonFile.TESTFILE);
			  vcoPage.changeOrderWindow.clickCheckBoxOption(super.bcf.getProperty("approver"));
			  Thread.sleep(1000);
			  vcoPage.functionSection.clickButton(">>");
			  Thread.sleep(1000);
			  vcoPage.functionSection.clickButton("确定");
			  Thread.sleep(1000);
		  }
			  
		  //start approval process
		  vcoPage.functionSection.clickButton("启动审批流程");
		  Thread.sleep(5000);
		  
		  Map<String, String> testData=new HashMap<String, String>();
		  testData.put("ChangeOrder",vcoPage.changeOrderWindow.getLatestChangeOrder(ChangeOrderCode.VCO));
		  super.bcf.writeJasonFile(EnvJsonFile.TESTDATA, testData);
		  
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
