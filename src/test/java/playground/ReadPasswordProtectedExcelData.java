package playground;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class ReadPasswordProtectedExcelData {

    @Test(description = "Reading data from password protected Excel File")
    public void readPasswordProtectedFile() throws IOException {

        String filePath = ".\\src\\test\\resources\\protected_data.xlsx";
        String password = "12345";

        // Opening password protected Workbook using WorkbookFactory
        Workbook wb = WorkbookFactory.create(new File(filePath), password);

        XSSFSheet dataSheet = (XSSFSheet) wb.getSheet("User_Data");

        int rowCount = dataSheet.getLastRowNum();
        int columnCount = dataSheet.getRow(1).getLastCellNum();

        for (int r=0; r<=rowCount; r++){

            XSSFRow row = dataSheet.getRow(r);

            for (int c=0; c<columnCount; c++){

                XSSFCell cellData = row.getCell(c);

                switch (cellData.getCellType()){
                    case STRING:
                        System.out.print(cellData.getStringCellValue());
                        break;
                    case NUMERIC, FORMULA:
                        System.out.print(cellData.getNumericCellValue());
                        break;
                    case BOOLEAN:
                        System.out.print(cellData.getBooleanCellValue());
                        break;
                }
                System.out.print(" | ");
            }
            System.out.println();
        }

        wb.close();


    }
}
