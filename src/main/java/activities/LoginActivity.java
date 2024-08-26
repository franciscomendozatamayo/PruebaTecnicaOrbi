package activities;

import core.ActivityHelper;
import core.Driver;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginActivity {

    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[1]\n")
    WebElement inputUsername;

    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[2]\n")
    WebElement inputPassword;

    @FindBy(xpath = "//android.widget.Button[@content-desc=\"Login\"]")
    WebElement btnLogin;

    @FindBy(xpath = "//android.view.View[@content-desc=\"Please enter your username\"]\n")
    WebElement messageErrorUsername;

    @FindBy(xpath = "//android.view.View[@content-desc=\"Please enter your password\"]\n")
    WebElement messageErrorPassword;


    public LoginActivity(AppiumDriver appiumDriver){
        PageFactory.initElements(appiumDriver, this);
    }
    public void captureCredentialsAndLogin(String username, String password){
        ActivityHelper.clickElement(Driver.appiumDriver, inputUsername);
        ActivityHelper.sendKeysElement(Driver.appiumDriver, inputUsername, username);
        ActivityHelper.clickElement(Driver.appiumDriver, inputPassword);
        ActivityHelper.sendKeysElement(Driver.appiumDriver, inputPassword, password);
        ActivityHelper.clickElement(Driver.appiumDriver, btnLogin);
    }

    public String getMessageErrorUsername(){
        String text = ActivityHelper.getAttribute(Driver.appiumDriver, messageErrorUsername);
        return text;
    }

    public String getMessageErrorPassword(){
        String text = ActivityHelper.getAttribute(Driver.appiumDriver, messageErrorPassword);
        return text;
    }



}
