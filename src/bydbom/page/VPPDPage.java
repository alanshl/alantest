package bydbom.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import bydbom.base.BPage;

public class VPPDPage extends BPage{

	public VPPDPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
			
		}
	
	/*
	 * just for VPPD page to select the last option from the dropdownlist
	 */
	public void selectOption(){
		WebElement element=super.driver.findElement(By.xpath("//ul[contains(@class, 'x-list-plain')]/li[last()]"));
		element.click();
	}
	
	/*
     * click the check box to select the node
     */
	public void clickCheckBox(int node) {
		elementList=this.driver.findElements(By.xpath("//*[contains(@id,'gridview-')]/td[1]/div/div"));
		if(elementList.size()>0)
		{
			elementList.get(node).click();
		}
	}
	
	/*
	 * open the static text box to make it active
	 */
	public void openTextBox(int Id, int index) {
		
		String xPath="//td[contains(@class, 'headerId-gridcolumn-" + Id + "')]/div[contains(@class, 'grid-cell-inner')]";
		elementList=this.driver.findElements(By.xpath(xPath));
		elementList.get(index).click();
	}
	
	public int getColumnId(String node)
	{
		int Id=0;
		String temp;
		String xPath="//td[contains(@class, 'headerId-gridcolumn-')]";
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
						  	if(elementList.get(i).getText().contains(node))
						  	{
								temp=String.valueOf(elementList.get(i).getAttribute("class"));
								int start=temp.indexOf("gridcolumn-")+"gridcolumn-".length();
								int end=temp.length();
								temp=temp.substring(start, end);
								Id=Integer.parseInt(temp);
								System.out.println(temp);
						  		break;
						  	}
						  	
						  }
		return Id;
	}

}
