package bydbom.test;

import org.testng.annotations.Test;

import bydbom.base.BTest;
import bydbom.common.EnvJsonFile;
import bydbom.page.MainPage;
import bydbom.page.VPPDPage;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

/* Test: VPPDά��
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
		  super.StartBOM(EnvJsonFile.BASICFILE, "internal");
		  Thread.sleep(5000);
		
		  //login BOM
		  super.LoginBOM();
		  Thread.sleep(5000);
		  
		//open product spectrum window
		  MainPage mainPage=new MainPage(super.driver);
		  mainPage.hoverMenu("��Ʒ����");
		  Thread.sleep(2000);
		  mainPage.clickMenu("��Ʒ�ṹģ��");
		  Thread.sleep(2000);
		  
		  //select version to lookup VPPD
		  VPPDPage vppdPage=new VPPDPage(super.driver);
		  vppdPage.openComboxFromQuerySection("�汾");
		  Thread.sleep(1000);
		  vppdPage.selectOption();
		  Thread.sleep(1000);
		  vppdPage.clickButton("��ѯ");
		  Thread.sleep(2000);
		  
		  vppdPage.clickCheckBox(0);
		  vppdPage.clickButton("����༭");
		  Thread.sleep(1000);
		  
		  //add a node
		  vppdPage.clickButton("����");
		  Thread.sleep(1000);
		  vppdPage.clickMenu("�����ӽڵ�");
		  Thread.sleep(1000);
		  
		  //select the newly added node to edit its attributes
		  vppdPage.clickCheckBox(1);
		  Thread.sleep(1000);
		  
		  //add the code
		  int columnId=vppdPage.getColumnId("BYD");
		  vppdPage.openTextBox(columnId,1);
		  Thread.sleep(1000);
		  vppdPage.inputText(super.bcf.getTimeStamp());
		  
		  //add the simple code
		  vppdPage.openTextBox(columnId+1,1);
		  Thread.sleep(1000);
		  vppdPage.inputText(super.bcf.getTimeStamp());
		  
		  //add the function position code
		  vppdPage.openTextBox(columnId+2,1);
		  Thread.sleep(1000);
		  vppdPage.inputText(super.bcf.getTimeStamp());
		  
		  //add the Chinese description
		  vppdPage.openTextBox(columnId+3,1);
		  Thread.sleep(1000);
		  vppdPage.inputText(super.bcf.getTimeStamp());
		  
		  vppdPage.clickButton("����");
		  
		  
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
  }

}
