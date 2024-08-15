package com.base;
import com.pages.BasePage;
import com.pages.Page;
import com.util.PropertyUtils;
import com.util.TestUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public WebDriver driver;
    public static Page page;
    public WebDriverWait wait;
    private Properties properties=new PropertyUtils().getProperties();
    @BeforeMethod
    public void initialization() {
        String browserName = properties.getProperty("browser");
        if (browserName.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options =new ChromeOptions();
            options.setExperimentalOption("prefs", chromeOptionToDownloadFile());
            driver = new ChromeDriver(options);
        } else if (browserName.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else {
            System.out.println("Please provide browser name");
        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIME, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
        driver.get(properties.getProperty("url"));
        page = new BasePage(driver,wait);
    }
    private Map<String, Object> chromeOptionToDownloadFile(){
        String downloadFilepath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"+ File.separator + "resources" + File.separator + "download";
        Map<String, Object> prefs = new HashMap<>();

        // Set the download directory
        prefs.put("download.default_directory", downloadFilepath);

        // Other options you might need
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.directory_upgrade", true);
        prefs.put("safebrowsing.enabled", "true");
        return prefs;
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
