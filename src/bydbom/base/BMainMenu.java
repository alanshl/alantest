package bydbom.base;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

/**
 * provide the elements for main menu and related actions
 * @author alans
 *
 */
public class BMainMenu {
	protected List<WebElement> elementList;
	protected Actions action;
	protected WebDriver driver;
	
	public BMainMenu(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
		action=new Actions(this.driver);
	}
	
	/**
	 * hover the main menu
	 * @param String menu
	 * @return void
	 */
	public void hoverMenu(String menu) {
		elementList=this.driver.findElements(By.xpath("//*[contains(@id,'menuitem-')]"));
		int i;
		 for(i=0;i<elementList.size();i++)
				  {
				  	if(elementList.get(i).getText().contains(menu))
				  	{
						action.moveToElement(elementList.get(i)).perform();
				  		break;
				  	}
				  }
	}
	
	/**
	 * click the main menu
	 * @param String menu
	 * @return void
	 */
	public void clickMenu(String menu) {
		elementList=this.driver.findElements(By.xpath("//*[contains(@id,'menuitem-') and contains(@id, 'textEl')]"));
		int i;
		for(i=0;i<elementList.size();i++)
				  {
				  	if(elementList.get(i).getText().equals(menu))
				  	{
				  		if(!elementList.get(i).getAttribute("class").contains("right-arrow")) {
				  			elementList.get(i).click();
					  		break;
				  		}
				  	}
				  }
	}
	
	/**
	 * click the main menu
	 * @param menu: menu name
	 * @param index: menu index when there is multiple menus with the same names, 1, 2, 3...
	 */
	public void clickMenu(String menu, int index) {
		elementList=this.driver.findElements(By.xpath("//*[contains(@id,'menuitem-') and contains(@id, 'textEl')]"));
		int i;
		int menuCount=1;
		for(i=0;i<elementList.size();i++)
				  {
				  	if(elementList.get(i).getText().contains(menu))
				  	{
				  		//if(index==menuCount) {
				  			System.out.println(elementList.get(i).getText());
				  			System.out.println(elementList.get(i).getAttribute("id"));
				  			System.out.println(elementList.get(i).getAttribute("class"));
				  			//elementList.get(i).click();
					  		//break;
				  		//}
				  		//else
				  			menuCount=menuCount+1;
				  	}
				  }
	}

}
