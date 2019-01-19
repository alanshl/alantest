package bydbom.base;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class BText {
	protected List<WebElement> elementList;
	protected Actions action;
	protected WebDriver driver;
	
	public BText(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
		action=new Actions(this.driver);
	}
	
	/**cover the change order page
	 * open the static text box to make it active
	 * @param column Id
	 * @param index: indicate which text box
	 */
	public void openTextBox(String Id, int index) {
		String xPath="//td[contains(@class, 'gridcolumn-" + Id + "')]/div";
		elementList=this.driver.findElements(By.xpath(xPath));
		if(elementList.get(index).getText().contains(""))
			elementList.get(index).click();
	}
	
	
	/**
	 * cover: product spectrum page
	 * open the static textbox to make it gain the focus
	 * @param attribute
	 */
	public void openTextBox(String attribute) {
		String xPath="//*[contains(@id,'gridview-') and contains(@id,'" + attribute + "')]/td[2]/div";
		WebElement element=this.driver.findElement(By.xpath(xPath));
		element.click();
	}
	
	/**
	 * cover: product spectrum page, VPPD page
	 * input text into the text box which is currently gaining focus
	 * @param text
	 */
	public void inputText(String text) {
		WebElement element=this.driver.findElement(By.xpath("//*[contains(@id,'textfield-') and contains(@id, '-inputEl') and contains(@class, 'focus')]"));
		element.clear();
		element.sendKeys(text);
	}

}
