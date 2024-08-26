package core;
import org.openqa.selenium.Proxy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import config.EnvironmentConfig;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class Driver {

    public static AppiumDriver appiumDriver;
    public static WebDriver webDriver;

    public static AppiumDriver startDevice(String deviceName) {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        try {

            switch (deviceName) {

                case "androidLocal":
                    capabilities.setCapability("platformName", "Android");
                    capabilities.setCapability("appium:platformVersion", "15");
                    capabilities.setCapability("appium:deviceName", "emulator-5554");
                    capabilities.setCapability("appium:appPackage", "com.example.test");
                    capabilities.setCapability("appium:appActivity", "com.example.test.MainActivity");
                    capabilities.setCapability("appium:automationName", "UiAutomator2");
                    capabilities.setCapability("appium:noReset", "false");
                    capabilities.setCapability("appium:fullReset", "false");
                    appiumDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), capabilities);
                    break;
                case "iosLocal":
                    break;
                case "androidRemote":
                    break;
                case "iosRemote":
                    break;
            }// end switch

            return appiumDriver;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;

    }// end startDevice


    public static WebDriver startWebDriver(String browserName) {
        try {
            switch (browserName) {
                case "firefoxLocal":
                    WebDriverManager.firefoxdriver().setup();
                    webDriver = new FirefoxDriver();
                    webDriver.manage().deleteAllCookies();
                    break;
                case "chromeLocal":
                    WebDriverManager.chromedriver().setup();
                    webDriver = new ChromeDriver();
                    webDriver.manage().deleteAllCookies();
                    break;
                case "edgeLocal":
                    WebDriverManager.edgedriver().setup();
                    webDriver = new EdgeDriver();
                    webDriver.manage().deleteAllCookies();
                    break;
                case "chromeRemote":
                    break;
                case "firefoxRemote":
                    break;
                case "edgeRemote":
                    break;
                case "chromeLocalS":
                    break;
            }

            return webDriver;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }//end startWebDriver




}//end Driver
