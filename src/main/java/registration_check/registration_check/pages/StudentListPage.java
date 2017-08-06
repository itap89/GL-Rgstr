package registration_check.registration_check.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import registration_check.registration_check.Constants;

public class StudentListPage extends CommonPage {

	@FindBy(id = "StudentsTile3_label")
	private WebElement studentCategoryOption;
	
	@FindBy(xpath = "//input[@title = 'Edit filter (/)']")
	private WebElement studentFilterInputBox;
	
	@FindBy(xpath = "//a[@title = 'Apply filter (Enter)']")
	private WebElement searchButton;
	
	@FindBy(xpath = "//div[contains(@id, 'students_s_list_acc_list_containerList__Access_Control') and @role = 'listbox']"
			+ "//div[contains(@id, 'listitem__') and @aria-hidden = 'false']")
	private List<WebElement> studentOptions;
	
	@FindBy(xpath = "//a[@class = 'sri-display-hyperlink']")
	private WebElement parentNamesLink;
	
	@FindBy(xpath = "//div[contains(@id, 'students_s_list_actionbar_container')]//div[@title = 'Edit (E)']")
	private WebElement editButton;
	
	@FindBy(xpath = "//div[contains(@id, 'students_s_list_actionbar_container')]//div[@title = 'Save (Ctrl+S)']")
	private WebElement saveButton;
	
	@FindBy(xpath = "//input[contains(@id, 'First_Communion_DateTextBox_AccessControl')]")
	private WebElement studentFCDateInputBox;
	
	@FindBy(xpath = "input[contains(@id, 'First_Communion_ParishTextBox_AccessControl')]")
	private WebElement studentFCParishInputBox;
		
	public StudentListPage(WebDriver webDriver) {
		super(webDriver);
	}
	
	/**
	 * Return true if found given student
	 * with given parents in DB
	 * 
	 * @param studentName student to search for
	 * @param parentNames parents of student to search for
	 * 
	 * @return if found student with given parents in DB
	 */
	public boolean filterByStudentName(String studentName, String parentNames) {
		clickElementWithJavascript(studentCategoryOption);
		clearTextInElement(studentFilterInputBox);
		enterTextInElement(studentFilterInputBox, studentName);
		waitForTime(250);
		clickElementWithJavascript(searchButton);
		waitForTime(1000);
		
		for(int i = 0; i < studentOptions.size(); i++) {
			
			System.out.println(studentOptions.get(i)
					.findElement(By.xpath(Constants.PATH_STUDENT_OPTION_TO_STUDENT_NAME_LABEL)).getText());
			
			if( (studentOptions.get(i)
					.findElement(By.xpath(Constants.PATH_STUDENT_OPTION_TO_STUDENT_NAME_LABEL)).getText().equals(studentName)) ) {
				
				System.out.println("STUDENT NAMES MATCH");
				System.out.println("Expected parent names: " + parentNames);
				System.out.println("Actual parent names: " + parentNamesLink.getText());
				System.out.println("^^^^^^^^^^^^^^^^");
				if(parentNamesLink.getText().equals(parentNames)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Enter first communion information for currently selected student in DOM
	 */
	public void enterFCInfo(String studentName) {
		boolean currentlyEditing = false;
		waitForVisibilityOfElement(studentFCDateInputBox);
		waitForVisibilityOfElement(studentFCParishInputBox);
		
		System.out.println("***** START *****");
		System.out.println(studentFCDateInputBox.findElement(By.xpath(Constants.PATH_SACRAMENT_FOLLOWING_SIBILING))
				.getText());
		System.out.println(studentFCDateInputBox.findElement(By.xpath(Constants.PATH_SACRAMENT_FOLLOWING_SIBILING))
				.getText().length());
		System.out.println("***** END *****");
		
		if( studentFCDateInputBox.findElement(By.xpath(Constants.PATH_SACRAMENT_FOLLOWING_SIBILING))
				.getText().length() == 0 ) {
			
			clickElementWithJavascript(editButton);		
			enterTextInElement(studentFCDateInputBox, Constants.COMM_DATE);
			currentlyEditing = true;
		}
		
		if( studentFCParishInputBox.findElement(By.xpath(Constants.PATH_SACRAMENT_FOLLOWING_SIBILING))
				.getText().length() == 0 ) {
			
			if(!currentlyEditing) {
				clickElementWithJavascript(editButton);		
			}
			enterTextInElement(studentFCParishInputBox, Constants.PARISH);
			currentlyEditing = true;
		}
		
		if(currentlyEditing) {
			System.out.println(studentName);
			System.out.println("****");
			clickElementWithJavascript(saveButton);
		}	
		
	}

}
