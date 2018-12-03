package bydbom.base;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class BFunctionSection {
	protected List<WebElement> elementList;
	protected Actions action;
	protected WebDriver driver;
	
	public BFunctionSection(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
		action=new Actions(this.driver);
	}
	
	/**
	 * click the functional buttons, like "start editing", "add", etc.
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
	 * click the child button
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

}
