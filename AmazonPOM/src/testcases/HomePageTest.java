package testcases;

import static pages.HomePage.*;
import static pages.SignInPage.*;
import org.openqa.selenium.WebDriver;
import base.BaseClass;
import pages.HomePage;
import pages.SignInPage;
import utils.Utilities;

public class HomePageTest extends BaseClass
{
	HomePage homepg;
	static SignInPage signIn;
	Utilities utils;
	BaseClass basecl;
	
	public HomePageTest()
	{
	 super();
	 homepg=new HomePage();
	 signIn=new SignInPage();
	 utils=new Utilities();
	}
	
	public HomePageTest(WebDriver driver)
	{
	 super(driver);
	 HomePageTest.driver=driver;
	}
	
	public static void main(String[] args)
	{
		HomePageTest testscr=new HomePageTest();
		System.out.println("Appltnurl:"+testscr.getProperty("applicationurl"));
	    setUp();
		moveOverAccountsLists();
		signIn=clickSignIn();
		EnterEMailMobNumber(getProperty("EmailorPhoneNumber"));
		clickContinue();
		EnterPassword(getProperty("Password"));
		clickSignInButton();
		//tearDown();
	}

}
