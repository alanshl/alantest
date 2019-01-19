package bydbom.test;

import org.testng.annotations.Test;

import bydbom.base.BTest;
import bydbom.common.ColumnStyle;
import bydbom.common.EnvJsonFile;
import bydbom.page.MainPage;
import bydbom.page.PendingTaskPage;

import org.testng.annotations.BeforeTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class ApproveChangeOrder extends BTest {
  @Test()
  public void ApproveCO() {
	//start BOM
	  super.StartBOM(EnvJsonFile.BASICFILE, "integration");
	  try {
		  Thread.sleep(10000);
		  
		  //login BOM
		  super.LoginBOMAsApprover();
		  Thread.sleep(10000);
		  
		  //open pending task window
		  MainPage mainPage=new MainPage(super.driver);
		  mainPage.mainMenu.hoverMenu("��������");
		  Thread.sleep(2000);
		  mainPage.mainMenu.clickMenu("����������");
		  Thread.sleep(5000);
		
		  //click the change order link and keep approving the order till the approver completes his task
		  PendingTaskPage pendingTaskPage=new PendingTaskPage(super.driver);
		  super.bcf.readJasonFile(EnvJsonFile.TESTDATA);
		  while(pendingTaskPage.link.clickLinkByText(super.bcf.getProperty("ChangeOrder"))) {
			  Thread.sleep(2000);
			  
			  //click the approval tab
			  pendingTaskPage.tab.clickTab("��������");
			  Thread.sleep(2000);
			  
			  //check the approval option
			  pendingTaskPage.otherElements.clickRowByText("��׼");
			  Thread.sleep(1000);
			  
			  //assign next approvers 
			  String columnId=pendingTaskPage.otherElements.getColumnId(ColumnStyle.GANTLINKCOLUMN,"��������");
			  int approverCount=pendingTaskPage.link.getLinkCount(columnId);
			  
			  if(approverCount!=0) {
				  columnId=pendingTaskPage.otherElements.getColumnId(ColumnStyle.GRIDCOLUMN,"������ѡ��");
				  
				  for (int i=0;i<approverCount;i++) {
					  pendingTaskPage.text.openTextBox(columnId, i);
					  Thread.sleep(1000);
					  pendingTaskPage.button.clickMagnifyingGlass();
					  Thread.sleep(1000);
					  super.bcf.readJasonFile(EnvJsonFile.TESTFILE);
					  pendingTaskPage.option.clickCheckBoxOption(super.bcf.getProperty("approver"));
					  Thread.sleep(1000);
					  //pendingTaskPage.clickMoveToButton(">>");
					  pendingTaskPage.button.clickButton(">>");
					  Thread.sleep(1000);
					  pendingTaskPage.button.clickButton("ȷ��");
					  Thread.sleep(1000);
				  }
			  }
			  
			  
			  //click the approve button
			  pendingTaskPage.button.clickButton("ִ�в���");
			  Thread.sleep(1000);
			  pendingTaskPage.button.clickButton("��");
			  Thread.sleep(3000);
			   
		  }
		  
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
