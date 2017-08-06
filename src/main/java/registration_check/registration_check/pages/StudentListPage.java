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
	
	@FindBy(xpath = "//div[contains(@id, 'students_s_list_actionbar_container')]//div[@title = 'Cancel (Esc)']")
	private WebElement cancelButton;
	
	@FindBy(xpath = "//input[contains(@id, 'First_Communion_DateTextBox_AccessControl')]")
	private WebElement studentFCDateInputBox;
	
	@FindBy(xpath = "//input[contains(@id, 'First_Communion_ParishTextBox_AccessControl')]")
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
		waitForTime(1000);
		clearTextInElement(studentFilterInputBox);
		enterTextInElement(studentFilterInputBox, studentName);
		waitForTime(250);
		clickElementWithJavascript(searchButton);
		waitForTime(1000);
		
		System.out.println("Student options: "+ studentOptions.size());
		
		for(int i = 0; i < studentOptions.size(); i++) {
			clickElementWithJavascript(studentOptions.get(i));
			if( (studentOptions.get(i)
					.findElement(By.xpath(Constants.PATH_STUDENT_OPTION_TO_STUDENT_NAME_LABEL)).getText().equals(studentName)) ) {
				
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
		boolean edited = false;
				
		waitForPresenceOfElement(studentFCDateInputBox);
		waitForPresenceOfElement(studentFCParishInputBox);
		
		System.out.println(studentFCDateInputBox.findElement(By.xpath(Constants.PATH_SACRAMENT_FOLLOWING_SIBILING))
				.getAttribute("title"));
		
		if( studentFCDateInputBox.findElement(By.xpath(Constants.PATH_SACRAMENT_FOLLOWING_SIBILING))
				.getAttribute("title").length() == 0 ) {
			clickElementWithJavascript(editButton);		
			waitForTime(1000);
			enterTextInElement(studentFCDateInputBox, Constants.COMM_DATE);
			edited = true;
		}
		
		if( studentFCParishInputBox.findElement(By.xpath(Constants.PATH_SACRAMENT_FOLLOWING_SIBILING))
				.getAttribute("title").length() == 0 ) {
			
			if(!edited) {
				clickElementWithJavascript(editButton);
				waitForTime(1000);
			}
			enterTextInElement(studentFCParishInputBox, Constants.PARISH);
			edited = true;
		}
		
		if(edited) {
			System.out.println(studentName);
			System.out.println("****");
			clickElementWithJavascript(saveButton);
			waitForTime(1000);
		}
	}

}
