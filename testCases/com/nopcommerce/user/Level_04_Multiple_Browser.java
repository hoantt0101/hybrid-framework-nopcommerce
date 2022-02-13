package com.nopcommerce.user;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import pageObjects.HomePageObject;
import pageObjects.RegisterPageObject;

public class Level_04_Multiple_Browser extends BaseTest{

	WebDriver driver;
	HomePageObject homePage;
	RegisterPageObject registerPage;
	BasePage basePage;
	String projectPath = System.getProperty("user.dir");

	private String generateEmail() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyymmddHHmmss");
		LocalDateTime now = LocalDateTime.now();
		String email = "email" + format.format(now).toString() + "@gmai.com";
		return email;
	}
	
	@Parameters ("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		
		driver = getBrowserDriver(browserName);
		
		homePage = new HomePageObject(driver);
		registerPage = new RegisterPageObject(driver);
		basePage = new BasePage();
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");

	}
	@Test
	public void TC_01_Register_Empty_Data() {
		
		homePage.clickToRegisterLink();
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getErrorMessageAtFirstNameTextbox(), "First name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtLastNameTextbox(), "Last name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Email is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "Password is required.");

	}
	
	@Test
	public void TC_02_Register_Invalid_Email() {
		homePage.clickToRegisterLink();
		registerPage.sendKeyToFirstNameTextbox("Hoa");
		registerPage.sendKeyToLastNameTextbox("Test");
		registerPage.sendKeyToEmailTextbox("abcd@");
		registerPage.sendKeyToPasswordTextbox("Abcd@1234");
		registerPage.sendKeyToConfirmPasswordTextbox("Abcd@1234");
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Wrong email");
		
	}
	/*
	 * @Test public void TC_03_Register_Success() {
	 * 
	 * homePage.clickToRegisterLink();
	 * registerPage.sendKeyToFirstNameTextbox("Hoa");
	 * registerPage.sendKeyToLastNameTextbox("Test");
	 * registerPage.sendKeyToEmailTextbox(generateEmail());
	 * registerPage.sendKeyToPasswordTextbox("Abcd@1234");
	 * registerPage.sendKeyToConfirmPasswordTextbox("Abcd@1234");
	 * registerPage.clickToRegisterButton(); registerPage.clickToRegisterButton();
	 * Assert.assertEquals(registerPage.getRegisterSuccessfullyMessage(),
	 * "Your registration completed");
	 * 
	 * registerPage.clickToLogoutButton();
	 * 
	 * }
	 * 
	 * @Test public void TC_04_Register_Existing_Email() {
	 * 
	 * homePage.clickToRegisterLink();
	 * registerPage.sendKeyToFirstNameTextbox("Hoa");
	 * registerPage.sendKeyToLastNameTextbox("Test");
	 * registerPage.sendKeyToEmailTextbox("hoantt1@mailinator.com");
	 * registerPage.sendKeyToPasswordTextbox("Abcd@1234");
	 * registerPage.sendKeyToConfirmPasswordTextbox("Abcd@1234");
	 * 
	 * registerPage.clickToRegisterButton(); registerPage.clickToRegisterButton();
	 * 
	 * Assert.assertEquals(registerPage.getRegisterWithExistedEmailMessage(),
	 * "The specified email already exists"); }
	 * 
	 * @Test public void TC_05_Register_Password_Less_Than_6_Chars() {
	 * 
	 * homePage.clickToRegisterLink();
	 * registerPage.sendKeyToFirstNameTextbox("Hoa");
	 * registerPage.sendKeyToLastNameTextbox("Test");
	 * registerPage.sendKeyToEmailTextbox(generateEmail());
	 * registerPage.sendKeyToPasswordTextbox("Abcd");
	 * registerPage.sendKeyToConfirmPasswordTextbox("Abcd");
	 * registerPage.clickToRegisterButton();
	 * Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(),
	 * "Password must meet the following rules:\nmust have at least 6 characters");
	 * }
	 * 
	 * @Test public void TC_06_Register_Invalid_Confirm_Password() {
	 * 
	 * homePage.clickToRegisterLink();
	 * registerPage.sendKeyToFirstNameTextbox("Hoa");
	 * registerPage.sendKeyToLastNameTextbox("Test");
	 * registerPage.sendKeyToEmailTextbox(generateEmail());
	 * registerPage.sendKeyToPasswordTextbox("Abcd@1234");
	 * registerPage.sendKeyToConfirmPasswordTextbox("abcdjkjssd");
	 * registerPage.clickToRegisterButton();
	 * Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(),
	 * "The password and confirmation password do not match."); }
	 */

	@AfterClass
	public void afterClass() {
		driver.quit();
		
	}
}
