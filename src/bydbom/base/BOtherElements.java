package bydbom.base;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import bydbom.common.ChangeOrderCode;
import bydbom.common.ColumnStyle;
import bydbom.common.LabelStyle;
import bydbom.common.ListViewStyle;

/**
 * cover: row, column
 * @author alans
 *
 */
public class BOtherElements {
	protected List<WebElement> elementList;
	protected Actions action;
	protected WebDriver driver;
	
	public BOtherElements(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
		action=new Actions(this.driver);
	}
	
	/**
	 * click the row by the specific text
	 * @param text
	 */
	public void clickRowByText(String text) {
		String xPath="//tr[contains(@id, 'gridview')]/td[2]/div[contains(@class, 'x-grid-cell-inner')]";
		elementList=driver.findElements(By.xpath(xPath));
		
		int i;
		for(i=0;i<elementList.size();i++)
			{	
			    if(elementList.get(i).getText().contains(text)){
			    	elementList.get(i).click();
				    break;
			    }
			}
	}
	
	/**
	 * cover: change order page, pending task page
	 * get the column Id to for further further element xPath definition
	 * @param PageName 
	 * PageName.APPROVALPAGE - change order page
	 * PageName.MAINDATASECTION - VPPD page
	 * @param node
	 * @return column ID
	 */
	public String getColumnId(ColumnStyle cs, String node)
	{
		String temp="";
		String xPath="";
		String searchString="";
		if(cs==ColumnStyle.GANTLINKCOLUMN)
			searchString="gantlinkcolumn-";
		else if(cs==ColumnStyle.GRIDCOLUMN)
			searchString="gridcolumn-";
	
		xPath="//span[contains(@id, '" + searchString + "') and contains(@class, 'column-header')]";
		elementList=this.driver.findElements(By.xpath(xPath));
		int i;
		for(i=0;i<elementList.size();i++){	
			if(elementList.get(i).getText().equalsIgnoreCase(node)){
				temp=String.valueOf(elementList.get(i).getAttribute("id"));
				int start=temp.indexOf(searchString)+searchString.length();
				int end=temp.length()-"textEl".length()-1;
				temp=temp.substring(start, end);
				break;
			}			  	
		}
		return temp;
	}
	

	/**
	 * get the latest change order number
	 * @param codeStyle: some page need to use 'changeCode' as element recognition, some uses 'gantlinkcolumn'
	 * the value can be 'ECO', 'VCO'...
	 * @return change order number
	 */
	public String getLatestChangeOrder(ChangeOrderCode coc) {
		String xPath;
		if(coc==ChangeOrderCode.VCO) {
			xPath="//a[contains(@class, 'linkcolumn') and contains(@onclick, 'gantlinkcolumn')]";
		}
		else {
			xPath="//a[contains(@class, 'linkcolumn') and contains(@onclick, 'changeCode')]";
		}
		elementList=driver.findElements(By.xpath(xPath));
		String result="";
		if(elementList.size()>0) {
			result=elementList.get(0).getText();
		}
		return result;
	}
	
	/**
	 * cover: product spectrum page, VPPD page
	 * judge if there is still the editing flag "+", if yes, not saving successfully, otherwise, saving successfully
	 * @return true=existing, false=non-existing
	 */
	public boolean isEditFlagDisappeared(ListViewStyle lvs) {
		boolean result=false;
		String xPath;
		if(lvs==ListViewStyle.TREEVIEW)
			xPath="//*[contains(@id, 'treeview') and contains(@id, 'record')]/td[2]/div/font";
		else
			xPath="//*[contains(@id, 'gridview') and contains(@id, 'record')]/td[2]/div/font";
		try {
			this.driver.findElement(By.xpath(xPath));
		}
		catch(Exception e) {
			result=true;
		}
		return result;
	}
	
	/**
	 * cover: eBom page
	 * get the label ID per label name
	 * @param labelName
	 * @return label ID
	 */
	public String getLabelId(LabelStyle ls, String labelName) {
		String Id="";
		String temp;
		String xPath="";
		String searchString="";
		if(ls==LabelStyle.COMBO) 
			searchString="combo-";
		else if(ls==LabelStyle.TEXTFIELD)
			searchString="textfield-";
		else if(ls==LabelStyle.GANTCODETYPECOMBOBOX)
			searchString="gantcodetypecombobox-";
		
		xPath="//label[contains(@id, '" + searchString + "')]";
		
		elementList=this.driver.findElements(By.xpath(xPath));
		
				for(int i=0;i<elementList.size();i++)
						  {
						  	if(elementList.get(i).getText().contains(labelName))
						  	{
								temp=String.valueOf(elementList.get(i).getAttribute("id"));
								System.out.println(temp);
								int start=temp.indexOf(searchString)+searchString.length();
								int end=temp.indexOf("-labelEl");
								Id=temp.substring(start, end);
								System.out.println(Id);
						  		break;
						  	}
						  }
		return Id;
	}
	
	
}
