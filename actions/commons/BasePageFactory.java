package commons;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePageFactory {

	private int longTimeOut = 30;
	private int shortTimeOut = 5;
	public void openUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}
	
	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}
	
	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}
	
	public String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}
	
	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}
	
	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}
	
	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}
	
	public Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}
	public void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}
	
	public void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}
	
	public String getAlertText(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}
	
	public void sendKeyToAlert(WebDriver driver, String text) {
		waitForAlertPresence(driver).sendKeys(text);
	}
	
	public void switchToWindowByID(WebDriver driver, String windowsID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			if (!id.equals(windowsID)) {
				driver.switchTo().window(id);
				break;
			}
			
		}
	}
	
	public void switchToWindowByTitle (WebDriver driver, String title) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id: allWindowIDs) {
			driver.switchTo().window(id);
			String actualTitle = driver.getTitle();
			if (actualTitle == title) {
				break;
			}
		}
	}
	
	public void closeAllWindowsWithoutParent (WebDriver driver, String parentID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id: allWindowIDs) {
			if(!id.equals(parentID)){
				driver.switchTo().window(id);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}
	
	public WebElement findElement(WebDriver driver, String locator) {
		return driver.findElement(By.xpath(locator));
	}
	
	public List <WebElement> getElements(WebDriver driver, String locator){
		return driver.findElements(By.xpath(locator));
	}
	
	private By getByXpath (String locator) {
		return By.xpath(locator);
	}
	
	public void clickToElement (WebDriver driver, WebElement element) {
		element.click();
	}
	
	public void sendKeyToElement (WebElement element, String str) {
		element.clear();
		element.sendKeys(str);
	}
	
	public String getElementText (WebElement element) {
		return element.getText();
	}
	
	public void selectItemInDropdownByValue (WebElement element, String str) {
		Select select = new Select(element);
		select.selectByValue(str);
	}
	
	public String getDefaultItemInDropdown (WebDriver driver, String locator) {
		Select select = new Select (findElement(driver, locator));
		return select.getFirstSelectedOption().getText();
	}
	
	public boolean isDropdownMultiple (WebElement element) {
		Select select = new Select (element);
		return select.isMultiple();
	}
	
	public String getElementAttribute (WebDriver driver, WebElement element, String attName) {
		return element.getAttribute(attName);
	}
	
	public String getElementCssValue (WebDriver driver, WebElement element, String propertyName) {
		return element.getCssValue(propertyName);
	}
	
	public String getHexaFromRGBA (String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}
	
	public int getElementSize (WebDriver driver, String locator) {
		return getElements(driver, locator).size();
	}
	
	public void checkToDefaultCheckboxRadio (WebDriver driver, String locator) {
		WebElement element = findElement(driver, locator);
		if (!element.isSelected()) {
			element.click();
		}
	}
	
	public void uncheckToDefaultCheckboxRadio (WebDriver driver, String locator) {
		WebElement element = findElement(driver, locator);
		if (element.isSelected()) {
			element.click();
		}
	}
	
	public boolean isElementDisplayed (WebDriver driver, String locator) {
		return findElement(driver, locator).isDisplayed();
	}
	
	public boolean isElementSelected (WebDriver driver, String locator) {
		return findElement(driver, locator).isSelected();
	}
	
	public boolean isElementEnable (WebDriver driver, String locator) {
		return findElement(driver, locator).isEnabled();
	}
	
	public void switchToFrame (WebDriver driver, String locator) {
		driver.switchTo().frame(findElement(driver, locator));
	}
	
	public void switchToDefaultContent (WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	
	public void hoverMouse (WebDriver driver, String locator) {
		Actions action = new Actions(driver);
		action.moveToElement(findElement(driver, locator)).perform();
	}
	
	public void waitForElementVisible(WebDriver driver, WebElement element) {
		WebDriverWait explicit = new WebDriverWait(driver, longTimeOut);
		explicit.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElementsVisible(WebDriver driver, List<WebElement> elements) {
		WebDriverWait explicit = new WebDriverWait(driver, longTimeOut);
		explicit.until(ExpectedConditions.visibilityOfAllElements(elements));
	}
	
	public void waitForElementInvisible(WebDriver driver, WebElement element) {
		WebDriverWait explicit = new WebDriverWait(driver, longTimeOut);
		explicit.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public void waitForElementsInvisible(WebDriver driver, List<WebElement> elements) {
		WebDriverWait explicit = new WebDriverWait(driver, longTimeOut);
		explicit.until(ExpectedConditions.invisibilityOfAllElements(elements));
	}
	
	public void waitForElementClickable(WebDriver driver, WebElement element) {
		WebDriverWait explicit = new WebDriverWait(driver, longTimeOut);
		explicit.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void sleepInSecond (long time) {
		try {
			Thread.sleep(time*1000);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
