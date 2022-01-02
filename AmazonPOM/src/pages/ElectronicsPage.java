package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BaseClass;
import utils.Utilities;

import static utils.Utilities.*;

public class ElectronicsPage extends BaseClass{
	static WebElement element;
	public Utilities utilobj;
	
	public ElectronicsPage()
	{
		super();
		element=null;
	}
	
	public ElectronicsPage(WebDriver driver1)
	{
		super(driver1);
		driver=driver1;
		utilobj=new Utilities(driver1);
	}
	
	static By TopResultsLabel=By.xpath("//div[@id='search']//span[contains(text(),'results')]/parent::div/span[3]");

	public static String getTextLabel()
	{
		element=driver.findElement(TopResultsLabel);
		return getText(element);
	}
	

}
