package runner.app;

import activities.NewUserActivity;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import runner.hook.Hooks;

public class NewUserSteps {

    private NewUserActivity newUserActivity = new NewUserActivity(Hooks.appiumDriver);

    @And("Add new user {string} {string} {string} - New User")
    public void Add_new_user_New_User(String Name, String Email, String Age) throws InterruptedException {
        newUserActivity.captureNewUser(Name, Email, Age);
        Thread.sleep(5000);
    }

    @Then("Error message {string} displayed Name - New User")
    public void Error_message_displayed_Name_New_User(String message){
        String label = newUserActivity.getMessageErrorName();
        Assert.assertEquals(message, label);
    }

    @Then("Error message {string} displayed Email - New User")
    public void Error_message_displayed_Email_New_User(String message){
        String label = newUserActivity.getMessageErrorEmail();
        Assert.assertEquals(message, label);
    }

    @Then("Error message {string} displayed Age - New User")
    public void Error_message_displayed_Age_New_User(String message){
        String label = newUserActivity.getMessageErrorAge();
        Assert.assertEquals(message, label);
    }

    @Then("Error message {string} displayed Invalid email - New User")
    public void Error_message_displayed_Invalid_email_New_User(String message){
        String label = newUserActivity.getMessageValidEmailAddress();
        Assert.assertEquals(message, label);
    }

    @Then("Error message {string} displayed Invalid age - New User")
    public void Error_message_displayed_Invalid_age_New_User(String message){
        String label = newUserActivity.getMessageValidAge();
        Assert.assertEquals(message, label);
    }

    @Then("Validate user registration data {string} {string} {string} - New User")
    public void Validate_user_registration_data_New_User(String Name, String Email, String Age) throws InterruptedException {
        boolean statusName = newUserActivity.getTextElementByText(Name);
        Assert.assertTrue(statusName);
        boolean statusEmail = newUserActivity.getTextElementByText(Email);
        Assert.assertTrue(statusEmail);
        boolean statusAge = newUserActivity.getTextElementByText(Age);
        Assert.assertTrue(statusAge);
    }

    @When("Update User {string} {string} {string} {string} {string} {string} - User Details")
    public void Update_User_User_Details(String Name, String Email, String Age, String NameUpdate, String EmailUpdate, String Details){
        newUserActivity.updateUser(Name, Email, Age, NameUpdate, EmailUpdate, Details);
    }
}
