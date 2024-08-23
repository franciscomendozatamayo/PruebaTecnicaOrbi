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
                    capabilities.setCapability("deviceName", EnvironmentConfig.deviceName);
                    capabilities.setCapability("platformVersion", EnvironmentConfig.platformVersion);
                    capabilities.setCapability("appPackage", EnvironmentConfig.appPackage);
                    capabilities.setCapability("appActivity", EnvironmentConfig.appActivity);
                    capabilities.setCapability("platformName", EnvironmentConfig.platformName);
                    capabilities.setCapability("automationName", "UiAutomator2");
                    capabilities.setCapability("noReset", true);
                    capabilities.setCapability("fast_reset", true);
                    appiumDriver = new AndroidDriver(new URL("http://127.0.0.1:4723"), capabilities);
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
                    //System.setProperty("webdriver.chrome.driver", "C:\\Users\\pakig\\Documents\\GitHub\\TransNetworkFarmeworkBDD\\src\\test\\resources\\drivers\\Chrome\\chromedriver.exe");
                    ChromeOptions options = new ChromeOptions();
                    //options.addArguments("force-device-scale-factor=0.75");
                    WebDriverManager.chromedriver().setup();
                    webDriver = new ChromeDriver(options);
                    webDriver.manage().deleteAllCookies();

                    break;
                case "edgeLocal":
                    WebDriverManager.edgedriver().setup();
                    webDriver = new EdgeDriver();
                    webDriver.manage().deleteAllCookies();
                    break;
                case "chromeRemote":
                    DesiredCapabilities caps = new DesiredCapabilities();
                    caps.setCapability("browser", "Chrome");
                    caps.setCapability("browser_version", "latest");
                    caps.setCapability("os", "Windows");
                    caps.setCapability("os_version", "11");
                    caps.setCapability("resolution", "1850x1148");

                    String username = "serviciosti_IEYbZk";
                    String accessKey = "d6fPDFngKGyM2zygHEtF";
                    String browserStackUrl = "https://" + username + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub";

                    webDriver = new RemoteWebDriver(new URL(browserStackUrl), caps);
                    break;
                case "firefoxRemote":
                    break;
                case "edgeRemote":
                    break;
                case "chromeLocalS":
                    ChromeOptions optionsOther = new ChromeOptions();

                    // Configura el User-Agent
                    //optionsOther.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");

                    // Configura el proxy si es necesario
                    Proxy proxy = new Proxy();
                    proxy.setHttpProxy("your-proxy-ip:port"); // Reemplaza "your-proxy-ip:port" con la dirección de tu proxy
                    optionsOther.setProxy(proxy);

                    // Configura el modo headless si es necesario
                    // options.addArguments("--headless");

                    optionsOther.addArguments("force-device-scale-factor=0.75");
                    WebDriverManager.chromedriver().setup();
                    WebDriver webDriver = new ChromeDriver(optionsOther);
                    webDriver.manage().deleteAllCookies();
                    break;
            }

            return webDriver;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }//end startWebDriver




}//end Driver
