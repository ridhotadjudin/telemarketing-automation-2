package com.telemarketing.test.pom;

import org.openqa.selenium.WebDriver;

public class DataFollowUpPage extends MainPage{
	
	protected WebDriver driver;

	public DataFollowUpPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
}
