import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CreateAndWriteExcelData {

    @Test(description = "Creating and storing data in the Excel Sheet.")
    public void createExcelFile() {

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("User_Data");

        Object userData[][] = {
                {"User_Name", "Password", "Employee_Status"},
                {"Steve", "Steve123", "Permanent"},
                {"David", "DavidJ063", "Contractual"},
                {"Joseph", "Joseph639", "Contractual"},
                {"Oscar", "OscarR523", "Trainee"}
        };

        int rowCount = 0;

        for (Object user[] : userData) {

            XSSFRow dataRow = sheet.createRow(rowCount++);

            int columnCount = 0;

            for (Object data : user) {

                XSSFCell cellData = dataRow.createCell(columnCount++);

                if (data instanceof String) cellData.setCellValue((String) data);
                if (data instanceof Integer) cellData.setCellValue((Integer) data);
                if (data instanceof Boolean) cellData.setCellValue((Boolean) data);

            }

        }

        String filePath = ".\\src\\test\\resources\\userdata.xlsx";

        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(filePath);
        } catch (FileNotFoundException e) {
            System.out.println("[!] Error creating file in the system.");
        }

        try {
            wb.write(fos);
        } catch (IOException e) {
            System.out.println("[!] Error writing file in the system.");
        }

        try {
            wb.close();
            fos.close();
        } catch (IOException e) {
            System.out.println("[!] Error closing the workbook or output stream.");
        }


    }
}
