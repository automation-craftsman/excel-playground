package utility;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtils {
    public XSSFWorkbook wb;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    public CellStyle style;
    public FileOutputStream fos;
    String filePath = null;

    public ExcelUtils(String filePath){

        this.filePath = filePath;
    }

    // Get the total number of rows in the sheet
    public int getRowCount(String sheetName) throws IOException {

        wb = new XSSFWorkbook(filePath);
        sheet = wb.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum();

        wb.close();
        return rowCount;
    }

    // Get the number of columns in the sheet
    public int getColumnCount(String sheetName) throws IOException {

        wb =new XSSFWorkbook(filePath);
        sheet = wb.getSheet(sheetName);
        int numberOfColumns = sheet.getRow(0).getLastCellNum();

        wb.close();

        return numberOfColumns;
    }

    // Get data from a cell
    public String getCellData(String sheetName,int rowIndex,int columnIndex) throws IOException {

        wb = new XSSFWorkbook(filePath);
        sheet = wb.getSheet(sheetName);
        cell = sheet.getRow(rowIndex).getCell(columnIndex);

        DataFormatter formatter = new DataFormatter();

        String cellData;

        try{
            // Get the formatted value of a cell as String regardless of the cell type.
            cellData = formatter.formatCellValue(cell);
        }
        catch(Exception e)
        {
            cellData = "";
        }

        wb.close();

        return cellData;
    }

    // Insert data to a cell in the sheet
    public void setCellData(String sheetName, int rowIndex, int columnIndex, String data) throws IOException {

        File excelFile = new File(filePath);

        // If file does not exist, then create one
        if (!excelFile.exists()) {

            createWorkbook(sheetName);
        }


        wb = new XSSFWorkbook(filePath);
        sheet = wb.getSheet(sheetName);

        // If row not exists then create new Row
        if (sheet.getRow(rowIndex)==null) {

            sheet.createRow(rowIndex);
        }

        row = sheet.getRow(rowIndex);

        cell = row.createCell(columnIndex);
        cell.setCellValue(data);

        fos = new FileOutputStream(filePath);
        wb.write(fos);
        wb.close();
        fos.close();
    }

    // Helper method to create a Workbook if not present
    private void createWorkbook(String sheetName) throws IOException {
        wb = new XSSFWorkbook();
        sheet = wb.createSheet(sheetName);

        fos = new FileOutputStream(filePath);

        wb.write(fos);
        fos.close();
    }

    // Fill a cell with Green color
    public void fillGreenColor(String sheetName,int rowIndex,int columnIndex) throws IOException {

        wb = new XSSFWorkbook(filePath);
        sheet = wb.getSheet(sheetName);

        row = sheet.getRow(rowIndex);
        cell = row.getCell(columnIndex);

        style = wb.createCellStyle();

        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);

        fos = new FileOutputStream(filePath);
        wb.write(fos);
        wb.close();
        fos.close();
    }

    // Fill a cell with Red color
    public void fillRedColor(String sheetName,int rowIndex,int columnIndex) throws IOException {

        wb = new XSSFWorkbook(filePath);
        sheet = wb.getSheet(sheetName);

        row = sheet.getRow(rowIndex);
        cell = row.getCell(columnIndex);

        style = wb.createCellStyle();

        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);

        fos = new FileOutputStream(filePath);
        wb.write(fos);
        wb.close();
        fos.close();
    }

//    public int getDesiredRowNumber(String sheetName, String valueToCheck) throws IOException {
//
//        wb = new XSSFWorkbook(filePath);
//        sheet = wb.getSheet(sheetName);
//
//        int rowCount = getRowCount(sheetName);
//        int columnCount = getColumnCount(sheetName);
//
//        int desiredRowNumber = 0;
//
//        for (int r=1; r<=rowCount; r++){
//            for (int c=0; c<columnCount; c++){
//                if (getCellData(sheetName, r, c).equalsIgnoreCase(valueToCheck)){
//                    desiredRowNumber = r;
//                    break;
//                }
//            }
//        }
//
//        return desiredRowNumber;
//    }


}
