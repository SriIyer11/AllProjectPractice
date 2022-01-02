package testcases;

import static pages.HomePage.*;
import static pages.SignInPage.*;
import static utils.Utilities.*;
import static pages.ElectronicsPage.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.BaseClass;
import config.Consts;
import main.ListenersCl;
import pages.ElectronicsPage;
import pages.HomePage;
import pages.SignInPage;
import utils.Utilities;

@Listeners(main.ListenersCl.class)
public class HomePageTestNG extends BaseClass
{
	HomePage homepg;
	static SignInPage signIn;
	Utilities utils;
	BaseClass basecl;
	SoftAssert softassert;
	ElectronicsPage electr;
	HomePageTestNG obj;
	int count=0;
	
	public HomePageTestNG()
	{
	 super();
	 homepg=new HomePage();
	 signIn=new SignInPage();
	 utils=new Utilities();
	 softassert=new SoftAssert();
	 electr=new ElectronicsPage();
	}
	
	public HomePageTestNG(WebDriver driver)
	{
	 super(driver);
	 HomePageTestNG.driver=driver;
	}
	
	@Test(enabled=true,priority=1,description="testAmazon SignIn Testcase",groups= {"sanity","smoke","regression"})//highest priority
	public void testAmazon()//if dependent test method fails then this test method would be skipped
	{
		Reporter.log("In Testcase:testAmazon");
		System.out.println("1st Testcase");
		HomePageTestNG testscr=new HomePageTestNG();
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
	
	@DataProvider//(name="SearchDataProvider")
	public String[][] provideSearchData()
	{
		return new String[][] {{"Rahul","Electronics","rahul88@gmail.com","text"},{"Srini","Deals","sriniiyer86@gmail.com","text"}};
	}
	
	@DataProvider(name="TypeSearchDataProvider")
	public String[] provideSearchData1()
	{
		return new String[] {"Electronics","Furniture","Books","Appliances"};
	}

	public void Inputs(String username,String searchtext,String emailAddress,String Dropdowntype)
	{
		System.out.println("In searchAmazon testcase");
		Reporter.log("In Testcase:testAmazon");
		System.out.println("1st Testcase");
		HomePageTestNG obj=new HomePageTestNG();
		System.out.println("Appltnurl:"+getProperty("applicationurl"));
	    setUp();
        System.out.println("User logged in:"+username+" having email address:"+emailAddress);
        obj=new HomePageTestNG(driver);
        homepg=new HomePage(driver);
        utils=new Utilities(driver);       
        selectType(searchtext,Consts.Type);
        typeSearchBox(searchtext);
        electr=clickSearchButton();
        Assert.assertEquals(getTitle(),Consts.ExpectedTitle+searchtext);
        Assert.assertEquals(getTextLabel(),"\""+searchtext+"\"");
        tearDown();
	}
	
	
	@Test(priority=0,groups= {"dataProviderSearch"},description="Using TestNG data provider",dataProvider="provideSearchData")
	public void searchAmazon_useDataProvider(String username,String searchtext,String emailAddress,String Dropdowntype)
	{
       Inputs(username,searchtext,emailAddress,Dropdowntype);
	}	
	
	@DataProvider(name="ExcelDataProvider")//dataProvider="provideSearchData")
	public Object[][] provideDataFromExcel()
	{
		Object[][] data=Utilities.readFromExcel1(System.getProperty("user.dir")+getProperty("ExcelRelativeFilePath"),getProperty("ExcelFileName"));
	    return data;
	}	
	
	@Test(dataProvider="ExcelDataProvider",groups= {"dataExcelTest"})
	public void testExcelData(String SrNo,String TestCaseName,String StepsDescription,String DropdownValue,String UserName,String ProfileName,String Status,String Title)
	{
	   count=count+1;
	   System.out.println("RowData"+count+":");
	   System.out.println(SrNo+":"+TestCaseName+":"+StepsDescription+":"+DropdownValue+":"+UserName+":"+ProfileName+":"+Status+":"+Title);
	}
	
	@Parameters({"username","searchtext","emailAdress","Dropdowntype"})
	@Test(priority=0,groups= {"searchSanity"},description="Execute Search on Amazon HomePage")
	public void searchAmazon(String username,String searchtext,String emailAddress,String Dropdowntype)
	{
		Inputs(username,searchtext,emailAddress,Dropdowntype);
	}
	
	@Test(priority=0,groups= {"sanity"},description="testAmazon1 Testcase")//,dependsOnMethods= {"testAmazon"})
	public void testAmazon1()
	{
	
		System.out.println("2nd Testcase");
		Reporter.log("In Testcase:testAmazon1");
		//Assert.fail();
		softassert.assertTrue(2>3);		
	}
	
	@Test(priority=1,groups= {"sanity","smoke","regression"},description="DEF Amazon1 Testcase")
	public void DEFAmazon1()
	{
		Reporter.log("In Testcase:DEFAmazon1");
		System.out.println("3rd Testcase");
	}
	
	@Test(priority=4,groups= {"regression"},description="ABC Amazon Testcase")
	public void ABCAmazon1()
	{
		Reporter.log("In Testcase:ABCAmazon1");
		System.out.println("4th Testcase");
	}
	//TestMethod and not TestCase in TestNG
	@Test(priority=2,groups= {"smoke","regression"},description="XYZAmazon1 Testcase")//Test Method Annotation
	public void XYZAmazon1()
	{
		Reporter.log("In Testcase:XYZAmazon1");
		System.out.println("5th Testcase");
	}

	@AfterMethod(groups="sanityMethod")
	public void afteMethod()
	{
		System.out.println("AfterMethod");
		Reporter.log("AfterMethod");
		tearDown();
	}
	
	@AfterTest(enabled=false,groups= {"sanity"})
	public void afttest()
	{
	System.out.println("AfterTest");
	Reporter.log("AfterTest");	
	}
	
	@BeforeMethod(groups= {"sanityMethod","sanityMethod1"})
	public void b4Method()
	{
	System.out.println("BeforeMethod");
	Reporter.log("BeforeMethod");
	setUp();
	obj=new HomePageTestNG(driver);
    homepg=new HomePage(driver);
    utils=new Utilities(driver);
    basecl=new BaseClass(driver);
	}
	
	@BeforeTest(enabled=true,groups= {"sanityMethod","sanityMethod1","dataExcelTest"})
	public void b4Test()
	{
	System.out.println("@BeforeTest");
	Reporter.log("@BeforeTest");
	obj=new HomePageTestNG();
    homepg=new HomePage();
    utils=new Utilities();
    basecl=new BaseClass();
	}
	
	@Test(groups= {"sanityMethod1"})
	public void testSanity()
	{
	  String title=getTitle();
	  System.out.println("Title:"+title);	  
	  Assert.assertEquals(title, "Title1");
	}
	
	@Test(groups= {"sanityMethod1"})
	public void testSanity1()
	{
	  String title=getTitle();
	  System.out.println("Title:"+title);	  
	  //Assert.assertEquals(title, "Title");
	}
	
	@Test(groups= {"sanityMethod"},description="Data Provider check for searching in Amazon",dataProvider="TypeSearchDataProvider")
	public void testSanity1(String value)
	{
		/*obj=new HomePageTestNG(driver);
        homepg=new HomePage(driver);
        utils=new Utilities(driver);*/  
        selectType(value,"text");
        System.out.println("Dropdown value selected is:"+value);
        typeSearchBox(value);
        electr=clickSearchButton();  	
	}
	
	
	@BeforeClass(groups="sanity")
	public void b4Class()
	{
	System.out.println("BeforeClass");
	Reporter.log("BeforeClass");
	}	
	
	@BeforeSuite
	public void b4Suite()
	{
	System.out.println("BeforeSuite");
	Reporter.log("BeforeSuite");
	}	
	
	@AfterSuite
	public void afterSuite()
	{
	System.out.println("afterSuite");
	Reporter.log("afterSuite");
	}	
	@AfterClass(enabled=true,description="AfterClass method",groups="sanity")//(dependsOnMethods= {"testAmazon"})
	public void tearDownTestCase()
	{
		System.out.println("AfterClass");
		Reporter.log("AfterClass");
		//tearDown();
		//softassert.assertAll();
	}
}
