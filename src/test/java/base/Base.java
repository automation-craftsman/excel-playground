package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.util.Properties;

public class Base {

    /**
     * Loaded the config.properties file.
     */
    public Base() {
        loadProperties();
    }

    public static WebDriver driver;
    public static Properties prop;

    private static final ThreadLocal<WebDriver> tDriver = new ThreadLocal<>();

    private static void setDriver(WebDriver driver) {
        Base.tDriver.set(driver);
    }

    public static WebDriver getDriver() {
        return tDriver.get();
    }


    /**
     * This method will initiate the thread safe driver instance of user specified browser.
     *
     * @param browser holds the user specified name of the browser to run the tests.
     */
    public static WebDriver getBrowser(String browser) {

        if (browser.equalsIgnoreCase("Chrome")) {
            driver = WebDriverManager.chromedriver().create();
            setDriver(driver);

        } else if (browser.equalsIgnoreCase("Firefox")) {
            driver = WebDriverManager.firefoxdriver().create();
            setDriver(driver);

        } else {
            System.out.println("[!] Invalid option provided. Initiating Chrome driver as default.");
            driver = WebDriverManager.chromedriver().create();
            setDriver(driver);
        }

        return getDriver();
    }

    /**
     * Generic method to load the config.properties file
     */
    public static void loadProperties() {
        prop = new Properties();
        try {
            FileInputStream fis = new FileInputStream(".\\src\\test\\java\\config\\config.properties");
            prop.load(fis);

        } catch (Exception e) {
            System.out.println("[!] Error loading the config file.");
        }

    }

    @BeforeSuite
    public static synchronized void setUp() {
        driver = getBrowser(prop.getProperty("browser"));
        driver.manage().window().maximize();
        driver.get(prop.getProperty("url"));
    }

    /**
     * Method to close the browser and clear ThreadLocal
     */
    @AfterSuite
    public static synchronized void cleanUp() {

        getDriver().quit();
    }

}
