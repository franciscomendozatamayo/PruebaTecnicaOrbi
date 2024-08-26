package core;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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


}//end ActivityHelper