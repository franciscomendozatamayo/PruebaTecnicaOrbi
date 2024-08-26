package runner.hook;

import core.Driver;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Hooks {

    public static AppiumDriver appiumDriver;
    public static WebDriver webdriver;
    private static Scenario currentScenario;

    @Before
    public void setUp(Scenario scenario) {

        currentScenario = scenario;

        if (scenario.getSourceTagNames().contains("@mobile")){

            if(scenario.getSourceTagNames().contains("@androidLocal")){
                appiumDriver = Driver.startDevice("androidLocal");
                scenario.attach("Start Scenario: " + scenario.getName(), "text/plain", "Start Scenario");
            } else if (scenario.getSourceTagNames().contains("@iosLocal")) {
                appiumDriver = Driver.startDevice("iosLocal");
                scenario.attach("Start Scenario: " + scenario.getName(), "text/plain", "Start Scenario");
            } else if (scenario.getSourceTagNames().contains("@androidRemote")) {
                appiumDriver = Driver.startDevice("androidRemote");
                scenario.attach("Start Scenario: " + scenario.getName(), "text/plain", "Start Scenario");
            } else if (scenario.getSourceTagNames().contains("@iosRemote")) {
                appiumDriver = Driver.startDevice("iosRemote");
                scenario.attach("Start Scenario: " + scenario.getName(), "text/plain", "Start Scenario");
            }


        } else if (scenario.getSourceTagNames().contains("@web")) {
            System.out.println("Initialize Selenium");
            if (scenario.getSourceTagNames().contains("@firefoxLocal")){
                webdriver = Driver.startWebDriver("firefoxLocal");
                scenario.attach("Start Scenario: " + scenario.getName(), "text/plain", "Start Scenario");
            } else if (scenario.getSourceTagNames().contains("@chromeLocal")) {
                webdriver = Driver.startWebDriver("chromeLocal");
                scenario.attach("Start Scenario: " + scenario.getName(), "text/plain", "Start Scenario");
            } else if (scenario.getSourceTagNames().contains("@chromeLocalS")) {
                webdriver = Driver.startWebDriver("chromeLocalS");
                scenario.attach("Start Scenario: " + scenario.getName(), "text/plain", "Start Scenario");
            }
            else if (scenario.getSourceTagNames().contains("@edgeLocal")) {
                webdriver = Driver.startWebDriver("edgeLocal");
                scenario.attach("Start Scenario: " + scenario.getName(), "text/plain", "Start Scenario");
            } else if (scenario.getSourceTagNames().contains("@chromeRemote")) {
                webdriver = Driver.startWebDriver("chromeRemote");
                scenario.attach("Start Scenario: " + scenario.getName(), "text/plain", "Start Scenario");
            } else if (scenario.getSourceTagNames().contains("@firefoxRemote")) {
                webdriver = Driver.startWebDriver("firefoxRemote");
                scenario.attach("Start Scenario: " + scenario.getName(), "text/plain", "Start Scenario");
            } else if (scenario.getSourceTagNames().contains("@edgeRemote")) {
                webdriver = Driver.startWebDriver("edgeRemote");
                scenario.attach("Start Scenario: " + scenario.getName(), "text/plain", "Start Scenario");
            }

        } else if (scenario.getSourceTagNames().contains("@api")) {
            System.out.println("Initialize RestAssured");
        }


    }//end setUp

    @AfterStep
    public void afterEachStep(Scenario scenario) throws InterruptedException {

        if (scenario.getSourceTagNames().contains("@mobile")){

            System.out.println("Step Appium");
            Thread.sleep(1000);
            final byte[] screenshot = takeScreenshotMobile();
            scenario.attach(screenshot, "image/png", "Step Screenshot");

        } else if (scenario.getSourceTagNames().contains("@web")) {
            System.out.println("Step Selenium");
            Thread.sleep(1000);
            final byte[] screenshot = takeScreenshotWeb();
            scenario.attach(screenshot, "image/png", "Step Screenshot");

        } else if (scenario.getSourceTagNames().contains("@api")) {
            System.out.println("Step RestAssured");


        }


    }//end afterEachStep

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {

            if (scenario.getSourceTagNames().contains("@mobile")){

                final byte[] screenshot = takeScreenshotMobile();
                scenario.attach(screenshot, "image/png", "Bug");

            } else if (scenario.getSourceTagNames().contains("@web")) {

                final byte[] screenshot = takeScreenshotWeb();
                scenario.attach(screenshot, "image/png", "Bug");

            } else if (scenario.getSourceTagNames().contains("@api")) {
                System.out.println("End RestAssured");
            }

        } else if (scenario.getSourceTagNames().contains("@mobile")) {

            if(appiumDriver != null) {
                appiumDriver.quit();
            }
        } else if (scenario.getSourceTagNames().contains("@web")) {

            if(webdriver != null) {
                webdriver.quit();
            }
        } else if (scenario.getSourceTagNames().contains("@api")) {

        }

    }//end tearDown

    private byte[] takeScreenshotMobile() {
        byte[] screenshot = ((TakesScreenshot) appiumDriver).getScreenshotAs(OutputType.BYTES);

        // Convirtiendo la captura de pantalla a una imagen BufferedImage
        BufferedImage img = null;
        try {
            img = ImageIO.read(new ByteArrayInputStream(screenshot));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Reduciendo la resolución
        Image tmp = img.getScaledInstance(img.getWidth() / 3, img.getHeight() / 3, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(img.getWidth() / 3, img.getHeight() / 3, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        // Convirtiendo la imagen reducida de nuevo a un array de bytes
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(dimg, "png", baos);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return baos.toByteArray();
    }

    private byte[] takeScreenshotWeb() {
        byte[] screenshot = ((TakesScreenshot) webdriver).getScreenshotAs(OutputType.BYTES);

        // Convirtiendo la captura de pantalla a una imagen BufferedImage
        BufferedImage img = null;
        try {
            img = ImageIO.read(new ByteArrayInputStream(screenshot));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Reduciendo la resolución
        Image tmp = img.getScaledInstance(img.getWidth() / 3, img.getHeight() / 3, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(img.getWidth() / 3, img.getHeight() / 3, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        // Convirtiendo la imagen reducida de nuevo a un array de bytes
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(dimg, "png", baos);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return baos.toByteArray();
    }

    public static void log(String message) {
        if (currentScenario != null) {
            currentScenario.log(message);
        }
    }



}//end Hooks