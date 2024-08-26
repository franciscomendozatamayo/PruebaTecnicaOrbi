package activities;

import core.ActivityHelper;
import core.Driver;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewUserActivity {

    public NewUserActivity(AppiumDriver appiumDriver){
        PageFactory.initElements(appiumDriver, this);
    }

    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[1]\n")
    WebElement inputName;
    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[2]\n")
    WebElement inputEmail;
    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[3]\n")
    WebElement inputAge;
    @FindBy(xpath = "//android.widget.Button[@content-desc=\"Create User\"]")
    WebElement btnCreateUser;

    @FindBy(xpath = "//android.view.View[@content-desc=\"Please enter the name\"]")
    WebElement messageErrorName;
    @FindBy(xpath = "//android.view.View[@content-desc=\"Please enter the email\"]")
    WebElement messageErrorEmail;
    @FindBy(xpath = "//android.view.View[@content-desc=\"Please enter the age\"]")
    WebElement messageErrorAge;
    @FindBy(xpath = "//android.view.View[@content-desc=\"Please enter a valid email address\"]\n")
    WebElement messageValidEmailAddress;
    @FindBy(xpath = "//android.view.View[@content-desc=\"Please enter a valid number\"]\n")
    WebElement messageValidAge;

    public void captureNewUser(String Name, String Email, String Age){
        ActivityHelper.clickElement(Driver.appiumDriver, inputName);
        ActivityHelper.sendKeysElement(Driver.appiumDriver, inputName, Name);
        ActivityHelper.clickElement(Driver.appiumDriver, inputEmail);
        ActivityHelper.sendKeysElement(Driver.appiumDriver, inputEmail, Email);
        ActivityHelper.clickElement(Driver.appiumDriver, inputAge);
        ActivityHelper.sendKeysElement(Driver.appiumDriver, inputAge, Age);
        ActivityHelper.clickElement(Driver.appiumDriver, btnCreateUser);
    }

    public String getMessageErrorName(){
        String text = ActivityHelper.getAttribute(Driver.appiumDriver, messageErrorName);
        return text;
    }

    public String getMessageErrorEmail(){
        String text = ActivityHelper.getAttribute(Driver.appiumDriver, messageErrorEmail);
        return text;
    }

    public String getMessageErrorAge(){
        String text = ActivityHelper.getAttribute(Driver.appiumDriver, messageErrorAge);
        return text;
    }

    public String getMessageValidEmailAddress(){
        String text = ActivityHelper.getAttribute(Driver.appiumDriver, messageValidEmailAddress);
        return text;
    }

    public String getMessageValidAge(){
        String text = ActivityHelper.getAttribute(Driver.appiumDriver, messageValidAge);
        return text;
    }




}
