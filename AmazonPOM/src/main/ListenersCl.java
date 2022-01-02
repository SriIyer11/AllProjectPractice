package main;

import org.openqa.selenium.WebDriver;
import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


import base.BaseClass;
import utils.Utilities;

public class ListenersCl extends BaseClass implements ITestListener, IRetryAnalyzer
{
	
	
	@Retention(RetentionPolicy.RUNTIME)
	public @interface RetryCountIfFailed {//CustomAnnotation

		// Specify how many times you want to 
		// retry the test if failed.
		// Default value of retry count is 0,but we have customized value to 3.
		int value() default 3;
	}
	
 int counter=0;
	public ListenersCl()
	{
		System.out.println("Default ListenersCl Constructor");
	}
	
	public ListenersCl(WebDriver driver1)
	{
		System.out.println("Parameterized ListenersCl Constructor");
		driver=driver1;
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("TEST SUCCESS");	
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("TEST FAILURE");
		Utilities utill=new Utilities(driver);
		utill.takeSreenshot("FailureScreenshot",".jpg");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("TEST SKIPPED");		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	//Retry testcase execution
	@Override
	public boolean retry(ITestResult result) {
		RetryCountIfFailed annotation = result.getMethod().getConstructorOrMethod().getMethod()
				.getAnnotation(RetryCountIfFailed.class);
		// based on the value of annotation see if test needs to be rerun
		if((annotation != null) && (counter < annotation.value()))
		{
			counter++;
			return true;
		}
		return false;
	}

}
