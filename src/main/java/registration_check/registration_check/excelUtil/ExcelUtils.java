package registration_check.registration_check.excelUtil;

import java.io.File;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import registration_check.registration_check.Constants;
import registration_check.registration_check.model.Stuff;

public class ExcelUtils {
	
	public static Stuff getStuff() {
		Stuff stuff = new Stuff();
		// FC Student Database 2017
		try {
			
			XSSFWorkbook wb = new XSSFWorkbook(new File(Constants.PATH_STUFF_EXCEL));
			XSSFSheet sheet = wb.getSheetAt(0);
			
			int numCols = sheet.getRow(1).getPhysicalNumberOfCells();
			for(int j = 0; j < numCols; j++) {
				stuff.setBar( sheet.getRow(1).getCell(0).getStringCellValue() );
				stuff.setFoo( sheet.getRow(1).getCell(1).getStringCellValue() );
				stuff.setBazz( sheet.getRow(1).getCell(2).getStringCellValue() );
			}
			wb.close();
			
		} catch(Exception e) {
			
		}
		return stuff;
	}

	/**
	 * Student name : Parent names
	 * 
	 * @param communionMap
	 */
	public static void setCommunionMap(Map<String, String> communionMap) {
		// FC Student Database 2017
		try {
			
			XSSFWorkbook wb = new XSSFWorkbook(new File(Constants.PATH_COMM_EXCEL));
			XSSFSheet sheet = wb.getSheetAt(0);

			XSSFRow curRow;
			XSSFCell curCell;

			int numRows = sheet.getPhysicalNumberOfRows();

			int studentNameCol = -1;
			int familyCol = -1;
			
			// Go through all rows to find where data starts
			for (int i = 0; i < numRows; i++) {
				// found both student name and family display columns
				if(studentNameCol != -1 && familyCol != -1) {
					break;
				}
				
				curRow = sheet.getRow(i);
				if (curRow != null) {
					// go through columns to find indexes of student and family names
					int numCols = sheet.getRow(i).getPhysicalNumberOfCells();
					for(int j = 0; j < numCols; j++) {
						curCell = curRow.getCell( j );
						
						if(curCell != null) {
							// check if found student name or family name columns
							if(curCell.getStringCellValue().equals(Constants.STUDENT_NAME_COL)) {
								studentNameCol = j;
							} else if(curCell.getStringCellValue().equals(Constants.FAMILY_NAME_COL)) {
								familyCol = j;
							}
							
						}
						// found both student name and family display columns
						if(studentNameCol != -1 && familyCol != -1) {
							System.out.println(studentNameCol);
							System.out.println(familyCol);
							break;
						}
						
					}
					
				}
			}
			
			// put all student names and family displays in map
		    for(int r = 0; r < numRows; r++) {
		        curRow = sheet.getRow(r);
		        if(curRow != null) {
		        	String studentName = curRow.getCell(studentNameCol).getStringCellValue();
		        	String familyDisplay = curRow.getCell(familyCol).getStringCellValue();
		        	communionMap.put(studentName, familyDisplay);
		        }
		    }
		    
			wb.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		System.out.println(communionMap);
	}

	public static void setConfirmationMap(Map<String, String> confirmationMap) {
		// Confirmation list 5.26.2017
	}
	
}
