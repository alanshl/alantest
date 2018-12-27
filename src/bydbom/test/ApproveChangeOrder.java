package bydbom.test;

import org.testng.annotations.Test;

import bydbom.base.BTest;
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
		  while(pendingTaskPage.mainDataSection.clickLinkByText(super.bcf.getProperty("ChangeOrder"))) {
			  Thread.sleep(2000);
			  
			  //click the approval tab
			  pendingTaskPage.changeOrderWindow.clickTabPage("��������");
			  Thread.sleep(2000);
			  
			  //check the approval option
			  pendingTaskPage.changeOrderWindow.clickRowByText("��׼");
			  Thread.sleep(1000);
			  
			  //assign next approvers 
			  String columnId=pendingTaskPage.changeOrderWindow.getColumnId("��������","gantlinkcolumn-");
			  int approverCount=pendingTaskPage.changeOrderWindow.getLinkCount(columnId);
			  
			  if(approverCount!=0) {
				  columnId=pendingTaskPage.changeOrderWindow.getColumnId("������ѡ��");
				  
				  for (int i=0;i<approverCount;i++) {
					  pendingTaskPage.changeOrderWindow.openTextBox(columnId, i);
					  Thread.sleep(1000);
					  pendingTaskPage.changeOrderWindow.clickMagnifyingGlass();
					  Thread.sleep(1000);
					  super.bcf.readJasonFile(EnvJsonFile.TESTFILE);
					  pendingTaskPage.changeOrderWindow.clickCheckBoxOption(super.bcf.getProperty("approver"));
					  Thread.sleep(1000);
					  //pendingTaskPage.clickMoveToButton(">>");
					  pendingTaskPage.functionSection.clickButton(">>");
					  Thread.sleep(1000);
					  pendingTaskPage.functionSection.clickButton("ȷ��");
					  Thread.sleep(1000);
				  }
			  }
			  
			  
			  //click the approve button
			  pendingTaskPage.functionSection.clickButton("ִ�в���");
			  Thread.sleep(1000);
			  pendingTaskPage.functionSection.clickButton("��");
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
