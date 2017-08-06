package registration_check.registration_check.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


/**
 * New Login exp page to GL DB
 * 
 * @author Tu Anh Vu
 *
 */
public class NewLoginPage extends CommonPage {

	@FindBy(xpath = "normalize-space(.) = 'Email or phone'")
	private WebElement emailPhoneInputBox;
	
	@FindBy(id = "idSIButton9")
	private WebElement emailPhoneNextButton;
	
	@FindBy(id = "i0118")
	private WebElement passwordInputBox;
	
	@FindBy(id = "idSIButton9")
	private WebElement signinButton;
	
	public NewLoginPage(WebDriver webDriver) {
		super(webDriver);
	}
	
	/**
	 * Login with given email and password
	 * 
	 * @param email email fo log in 
	 * @param password password for login
	 */
	public void login(String email, String password) {
		enterTextInElement(emailPhoneInputBox, email);
		clickElementWithJavascript(emailPhoneNextButton);
		enterTextInElement(passwordInputBox, password);
		clickElementWithJavascript(signinButton);
	}

}
