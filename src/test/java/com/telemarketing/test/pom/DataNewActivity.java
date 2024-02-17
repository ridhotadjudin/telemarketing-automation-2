package com.telemarketing.test.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DataNewActivity {
	
	protected WebDriver driver;

	public DataNewActivity(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy(id = "ui-id-61")
	public WebElement txtNewActivity;
	public String getTextNewActivity() {return txtNewActivity.getText();}
	
	
}
