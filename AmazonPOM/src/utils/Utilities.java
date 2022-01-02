package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import base.BaseClass;
import main.CustomUserDefinedExceptions;

public class Utilities extends BaseClass {
	
	public static Workbook wkb=null;//entire excel sheet
	public static Sheet sheet=null;//sheetname
	public static Row row=null;//Row
	public static Cell cell=null;//Row- cell:Column
	public static int rowcount=0;
	public static int colcount=0;
	public static Object[][] sheetdata=null;
	
	public Utilities(WebDriver driver1)
	{
		super(driver1);
		driver=driver1;
	}

	public Utilities()
	{
		super();
	}
	
	public static String getTitle()
	{
		return driver.getTitle();
	}
	
	public static Object getRowCellData(int rownum,int colnum)
	{
		return sheetdata[rownum][colnum];
	}
	
	public static void print2DArray()
	{
		for(int i=0;i<sheetdata.length;i++)
		{	
			for(int j=0;j<sheetdata[i].length;j++)
			{
				System.out.print(sheetdata[i][j]+" ");				
			}
		  System.out.println();
		}  
	}

	// Read from Excel Sheet
	public static Object[][] readFromExcel1(String filePath,String fileName)
	{
		
		try {
			File file=new File(filePath);
			String extn="";
			//WorkBook,Sheet, Row, Cell
			FileInputStream fip=new FileInputStream(file);
			extn=fileName.substring(fileName.indexOf(".")+1);//xlsx xls
			switch(extn)
			{
			case "xlsx":
			      wkb=new XSSFWorkbook(fip);
			break;
			
			case "xls":
				  wkb=new HSSFWorkbook(fip);
			break;	
			
			default:
				System.out.println("Invalid extension, please provide proper filename with correct extension: xls or xlsx");
			}
			sheet=wkb.getSheet(getProperty("Sheet"));
            rowcount=sheet.getLastRowNum()-sheet.getFirstRowNum();
            row=sheet.getRow(0);
            colcount=row.getLastCellNum()-row.getFirstCellNum();
            System.out.println("rowcount:"+rowcount+" colcount:"+colcount);
            sheetdata=new Object[rowcount][colcount];
            for(int i=0;i<rowcount;i++)//row iteration
            	for(int j=0;j<colcount;j++)
            	{
            		row=sheet.getRow(i+1);
            		cell=row.getCell(j);         		
            		
            		if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC)
            			sheetdata[i][j]=cell.getNumericCellValue();        			
            		
            		else if(cell.getCellType()==Cell.CELL_TYPE_STRING)
            		{	
             		    //cell.getCellType()
            			sheetdata[i][j]=cell.getStringCellValue();
             			
            		}	
            	}
             				
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException found in readFromExcel:"+e.getMessage());
		}
		 catch (Exception e1) {
				System.out.println("Exception found in readFromExcel:"+e1.getMessage());
		}
      return sheetdata;
	}
	// Read from Excel Sheet
	public static void readFromExcel(String filePath,String fileName)
	{
		
		try {
			File file=new File(filePath);
			String extn="";
			//WorkBook,Sheet, Row, Cell
			FileInputStream fip=new FileInputStream(file);
			extn=fileName.substring(fileName.indexOf(".")+1);//xlsx xls
			switch(extn)
			{
			case "xlsx":
			      wkb=new XSSFWorkbook(fip);
			break;
			
			case "xls":
				  wkb=new HSSFWorkbook(fip);
			break;	
			
			default:
				System.out.println("Invalid extension, please provide proper filename with correct extension: xls or xlsx");
			}
			sheet=wkb.getSheet(getProperty("Sheet"));
            rowcount=sheet.getLastRowNum()-sheet.getFirstRowNum();
            row=sheet.getRow(0);
            colcount=row.getLastCellNum()-row.getFirstCellNum();
            System.out.println("rowcount:"+rowcount+" colcount:"+colcount);
            sheetdata=new Object[rowcount][colcount];
            for(int i=0;i<rowcount;i++)//row iteration
            	for(int j=0;j<colcount;j++)
            	{
            		row=sheet.getRow(i+1);
            		cell=row.getCell(j);         		
            		
            		if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC)
            			sheetdata[i][j]=cell.getNumericCellValue();        			
            		
            		else if(cell.getCellType()==Cell.CELL_TYPE_STRING)
            		{	
             		    //cell.getCellType()
            			sheetdata[i][j]=cell.getStringCellValue();
             			
            		}	
            	}
             				
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException found in readFromExcel:"+e.getMessage());
		}
		 catch (Exception e1) {
				System.out.println("Exception found in readFromExcel:"+e1.getMessage());
		}

	}
	
	//Object[][]
	
	
	// Write from Excel Sheet
	public static void writeToExcel(String filePath,String fileName,String sheetname, int rownum, HashMap<Integer,Object> hashmp)
	{
		File file=null;
		FileInputStream fip=null;
		FileOutputStream fop=null;
		
	  try {	
		file=new File(filePath);
		String extn="";
		//WorkBook,Sheet, Row, Cell
		fip=new FileInputStream(file);
		extn=fileName.substring(fileName.indexOf(".")+1);//xlsx xls
		switch(extn)
		{
		case "xlsx":
		      wkb=new XSSFWorkbook(fip);
		break;
		
		case "xls":
			  wkb=new HSSFWorkbook(fip);
		break;	
		
		default:
			System.out.println("Invalid extension, please provide proper filename with correct extension: xls or xlsx");
		}
		sheet=wkb.getSheet(getProperty("Sheet"));
        row=sheet.getRow(rownum);
        
		Set<Entry<Integer,Object>> setEntries=hashmp.entrySet();
		Iterator<Entry<Integer,Object>> ItrsetEntries= setEntries.iterator();
		while(ItrsetEntries.hasNext())//3 times loop will iterate
		{
			Entry<Integer,Object> entry=ItrsetEntries.next();	
			Integer intval=entry.getKey();
			cell=row.getCell(intval);//if cell wasnt part of excel sheet then use row.createCell(intval)
			if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC)
			 cell.setCellValue((double) entry.getValue());
			else if(cell.getCellType()==Cell.CELL_TYPE_STRING)
			 cell.setCellValue((String)entry.getValue());	
		}
		fop=new FileOutputStream(file);
		wkb.write(fop);		
		
      }
      catch(Exception e)
      {
    	  System.out.println("Exception caught in writeToExcel"+e.getMessage());
      }
	  finally {
		  try {
			fip.close();
			fop.close();
			wkb.close();
		} catch (IOException e) {
			System.out.println("IOException caught in writeToExcel"+e.getMessage());
		}
		  catch(Exception e1)
		  {
			  System.out.println("Exception caught in writeToExcel"+e1.getMessage());
		  }
		 
		  
	  }
		
	}
	
	public static void getValue(int row, int col)
	{
		
	}
		
	
	
	public static String getText(WebElement element)
	{
		return element.getText();
	}
	
	public static void buttonInteraction()
	{
		
	}
	
	public static void sendText(WebElement element,String text)
	{
		element.sendKeys(text);
	}
	
	//if elements are of type <li> then such child elements can be selectable
	public static void selectDropdown(WebElement dropdown,String text,String type)//type=value or text
	{
		try {
		Select select=new Select(dropdown);
		//select.selectByValue(text);
		switch(type)
		{
		case "text":     
     		select.selectByVisibleText(text);
         break;
        
		case "value":
			 select.selectByValue(text);
		 break;
		 
		case "index":
			 int index=0;
			 index=Integer.parseInt(text);
			 select.selectByIndex(index);
			 break;
			 	
		case "default":
			System.out.println("Invalid case, either provide text or value");
			break;
		}
		
		}
		catch(Exception e)
		{
			System.out.println("Exception caught in selectDropdown:"+e.getMessage());
		}
	}
	
	
	public static void performMouseOver(WebElement element,WebDriver driver)
	{
		Actions action=new Actions(driver);
		action.moveToElement(element).perform();	
	}

	public static WebElement formElementLocator(By by)
	{
		return driver.findElement(by);
	}
	
	public static WebElement formElementLocator(String type,String locator)
	{
		type=type.toLowerCase();
		WebElement element=null;
		
		switch(type)
		{
		case "id":
			element=driver.findElement(By.id(locator));
		break;
		
		case "xpath":
			element=driver.findElement(By.xpath(locator));
		break;
		
		case "css":
			element=driver.findElement(By.cssSelector(locator));
		break;
		
		case "linktext":
			element=driver.findElement(By.linkText(locator));
		break;
		
		case "partiallinktext":
			element=driver.findElement(By.partialLinkText(locator));
		break;
		
		case "classname":
			element=driver.findElement(By.className(locator));
		break;
		
		case "name":
			element=driver.findElement(By.name(locator));
		break;
			
		case "tagname":
			element=driver.findElement(By.tagName(locator));
		break;
		
		case "default":
			System.out.println("Not a valid locator strategy");;
		    try {
				throw new CustomUserDefinedExceptions("InvalidLocatorException");
			} catch (CustomUserDefinedExceptions e) {
				System.out.println("CustomUserDefinedExceptions caught in formElementLocator:"+e.getMessage());
			}
			break;
		}
		
		return element;
	}
	
	public String[] splitData(String name,String regExp)
	{
		return name.split(regExp);
	}
	
	public void takeSreenshot(String FileName,String extension)
	{
		Date date=new Date();
		long var=date.getTime();
		try {
		File file=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);  //. getScreenshotAs
	    File destFile=new File(System.getProperty("user.dir")+"//Screenshots//"+FileName+var+extension);
		FileUtils.moveFile(file, destFile);
		} catch (IOException e) {
			System.out.println("IOException caught in takeScreenshot method"+e.getMessage());
		}	
	}

	public static void main(String args[])
	{
		Utilities utils=new Utilities();
		readFromExcel(System.getProperty("user.dir")+getProperty("ExcelRelativeFilePath"),getProperty("ExcelFileName"));
		print2DArray();
		
		HashMap<Integer,Object> hashmp=new HashMap<Integer,Object>();
		/*hashmp.put(Integer.parseInt(getProperty("OP-ProfileNameIndex")), "Srini");
		hashmp.put(Integer.parseInt(getProperty("OP-StatusIndex")), "Passed");
		hashmp.put(Integer.parseInt(getProperty("OP-TitleIndex")), "Selenium WebDriver");
		
		hashmp.put(Integer.parseInt(getProperty("OP-ProfileNameIndex")), "Vivek");
		hashmp.put(Integer.parseInt(getProperty("OP-StatusIndex")), "Passed");
		hashmp.put(Integer.parseInt(getProperty("OP-TitleIndex")), "Selenium WebDriver");*/
	
		hashmp.put(Integer.parseInt(getProperty("OP-ProfileNameIndex")), "Rahul");
		hashmp.put(Integer.parseInt(getProperty("OP-StatusIndex")), "Distinction");
		hashmp.put(Integer.parseInt(getProperty("OP-TitleIndex")), "1Selenium WebDriver");
	
	    writeToExcel(getProperty("FilePath"),getProperty("ExcelFileName"),getProperty("Sheet"),1,hashmp);
	}
	
	
}
