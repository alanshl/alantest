package bydbom.test;

import org.testng.annotations.Test;

import bydbom.base.BTest;
import bydbom.common.EnvJsonFile;
import bydbom.common.ListViewStyle;
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
		  Thread.sleep(5000);
		  
		  //select project code and query the bom
		  mainPage.mainDataSection.getLabelId("项目代号");
		  super.bcf.readJasonFile(EnvJsonFile.TESTDATA);
		  super.bcf.getProperty("ProjectCode");
		  
		  
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
