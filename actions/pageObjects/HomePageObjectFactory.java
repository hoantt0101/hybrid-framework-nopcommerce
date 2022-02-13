package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import commons.BasePageFactory;

public class HomePageObjectFactory extends BasePageFactory {
	
	private WebDriver driver;
	
	public HomePageObjectFactory( WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Page UI
	
	@FindBy(how = How.XPATH, using ="//a[@class='ico-register']")
	private WebElement registerLink;
	
	//Action
	public void clickToRegisterLink() {
		waitForElementVisible(driver, registerLink);
		clickToElement(driver, registerLink);
	}
	
}
