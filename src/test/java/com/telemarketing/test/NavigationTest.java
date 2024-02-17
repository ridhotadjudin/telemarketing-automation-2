package com.telemarketing.test;

import static org.testng.Assert.assertEquals;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.telemarketing.test.pom.DataAgreePage;
import com.telemarketing.test.pom.DataNewPage;
import com.telemarketing.test.pom.LoginPage;
import com.telemarketing.test.pom.MainPage;
import com.telemarketing.test.utility.Utility;

public class NavigationTest {
	
	protected WebDriver driver;
	protected LoginPage loginPage;
	protected MainPage mainPage;
	protected DataNewPage newPage;
	protected DataAgreePage agreePage;
	
	
	protected Utility util;
	
	@BeforeTest
	public void init() {
		System.setProperty("url", "https://sqa.peluangkerjaku.com/tele/");
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
		loginPage = new LoginPage(driver);
		mainPage = new MainPage(driver);
		newPage = new DataNewPage(driver);
		agreePage = new DataAgreePage(driver);
		util = new Utility(driver);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(System.getProperty("url"));
	}

	@BeforeMethod
	public void cekSession() {
		driver.get(System.getProperty("url"));
	}

	@Test(priority = 1,enabled=false)
	public void testLogin() {
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		MainPage mainPage = loginPage.loginToMainPage("developer", "23", "ok");
		
		String parentWindowHandler = driver.getWindowHandle();
		String subWindowHandler = null;
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()){
		    subWindowHandler = iterator.next();
		}
		util.delay(1);
		driver.switchTo().window(subWindowHandler);
		assertEquals(mainPage.getTextPopUp(), "Information");
		util.delay(1);
		mainPage.clickOKPopUpAfterLogin();
		driver.switchTo().window(parentWindowHandler);
		
		util.delay(2);
		assertEquals(mainPage.getTextMainPage(), "Tele Marketing");
		
		
		
		//logout section
		mainPage.clickBtnLogoutAtMain();
		util.delay(1);
		assertEquals(mainPage.getTextPopUp(), "Logout");
		mainPage.logout();
		util.delay(1);
		assertEquals(loginPage.getTextLogin(), "[d] LOGIN");
	}
	
	@Test(priority = 2,enabled=false)
	public void testNavigate() {
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		MainPage mainPage = loginPage.loginToMainPage("developer", "23", "ok");
		
		String parentWindowHandler = driver.getWindowHandle();
		String subWindowHandler = null;
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()){
		    subWindowHandler = iterator.next();
		}
		//pop up
		util.delay(1);
		driver.switchTo().window(subWindowHandler);
		mainPage.clickOKPopUpAfterLogin();
		driver.switchTo().window(parentWindowHandler);
		
		util.delay(2);
		mainPage.clickTask();
		util.delay(1);
		DataNewPage newPage = mainPage.clickDataNew();
		util.delay(2);
//		String file = "<img src='file://" + util.screenShot() + "'height=\"450\" width=\"1017\"/>";
//		Reporter.log(file);
		assertEquals(newPage.getTextDataNewPage(),"DATA NEW");
		
		util.delay(1);
		DataAgreePage agreePage = mainPage.gotoDataAgreePage();
		util.delay(2);
//		file = "<img src='file://" + util.screenShot() + "'height=\"450\" width=\"1017\"/>";
//		Reporter.log(file);
		assertEquals(agreePage.getTextAgree(),"DATA AGREE");
		
		util.delay(1);
		mainPage.btnDataFollowUp.click();
		util.delay(2);
//		file = "<img src='file://" + util.screenShot() + "'height=\"450\" width=\"1017\"/>";
//		Reporter.log(file);
		assertEquals(mainPage.getTextFollowUp(),"DATA THINKING");
		
		util.delay(1);
		mainPage.btnDataAll.click();
		util.delay(2);
		assertEquals(mainPage.getTextAll(),"DATA ALL");
		
		util.delay(1);
		mainPage.btnDataFinal.click();
		util.delay(2);
		assertEquals(mainPage.getTextFinal(),"DATA FINAL");
		
		mainPage.clickBtnLogoutAtMain();
		
		util.delay(1);
		assertEquals(mainPage.getTextPopUp(), "Logout");
		mainPage.logout();
		
	}
	
	
	@AfterTest
	public void close() {
		driver.close();
	}
	
	
}
