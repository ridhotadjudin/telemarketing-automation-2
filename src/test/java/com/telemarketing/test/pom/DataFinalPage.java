package com.telemarketing.test.pom;

import org.openqa.selenium.WebDriver;

public class DataFinalPage extends MainPage{
	
	protected WebDriver driver;

	public DataFinalPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
}