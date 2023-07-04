package playground;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class CreateAndWriteExcelData {

    public static void main(String[] args) throws IOException {

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

        FileOutputStream fos = new FileOutputStream(filePath);

        wb.write(fos);
        wb.close();
        fos.close();

    }
}
