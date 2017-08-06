package registration_check.registration_check.excelUtil;

import java.io.File;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import registration_check.registration_check.Constants;

public class ExcelUtils {

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
			
			// Go through all rows to find indexes of student name and family display columns
			for (int i = 0; i < numRows; i++) {
				
				if(studentNameCol != -1 && familyCol != -1) {
					break;
				}
				curRow = sheet.getRow(i);
				if (curRow != null) {
					
					int numCols = sheet.getRow(i).getPhysicalNumberOfCells();
					for(int j = 0; j < numCols; j++) {
						curCell = curRow.getCell( j );
						
						if(curCell != null) {
							
							if(curCell.getStringCellValue().equals("Student Name")) {
								studentNameCol = j;
							} else if(curCell.getStringCellValue().equals("Family Display Name")) {
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
