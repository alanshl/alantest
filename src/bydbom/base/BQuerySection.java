package bydbom.base;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class BQuerySection {
	protected List<WebElement> elementList;
	protected Actions action;
	protected WebDriver driver;
	
	public BQuerySection(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
		action=new Actions(this.driver);
	}
	
	
	/**
	 * open the dropdown list with the specific label
	 * @param label
	 */
	public void openComboxFromQuerySection(String label) {
		elementList=this.driver.findElements(By.xpath("//label[contains(@id, 'combo')]"));
		int i;
		for(i=0;i<elementList.size();i++)
				  {
				  	if(elementList.get(i).getText().contains(label))
				  	{
				  		String id=elementList.get(i).getAttribute("id");
				  		id=id.replaceAll("label", "input");
				  		WebElement element=this.driver.findElement(By.xpath("//input[contains(@id,'" + id + "')]"));
				  		element.click();
				  		break;
				  	}
				  	
				  }
	}
	
	/**
	 * select last option
	 */
	public void selectLastOption(){
		WebElement element=driver.findElement(By.xpath("//ul[contains(@class, 'x-list-plain')]/li[last()]"));
		element.click();
	}
	
	/**
	 * click the buttons in query section, like "search", etc.
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

}
