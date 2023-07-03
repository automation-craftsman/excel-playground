package pages;

import base.Base;
import org.openqa.selenium.By;

public class LoginPage extends Base {

    public By userNameField =By.xpath("//input[@id=\"user-name\"]");
    public By passwordFiled = By.xpath("//input[@id=\"password\"]");
    public By loginButton = By.xpath("//input[@id=\"login-button\"]");


    public ProductsPage loginUser(String userName, String userPassword){

        driver.findElement(userNameField).sendKeys(userName);
        driver.findElement(passwordFiled).sendKeys(userPassword);

        driver.findElement(loginButton).click();

        return new ProductsPage();
    }

}
