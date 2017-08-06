package registration_check.registration_check.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import registration_check.registration_check.Constants;

/**
 * Contains common functionality across all web pages
 * 
 * @author Tu Anh Vu
 */
public class CommonPage {

	private WebDriver webDriver;
	
	public CommonPage(WebDriver webDriver) {
		this.webDriver = webDriver;
		PageFactory.initElements(webDriver, this);
	}
	
	/** 
	 * @return webdriver used to display page
	 */
	public WebDriver getDriver() {
		return this.webDriver;
	}
	
	/**
	 * Click on given element with javascript
	 * 
	 * @param element to click on
	 */
	public void clickElementWithJavascript(WebElement element) {
		waitForElementClickable(element);
		JavascriptExecutor jse = (JavascriptExecutor) webDriver;
		jse.executeScript("arguments[0].scrollIntoView()", element);
		jse.executeScript("arguments[0].click();", element);
	}
	
	/**
	 * Enter given text in given element
	 * 
	 * @param element web element to enter text into
	 * @param text to enter
	 */
	public void enterTextInElement(WebElement element, String text) {
		waitForVisibilityOfElement(element);
		element.sendKeys(text);
	}
	
	public void clearTextInElement(WebElement element) {
		waitForVisibilityOfElement(element);
		element.clear();
	}
	

	/**
	 * 
	 */
	public void waitPageLoad() {
		JavascriptExecutor js = (JavascriptExecutor) webDriver;

		// This loop will rotate for 25 times to check If page Is ready after
		// every 1 second.
		// You can replace your value with 25 If you wants to Increase or
		// decrease wait time.
		for (int i = 0; i < 25; i++) {
			try {
				Thread.sleep(1000);

			} catch (InterruptedException e) {
			}
			// To check page ready state.
			if (js.executeScript("return document.readyState").toString().equals("complete")) {
				System.out.println("Page is ready after: " + i);
				break;
			}
		}
	}
	
	/**
	 * Wait for driver to switch from page with oldUrl to page with different url
	 * 
	 * @param oldUrl
	 */
	public void waitForUrlChange(String oldUrl) {
		while(webDriver.getCurrentUrl().equals(oldUrl)) {
			try {
				Thread.sleep(1000);

			} catch (InterruptedException e) {
			}
		}
	}
	
	/**
	 * Wait for given webelement to be visible
	 * 
	 * @param element
	 */
	public void waitForVisibilityOfElement(WebElement element) {
		waitFor(ExpectedConditions.visibilityOf(element));
	}
	
	/**
	 * Wait for given webelement to be visible
	 * 
	 * @param element
	 */
	public void waitForPresenceOfElement(WebElement element) {
		waitFor(ExpectedConditions.presenceOfNestedElementLocatedBy(element, By.xpath(".")));
	}
	
	/**
	 * Wait for given element to be clickable
	 * 
	 * @param element
	 */
	public void waitForElementClickable(WebElement element) {
		waitFor(ExpectedConditions.elementToBeClickable(element));
	}
	
	/**
	 * Wait for expected condition to be fulfilled;
	 * waits for time given by value in constants
	 * 
	 * @param condition
	 */
	public void waitFor(ExpectedCondition<WebElement> condition) {
		WebDriverWait wait = new WebDriverWait(webDriver, Constants.TIMEOUT_SEC);
		wait.until(condition);
	}
	
	/**
	 * Pauses thread for given time
	 * 
	 * @param waitTime wait time to pause thread for
	 */
	public void waitForTime(int waitTime) {
		try {
			Thread.sleep(waitTime);
		} catch (InterruptedException e) {

		}
	}
	
}
