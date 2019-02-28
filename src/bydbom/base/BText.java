package bydbom.base;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import bydbom.common.TableStyle;
import bydbom.common.TextStyle;

public class BText {
	protected List<WebElement> elementList;
	protected Actions action;
	protected WebDriver driver;
	
	public BText(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
		action=new Actions(this.driver);
	}
	

	
	/**
	 * Click text box to ensure it got focus
	 * @param ts: 
	 * text style: IDINTR means the id is in the TR element, 
	 * IDINTD means the id is in the TD element,
	 * IDININPUT means the id is in the input element
	 * @param Id
	 * @param index: if there are several elements with the same xPath, 
	 * the index indicates which element should be used, it starts with 1,  
	 */
	public void openTextBox(TextStyle ts, String Id, int index) {
		String xPath="";
		if(ts==TextStyle.IDINTR) {
			xPath="//*[contains(@id,'gridview-') and contains(@id,'" + Id + "')]/td[2]/div";
			WebElement element=this.driver.findElement(By.xpath(xPath));
			element.click();
		}
		else if(ts==TextStyle.IDINTD) {
			xPath="//td[contains(@class, 'gridcolumn-" + Id + "')]/div";
			elementList=this.driver.findElements(By.xpath(xPath));
			if(elementList.get(index).getText().contains(""))
				elementList.get(index).click();
		}
		else if(ts==TextStyle.IDININPUT) {
			xPath="//input[contains(@id, 'textfield') and contains(@id, '" + Id + "') and contains(@id, 'inputEl')]";
			WebElement element=this.driver.findElement(By.xpath(xPath));
			element.click();
		}
	}
	
	/**
	 * click the text box from the table with the specific row and cell
	 * @param tableId
	 * @param row
	 * @param col
	 */
	public void openTextBox(String tableId, int row, int col) {
		String xPath="//table[contains(@id, 'gridview') and contains(@id, '" + tableId + "')]/tbody/tr[" + row + "]/td[" + col + "]/div";
		WebElement element=this.driver.findElement(By.xpath(xPath));
		element.click();
		
	}
	
	/**
	 * input the text in the table per the specific row and col
	 * @param tableId
	 * @param row
	 * @param col
	 * @param text
	 */
	public void inputText(String tableId, int row, int col, String text) {
		String xPath="//table[contains(@id, 'gridview') and contains(@id, '" + tableId + "')]/tbody/tr[" + row + "]/td[" + col + "]/div";
		WebElement element=this.driver.findElement(By.xpath(xPath));
		element.sendKeys(text);
	}
	

	/**
	 * cover: product spectrum page, VPPD page
	 * input text into the text box which is currently gaining focus
	 * @param text
	 */
	public void inputText(TextStyle ts,String text) {
		String xPath="";
		if(ts==TextStyle.TEXTFIELD) {
			xPath="//*[contains(@id,'textfield-') and contains(@id, '-inputEl') and contains(@class, 'focus')]";
		}
		else if(ts==TextStyle.NUMBERFIELD) {
			xPath="//*[contains(@id,'numberfield-') and contains(@id, '-inputEl') and contains(@class, 'focus')]";
		}
		WebElement element=this.driver.findElement(By.xpath(xPath));
		element.clear();
		element.sendKeys(text);
	}
	
	
	public String getValueFromTextBox(TableStyle ts, String tableId, int row, int col) {
		String value="";
		String xPath="";
		if(ts==TableStyle.GRIDVIEW) {
			xPath="//table[contains(@id, 'gridview') and contains(@id, '" + tableId + "') and contains(@id, 'table')]/tbody/tr[" + row + "]/td[" + col + "]/div";
			WebElement element=this.driver.findElement(By.xpath(xPath));
			value=element.getText();
			System.out.println(value);
		}
		
		return value;
	}
	
	/**
	 * check if there is value in the specific cell
	 * @param tableId
	 * @param row
	 * @param col
	 * @return true: the cell is empty, false: the cell is not empty
	 */
	public boolean isTextBoxEmpty(String tableId, int row, int col) {
		String xPath="//table[contains(@id, 'gridview') and contains(@id, '" + tableId + "')]/tbody/tr[" + row + "]/td[" + col + "]/div";
		WebElement element=this.driver.findElement(By.xpath(xPath));
		String temp=element.getText().trim();
		return temp.equalsIgnoreCase("");
	}
	
}
