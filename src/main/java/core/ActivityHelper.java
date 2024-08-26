package core;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ActivityHelper {
    static WebDriverWait wait;

    public static boolean isDisplayedElement(AppiumDriver appiumDriver, WebElement element){
        wait = new WebDriverWait(appiumDriver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(element));
        try{
            return element.isDisplayed();

        }catch (Exception e){
            return false;
        }

    }//end isDisplayedElement

    public static void sendKeysElement(AppiumDriver appiumDriver, WebElement element, String text) {

        try {
            boolean isDisplayedElement = isDisplayedElement(appiumDriver,element);
            if (isDisplayedElement){
                element.clear();
                element.sendKeys(text);
            }
        }catch (Exception e){

            e.printStackTrace();
        }
    }//end sendKeysElement

    public static void clickElement(AppiumDriver appiumDriver, WebElement element) {

        try {
            boolean isDisplayedElement = isDisplayedElement(appiumDriver,element);
            if (isDisplayedElement==true){
                element.click();
            }

        }catch (Exception e){

            e.printStackTrace();
        }

    }//end clickElement

    public static String getTextElement(AppiumDriver appiumDriver, WebElement element){
        wait = new WebDriverWait(appiumDriver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(element));
        return  element.getText();
    }//end getTextElement

    public static String getAttribute(AppiumDriver appiumDriver, WebElement element){
        wait = new WebDriverWait(appiumDriver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getAttribute("content-desc");
    }//end getTextElement

    public static boolean clickOnElementWithContentDesc(AppiumDriver appiumDriver, String name) {
        wait = new WebDriverWait(appiumDriver, Duration.ofSeconds(30));
        boolean isElementFound = false;


        String targetXPath = "//*[contains(@content-desc, '" + name + "')]";
        System.out.println("Buscando el elemento con XPath: " + targetXPath);

        while (!isElementFound) {
            List<WebElement> elementsWithContentDesc = appiumDriver.findElements(By.xpath(targetXPath));

            if (!elementsWithContentDesc.isEmpty()) {
                for (WebElement element : elementsWithContentDesc) {
                    wait.until(ExpectedConditions.visibilityOf(element)); // Espera a que el elemento sea visible

                    // Verifica si el elemento es visible y habilitado
                    if (element.isDisplayed() && element.isEnabled()) {
                        isElementFound = true;
                        element.click(); // Hace clic en el elemento
                        System.out.println("Elemento encontrado y clicado con content-desc: " + element.getAttribute("content-desc"));
                        return true;
                    } else {
                        System.out.println("Elemento encontrado pero no está visible o habilitado para hacer clic.");
                    }
                }
            }


            if (!isElementFound) {
                System.out.println("Elemento no encontrado en la vista actual, realizando scroll...");
                scrollDownW3C(appiumDriver);
            }
        }

        return isElementFound;
    }

    private static void scrollDownW3C(AppiumDriver appiumDriver) {

        int screenHeight = appiumDriver.manage().window().getSize().getHeight();
        int screenWidth = appiumDriver.manage().window().getSize().getWidth();


        int startX = screenWidth / 2;
        int startY = (int) (screenHeight * 0.8);
        int endY = (int) (screenHeight * 0.2);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence scroll = new Sequence(finger, 1);
        scroll.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY));
        scroll.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        scroll.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), startX, endY));
        scroll.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        appiumDriver.perform(List.of(scroll));
    }


    public static boolean clickOnElementByContentDesc(AppiumDriver appiumDriver, String name) {
        wait = new WebDriverWait(appiumDriver, Duration.ofSeconds(30));
        boolean isElementFound = false;

        String targetXPath = "//*[contains(@content-desc, '" + name + "')]";
        System.out.println("Buscando el elemento con XPath: " + targetXPath);

        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(targetXPath)));

            if (element.isDisplayed() && element.isEnabled()) {
                element.click();
                System.out.println("Elemento encontrado y clicado con content-desc: " + element.getAttribute("content-desc"));
                isElementFound = true;
            } else {
                System.out.println("Elemento encontrado pero no está visible o habilitado para hacer clic.");
            }

        } catch (Exception e) {
            System.out.println("Elemento no encontrado con content-desc que contiene: " + name);
            e.printStackTrace();
        }

        return isElementFound;
    }

    public static boolean isElementPresentByText(AppiumDriver appiumDriver, String text) {
        wait = new WebDriverWait(appiumDriver, Duration.ofSeconds(30));
        boolean isPresent = false;

        // Construye el XPath para buscar el elemento basado en el texto proporcionado
        String targetXPath = "//android.widget.EditText[@text='" + text + "']";
        System.out.println("Buscando el elemento con XPath: " + targetXPath);

        try {
            // Encuentra el elemento usando el XPath
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(targetXPath)));

            // Verifica si el elemento está presente y visible
            isPresent = element.isDisplayed();
            System.out.println("Elemento presente en la pantalla con texto: " + text);

        } catch (Exception e) {
            System.out.println("Elemento no encontrado con el texto: " + text);
        }

        return isPresent;
    }

    public static void clickOnElementByText(AppiumDriver appiumDriver, String text) {
        wait = new WebDriverWait(appiumDriver, Duration.ofSeconds(30));

        String targetXPath = "//android.widget.EditText[@text='" + text + "']";
        System.out.println("Buscando el elemento con XPath: " + targetXPath);

        try {

            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(targetXPath)));


            if (element.isDisplayed()) {
                element.click();
                System.out.println("Elemento encontrado y clicado en la pantalla con texto: " + text);
            }

        } catch (Exception e) {
            System.out.println("Elemento no encontrado con el texto: " + text);
        }
    }

    public static void sendKeysToElementByText(AppiumDriver appiumDriver, String text, String inputText) {
        wait = new WebDriverWait(appiumDriver, Duration.ofSeconds(30));
        boolean isSuccess = false;

        String targetXPath = "//android.widget.EditText[@text='" + text + "']";
        System.out.println("Buscando el elemento con XPath: " + targetXPath);

        try {

            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(targetXPath)));

            if (element.isDisplayed()) {
                element.click();
                element.clear();
                element.sendKeys(inputText);
                System.out.println("Texto '" + inputText + "' enviado al elemento con texto: " + text);
            }

        } catch (Exception e) {
            System.out.println("Elemento no encontrado o no se pudo enviar texto al elemento con texto: " + text);
        }
    }


}//end ActivityHelper