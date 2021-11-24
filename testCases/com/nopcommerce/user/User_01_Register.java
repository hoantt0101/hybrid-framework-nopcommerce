package com.nopcommerce.user;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BasePage;

public class User_01_Register {

	WebDriver driver;
	BasePage basePage;
	String projectPath = System.getProperty("user.dir");

	private String generateEmail() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyymmddHHmmss");
		LocalDateTime now = LocalDateTime.now();
		String email = "email" + format.format(now).toString() + "@gmai.com";
		return email;
	}
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		//System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
	
		//driver = new ChromeDriver();
		driver = new FirefoxDriver();

		basePage = new BasePage();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");

	}
	@Test
	public void TC_01_Register_Empty_Data() {
		basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
		basePage.clickToElement(driver, "//a[@class='ico-register']");

		basePage.waitForElementClickable(driver, "//button[@id=\'register-button\']");
		basePage.clickToElement(driver, "//button[@id=\'register-button\']");

		Assert.assertEquals(basePage.getElementText(driver, "//span[@class='field-validation-error']"), "First name is required.");
		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='LastName-error']"), "Last name is required.");
		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Email-error']"), "Email is required.");
		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Password-error']"), "Password is required.");
		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='ConfirmPassword-error']"), "Password is required.");

	}
	
	@Test
	public void TC_02_Register_Invalid_Email() {
		basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
		basePage.clickToElement(driver, "//a[@class='ico-register']");
		basePage.sendKeyToElement(driver, "//input[@id='FirstName']", "Hoa");
		basePage.sendKeyToElement(driver, "//input[@id='LastName']", "Test");
		basePage.sendKeyToElement(driver, "//input[@id='Email']", "abcd@");
		basePage.sendKeyToElement(driver, "//input[@id='Password']", "Abcd@1234");
		basePage.sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "Abcd@1234");
		basePage.waitForElementClickable(driver, "//button[@id=\'register-button\']");
		basePage.clickToElement(driver, "//button[@id=\'register-button\']");
	
		//Assert.assertEquals(basePage.getElementText(driver, "//div[@class='message-error validation-summary-errors']//ul//li"), "Wrong email");
		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Email-error']"), "Wrong email");
		
	}
	@Test
	public void TC_03_Register_Success() {
		basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
		basePage.clickToElement(driver, "//a[@class='ico-register']");
		basePage.sendKeyToElement(driver, "//input[@id='FirstName']", "Hoa");
		basePage.sendKeyToElement(driver, "//input[@id='LastName']", "Test");
		basePage.sendKeyToElement(driver, "//input[@id='Email']", generateEmail());
		basePage.sendKeyToElement(driver, "//input[@id='Password']", "Abcd@1234");
		basePage.sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "Abcd@1234");
		basePage.waitForElementClickable(driver, "//button[@id=\'register-button\']");
		basePage.clickToElement(driver, "//button[@id=\'register-button\']");
	
		Assert.assertEquals(basePage.getElementText(driver, "//div[@class='result']"), "Your registration completed");
		
		basePage.waitForElementClickable(driver, "//a[@class='ico-logout']");
		basePage.clickToElement(driver, "//a[@class='ico-logout']");
		
	}

	@Test
	public void TC_04_Register_Existing_Email() {
		basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
		basePage.clickToElement(driver, "//a[@class='ico-register']");
		
		basePage.sendKeyToElement(driver, "//input[@id='FirstName']", "Hoa");
		basePage.sendKeyToElement(driver, "//input[@id='LastName']", "Test");
		basePage.sendKeyToElement(driver, "//input[@id='Email']", "hoantt1@mailinator.com");
		basePage.sendKeyToElement(driver, "//input[@id='Password']", "Abcd@1234");
		basePage.sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "Abcd@1234");
		
		basePage.waitForElementClickable(driver, "//button[@id=\'register-button\']");
		basePage.clickToElement(driver, "//button[@id=\'register-button\']");
		
		Assert.assertEquals(basePage.getElementText(driver, "//div[@class='message-error validation-summary-errors']//ul//li"), "The specified email already exists");
		
	}

	@Test
	public void TC_05_Register_Password_Less_Than_6_Chars() {
		basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
		basePage.clickToElement(driver, "//a[@class='ico-register']");
		basePage.sendKeyToElement(driver, "//input[@id='FirstName']", "Hoa");
		basePage.sendKeyToElement(driver, "//input[@id='LastName']", "Test");
		basePage.sendKeyToElement(driver, "//input[@id='Email']", "hoantt1@mailinator.com");
		basePage.sendKeyToElement(driver, "//input[@id='Password']", "Abcd");
		basePage.sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "Abcd");
		basePage.waitForElementClickable(driver, "//button[@id=\'register-button\']");
		basePage.clickToElement(driver, "//button[@id=\'register-button\']");
		
		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Password-error']"), "Password must meet the following rules:\nmust have at least 6 characters");
		
	}

	@Test
	public void TC_06_Register_Invalid_Confirm_Password() {
		basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
		basePage.clickToElement(driver, "//a[@class='ico-register']");
		basePage.sendKeyToElement(driver, "//input[@id='FirstName']", "Hoa");
		basePage.sendKeyToElement(driver, "//input[@id='LastName']", "Test");
		basePage.sendKeyToElement(driver, "//input[@id='Email']", "hoantt1@mailinator.com");
		basePage.sendKeyToElement(driver, "//input[@id='Password']", "Abcd");
		basePage.sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "Abcde");
		basePage.waitForElementClickable(driver, "//button[@id=\'register-button\']");
		basePage.clickToElement(driver, "//button[@id=\'register-button\']");
		
		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='ConfirmPassword-error']"), "The password and confirmation password do not match.");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
		
	}

}
