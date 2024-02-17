package com.telemarketing.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.telemarketing.test.pom.DataAgreePage;
import com.telemarketing.test.pom.DataNewActivity;
import com.telemarketing.test.pom.DataNewPage;
import com.telemarketing.test.pom.LoginPage;
import com.telemarketing.test.pom.MainPage;
import com.telemarketing.test.utility.Utility;

public class TaskNewTest {

	protected WebDriver driver;
	protected LoginPage loginPage;
	protected MainPage mainPage;
	
	protected DataNewPage newPage;
	protected DataNewActivity newAct;
	
	protected DataAgreePage agreePage;

	protected Utility util;

	private File getLatestFilefromDir(String dirPath) {
		File dir = new File(dirPath);
		File[] files = dir.listFiles();
		if (files == null || files.length == 0) {
			return null;
		}

		File lastModifiedFile = files[0];
		for (int i = 1; i < files.length; i++) {
			if (lastModifiedFile.lastModified() < files[i].lastModified()) {
				lastModifiedFile = files[i];
			}
		}
		return lastModifiedFile;
	}

	@BeforeTest
	public void init() {
		System.setProperty("url", "https://sqa.peluangkerjaku.com/tele/");
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
		loginPage = new LoginPage(driver);
		mainPage = new MainPage(driver);
		newPage = new DataNewPage(driver);
		newAct = new DataNewActivity(driver);
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

	@DataProvider(name = "validData")
	public Object[] validData() {
		Object[] myData = { "Passat", "1", "Toko_Adil_3" };
		return myData;
	}

	@Test(priority = 1, dataProvider = "validData")
	public void test_search_valid(String in) {
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		MainPage mainPage = loginPage.loginToMainPage();

		String parentWindowHandler = driver.getWindowHandle();
		String subWindowHandler = null;
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()) {
			subWindowHandler = iterator.next();
		}
		util.delay(1);
		driver.switchTo().window(subWindowHandler);
		mainPage.clickOKPopUpAfterLogin();
		driver.switchTo().window(parentWindowHandler);

		util.delay(2);
		mainPage.clickTask();
		util.delay(1);
		DataNewPage newPage = mainPage.clickDataNew();
		util.delay(2);
		newPage.searchDataNew(in);

		util.delay(1);
		List<WebElement> lstElement = driver.findElements(By.xpath("(//tr)[41]"));
		String expectedChar = in;
		boolean check = false;
		for (WebElement webElement : lstElement) {
			if (webElement.getText().contains(expectedChar)) {
				check = true;
				util.delay(3);
				break;
			}
		}
		assertTrue(check);
		util.delay(2);

		mainPage.clickBtnLogoutAtMain();
		util.delay(2);
		newPage.logout();
		util.delay(2);
	}

	@DataProvider(name = "unusualData")
	public Object[] unusualData() {
		Object[] myData = { "", "@", "&", "_" };
		return myData;
	}

	@Test(priority = 2, dataProvider = "unusualData")
	public void test_search_empty_and_unusual(String in) {
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		MainPage mainPage = loginPage.loginToMainPage();

		String parentWindowHandler = driver.getWindowHandle();
		String subWindowHandler = null;
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()) {
			subWindowHandler = iterator.next();
		}
		util.delay(1);
		driver.switchTo().window(subWindowHandler);
		mainPage.clickOKPopUpAfterLogin();
		driver.switchTo().window(parentWindowHandler);

		util.delay(2);
		mainPage.clickTask();
		util.delay(1);
		DataNewPage newPage = mainPage.clickDataNew();
		util.delay(2);
		newPage.searchDataNew(in);

		util.delay(1);
		List<WebElement> lstElement = driver.findElements(By.xpath("(//tr)[41]"));
		String expectedChar = in;
		boolean check = false;
		for (WebElement webElement : lstElement) {
			if (webElement.getText().contains(expectedChar)) {
				check = true;
				util.delay(3);
				break;
			}
		}
		assertTrue(check);
		util.delay(2);
		mainPage.clickBtnLogoutAtMain();
		util.delay(2);
		newPage.logout();
		util.delay(2);
	}

	@Test(priority = 3)
	public void test_export_dataNew() {
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		MainPage mainPage = loginPage.loginToMainPage();

		String parentWindowHandler = driver.getWindowHandle();
		String subWindowHandler = null;
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()) {
			subWindowHandler = iterator.next();
		}
		util.delay(1);
		driver.switchTo().window(subWindowHandler);
		mainPage.clickOKPopUpAfterLogin();
		driver.switchTo().window(parentWindowHandler);

		util.delay(2);
		mainPage.clickTask();
		util.delay(1);
		DataNewPage newPage = mainPage.clickDataNew();

		util.delay(1);
		newPage.clickExportNew();
		util.delay(3);
		String downloadPath = "C:\\Users\\nexsoft\\Downloads";
		File getLatestFile = getLatestFilefromDir(downloadPath);
		String fileName = getLatestFile.getName();
		assertTrue(fileName.contains("exportnew"), "Data tidak ada/tidak sesuai");
		util.delay(3);
		mainPage.clickBtnLogoutAtMain();
		util.delay(2);
		newPage.logout();
		util.delay(2);
	}
	
	@Test(priority = 4)
	public void test_clickTable_gotoActivity() {
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		MainPage mainPage = loginPage.loginToMainPage();

		util.delay(1);
		mainPage.clickOKPopUpAfterLogin();

		util.delay(1);
		mainPage.clickTask();
		util.delay(1);
		DataNewPage newPage = mainPage.clickDataNew();
		util.delay(1);
		newPage.searchDataNew("lumina");
		util.delay(1);
//		DataNewActivity newAct = newPage.clickTableToActivity();
		newPage.topTable.click();
		util.delay(5);
		System.out.println(newPage.txtNewActivity.getText());
		assertEquals(newPage.txtNewActivity.getText(),"[d] Activity");
		util.delay(10);
	}
	
	
//	@DataProvider(name = "viewData")
//	public Object[] viewData() {
//		Object[] myData = { 10, 25, 50, 100, 500 };
//		return myData;
//	}
//	
//	@Test(priority = 4,dataProvider="viewData")
//	public void test_view_functionality(int in) {
//		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
//		MainPage mainPage = loginPage.loginToMainPage();
//
//		util.delay(2);
//		mainPage.clickOKPopUpAfterLogin();
//
//		util.delay(2);
//		mainPage.clickTask();
//		util.delay(1);
//		DataNewPage newPage = mainPage.clickDataNew();
//		util.delay(1);
//		
//		newPage.viewTable(500);
//		util.delay(1);
//		assertEquals(newPage.check500.getText(),"500");
//		
//		util.delay(5);
//		
//		
//		mainPage.clickBtnLogoutAtMain();
//		util.delay(2);
//		newPage.logout();
//		util.delay(2);
//	}

	@AfterTest
	public void close() {
		File file = new File("C:\\Users\\nexsoft\\Downloads\\exportnew.xls");
		file.delete();
		driver.close();
	}
}
