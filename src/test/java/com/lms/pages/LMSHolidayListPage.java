package com.lms.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class LMSHolidayListPage {

	private WebDriver driver;
	private static final Logger logger = LogManager.getLogger(LMSHolidayListPage.class);

	// Constructor of the page class
	public LMSHolidayListPage(WebDriver driver) {
		this.driver = driver;
	}

	String[][] tableVal1;
	String[][] tableVal2;
	int rowCount, columnCount;
	String holidayType;
	FileOutputStream outputStream;

	private By windowTitle = By.xpath("//div/h4[contains(text(),\"Office Holiday list\")]");

	// Method to get the title of the LMS Holiday List window
	public String getWindowTitle() throws InterruptedException {

		// Title of the window
		// WebDriverWait wait = new WebDriverWait(this.driver,Duration.ofSeconds(30));
		Thread.sleep(5000);
		return driver.findElement(windowTitle).getText();

	}

	// Method to count the Public Holidays
	public void countOfPublicHolidays() throws IOException {

		logger.info("Validate whether the count of public holidays is less than 10");

		int rowSize = getTableRows().size();
		logger.info("No of rows in the table is: " + rowSize);

		int colSize = getTableColumns().size();
		logger.info("No of columns in the table is: " + colSize);

		int counter = 0;
		for (int i = 1; i <= rowSize; i++) {
			for (int j = 1; j <= colSize; j++) {

				String cellContent = driver
						.findElement(By
								.xpath("//*[@id=\"display-holidaylist-form\"]/table/tbody/tr[" + i + "]/td[" + j + "]"))
						.getText() + "  ";

				if (cellContent.contains("Public Holiday")) {
					// Increment the counter
					counter++;
				}

			}
		}
		// Check if the count of public holidays is greater than or equal to 10
		if (counter >= 10) {
			logger.info("Number of public holidays:" + counter);
		}

	}

	public void getTableSize() throws IOException{

        //get Row size
        List<WebElement> rowData =  getTableRows();
        
        //get Column size
        List<WebElement> columnData = getTableColumns();
        
        rowCount = rowData.size();
        columnCount = columnData.size();
        
        logger.info("Row :"+rowCount+" Clounm :"+columnCount);
               
        
        tableVal1 = new String[rowCount][columnCount];
        
        tableVal2 = new String[rowCount][columnCount];
        
        for(int i =1 ; i <= rowCount ; i++ )
        {
          //Check if the record is a Public Holiday or not
        	//List<WebElement> holidayType = driver.findElements(By.xpath("//*[@id=\"display-holidaylist-form\"]/table/tbody/tr["+i+"]"));
        	System.out.println("I am i:" +i);

        	
            for(int j =1 ; j <= columnCount ; j++ ) {
            	System.out.println("I am j:" +j);

                if(i == 1) {
                    //Get header value
                    tableVal1[i - 1][j - 1] = driver.findElement
                                 (By.xpath("//*[@id=\"display-holidaylist-form\"]/table/thead/tr["+i+"]/th["+j+"]")).getText();
                  //Get header value
                    tableVal2[i - 1][j - 1] = driver.findElement
                                 (By.xpath("//*[@id=\"display-holidaylist-form\"]/table/thead/tr["+i+"]/th["+j+"]")).getText();
                           }
                else{
                	String type = driver.findElement
                			(By.xpath("//*[@id=\"display-holidaylist-form\"]/table/tbody/tr["+(i-1)+"]/td[4]"))
                			.getText();
                	if(type.equalsIgnoreCase("Public Holiday"))
                	{
                    //get table data values
                	tableVal1[i-1][j-1] =driver.findElement
                                 (By.xpath("//*[@id=\"display-holidaylist-form\"]/table/tbody/tr["+(i-1)+"]/td["+(j)+"]")).getText();
                	}
                	else if (type.equalsIgnoreCase("Optional Holiday"))
                			{
                        //get table data values
                    	tableVal2[i-1][j-1] =driver.findElement
                                     (By.xpath("//*[@id=\"display-holidaylist-form\"]/table/tbody/tr["+(i-1)+"]/td["+(j)+"]")).getText();
                    	}
                }
            }
        	}
        
        logger.info("Printing the array one");
                logger.info((Arrays.deepToString(tableVal1)));
                logger.info("Printing the array two");

        logger.info((Arrays.deepToString(tableVal2)));

        
        holidayType = "Public Holidays";
        generateHolidayReport(tableVal1,holidayType);
  
        holidayType = "Optional Holidays";
        generateHolidayReport(tableVal2,holidayType);
   
    }

	public void generateHolidayReport(String[][] tableData, String holidayType) throws IOException {

		// Create a workbook in xlsx format
		Workbook workbook = new XSSFWorkbook();
		// Create sheet
		Sheet sheet = workbook.createSheet(holidayType);

		int rowNum = 0;
		System.out.println("Creating excel");

		for (Object[] datatype : tableData) {
			Row row = sheet.createRow(rowNum++);
			int colNum = 0;
			for (Object field : datatype) {
				Cell cell = row.createCell(colNum++);
				if (field instanceof String) {
					cell.setCellValue((String) field);
				} else if (field instanceof Integer) {
					cell.setCellValue((Integer) field);
				}
			}
		}

		try {
			if (holidayType.equalsIgnoreCase("Public Holidays")) {
				outputStream = new FileOutputStream(
						"C:\\Users\\Aparna.Venugopal\\eclipse\\1_LMS_Trial2\\PublicHolidays_Report.xlsx");
			} else if (holidayType.equalsIgnoreCase("Optional Holidays")) {
				outputStream = new FileOutputStream(
						"C:\\Users\\Aparna.Venugopal\\eclipse\\1_LMS_Trial2\\OptionalHolidays_Report.xlsx");
			}
			workbook.write(outputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			workbook.close();
		}

		logger.info("Report generated");
	}

	// Method to get the table headers
	public List<WebElement> getTableHeader() {

		logger.info("Get the values of the table header");
		// Get the headers
		List<WebElement> tableHeaders = driver
				.findElements(By.xpath("//*[@id=\"display-holidaylist-form\"]/table/thead/tr[1]/th"));

		return tableHeaders;
	}

	// Method to get the table rows
	public List<WebElement> getTableRows() {

		// Get all the row elements
		List<WebElement> rowElements = driver
				.findElements(By.xpath("//*[@id=\"display-holidaylist-form\"]/table/tbody/tr"));
		return rowElements;
	}

	// Method to get the table columns
	public List<WebElement> getTableColumns() {

		// Get all the row elements
		List<WebElement> colElements = driver
				.findElements(By.xpath("//*[@id=\"display-holidaylist-form\"]/table/tbody/tr[1]/td"));

		return colElements;
	}
}
