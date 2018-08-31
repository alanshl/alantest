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
		  super.StartBOM(EnvJsonFile.BASICFILE, "internal");
		  Thread.sleep(5000);
		
		  //login BOM
		  super.LoginBOM();
		  Thread.sleep(5000);
		  
		  //open VCO window
		  MainPage mainPage=new MainPage(super.driver);
		  mainPage.hoverMenu("�������");
		  Thread.sleep(2000);
		  mainPage.clickMenu("��Ʒ�ṹ�������");
		  Thread.sleep(2000);
		  
		  //create a new VCO
		  VCOPage vcoPage=new VCOPage(super.driver);
		  vcoPage.clickButton("����");
		  Thread.sleep(1000);
		  vcoPage.clickButton("����");
		  Thread.sleep(1000);
		  
		  //add the change content
		  vcoPage.clickTabPage("�������");
		  Thread.sleep(1000);
		  vcoPage.clickButton("�������");
		  Thread.sleep(1000);
		  
		  //add approver
		  vcoPage.clickTabPage("��������");
		  Thread.sleep(1000);
		  String columnId=vcoPage.getColumnId("������ѡ��");
		  vcoPage.openTextBox(columnId);
		  Thread.sleep(1000);
		  vcoPage.clickMagnifyingGlass();
		  
		  
	  }
	  catch(Exception e) {
		  
	  }
  }
  @BeforeTest
  public void beforeTest() {
  }

  @AfterTest
  public void afterTest() {
  }

}
