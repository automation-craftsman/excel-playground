package pages;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductsPage extends Base {

    public By burgerMenuButton = By.xpath("//button[@id=\"react-burger-menu-btn\"]");
    public By logoutButton = By.xpath("//a[@id=\"logout_sidebar_link\"]");
    public By pageHeader = By.xpath("//span[@class=\"title\"]");


    public String getPageHeaderTitle(){

        return driver.findElement(pageHeader).getText();
    }

    public void clickMenuButton(){

        driver.findElement(burgerMenuButton).click();
    }

    public LoginPage logoutUser(){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));

        wait.until(ExpectedConditions.elementToBeClickable(logoutButton));

        driver.findElement(logoutButton).click();

        return new LoginPage();
    }
}
