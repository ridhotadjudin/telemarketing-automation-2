package com.telemarketing.test.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DataAgreePage extends MainPage {

	protected WebDriver driver;

	public DataAgreePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@FindBy(id = "tl_agree--52771_text")
	protected WebElement txtDataAgree;

	public String getTextAgree() {
		return txtDataAgree.getAttribute("value");
	}
}