package runner.api.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import runner.api.methods.APIMethods;
import runner.hook.Hooks;

public class APISteps {

    Response response;
    private APIMethods apiMethods = new APIMethods();
    String accessToken;

    @When("Resquest Method POST Access Token {string} {string}")
    public void Resquest_Method_POST_Access_Token(String username, String password){
        String logOutput = apiMethods.APILoginSuccessful(username, password);
        Hooks.log("JSON Request " + ":");
        Hooks.log(logOutput);
    }

    @When("Resquest Method POST Access Token {string} {string} - Failed")
    public void Resquest_Method_POST_Access_Token_Failed(String username, String password){
        apiMethods.APILoginFailed(username, password);
    }

    @Then("Validation JSON Response access_token - Access Token")
    public void Validation_JSON_Response_access_token_Access_Token(){
        boolean status = apiMethods.validateResponseContainsAccessToken();
        Assert.assertTrue(status);
    }

    @Then("Validation Response Code 201")
    public void Validation_Response_Code_201(){
        boolean status = apiMethods.validateStatusCodeIs201();
        Assert.assertTrue(status);
    }

    @Then("Validation Response Code 200")
    public void Validation_Response_Code_200(){
        boolean status = apiMethods.validateStatusCodeIs200();
        Assert.assertTrue(status);
    }

    @Then("Validation Response Code 400")
    public void Validation_Response_Code_400(){
        boolean status = apiMethods.validateStatusCodeIs400();
        Assert.assertTrue(status);
    }

    @Then("Validation Response Code 404")
    public void Validation_Response_Code_404(){
        boolean status = apiMethods.validateStatusCodeIs404();
        Assert.assertTrue(status);
    }

    @Then("Validation Response Time")
    public void Validation_Response_Time_Access_Token(){
        boolean status = apiMethods.validateResponseTimeLessThanOrEqualTo2Seconds();
        Assert.assertTrue(status);
    }

    @And("Save access_token")
    public void Save_access_token(){
        accessToken = apiMethods.getAccessToken();
        Hooks.log("JSON Response -  access_token" + ":");
        Hooks.log(accessToken);
    }

    @Then("Validation Response Code 401")
    public void Validation_Response_Code_401(){
        boolean status = apiMethods.validateStatusCodeIs401();
        Assert.assertTrue(status);
    }

    @Then("Validation Response Message - Access Token")
    public void Validation_Response_Message_Access_Token(){
        String expectedMessage = "Invalid credentials";
        boolean status = apiMethods.validateMessage(expectedMessage);
        Assert.assertTrue(status);
    }

    @Then("Validation Response Message - Create User")
    public void Validation_Response_Message_Create_User(){
        String expectedMessage = "Unauthorized";
        boolean status = apiMethods.validateMessage(expectedMessage);
        Assert.assertTrue(status);
    }

    @Then("Validation Response Error - Access Token")
    public void Validation_Response_Error_Access_Token(){
        String expectedError = "Unauthorized";
        boolean status = apiMethods.validateError(expectedError);
        Assert.assertTrue(status);
    }

    @Then("Validation Response Error - Create User")
    public void Validation_Response_Error_Create_User(){
        String expectedError = "Bad Request";
        boolean status = apiMethods.validateError(expectedError);
        Assert.assertTrue(status);
    }

    @Then("Validation Response Error - Update User")
    public void Validation_Response_Error_Update_User(){
        String expectedError = "Bad Request";
        boolean status = apiMethods.validateError(expectedError);
        Assert.assertTrue(status);
    }

    @Then("Validation Response Error - Not Found")
    public void Validation_Response_Error_Not_Found(){
        String expectedError = "Not Found";
        boolean status = apiMethods.validateError(expectedError);
        Assert.assertTrue(status);
    }

    @Then("Validation Response Message - Create User - Email is not captured")
    public void Validation_Response_Message_Create_User_Email_is_not_captured(){
        String expectedError = "email must be an email";
        boolean status = apiMethods.validateErrorMessage(expectedError);
        Assert.assertTrue(status);
    }

    @When("Resquest Method POST Create User {string} {string} {int}")
    public void Resquest_Method_POST_Create_User(String name, String email, Integer age){
        String logOutput = apiMethods.APICreateUser(name, email, age);
        Hooks.log("JSON Request " + ":");
        Hooks.log(logOutput);
    }

    @When("Resquest Method POST Create User {string} {string} {int} - Return Id")
    public void Resquest_Method_POST_Create_User_Return_Id(String name, String email, Integer age){
        apiMethods.APICreateUserUpdateInt(name, email, age);
    }

    @When("Resquest Method GET All users")
    public void Resquest_Method_GET_All_users(){
        String logOutput = apiMethods.APIAllUsers();
        Hooks.log("JSON Request " + ":");
        Hooks.log(logOutput);
    }

    @Then("Validate record within JSON Response list - All Users {string} {string} {int}")
    public void Validate_record_within_JSON_Response_list_All_Users(String name, String email, Integer age){
        int id = apiMethods.getUserId();

        boolean status = apiMethods.validateUserInfoInResponseList(id, name, email, age);
        Assert.assertTrue(status);
    }

    @When("Resquest Method POST Create User {string} {string} {int} - Without Id")
    public void Resquest_Method_POST_Create_User_Without_Id(String name, String email, Integer age){
        String logOutput = apiMethods.APICreateUserWithoutId(name, email, age);
        Hooks.log("JSON Request " + ":");
        Hooks.log(logOutput);
    }

    @When("Resquest Method POST Create User {string} {string} {string} - Age with string field")
    public void Resquest_Method_POST_Create_User_Age_with_string_field(String name, String email, String age){
        String logOutput = apiMethods.APICreateUserAgeString(name, email, age);
        Hooks.log("JSON Request " + ":");
        Hooks.log(logOutput);
    }

    @When("Resquest Method POST Create User {string} {int} - Name is not captured")
    public void Resquest_Method_POST_Create_User_Name_is_not_captured(String email, Integer age){
        String logOutput = apiMethods.APICreateUserNullName(email, age);
        Hooks.log("JSON Request " + ":");
        Hooks.log(logOutput);
    }

    @When("Resquest Method POST Create User {int} {string} {int} - Name with numeric field")
    public void Resquest_Method_POST_Create_User_Name_with_numeric_field(Integer name, String email, Integer age){
        String logOutput = apiMethods.APICreateUserNameNumeric(name, email, age);
        Hooks.log("JSON Request " + ":");
        Hooks.log(logOutput);
    }



    @When("Resquest Method POST Create User {string} {string} - Field Age No Information")
    public void Resquest_Method_POST_Create_User_Field_Age_No_Information(String name, String email){
        String logOutput = apiMethods.APICreateUserNullAge(name, email);
        Hooks.log("JSON Request " + ":");
        Hooks.log(logOutput);
    }

    @When("Resquest Method POST Create User {string} {int} {int} - Email with numeric field")
    public void Resquest_Method_POST_Create_User_Email_with_numeric_field(String name, Integer email, Integer age){
        String logOutput = apiMethods.APICreateUserEmailNumeric(name, email, age);
        Hooks.log("JSON Request " + ":");
        Hooks.log(logOutput);
    }

    @When("Resquest Method POST Create User {string} {string} {int} - Without Access Token")
    public void Resquest_Method_POST_Create_User_Without_Access_Token(String name, String email, Integer age){
        String logOutput = apiMethods.APICreateUserWithoutAccessToken(name, email, age);
        Hooks.log("JSON Request " + ":");
        Hooks.log(logOutput);
    }

    @Then("Validate JSON Response Create User {string} {string} {int}")
    public void Validate_JSON_Response_Create_User(String name, String email, Integer age){
        boolean status = apiMethods.validateJSONResponseCreateUser(name, email, age);
        Assert.assertTrue(status);
    }

    @When("Validate User by ID {string} {string} {int} {string} - User by ID")
    public void Validate_User_by_ID_User_by_ID(String name, String email, Integer age, String isActiveString){
        int id = apiMethods.getUserId();
        apiMethods.APIUserByDI(id);

        boolean statusID = apiMethods.validateID(id);
        Assert.assertTrue(statusID);

        boolean statusName = apiMethods.validateName(name);
        Assert.assertTrue(statusName);

        boolean statusEmail = apiMethods.validateEmail(email);
        Assert.assertTrue(statusEmail);

        boolean statusAge = apiMethods.validateAge(age);
        Assert.assertTrue(statusAge);

        boolean isActive = Boolean.parseBoolean(isActiveString);
        boolean statusIsActive = apiMethods.validateIsActive(isActive);
        Assert.assertTrue(statusIsActive);

    }

    @When("Validate User by ID {int} - User by ID - Does Not Exist")
    public void Validate_User_by_ID_User_by_ID_Does_Not_Exist(Integer id){
        apiMethods.APIUserByDI(id);
        boolean status = apiMethods.validateErrorUserById(id);
        Assert.assertTrue("El mensaje de error no coincide con el esperado", status);
    }

    @When("Update user {string} {string} {int}")
    public void Update_user(String nameUpdate, String emailUpdate, Integer ageUpdate){
        int id = apiMethods.getUserId();
        String logOutput = apiMethods.APIUpdateUser(nameUpdate, emailUpdate, ageUpdate, id);
        Hooks.log("JSON Request " + ":");
        Hooks.log(logOutput);
    }

    @When("Update user {string} {string} {string} - Age String")
    public void Update_user_Age_String(String nameUpdate, String emailUpdate, String ageUpdate){
        int id = apiMethods.getUserId();
        String logOutput = apiMethods.APIUpdateUserAgeString(nameUpdate, emailUpdate, ageUpdate, id);
        Hooks.log("JSON Request " + ":");
        Hooks.log(logOutput);
    }

    @When("Update user {string} {string} - Age NULL")
    public void Update_user_Age_NULL(String nameUpdate, String emailUpdate){
        int id = apiMethods.getUserId();
        String logOutput = apiMethods.APIUpdateUserAgeNULL(nameUpdate, emailUpdate, id);
        Hooks.log("JSON Request " + ":");
        Hooks.log(logOutput);
    }

    @When("Update user {string} {int} - name NULL")
    public void Update_user_name_NULL(String emailUpdate, Integer ageUpdate){
        int id = apiMethods.getUserId();
        String logOutput = apiMethods.APIUpdateUserNameNULL(emailUpdate, ageUpdate, id);
        Hooks.log("JSON Request " + ":");
        Hooks.log(logOutput);
    }

    @When("Update user {int} {string} {int} - Field Name Numeric")
    public void Update_user_Field_Name_Numeric(Integer nameUpdate, String emailUpdate, Integer ageUpdate){
        int id = apiMethods.getUserId();
        String logOutput = apiMethods.APIUpdateUserNameNumeric(nameUpdate, emailUpdate, ageUpdate, id);
        Hooks.log("JSON Request " + ":");
        Hooks.log(logOutput);
    }

    @When("Update user {string} {int} {int} - Field Email Numeric")
    public void Update_user_Field_Email_Numeric(String nameUpdate, Integer emailUpdate, Integer ageUpdate){
        int id = apiMethods.getUserId();
        String logOutput = apiMethods.APIUpdateUserEmailNumeric(nameUpdate, emailUpdate, ageUpdate, id);
        Hooks.log("JSON Request " + ":");
        Hooks.log(logOutput);
    }

    @When("When Update user {string} {string} {int} {int} - Non Existing ID")
    public void When_Update_user_Non_Existing_ID(String nameUpdate, String emailUpdate, Integer ageUpdate, Integer id){
        String logOutput = apiMethods.APIUpdateUser(nameUpdate, emailUpdate, ageUpdate, id);
        Hooks.log("JSON Request " + ":");
        Hooks.log(logOutput);
    }



    @Then("Validate JSON Response {string} {string} {int} {string}")
    public void Validate_JSON_Response(String nameUpdate, String emailUpdate, Integer ageUpdate, String isActiveString){
        int id = apiMethods.getUserId();
        boolean statusID = apiMethods.validateID(id);
        Assert.assertTrue(statusID);

        boolean statusName = apiMethods.validateName(nameUpdate);
        Assert.assertTrue(statusName);

        boolean statusEmail = apiMethods.validateEmail(emailUpdate);
        Assert.assertTrue(statusEmail);

        boolean statusAge = apiMethods.validateAge(ageUpdate);
        Assert.assertTrue(statusAge);

        boolean statusIsActive = apiMethods.validateIsActiveIsFalse();
        Assert.assertTrue("isActive debe ser false", statusIsActive);
    }

    @Then("Validate JSON Response {string} {string} {int} {string} - true")
    public void Validate_JSON_Response_true(String nameUpdate, String emailUpdate, Integer ageUpdate, String isActiveString){
        int id = apiMethods.getUserId();
        boolean statusID = apiMethods.validateID(id);
        Assert.assertTrue(statusID);

        boolean statusName = apiMethods.validateName(nameUpdate);
        Assert.assertTrue(statusName);

        boolean statusEmail = apiMethods.validateEmail(emailUpdate);
        Assert.assertTrue(statusEmail);

        boolean statusAge = apiMethods.validateAge(ageUpdate);
        Assert.assertTrue(statusAge);

        boolean statusIsActive = apiMethods.validateIsActiveIsTrue();
        Assert.assertTrue("isActive debe ser true", statusIsActive);
    }

    @When("Delete User")
    public void Delete_User(){
        int id = apiMethods.getUserId();
        apiMethods.APIDeleteUser(id);
    }

    @When("Delete User {int} - Non Existing ID")
    public void Delete_User_Non_Existing_ID(Integer id){
        apiMethods.APIDeleteUser(id);
    }






}
