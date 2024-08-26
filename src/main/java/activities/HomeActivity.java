package activities;

import core.ActivityHelper;
import core.Driver;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomeActivity {

    public HomeActivity(AppiumDriver appiumDriver){
        PageFactory.initElements(appiumDriver, this);
    }

    @FindBy(xpath = "//android.view.View[@content-desc=\"Users List\"]")
    WebElement labelTitleUsersList;

    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.Button\n")
    WebElement btnAddRecord;

    public boolean getLabelTitleUsersList(){
        boolean label = ActivityHelper.isDisplayedElement(Driver.appiumDriver, labelTitleUsersList);
        return label;
    }

    public void clickBtnAddRecord(){
        ActivityHelper.clickElement(Driver.appiumDriver, btnAddRecord);
    }

    public void getAllContentDescAttributes(String name){
        ActivityHelper.clickOnElementByContentDesc(Driver.appiumDriver, name);
    }

}
