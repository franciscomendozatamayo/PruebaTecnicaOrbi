package runner.app;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import activities.LoginActivity;
import org.junit.Assert;
import runner.hook.Hooks;

public class LoginSteps {

    private LoginActivity loginPage = new LoginActivity(Hooks.appiumDriver);

    @Given("Open mobile application - Orbi")
    public void Open_mobile_application_Orbi(){

    }

    @When("Capture credentials {string} {string} and click on the Login button")
    public void Capture_credentials_and_click_on_the_Login_button(String username, String password) {
        loginPage.captureCredentialsAndLogin(username, password);
    }

    @Then("Error message is displayed {string} - Login username")
    public void Error_message_is_displayed_Login_username(String message){
        String actualMessage = loginPage.getMessageErrorUsername();
        Assert.assertEquals(message, actualMessage);
    }

    @Then("Error message is displayed {string} - Login password")
    public void Error_message_is_displayed_Login_password(String message){
        String actualMessage = loginPage.getMessageErrorPassword();
        Assert.assertEquals(message, actualMessage);
    }


}
