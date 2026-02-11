package pageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import testBase.BaseClass;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver)
	{
		super (driver);
	}
	
	@FindBy(xpath="//input[@name='username']")
	WebElement txtusername;
	
	@FindBy(xpath="//input[@name='password']")
	WebElement txtpassword;
	
	@FindBy(xpath="//button[contains(text(),Login)]")
	WebElement login_btn;
	
	
	
	public void enterUsername(String username)
	{
		txtusername.sendKeys(username);
	}

	public void enterPassword(String password)
	{
		txtpassword.sendKeys(password);
	}

	public void clickLogin()
	{
		login_btn.click();
	}



}
