package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import config.Consts;

public class BaseClass {
public static WebDriver driver;
public static Properties prop;

//BaseClass of framework--Rahul changed it
public BaseClass()
{
	driver=null;
	prop=null;
	initializeProperties();
}
//base class conflict


public void testDemo()
{
 System.out.println("New method added");
 System.out.println("Extra code worked upon");
}

public BaseClass(WebDriver driver1)
{
	driver=driver1;
	prop=null;
	initializeProperties();
}

public static void setUp()//launch the browser
{
	System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+getProperty(Consts.ChromeDriverPath));
    driver=new ChromeDriver();
	driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    driver.get(getProperty(Consts.ApplicationUrl));        
}

public static void tearDown()//close/quit the browser
{
	//driver.close();
	driver.quit();//close all the open windows
	//close only current window where the window is focussed
}

public static void initializeProperties()
{
	String Filepath=System.getProperty("user.dir")+"//src//config//config.properties";
	System.out.println("Filepath:"+Filepath);	   	
   	//BufferedInputStream
   	try {
   		File file=new File(Filepath);
		FileInputStream fip=new FileInputStream(file);
		prop=new Properties();
		prop.load(fip);			
	} catch (FileNotFoundException e) {
		System.out.println("FileNotFoundException caught in initializeProperties:"+e.getMessage());
	}
   	catch(IOException e1)
   	{
		System.out.println("IOException caught in initializeProperties:"+e1.getMessage());	   		
   	}
   	
}

//get the property from config.properties file
public static String getProperty(String propertyName)
{
	return prop.getProperty(propertyName);
}

	
}
