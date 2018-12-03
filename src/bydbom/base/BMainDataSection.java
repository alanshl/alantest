package bydbom.base;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import bydbom.common.ListViewStyle;

/**
 * define the main data section and provide the related actions
 * @author alans
 *
 */
public class BMainDataSection {
	protected List<WebElement> elementList;
	protected Actions action;
	protected WebDriver driver;
	
	public BMainDataSection(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
		action=new Actions(this.driver);
	}
	
	/**
	 * click the checkBox in tree list view 
	 * @param node
	 */
	public void clickCheckBox(int node, ListViewStyle lvs) {
		if(lvs==ListViewStyle.TREEVIEW) {
			elementList=this.driver.findElements(By.xpath("//*[contains(@id,'treeview-')]/td[1]/div/div"));
			if(elementList.size()>0)
			{
				elementList.get(node).click();
			}
		}
		else if(lvs==ListViewStyle.GRIDVIEW){
			elementList=this.driver.findElements(By.xpath("//*[contains(@id,'gridview-')]/td[1]/div/div"));
			if(elementList.size()>0)
			{
				elementList.get(node).click();
			}
		}
	}
	
	/**
	 * open the external pane
	 */
	public void clickExternalButton() {
		WebElement element=this.driver.findElement(By.xpath("//span[contains(@class, 'x-btn-icon-el iconfont icon-snimiccaidanliebia')]"));
		element.click();
	}
	
	/**
	 * open the static textbox to make it gain the focus
	 * @param attribute
	 */
	public void openTextBox(String attribute) {
		String xPath="//*[contains(@id,'gridview-') and contains(@id,'" + attribute + "')]/td[2]/div";
		WebElement element=this.driver.findElement(By.xpath(xPath));
		element.click();
	}
	
	/**
	 * open the static textbox with the specific id and index
	 * @param Id column id
	 * @param index row index
	 */
	public void openTextBox(int Id, int index) {
		
		String xPath="//td[contains(@class, 'headerId-gridcolumn-" + Id + "')]/div[contains(@class, 'grid-cell-inner')]";
		elementList=this.driver.findElements(By.xpath(xPath));
		elementList.get(index).click();
	}
	
	
	/**
	 * input text into the text box which is currently gaining focus
	 * @param text
	 */
	public void inputText(String text) {
		WebElement element=this.driver.findElement(By.xpath("//*[contains(@id,'textfield-') and contains(@id, '-inputEl') and contains(@class, 'focus')]"));
		element.clear();
		element.sendKeys(text);
	}
	
	/**
	 * expand dropdown list
	 */
	public void expandDropdownList()
	{
		WebElement element=this.driver.findElement(By.xpath("//*[contains(@id, 'combobox') and contains(@id, '-inputEl') and contains(@class, 'focus')]"));
		element.click();
	}
	
	/**
	 * select the option from the dropdown list which is currently on focus
	 * @param option
	 */
	public void selectOption(String option) {
		String xPath="//li[contains(@role, 'option') and contains(@class, 'x-boundlist-item')]";
		elementList=this.driver.findElements(By.xpath(xPath));
		//System.out.println(elementList.size());
		int i;
		for(i=0;i<elementList.size();i++)
				  {
				  	if(elementList.get(i).getText().contains(option))
				  	{
						elementList.get(i).click();
				  		break;
				  	}
				  	
				  }
	}
	
	/**
	 * select the checkbox option from the dropdown list which is currently on focus
	 * @param option
	 */
	public void SelectCheckboxOption(String option) {
		String xPath="//input[contains(@type, 'checkbox')]";
		elementList=this.driver.findElements(By.xpath(xPath));
		System.out.println(elementList.size());
		int i;
		for(i=0;i<elementList.size();i++)
				  {
	        		elementList.get(i).click();
				  }
	}
	
	/**
	 * get the column id with the specific node
	 * @param node
	 * @return id
	 */
	public int getColumnId(String node)
	{
		int Id=0;
		String temp;
		String xPath="//td[contains(@class, 'headerId-gridcolumn-')]";
		elementList=this.driver.findElements(By.xpath(xPath));
		
				int i;
				for(i=0;i<elementList.size();i++)
						  {
						  	if(elementList.get(i).getText().contains(node))
						  	{
								temp=String.valueOf(elementList.get(i).getAttribute("class"));
								int start=temp.indexOf("gridcolumn-")+"gridcolumn-".length();
								int end=temp.length();
								temp=temp.substring(start, end);
								Id=Integer.parseInt(temp);
						  		break;
						  	}
						  	
						  }
		return Id;
	}

}
