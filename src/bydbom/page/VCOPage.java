package bydbom.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import bydbom.base.BPage;

public class VCOPage extends BPage {

	public VCOPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	/*
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
	
	public String getColumnId(String node)
	{
		String temp="";
		String xPath="//span[contains(@id, 'gridcolumn') and contains(@class, 'column-header')]";
		elementList=this.driver.findElements(By.xpath(xPath));
		
		//System.out.println(elementList.size());
		//elementList.get(index).click();
		int i;
		for(i=0;i<elementList.size();i++)
			{	
				//System.out.println(elementList.get(i).getText());
			    //System.out.println(elementList.get(i).getAttribute("id"));
			    //System.out.println(elementList.get(i).getAttribute("class"));
				if(elementList.get(i).getText().contains(node))
				{
					temp=String.valueOf(elementList.get(i).getAttribute("id"));
					int start=temp.indexOf("gridcolumn-")+"gridcolumn-".length();
					int end=temp.length()-"textEl".length()-1;
					temp=temp.substring(start, end);
					//Id=Integer.parseInt(temp);
					//System.out.println(start + "-" + end);
					//System.out.println(temp);
					break;
				}			  	
			}
		return temp;
	}
	
	/*
	 * open the static text box to make it active
	 */
	public void openTextBox(String Id) {
		String xPath="//td[contains(@class, 'gridcolumn-" + Id + "')]/div";
		//System.out.println(xPath);
		//WebElement element=this.driver.findElement(By.xpath(xPath));
		elementList=this.driver.findElements(By.xpath(xPath));
		
		//System.out.println(elementList.size());
		//elementList.get(index).click();
		int i;
		for(i=0;i<elementList.size();i++)
			{	
			/*
				System.out.println(elementList.get(i).getText());
			    System.out.println(elementList.get(i).getAttribute("id"));
			    System.out.println(elementList.get(i).getAttribute("class"));
			    */
			    elementList.get(i).click();
			    
			    break;
			}
	}
	
	/*
	 * click magnifying glass icon
	 */
	public void clickMagnifyingGlass() {
		String xPath="//td[contains(@id,'workflowTaskOwnerTriggerField') and contains(@id, 'inputCell')]/../following-sibling::td[1]/div";
		System.out.println(xPath);
		//WebElement element=this.driver.findElement(By.xpath(xPath));
		elementList=this.driver.findElements(By.xpath(xPath));
		
		System.out.println(elementList.size());
		//elementList.get(index).click();
		int i;
		for(i=0;i<elementList.size();i++)
			{	
				System.out.println(elementList.get(i).getText());
			    System.out.println(elementList.get(i).getAttribute("id"));
			    System.out.println(elementList.get(i).getAttribute("class"));
			    //elementList.get(i).click();
			    //break;
			}
	}
}
