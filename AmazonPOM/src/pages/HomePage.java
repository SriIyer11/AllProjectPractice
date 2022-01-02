package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BaseClass;
import utils.Utilities;

import static pages.HomePage.element;
import static utils.Utilities.*;

public class HomePage extends BaseClass {
//instance variables- create locators
//#POM	
public static By AccountsLists=By.xpath("//*[contains(text(),'Account & Lists')]");
public static By Cart=By.id("nav-cart");
public static By SearchButton=By.id("nav-search-submit-button");
public static By SearchBox=By.id("twotabsearchtextbox");
public static By SearchType=By.cssSelector("select[title='Search in']");
public static By SigninButton=By.xpath("//*[text()='Sign in']");
//methods to work with these elements
public static WebElement element=null;
public Utilities utilobj;

public HomePage(WebDriver driver1)
{
	super(driver1);
	driver=driver1;
	utilobj=new Utilities(driver1);
}

public HomePage()
{
	super();
}

public static SignInPage clickSignIn()
{
	element=driver.findElement(SigninButton);
	element.click();
	return new SignInPage(driver);
}

public static void typeSearchBox(String text)
{
	element=formElementLocator(SearchBox);
	element.sendKeys(text);
}

public static ElectronicsPage clickSearchButton()
{
	element=formElementLocator(SearchButton);
	element.click();
	return new ElectronicsPage(driver);
}

public static void selectType(String searchtext,String type)
{
	element=formElementLocator(SearchType);//driver.findElement(SearchType);
	//element.click();
	selectDropdown(element,searchtext,type);	
}
//search the text in searchtextbox and and then click on search button
public static void searchAndClick(String text)
{
	element=driver.findElement(SearchBox);
	element.sendKeys(text);
	element=driver.findElement(SearchButton);
	element.click();
}

public static void clickCart()
{
	element=driver.findElement(Cart);
	element.click();
}

public static void moveOverAccountsLists()
{
	element=driver.findElement(AccountsLists);
	performMouseOver(element,driver);
}
	
	
}
