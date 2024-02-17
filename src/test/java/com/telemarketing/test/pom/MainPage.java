package com.telemarketing.test.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {
	
	protected WebDriver driver;

	public MainPage(WebDriver driver) {
		this.driver = driver;
	}

	// main
	@FindBy(xpath = "//font[normalize-space()='Tele Marketing']")
	protected WebElement txtMainPage;
	public String getTextMainPage() {return txtMainPage.getText();}
	
	@FindBy(xpath = "//span[normalize-space()='DEVELOPER']")
	private WebElement btnLogoutMainPage;
	public void clickBtnLogoutAtMain() {btnLogoutMainPage.click();}
	
	@FindBy(xpath = "//h3[@id='ui-id-15']")
	protected WebElement btnTask;
	public void clickTask() {
		btnTask.click();
	}
	
	@FindBy(xpath = "//button[@id='52827_query']")
	protected WebElement btnDataNew;
	public DataNewPage clickDataNew() {
		btnDataNew.click();
		return PageFactory.initElements(driver, DataNewPage.class);
	}
	
	@FindBy(xpath = "//button[@id='52826_query']")
	public WebElement btnDataAgree;
	public DataAgreePage gotoDataAgreePage() {
		btnDataAgree.click();
		return PageFactory.initElements(driver, DataAgreePage.class);
	}
	
	
	@FindBy(xpath = "(//button[@id='52834_query'])[1]")
	public WebElement btnDataFollowUp;
	@FindBy(id = "tl_thinking--52762_text")
	protected WebElement txtDataFollowUp;
	public String getTextFollowUp() {return txtDataFollowUp.getAttribute("value");}
	
	@FindBy(xpath = "(//button[@id='52858_query'])[1]")
	public WebElement btnDataAll;
	@FindBy(id = "tl_all_data--53024_text")
	protected WebElement txtDataAll;
	public String getTextAll() {return txtDataAll.getAttribute("value");}
	
	@FindBy(xpath = "(//button[@id='52841_query'])[1]")
	public WebElement btnDataFinal;
	@FindBy(id = "tl_final--52228_text")
	protected WebElement txtDataFinal;
	public String getTextFinal() {return txtDataFinal.getAttribute("value");}
	
	
	
	// pop up login
	@FindBy(xpath = "//span[@id='ui-id-21']")
	protected WebElement txtPopUpOnMainPage;
	public String getTextPopUp() {return txtPopUpOnMainPage.getText();}
	@FindBy(xpath = "//span[normalize-space()='OK']")
	protected WebElement btnOKPopUpOnMainPage;
	public void clickOKPopUpAfterLogin() {btnOKPopUpOnMainPage.click();}
	@FindBy(xpath = "//span[@class='ui-button-icon-primary ui-icon ui-icon-closethick']")
	protected WebElement btnClosePopUpOnMainPage;
	public void clickCancelPopUpAfterLogin() {btnClosePopUpLogout.click();}
	
	// pop up logout
	@FindBy(xpath = "//span[@id='ui-id-21']")
	public WebElement txtPopUpLogout;
	@FindBy(xpath = "//span[@class='ui-button-icon-primary ui-icon ui-icon-closethick']")
	public WebElement btnClosePopUpLogout;
	@FindBy(xpath = "(//span[normalize-space()='TIDAK'])[1]") 
	public WebElement btnNoLogout;
	@FindBy(xpath = "(//span[normalize-space()='YA'])[1]")
	protected WebElement btnYesLogout;
	public LoginPage logout() {
		btnYesLogout.click();
		return PageFactory.initElements(driver, LoginPage.class);
	}
	
	
	
}
