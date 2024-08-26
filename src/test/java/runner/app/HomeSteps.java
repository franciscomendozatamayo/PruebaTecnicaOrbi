package runner.app;

import activities.HomeActivity;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import runner.hook.Hooks;

public class HomeSteps {

    private HomeActivity homeActivity = new HomeActivity(Hooks.appiumDriver);

    @Then("Title is displayed - Users List")
    public void Title_is_displayed_Users_List(){
        boolean status = homeActivity.getLabelTitleUsersList();
        Assert.assertTrue(status);
    }

    @And("Click New User")
    public void Click_New_User(){
        homeActivity.clickBtnAddRecord();
    }
}
