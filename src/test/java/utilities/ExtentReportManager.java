package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	String reportPath;
	
public void onStart(ITestContext context) {
 String timestamp=new SimpleDateFormat("dd-MM-YYYY_HH.mm.ss").format(new Date());
 reportPath=".\\reports\\"+timestamp+".html";
 sparkReporter=new ExtentSparkReporter(reportPath);
 sparkReporter.config().setReportName("Automation Report");	     	  
 sparkReporter.config().setDocumentTitle("Orange HRM application");	 
 sparkReporter.config().setTheme(Theme.DARK);
 
 extent=new ExtentReports();
 extent.attachReporter(sparkReporter);
 extent.setSystemInfo("Application", "OrangeHRM");
 extent.setSystemInfo("Module", "Login");
 extent.setSystemInfo("Username", System.getProperty("user.name"));
 extent.setSystemInfo("Environment", "QA");
 
 String os=context.getCurrentXmlTest().getParameter("os");
 extent.setSystemInfo("Operating system", os);
 
 String browser=context.getCurrentXmlTest().getParameter("browser");
 extent.setSystemInfo("Browser", browser);
 
 List<String> includedGroups=context.getCurrentXmlTest().getIncludedGroups();	 
 if (!includedGroups.isEmpty())	
 {
	 extent.setSystemInfo("Groups", includedGroups.toString());
 }
	 
	 }
	
	public void onTestStart(ITestResult result) {
	
	  }

	 
	  
	 public void onTestSuccess(ITestResult result) {
		 test=extent.createTest(result.getTestClass().getName());
	     test.assignCategory(result.getMethod().getGroups());
		 test.log(Status.PASS, result.getName()+" got successfully executed");
	  }

	 
	 public void onTestFailure(ITestResult result) {
     test=extent.createTest(result.getClass().getName());
     test.assignCategory(result.getMethod().getGroups());
     test.log(Status.FAIL, result.getName()+"got failed");
     test.log(Status.INFO, result.getThrowable().getMessage());
     
     try
     {
    	String imgPath=new BaseClass().captureScreen(result.getName());
    	test.addScreenCaptureFromPath(imgPath);
     }
     catch (Exception e)
     {
    	 e.printStackTrace();
     }
	 
	 }

	 
	  public void onTestSkipped(ITestResult result) {
		     test=extent.createTest(result.getClass().getName());
		     test.assignCategory(result.getMethod().getGroups());
		     test.log(Status.SKIP, result.getName()+"got skipped");
		     test.log(Status.INFO, result.getThrowable().getMessage());
	  }

	 
	  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	    // not implemented
	  }

	  
	  public void onTestFailedWithTimeout(ITestResult result) {
	    onTestFailure(result);
	  }

	 
	 

	  
	  public void onFinish(ITestContext context) {
	    extent.flush();
	  
	  
	  
	  
	  }
	
	
	
	
	
	
	
	
	
	
	
	
	
}
