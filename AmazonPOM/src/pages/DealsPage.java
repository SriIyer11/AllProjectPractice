package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BaseClass;
import utils.Utilities;

import static utils.Utilities.*;

public class DealsPage extends BaseClass{
	static WebElement element;
	public Utilities utilobj;
	
	public DealsPage()
	{
		super();
		element=null;
	}
	
	public DealsPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		utilobj=new Utilities(driver);
	}
	
	static By TopResultsLabel=By.xpath("//div[@id='search']//span[contains(text(),'results')]/parent::div/span[3]");

	public static String getTextLabel()
	{
		element=driver.findElement(TopResultsLabel);
		return getText(element);
	}
	

}
