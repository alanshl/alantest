package bydbom.base;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class  BPage {
	protected List<WebElement> elementList;
	protected Actions action;
	protected WebDriver driver;
	
	public BPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
		action=new Actions(this.driver);
	}
	
	/*
	 * hover the main menu
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
	
	/*
	 * click the main menu
	 */
	public void clickMenu(String menu) {
		elementList=this.driver.findElements(By.xpath("//*[contains(@id,'menuitem-')]"));
		int i;
		for(i=0;i<elementList.size();i++)
				  {
				  	if(elementList.get(i).getText().contains(menu))
				  	{
						elementList.get(i).click();
				  		break;
				  	}
				  }
	}
	
	/*
	 * click the functional button, according to the button text to click
	 */
	public void clickButton(String button) {
		elementList=this.driver.findElements(By.xpath("//*[contains(@id,'button-')]"));
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
	
    /*
     * click the check box to select the node
     */
	public void clickCheckBox(int node) {
		elementList=this.driver.findElements(By.xpath("//*[contains(@id,'treeview-')]/td[1]/div/div"));
		if(elementList.size()>0)
		{
			elementList.get(node).click();
		}
	}
	
	/*
	 * click the external panel button
	 */
	public void clickExternalButton() {
		WebElement element=this.driver.findElement(By.xpath("//span[contains(@class, 'x-btn-icon-el iconfont icon-snimiccaidanliebia')]"));
		element.click();
	}
	
	/*
	 * open the static text box to make it active
	 */
	public void openTextBox(String attribute) {
		String xPath="//*[contains(@id,'gridview-') and contains(@id,'" + attribute + "')]/td[2]/div";
		WebElement element=this.driver.findElement(By.xpath(xPath));
		element.click();
	}
	
	/*
	 * input text to the text box which is currently on focus
	 */
	public void inputText(String text) {
		WebElement element=this.driver.findElement(By.xpath("//*[contains(@id,'textfield-') and contains(@id, '-inputEl') and contains(@class, 'focus')]"));
		element.clear();
		element.sendKeys(text);
	}
	
	/*
	 * select the option from the dropdownlist which is currently on focus
	 */
	public void selectOption(String option) {
		elementList=this.driver.findElements(By.xpath("//li[contains(@role, 'option') and contains(@class, 'x-boundlist-item')]"));
		//System.out.println(elementList.size());
		int i;
		for(i=0;i<elementList.size();i++)
				  {
			/*
			System.out.println(elementList.get(i).getText());
	        System.out.println(elementList.get(i).getAttribute("id"));
	        System.out.println(elementList.get(i).getAttribute("class"));
			 */       
				  	if(elementList.get(i).getText().contains(option))
				  	{
						elementList.get(i).click();
				  		break;
				  	}
				  	
				  }
	}
	
	//public void selectOption(){}
	
	/*
	 * expand the dropdownlist which is currently on focus
	 */
	public void expandDropdownList()
	{
		WebElement element=this.driver.findElement(By.xpath("//*[contains(@id, 'combobox') and contains(@id, '-inputEl') and contains(@class, 'focus')]"));
		element.click();
	}
	
	/*
	 * open the combox in lookup section
	 */
	public void openComboxFromQuerySection(String label) {
		elementList=this.driver.findElements(By.xpath("//label[contains(@id, 'combo')]"));
		//System.out.println(elementList.size());
		int i;
		for(i=0;i<elementList.size();i++)
				  {
			/*
			System.out.println(elementList.get(i).getText());
	        System.out.println(elementList.get(i).getAttribute("id"));
	        System.out.println(elementList.get(i).getAttribute("class"));
	        //elementList.get(i).click();
			      */ 
				  	if(elementList.get(i).getText().contains(label))
				  	{
				  		String id=elementList.get(i).getAttribute("id");
				  		id=id.replaceAll("label", "input");
				  		System.out.println(id);
				  		WebElement element=this.driver.findElement(By.xpath("//input[contains(@id,'" + id + "')]"));
				  		element.click();
				  		break;
				  	}
				  	
				  }
	}
}
