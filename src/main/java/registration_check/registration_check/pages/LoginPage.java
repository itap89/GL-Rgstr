package registration_check.registration_check.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Old Login page to GL DB
 * 
 * @author Tu Anh Vu
 *
 */
public class LoginPage extends CommonPage {

	@FindBy(id ="cred_userid_inputtext")
	private WebElement emailInputBox;
	
	@FindBy(id = "cred_password_inputtext")
	private WebElement oldPasswordInputBox;
	
	@FindBy(xpath = "//input[@name='passwd' and not(@id='cred_password_inputtext')]")
	private WebElement newPasswordInputBox;
	
	@FindBy(xpath = "//input[@value='Sign in']")
	private WebElement signInButton;
	
	/**
	 * Associate given driver with current login page
	 * 
	 * @param webDriver
	 */
	public LoginPage(WebDriver webDriver) {
		super(webDriver);
	}
	
	/**
	 * Login with given email and password
	 * 
	 * @param email email fo log in 
	 * @param password password for login
	 */
	public void login(String email, String password) {
		enterTextInElement(emailInputBox, email);
		enterTextInElement(oldPasswordInputBox, "");
		enterTextInElement(newPasswordInputBox, password);
		clickElementWithJavascript(signInButton);		
	}

}
