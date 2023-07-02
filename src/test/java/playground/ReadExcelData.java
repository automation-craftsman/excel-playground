package playground;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;

public class ReadExcelData {

    public static void main(String[] args) throws IOException {
        String dataFilePath = ".\\src\\test\\resources\\userdata.xlsx";

        // Reading the Excel Workbook
        XSSFWorkbook wb = new XSSFWorkbook(dataFilePath);

        // Reading the Excel Worksheet
        XSSFSheet dataSheet = wb.getSheet("User_Data");

        // Number of Rows and Columns in the Worksheet
        int rows = dataSheet.getLastRowNum();
        System.out.println("Number of rows: " + rows);

        int cols = dataSheet.getRow(1).getLastCellNum();
        System.out.println("Number of columns: " + cols);

        for (int r=0; r<=rows; r++){

            XSSFRow selectedRow = dataSheet.getRow(r);

            for (int c=0; c<cols; c++){

                XSSFCell selectedCell = selectedRow.getCell(c);

                switch (selectedCell.getCellType()){
                    case STRING:
                        System.out.print(selectedCell.getStringCellValue());
                        break;
                    case NUMERIC, FORMULA:
                        System.out.print(selectedCell.getNumericCellValue());
                        break;
                    case BOOLEAN:
                        System.out.print(selectedCell.getBooleanCellValue());
                        break;
                }
                System.out.print(" | ");
            }
            System.out.println();
        }
    }
}
