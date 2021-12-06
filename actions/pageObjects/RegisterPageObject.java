package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.RegisterPageUI;

public class RegisterPageObject extends BasePage{
	private WebDriver driver;
	
	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToRegisterButton() {
		waitForElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);
		clickToElement(driver, RegisterPageUI.REGISTER_BUTTON);
	}
	
	public void clickToLogoutButton() {
		waitForElementClickable(driver, RegisterPageUI.LOGOUT_BUTTON);
		clickToElement(driver, RegisterPageUI.LOGOUT_BUTTON);
	}
	
	public String getErrorMessageAtFirstNameTextbox() {
		return getElementText(driver, RegisterPageUI.FIRST_NAME_ERROR_MESSAGE);
	}

	public String getErrorMessageAtLastNameTextbox() {
		return getElementText(driver, RegisterPageUI.LAST_NAME_ERROR_MESSAGE);
	}
 
	public String getErrorMessageAtEmailTextbox() {
		return getElementText(driver, RegisterPageUI.EMAIL_ERROR_MESSAGE);
	}

	public String getErrorMessageAtPasswordTextbox() {
		return getElementText(driver, RegisterPageUI.PASSWORD_ERROR_MESSAGE);
	}

	public String getErrorMessageAtConfirmPasswordTextbox() {
		return getElementText(driver, RegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
	}
	
	public String getRegisterSuccessfullyMessage() {
		waitForElementVisible(driver, RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
		return getElementText(driver, RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
	}
	
	public String getRegisterWithExistedEmailMessage() {
		return getElementText(driver, RegisterPageUI.REGISTER_WITH_EXISTED_MESSAGE);
	}

	public void sendKeyToFirstNameTextbox(String string) {
		sendKeyToElement(driver, RegisterPageUI.FIRST_NAME_TEXTBOX, string);
		
	}

	public void sendKeyToLastNameTextbox(String string) {
		sendKeyToElement(driver, RegisterPageUI.LAST_NAME_TEXTBOX, string);
		
	}

	public void sendKeyToEmailTextbox(String string) {
		sendKeyToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, string);
		
	}

	public void sendKeyToConfirmPasswordTextbox(String string) {
		sendKeyToElement(driver, RegisterPageUI.PASSWORD_TEXTBOX, string);
		
	}

	public void sendKeyToPasswordTextbox(String string) {
		sendKeyToElement(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, string);
		
	}
	
}
