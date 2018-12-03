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
		String xPath="//span[contains(@id, 'column') and contains(@class, 'column-header')]";
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
	public void openTextBox(String Id, int index) {
		String xPath="//td[contains(@class, 'gridcolumn-" + Id + "')]/div";
		//System.out.println(xPath);
		//WebElement element=this.driver.findElement(By.xpath(xPath));
		elementList=this.driver.findElements(By.xpath(xPath));
		elementList.get(index).click();
		/*
		//System.out.println(elementList.size());
		//elementList.get(index).click();
		int i;
		for(i=0;i<elementList.size();i++)
			{	
			
				System.out.println(elementList.get(i).getText());
			    System.out.println(elementList.get(i).getAttribute("id"));
			    System.out.println(elementList.get(i).getAttribute("class"));
			    
			    
			    
			    break;
			}
			*/
	}
	
	/*
	 * click magnifying glass icon
	 */
	public void clickMagnifyingGlass() {
		String xPath="//td[contains(@id,'workflowTaskOwnerTriggerField') and contains(@id, 'inputCell')]/../td/div[contains(@id, 'ext-gen') and contains(@class, 'search')]";
		//System.out.println(xPath);
		WebElement element=this.driver.findElement(By.xpath(xPath));
		//elementList=this.driver.findElements(By.xpath(xPath));
		element.click();
		//System.out.println(elementList.size());
		//elementList.get(index).click();
		/*
		int i;
		for(i=0;i<elementList.size();i++)
			{	
				System.out.println(elementList.get(i).getText());
			    System.out.println(elementList.get(i).getAttribute("id"));
			    System.out.println(elementList.get(i).getAttribute("class"));
			    //elementList.get(i).click();
			    //break;
			}
			*/
	}
	
	/*
	 * select the checkbox option
	 */
	public void clickCheckBoxOption(String option) {
		String xPath="//td[contains(@class, 'headerId-gridcolumn') and contains(@class, 'checker')]/following-sibling::td[1]/div[not(contains(@class, 'number'))]";
		//System.out.println(xPath);
		//WebElement element=this.driver.findElement(By.xpath(xPath));
		elementList=this.driver.findElements(By.xpath(xPath));
		//element.click();
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
			    if(elementList.get(i).getText().contains(option)){
			    	elementList.get(i).click();
				    break;
			    }
			    
			}
			
	}
	
	/*
	 * click move to right or left button
	 */
	public void clickMoveToButton(String direction) {
		String xPath="//a[contains(@class, 'noicon') and contains(@id, 'button')]";
		System.out.println(xPath);
		//WebElement element=this.driver.findElement(By.xpath(xPath));
		elementList=this.driver.findElements(By.xpath(xPath));
		//element.click();
		System.out.println(elementList.size());
		//elementList.get(index).click();
		
		int i;
		for(i=0;i<elementList.size();i++)
			{	
				System.out.println(elementList.get(i).getText());
			    System.out.println(elementList.get(i).getAttribute("id"));
			    System.out.println(elementList.get(i).getAttribute("class"));
			    
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
		String xPath="//a[contains(@onclick, '" + linkText + "')]";
		elementList=driver.findElements(By.xpath(xPath));
		return elementList.size();
	}
	
	
}
