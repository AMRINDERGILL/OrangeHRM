package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class LoginTestDDT extends BaseClass{

	@Test(dataProvider="logintest",dataProviderClass=DataProviders.class)
	public void login(String username,String password)
	{
	  logger.info("Starting login test ..........");
	  LoginPage login=new LoginPage(driver);
	  login.enterUsername(username);
	  logger.info("Entered username......");
	  login.enterPassword(password);
      logger.info("Entered password........");
	  login.clickLogin(); 	
      HomePage homepage=new HomePage(driver);
      Assert.assertTrue(homepage.dashboard.isDisplayed());
      logger.info("Login successful");
	}
	
}
