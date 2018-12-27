package bydbom.base;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import bydbom.common.ChangeOrderCode;

public class BChangeOrderWindow {
	protected List<WebElement> elementList;
	protected Actions action;
	protected WebDriver driver;
	
	public BChangeOrderWindow(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
		action=new Actions(this.driver);
	}
	
	/**
     * click the tab
     */
	public void clickTabPage(String tabName) {
		elementList=this.driver.findElements(By.xpath("//span[contains(@id,'tab') and contains(@id, 'btnInner') and contains(@class, 'tab')]"));
		int i;
		for(i=0;i<elementList.size();i++)
		{			
	        if(elementList.get(i).getText().contains(tabName))
	        {
	        	elementList.get(i).click();
	        	break;
	        }
		}
	}
	
	/**
	 * get the column Id to for further further element xPath definition
	 * @param node
	 * @return column ID
	 */
	public String getColumnId(String node)
	{
		String temp="";
		String xPath="//span[contains(@id, 'column') and contains(@class, 'column-header')]";
		elementList=this.driver.findElements(By.xpath(xPath));
		
		int i;
		for(i=0;i<elementList.size();i++)
			{	
				if(elementList.get(i).getText().equalsIgnoreCase(node))
				{
					temp=String.valueOf(elementList.get(i).getAttribute("id"));
					int start=temp.indexOf("gridcolumn-")+"gridcolumn-".length();
					int end=temp.length()-"textEl".length()-1;
					temp=temp.substring(start, end);
					break;
				}			  	
			}
		return temp;
	}
	
	/**
	 * get the column ID
	 * @param node: the header name
	 * @param startup: what string will be searched to start to pick up the column Id, example "gridcolumn-", "gantlinkcolumn-"
	 * @return
	 */
	public String getColumnId(String node, String startup) {
		String temp="";
		String xPath="//span[contains(@id, 'column') and contains(@class, 'column-header')]";
		elementList=this.driver.findElements(By.xpath(xPath));
		
		int i;
		for(i=0;i<elementList.size();i++)
			{	
				if(elementList.get(i).getText().equalsIgnoreCase(node))
				{
					temp=String.valueOf(elementList.get(i).getAttribute("id"));
					int start=temp.indexOf(startup)+startup.length();
					int end=temp.length()-"textEl".length()-1;
					temp=temp.substring(start, end);
					break;
				}			  	
			}
		return temp;
	}
	
	/**
	 * open the static text box to make it active
	 * @param column Id
	 * @param index: indicate which text box
	 */
	public void openTextBox(String Id, int index) {
		String xPath="//td[contains(@class, 'gridcolumn-" + Id + "')]/div";
		elementList=this.driver.findElements(By.xpath(xPath));
		if(elementList.get(index).getText().contains(""))
			elementList.get(index).click();
	}
	
	/**
	 * click magnifying glass icon
	 */
	public void clickMagnifyingGlass() {
		String xPath="//td[contains(@id,'workflowTaskOwnerTriggerField') and contains(@id, 'inputCell')]/../td/div[contains(@id, 'ext-gen') and contains(@class, 'search')]";
		WebElement element=this.driver.findElement(By.xpath(xPath));
		element.click();
	}
	
	/**
	 * click the checkbox option
	 * @param option: which option will be checked
	 */
	public void clickCheckBoxOption(String option) {
		String xPath="//td[contains(@class, 'headerId-gridcolumn') and contains(@class, 'checker')]/following-sibling::td[1]/div[not(contains(@class, 'number'))]";
		elementList=this.driver.findElements(By.xpath(xPath));
		
		int i;
		for(i=0;i<elementList.size();i++)
			{	
			    if(elementList.get(i).getText().contains(option)){
			    	elementList.get(i).click();
				    break;
			    }   
			}	
	}
	
	/**
	 * click move to button
	 * @param: direction >> or <<
	 */
	public void clickMoveToButton(String direction) {
		String xPath="//a[contains(@class, 'noicon') and contains(@id, 'button')]";
		System.out.println(xPath);
		elementList=this.driver.findElements(By.xpath(xPath));
		System.out.println(elementList.size());
		
		int i;
		for(i=0;i<elementList.size();i++)
			{	
			    if(elementList.get(i).getText().contains(direction)){
			    	elementList.get(i).click();
				    break;
			    }
			    
			    
			}
			
	}
	
	/**
	 * get the link count
	 * @param StringlinkText
	 * @return int how many links matching with the text
	 */
	public int getLinkCount(String linkText) {
		int linkCount=0;
		String xPath="//a[contains(@onclick, '" + linkText + "')]";
		elementList=driver.findElements(By.xpath(xPath));
		linkCount=elementList.size();
		if(linkCount!=0) {
			if(elementList.get(0).getText().equals("½áÊø")) {
				linkCount=0;
			}
				
		}
		
		return linkCount;
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
	
	
	

}
