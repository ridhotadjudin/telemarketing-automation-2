package com.telemarketing.test.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	protected WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy(xpath = "//span[@id='ui-id-1']")
	protected WebElement txtLogin;
	public String getTextLogin() {return txtLogin.getText();}
	
	@FindBy(id = "tl_login-1-51550_text")
	protected WebElement inputUsername;
	@FindBy(id = "tl_login-1-51551_text")
	protected WebElement inputPassword;
	@FindBy(xpath = "//span[normalize-space()='Sign In']")
	protected WebElement btnSignIn;
	@FindBy(id = "tl_login-1-51550_finder")
	protected WebElement btnGreenUsername;
	@FindBy(id = "tl_login-1-51551_finder")
	protected WebElement btnGreenPassword;
	
	public MainPage loginToMainPage(String inUser, String inPass, String click) {
		inputUsername.clear();
		inputUsername.sendKeys(inUser);
		inputPassword.clear();
		inputPassword.sendKeys(inPass);
		if(click.toString().equalsIgnoreCase("username")) {
			btnGreenUsername.click();
		}else if(click.toString().equalsIgnoreCase("password")) {
			btnGreenUsername.click();
		}else {
			btnSignIn.click();
		}
		return PageFactory.initElements(driver, MainPage.class);
	}
	public MainPage loginToMainPage() {
		inputUsername.clear();
		inputUsername.sendKeys("developer");
		inputPassword.clear();
		inputPassword.sendKeys("23");
		btnSignIn.click();
		return PageFactory.initElements(driver, MainPage.class);
	}
	
	@FindBy(xpath = "(//span[@id='ui-id-2'])[1]")
	public WebElement txtPeringatanLogin;
	@FindBy(xpath = "//span[normalize-space()='OK']")
	public WebElement btnOKPeringatan;
	@FindBy(xpath = "(//button[@title='Close'])[2]")
	public WebElement btnCancelPeringatan;
	
}
