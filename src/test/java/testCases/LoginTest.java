package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class LoginTest extends BaseClass {

	@Test(groups={"sanity"})
	public void login()
	{
	  logger.info("Starting login test ..........");
	  LoginPage login=new LoginPage(driver);
	  login.enterUsername(p.getProperty("username"));
	  logger.info("Entered username......");
	  login.enterPassword(p.getProperty("password"));
      logger.info("Entered password........");
	  login.clickLogin(); 	
      HomePage homepage=new HomePage(driver);
      Assert.assertTrue(homepage.dashboard.isDisplayed());
      logger.info("Login successful");
	  
	}
	
	
	
}
