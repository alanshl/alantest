package bydbom.test;

import org.testng.annotations.Test;

import bydbom.base.BTest;
import bydbom.common.EnvJsonFile;
import bydbom.common.LabelStyle;
import bydbom.common.ListViewStyle;
import bydbom.page.EBOMPage;
import bydbom.page.MainPage;

import org.testng.annotations.BeforeTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

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
		  mainPage.mainMenu.hoverMenu("BOM管理");
		  Thread.sleep(2000);
		  mainPage.mainMenu.hoverMenu("工程BOM管理");
		  Thread.sleep(2000);
		  mainPage.mainMenu.clickMenu("工程BOM管理");
		  Thread.sleep(10000);
		  
		  EBOMPage eBomPage=new EBOMPage(super.driver);
		  
		  //select project code and query the bom
		  String labelId=eBomPage.otherElements.getLabelId(LabelStyle.COMBO,"项目代号");
		  super.bcf.readJasonFile(EnvJsonFile.TESTDATA);
		  String prjectCode=super.bcf.getProperty("ProjectCode");
		  eBomPage.option.expandDropdownList(labelId);
		  Thread.sleep(2000);
		  eBomPage.option.selectOption(prjectCode);
		  Thread.sleep(1000);
		  eBomPage.button.clickButton("查询");
		  Thread.sleep(5000);
		  
		  eBomPage.option.clickCheckBox(0,ListViewStyle.GRIDVIEW);
		  
		  //add a part
		  eBomPage.button.clickButton("进入编辑");
		  Thread.sleep(1000);
		  eBomPage.button.clickButton("新增");
		  Thread.sleep(1000);
		  eBomPage.button.clickChildButton("新增子节点");
		  Thread.sleep(1000);
		  
		  //from the part selector, choose a part
		  

		  		  
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
	  //super.close();
	  
  }

}
