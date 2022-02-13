package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import commons.BasePageFactory;

public class RegisterPageObjectFactory extends BasePageFactory{
	
	private WebDriver driver;
	
	public RegisterPageObjectFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Page UI
	
	@FindBy(how = How.XPATH, using ="//input[@id='FirstName']")
	private WebElement firstNameTextbox;
	
	@FindBy(how = How.XPATH, using = "//input[@id='LastName']")
	private WebElement lastNameTextbox;
	
	@FindBy(how = How.XPATH, using = "//input[@id='Email']")
	private WebElement emailTextbox;
	
	@FindBy(how = How.XPATH, using = "//input[@id='Password']")
	private WebElement passwordTextbox;
	
	@FindBy(how = How.XPATH, using = "//input[@id='ConfirmPassword']")
	private WebElement confirmPasswordTextbox;
	
	@FindBy(how = How.XPATH, using = "//button[@id='register-button']")
	private WebElement registerButton;
	
	@FindBy(how = How.XPATH, using = "//span[@class='field-validation-error']")
	private WebElement firstNameErrorMsg;
	
	@FindBy(how = How.XPATH, using = "//span[@id='LastName-error']")
	private WebElement lastNameErrorMsg;
	
	@FindBy(how = How.XPATH, using = "//span[@id='Email-error']")
	private WebElement emailErrorMsg;
	
	@FindBy(how = How.XPATH, using = "//span[@id='Password-error']")
	private WebElement passwordErrorMsg;
	
	@FindBy(how = How.XPATH, using = "//span[@id='ConfirmPassword-error']")
	private WebElement confirmPasswordErrorMsg;
	
	@FindBy(how = How.XPATH, using = "//div[@class='result']")
	private WebElement registerSuccessMsg;
	
	@FindBy(how = How.XPATH, using = "//a[@class='ico-logout']")
	private WebElement logoutButton;
	
	@FindBy(how = How.XPATH, using = "//div[@class='message-error validation-summary-errors']//ul//li")
	private WebElement registerWithExistedMsg;
	
	public void clickToRegisterButton() {
		waitForElementClickable(driver, registerButton);
		clickToElement(driver, registerButton);
	}
	
	public void clickToLogoutButton() {
		waitForElementClickable(driver, logoutButton);
		clickToElement(driver, logoutButton);
	}
	
	public String getErrorMessageAtFirstNameTextbox() {
		return getElementText(firstNameErrorMsg);
	}

	public String getErrorMessageAtLastNameTextbox() {
		return getElementText(lastNameErrorMsg);
	}
 
	public String getErrorMessageAtEmailTextbox() {
		return getElementText(emailErrorMsg);
	}

	public String getErrorMessageAtPasswordTextbox() {
		return getElementText(passwordErrorMsg);
	}

	public String getErrorMessageAtConfirmPasswordTextbox() {
		return getElementText(confirmPasswordErrorMsg);
	}
	
	public String getRegisterSuccessfullyMessage() {
		waitForElementVisible(driver, registerSuccessMsg);
		return getElementText(registerSuccessMsg);
	}
	
	public String getRegisterWithExistedEmailMessage() {
		return getElementText(registerWithExistedMsg);
	}

	public void sendKeyToFirstNameTextbox(String string) {
		sendKeyToElement(firstNameTextbox, string);
		
	}

	public void sendKeyToLastNameTextbox(String string) {
		sendKeyToElement(lastNameTextbox, string);
		
	}

	public void sendKeyToEmailTextbox(String string) {
		sendKeyToElement(emailTextbox, string);
		
	}

	public void sendKeyToConfirmPasswordTextbox(String string) {
		sendKeyToElement(confirmPasswordTextbox, string);
		
	}

	public void sendKeyToPasswordTextbox(String string) {
		sendKeyToElement(passwordTextbox, string);
		
	}
	
	
}
