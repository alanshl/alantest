package bydbom.base;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

/**
 * elements 'button', 'tab'
 * @author alans
 *
 */

public class BButton {
	protected List<WebElement> elementList;
	protected Actions action;
	protected WebDriver driver;
	
	public BButton(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
		action=new Actions(this.driver);
	}

	/**
	 * cover: product spectrum page, VPPD page
	 * click the buttons by the button caption, it can cover query section, functional section
	 * @param button
	 */
	public void clickButton(String button) {
		elementList=this.driver.findElements(By.xpath("//span[contains(@id,'button-')]"));
		int i;
		for(i=0;i<elementList.size();i++)
				  {
				  	if(elementList.get(i).getText().contains(button))
				  	{
						elementList.get(i).click();
				  		break;
				  	}
				  }
	}

	
	/**
	 * click the child button by the specific button caption, 
	 * it cover the buttons which have child buttons in functional section
	 * @param String childButton
	 * @return void
	 */
	public void clickChildButton(String childButton) {
		elementList=this.driver.findElements(By.xpath("//*[contains(@id,'menuitem-')]"));
		int i;
		for(i=0;i<elementList.size();i++)
				  {
				  	if(elementList.get(i).getText().contains(childButton))
				  	{
						elementList.get(i).click();
				  		break;
				  	}
				  }
	}
	
	/**
	 * cover: change order page
	 * click magnifying glass icon
	 */
	public void clickMagnifyingGlass() {
		String xPath="//td[contains(@id,'workflowTaskOwnerTriggerField') and contains(@id, 'inputCell')]/../td/div[contains(@id, 'ext-gen') and contains(@class, 'search')]";
		WebElement element=this.driver.findElement(By.xpath(xPath));
		element.click();
	}
	
	/**
	 * cover: approver selector
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
	


}
