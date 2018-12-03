package bydbom.test;

import org.testng.annotations.Test;

import bydbom.base.BTest;
import bydbom.common.EnvJsonFile;
import bydbom.page.MainPage;
import bydbom.page.VCOPage;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class VCOPublishVPPD extends BTest {
  @Test
  public void f() {
	  try
	  {
		  //start BOM
		  super.StartBOM(EnvJsonFile.BASICFILE, "integration");
		  Thread.sleep(5000);
		
		  //login BOM
		  super.LoginBOM();
		  Thread.sleep(5000);
		  
		  //open VCO window
		  MainPage mainPage=new MainPage(super.driver);
		  mainPage.mainMenu.hoverMenu("�������");
		  Thread.sleep(2000);
		  mainPage.mainMenu.clickMenu("��Ʒ�ṹ�������");
		  Thread.sleep(2000);
		  
		  //create a new VCO
		  VCOPage vcoPage=new VCOPage(super.driver);
		  vcoPage.functionSection.clickButton("����");
		  Thread.sleep(1000);
		  vcoPage.functionSection.clickButton("����");
		  Thread.sleep(1000);
		  
		  //add the change content
		  vcoPage.clickTabPage("�������");
		  Thread.sleep(1000);
		  vcoPage.functionSection.clickButton("�������");
		  Thread.sleep(1000);
		  
		  //add approver
		  vcoPage.clickTabPage("��������");
		  Thread.sleep(1000);
		  String columnId=vcoPage.getColumnId("������������");
		  int approverCount=vcoPage.getLinkCount(columnId);
		  
		  columnId=vcoPage.getColumnId("������ѡ��");
		  
		  for (int i=0;i<approverCount;i++) {
			  vcoPage.openTextBox(columnId, i);
			  Thread.sleep(1000);
			  vcoPage.clickMagnifyingGlass();
			  Thread.sleep(1000);
			  super.bcf.readJasonFile(EnvJsonFile.TESTFILE);
			  vcoPage.clickCheckBoxOption(super.bcf.getProperty("approver"));
			  Thread.sleep(1000);
			  //vcoPage.clickMoveToButton(">>");
			  vcoPage.functionSection.clickButton(">>");
			  Thread.sleep(1000);
			  vcoPage.functionSection.clickButton("ȷ��");
			  Thread.sleep(1000);
		  }
			  
		  //start approval process
		  vcoPage.functionSection.clickButton("������������");
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
	  super.close();
  }

}
