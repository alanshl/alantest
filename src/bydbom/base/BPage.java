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
	public BMainMenu mainMenu;
	public BFunctionSection functionSection;
	public BMainDataSection mainDataSection;
	public BQuerySection querySection;
	public BChangeOrderWindow changeOrderWindow;
	
	public BPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
		action=new Actions(this.driver);
		mainMenu=new BMainMenu(driver);
		functionSection=new BFunctionSection(driver);
		mainDataSection =new BMainDataSection(driver);
		querySection=new BQuerySection(driver);
		changeOrderWindow=new BChangeOrderWindow(driver);
	}
}
