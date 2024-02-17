package com.telemarketing.test.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DataNewPage extends MainPage {
	
	protected WebDriver driver;

	public DataNewPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	@FindBy(id = "tl_data_new--52964_text")
	protected WebElement txtDataNew;
	public String getTextDataNewPage() {return txtDataNew.getAttribute("value");}
	
	@FindBy(id = "tl_data_new--52963_text")
	protected WebElement inputSearchNew;
	@FindBy(id = "52969_query")
	protected WebElement btnSearchNew;
	
	public void searchDataNew(String inString) {
		inputSearchNew.clear();
		inputSearchNew.sendKeys(inString);
		btnSearchNew.click();
	}
	
	@FindBy(xpath = "(//button[@id='52971_query'])[1]")
	protected WebElement btnExportNew;
	public void clickExportNew() {btnExportNew.click();}
	
	@FindBy(xpath = "//option[@value='10']")
	protected WebElement value10;
	@FindBy(xpath = "//option[@value='25']")
	protected WebElement value25;
	@FindBy(xpath = "//option[@value='50']")
	protected WebElement value50;
	@FindBy(xpath = "//option[@value='100']")
	protected WebElement value100;
	@FindBy(xpath = "//option[@value='500']")
	protected WebElement value500;
	
	public void viewTable(int in) {
		switch (in) {
		case 10: value10.click(); break;
		case 25: value25.click(); break;
		case 50: value50.click(); break;
		case 100: value100.click(); break;
		case 500: value500.click(); break;
		default: break;
		}
	}
	
	@FindBy(xpath = "(//td[contains(text(),'500')])[3]")
	public WebElement check500;
	
	@FindBy(xpath = "(//tr)[41]")
	public WebElement topTable;
	public DataNewActivity clickTableToActivity() {
		topTable.click();
		return PageFactory.initElements(driver, DataNewActivity.class);
	}
	
	@FindBy(id = "tl_user_activity--53043_text")// tl_user_activity--53053_text
	public WebElement txtNewActivity;
	public String getTextNewActivity() {
		return txtNewActivity.getAttribute("value");
	}
}
