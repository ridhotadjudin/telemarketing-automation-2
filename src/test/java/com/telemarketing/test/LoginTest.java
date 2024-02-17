package com.telemarketing.test;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.telemarketing.test.pom.LoginPage;
import com.telemarketing.test.pom.MainPage;

public class LoginTest {

	protected WebDriver driver;
	protected LoginPage loginPage;
	protected MainPage mainPage;

	public void delay(int inInt) {
		try {
			Thread.sleep(inInt * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public String screenShot() {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String waktu = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		String namaFile = "D:\\selenium-workspace\\Telemarketing\\hasilSS\\" + waktu + ".png";
		File screenshot = new File(namaFile);
		try {
			FileUtils.copyFile(srcFile, screenshot);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return namaFile;
	}

	@BeforeTest
	public void init() {
		System.setProperty("url", "https://sqa.peluangkerjaku.com/tele/");
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
		loginPage = new LoginPage(driver);
		mainPage = new MainPage(driver);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(System.getProperty("url"));
	}

	@BeforeMethod
	public void cekSession() {
		driver.get(System.getProperty("url"));
	}

	@Test(priority = 1)
	public void test_login_username_valid_password_valid() {
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		MainPage mainPage = loginPage.loginToMainPage("developer", "23", "ok");

		String parentWindowHandler = driver.getWindowHandle();
		String subWindowHandler = null;
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()) {
			subWindowHandler = iterator.next();
		}
		delay(1);
		driver.switchTo().window(subWindowHandler);
		assertEquals(mainPage.getTextPopUp(), "Information");

		delay(1);
		mainPage.clickOKPopUpAfterLogin();
		driver.switchTo().window(parentWindowHandler);

		delay(2);
		assertEquals(mainPage.getTextMainPage(), "Tele Marketing");
		String file = "<img src='file://" + screenShot() + "'height=\"450\" width=\"1017\"/>";
		Reporter.log(file);
		
		delay(1);
		mainPage.clickBtnLogoutAtMain();

		delay(1);
		assertEquals(mainPage.getTextPopUp(), "Logout");
		mainPage.logout();

		delay(1);
		assertEquals(loginPage.getTextLogin(), "[d] LOGIN");
	}

	@Test(priority = 2)
	public void test_login_username_valid_password_invalid() {
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.loginToMainPage("developer", "400", "ok");

		String parentWindowHandler = driver.getWindowHandle();
		String subWindowHandler = null;
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()) {
			subWindowHandler = iterator.next();
		}
		delay(1);
		driver.switchTo().window(subWindowHandler);
		assertEquals(loginPage.txtPeringatanLogin.getText(), "Peringatan");
		String file = "<img src='file://" + screenShot() + "'height=\"450\" width=\"1017\"/>";
		Reporter.log(file);
		
		delay(1);
		loginPage.btnOKPeringatan.click();
		driver.switchTo().window(parentWindowHandler);
	}

	@Test(priority = 3)
	public void test_login_username_invalid_password_invalid() {
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.loginToMainPage("mamam", "kue", "ok");

		String parentWindowHandler = driver.getWindowHandle();
		String subWindowHandler = null;
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()) {
			subWindowHandler = iterator.next();
		}
		delay(1);
		driver.switchTo().window(subWindowHandler);
		assertEquals(loginPage.txtPeringatanLogin.getText(), "Peringatan");
		String file = "<img src='file://" + screenShot() + "'height=\"450\" width=\"1017\"/>";
		Reporter.log(file);
		
		delay(1);
		loginPage.btnOKPeringatan.click();
		driver.switchTo().window(parentWindowHandler);
	}

	@Test(priority = 4)
	public void test_login_username_empty_password_empty() {
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.loginToMainPage("", "", "ok");

		String parentWindowHandler = driver.getWindowHandle();
		String subWindowHandler = null;
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()) {
			subWindowHandler = iterator.next();
		}
		delay(1);
		driver.switchTo().window(subWindowHandler);
		assertEquals(loginPage.txtPeringatanLogin.getText(), "Peringatan");
		String file = "<img src='file://" + screenShot() + "'height=\"450\" width=\"1017\"/>";
		Reporter.log(file);

		delay(1);
		loginPage.btnOKPeringatan.click();
		driver.switchTo().window(parentWindowHandler);
	}

	@Test(priority = 5)
	public void testLogin_usernameAndPasswordValid_clickGreenUsername() {
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		MainPage mainPage = loginPage.loginToMainPage("developer", "23", "username");

		String parentWindowHandler = driver.getWindowHandle();
		String subWindowHandler = null;
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()) {
			subWindowHandler = iterator.next();
		}
		delay(1);
		driver.switchTo().window(subWindowHandler);
		assertEquals(mainPage.getTextPopUp(), "Information");

		delay(1);
		mainPage.clickOKPopUpAfterLogin();
		driver.switchTo().window(parentWindowHandler);

		delay(2);
		assertEquals(mainPage.getTextMainPage(), "Tele Marketing");
		String file = "<img src='file://" + screenShot() + "'height=\"450\" width=\"1017\"/>";
		Reporter.log(file);
		
		delay(1);
		mainPage.clickBtnLogoutAtMain();

		delay(1);
		assertEquals(mainPage.getTextPopUp(), "Logout");
		mainPage.logout();

		delay(1);
		assertEquals(loginPage.getTextLogin(), "[d] LOGIN");
	}
	
	@Test(priority = 6)
	public void testLogin_usernameAndPasswordValid_clickGreenPassword() {
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		MainPage mainPage = loginPage.loginToMainPage("developer", "23", "password");

		String parentWindowHandler = driver.getWindowHandle();
		String subWindowHandler = null;
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()) {
			subWindowHandler = iterator.next();
		}
		delay(1);
		driver.switchTo().window(subWindowHandler);
		assertEquals(mainPage.getTextPopUp(), "Information");

		delay(1);
		mainPage.clickOKPopUpAfterLogin();
		driver.switchTo().window(parentWindowHandler);

		delay(2);
		assertEquals(mainPage.getTextMainPage(), "Tele Marketing");
		String file = "<img src='file://" + screenShot() + "'height=\"450\" width=\"1017\"/>";
		Reporter.log(file);
		
		delay(1);
		mainPage.clickBtnLogoutAtMain();

		delay(1);
		assertEquals(mainPage.getTextPopUp(), "Logout");
		mainPage.logout();

		delay(1);
		assertEquals(loginPage.getTextLogin(), "[d] LOGIN");
		
	}
	
	@AfterTest
	public void close() {
		driver.close();
	}

}
