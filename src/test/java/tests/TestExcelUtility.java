package tests;

import base.Base;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import utility.ExcelUtils;

import java.io.IOException;

public class TestExcelUtility extends Base {

    public ExcelUtils xl;
    public LoginPage loginPage;

    @Test(dataProvider = "Login_data", description = "testing login using data stored in Excel file")
    public void dataDrivenLoginTest(String userName, String userPassword) throws IOException {
        loginPage = new LoginPage();

        var productsPage = loginPage.loginUser(userName, userPassword);

        String actualPageHeader;

        try {
            actualPageHeader = productsPage.getPageHeaderTitle();
        } catch (Exception e) {
            actualPageHeader = "";
        }

        String expectedPageHeader = "Products";

        if (actualPageHeader.contains(expectedPageHeader)) {
            productsPage.clickMenuButton();
            productsPage.logoutUser();

            System.out.println("Login successful with Username : " + "'" + userName + "'"
                    + " and Password : " + "'" + userPassword + "'");
        } else {

            System.out.println("[!] Login denied with Username : " + "'" + userName + "'"
                    + " and Password : " + "'" + userPassword + "'");
        }


    }

    @DataProvider(name = "Login_data")
    public Object [][] getLoginData() throws IOException {

        String filePath = ".\\src\\test\\resources\\login_data.xlsx";

        xl = new ExcelUtils(filePath);

        int totalRows = xl.getRowCount("Login_Details");
        int totalColumns = xl.getColumnCount("Login_Details");

        Object loginData[][] = new Object [totalRows][totalColumns];

        for (int r=1; r<=totalRows; r++){

            // Iterating columns except 'Login_Status'
            for (int c=0; c<totalColumns; c++){
                loginData[r-1][c] = xl.getCellData("Login_Details", r, c);
            }
        }

        return loginData;
    }
}
