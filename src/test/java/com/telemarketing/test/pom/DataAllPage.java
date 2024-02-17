package com.telemarketing.test.pom;

import org.openqa.selenium.WebDriver;

public class DataAllPage extends MainPage{
	
	protected WebDriver driver;

	public DataAllPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
}