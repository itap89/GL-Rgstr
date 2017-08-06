package registration_check.registration_check;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import registration_check.registration_check.excelUtil.ExcelUtils;
import registration_check.registration_check.model.Stuff;
import registration_check.registration_check.pages.LoginPage;
import registration_check.registration_check.pages.StudentListPage;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) {

		Map<String, String> communionMap = new HashMap<String, String>();
		Map<String, String> confirmationMap = new HashMap<String, String>();;
		
		Stuff stuff = ExcelUtils.getStuff();
		ExcelUtils.setCommunionMap(communionMap);
		
		WebDriver webDriver = chromeDriver();
		LoginPage loginPage = new LoginPage(webDriver);
		StudentListPage studentListPage = new StudentListPage(webDriver);
		
		webDriver.get(stuff.getBazz());
		loginPage.login(stuff.getBar(), stuff.getFoo());
		
		int i = 0;
		for(String curStudentName : communionMap.keySet()) {
			if(i == 8) {
				break;
			}
			
			i++;
			
			if(studentListPage
				.filterByStudentName(curStudentName, communionMap.get(curStudentName))) {
				
				studentListPage.enterFCInfo(curStudentName);
			}
			
		}
	}
	
	public static WebDriver chromeDriver() {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setJavascriptEnabled(true);
		capabilities.setCapability("acceptSslCerts", true);

		return new ChromeDriver(capabilities);

	}

}
