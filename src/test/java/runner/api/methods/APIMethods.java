package runner.api.methods;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.lang.ObjectUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class APIMethods {

    private Response response;
    private String accessToken;
    private int userId;

    public String APILoginSuccessful(String username, String password) {
        JSONObject json = new JSONObject();
        json.put("username", username);
        json.put("password", password);

        String url = "https://basic-api-challege-2bcb8cd31390.herokuapp.com/auth/login";

        response = RestAssured.given().contentType(ContentType.JSON).body(json.toString()).when().post(url);
        response.then().log().all();
        String requestString = json.toString();

        this.accessToken = getAccessToken();

        return requestString;
    }

    public void APILoginFailed(String username, String password) {
        JSONObject json = new JSONObject();
        json.put("username", username);
        json.put("password", password);

        String url = "https://basic-api-challege-2bcb8cd31390.herokuapp.com/auth/login";

        response = RestAssured.given().contentType(ContentType.JSON).body(json.toString()).when().post(url);
        response.then().log().all();
        String requestString = json.toString();
    }

    public boolean validateResponseContainsAccessToken() {
        String responseBody = response.getBody().asString();
        JSONObject jsonResponse = new JSONObject(responseBody);
        return jsonResponse.has("access_token");
    }

    public boolean validateStatusCodeIs201() {
        int statusCode = response.getStatusCode();
        return statusCode == 201;
    }

    public boolean validateStatusCodeIs200() {
        int statusCode = response.getStatusCode();
        return statusCode == 200;
    }

    public boolean validateResponseTimeLessThanOrEqualTo2Seconds() {
        long responseTime = response.getTime();
        return responseTime <= 2000;
    }

    public String getAccessToken() {
        String responseBody = response.getBody().asString();
        JSONObject jsonResponse = new JSONObject(responseBody);
        return jsonResponse.getString("access_token");
    }

    public boolean validateStatusCodeIs401() {
        int statusCode = response.getStatusCode();
        return statusCode == 401;
    }

    public boolean validateStatusCodeIs400() {
        int statusCode = response.getStatusCode();
        return statusCode == 400;
    }

    public boolean validateStatusCodeIs404() {
        int statusCode = response.getStatusCode();
        return statusCode == 404;
    }

    public boolean validateMessage(String expectedMessage) {
        String responseBody = response.getBody().asString();
        JSONObject jsonResponse = new JSONObject(responseBody);
        return jsonResponse.getString("message").equals(expectedMessage);
    }

    public boolean validateError(String expectedError) {
        String responseBody = response.getBody().asString();
        JSONObject jsonResponse = new JSONObject(responseBody);
        return jsonResponse.getString("error").equals(expectedError);
    }

    public boolean validateErrorMessage(String expectedMessage) {
        String responseBody = response.getBody().asString();
        JSONObject jsonResponse = new JSONObject(responseBody);

        JSONArray messages = jsonResponse.getJSONArray("message");

        boolean messageFound = false;
        for (int i = 0; i < messages.length(); i++) {
            if (messages.getString(i).equals(expectedMessage)) {
                messageFound = true;
                break;
            }
        }

        return messageFound;
    }

    public String APICreateUser(String name, String email, int age) {
        if (this.accessToken == null || this.accessToken.isEmpty()) {
            throw new IllegalStateException("Access token is not available. Please authenticate first.");
        }

        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("email", email);
        json.put("age", age);

        String url = "https://basic-api-challege-2bcb8cd31390.herokuapp.com/user";

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + this.accessToken)
                .body(json.toString())
                .when()
                .post(url);
        response.then().log().all();
        this.userId = getUserId();

        return json.toString();
    }

    public int APICreateUserUpdateInt(String name, String email, int age) {
        if (this.accessToken == null || this.accessToken.isEmpty()) {
            throw new IllegalStateException("Access token is not available. Please authenticate first.");
        }

        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("email", email);
        json.put("age", age);

        String url = "https://basic-api-challege-2bcb8cd31390.herokuapp.com/user";

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + this.accessToken)
                .body(json.toString())
                .when()
                .post(url);
        response.then().log().all();

        String responseBody = response.getBody().asString();
        JSONObject jsonResponse = new JSONObject(responseBody);
        this.userId = jsonResponse.getInt("id");
        return this.userId;
    }

    public String APIAllUsers() {
        if (this.accessToken == null || this.accessToken.isEmpty()) {
            throw new IllegalStateException("Access token is not available. Please authenticate first.");
        }
        String url = "https://basic-api-challege-2bcb8cd31390.herokuapp.com/user";

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + this.accessToken)
                .when()
                .get(url);
        response.then().log().all();

        return response.getBody().asString();
    }

    public boolean validateUserInfoInResponseList(int expectedId, String expectedName, String expectedEmail, int expectedAge) {
        String responseBody = response.getBody().asString();
        JSONArray jsonResponseArray = new JSONArray(responseBody);

        for (int i = 0; i < jsonResponseArray.length(); i++) {
            JSONObject user = jsonResponseArray.getJSONObject(i);

            boolean isIdValid = user.getInt("id") == expectedId;
            boolean isNameValid = user.getString("name").trim().equals(expectedName.trim());
            boolean isEmailValid = user.getString("email").equals(expectedEmail);

            boolean isAgeValid = !user.isNull("age") && user.getInt("age") == expectedAge;
            boolean isActiveValid = user.getBoolean("isActive");

            if (isIdValid && isNameValid && isEmailValid && isAgeValid && isActiveValid) {
                return true;
            }
        }
        return false;
    }

    public String APICreateUserWithoutId(String name, String email, int age) {
        if (this.accessToken == null || this.accessToken.isEmpty()) {
            throw new IllegalStateException("Access token is not available. Please authenticate first.");
        }

        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("email", email);
        json.put("age", age);

        String url = "https://basic-api-challege-2bcb8cd31390.herokuapp.com/user";

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + this.accessToken)
                .body(json.toString())
                .when()
                .post(url);
        response.then().log().all();
        return json.toString();
    }

    public String APICreateUserAgeString(String name, String email, String age) {
        if (this.accessToken == null || this.accessToken.isEmpty()) {
            throw new IllegalStateException("Access token is not available. Please authenticate first.");
        }

        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("email", email);
        json.put("age", age);

        String url = "https://basic-api-challege-2bcb8cd31390.herokuapp.com/user";

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + this.accessToken)
                .body(json.toString())
                .when()
                .post(url);
        response.then().log().all();
        return json.toString();
    }

    public String APICreateUserWithoutIdNameInteger(int name, String email, int age) {
        if (this.accessToken == null || this.accessToken.isEmpty()) {
            throw new IllegalStateException("Access token is not available. Please authenticate first.");
        }

        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("email", email);
        json.put("age", age);

        String url = "https://basic-api-challege-2bcb8cd31390.herokuapp.com/user";

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + this.accessToken)
                .body(json.toString())
                .when()
                .post(url);
        response.then().log().all();
        return json.toString();
    }

    public String APICreateUserNullAge(String name, String email) {
        if (this.accessToken == null || this.accessToken.isEmpty()) {
            throw new IllegalStateException("Access token is not available. Please authenticate first.");
        }

        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("email", email);
        json.put("age",JSONObject.NULL);

        String url = "https://basic-api-challege-2bcb8cd31390.herokuapp.com/user";

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + this.accessToken)
                .body(json.toString())
                .when()
                .post(url);
        response.then().log().all();
        return json.toString();
    }

    public String APICreateUserEmailNumeric(String name, int email, int age) {
        if (this.accessToken == null || this.accessToken.isEmpty()) {
            throw new IllegalStateException("Access token is not available. Please authenticate first.");
        }

        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("email", email);
        json.put("age",JSONObject.NULL);

        String url = "https://basic-api-challege-2bcb8cd31390.herokuapp.com/user";

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + this.accessToken)
                .body(json.toString())
                .when()
                .post(url);
        response.then().log().all();
        return json.toString();
    }

    public String APICreateUserNullName(String email, int age) {
        if (this.accessToken == null || this.accessToken.isEmpty()) {
            throw new IllegalStateException("Access token is not available. Please authenticate first.");
        }

        JSONObject json = new JSONObject();
        json.put("name", JSONObject.NULL);
        json.put("email", email);
        json.put("age", age);

        String url = "https://basic-api-challege-2bcb8cd31390.herokuapp.com/user";

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + this.accessToken)
                .body(json.toString())
                .when()
                .post(url);
        response.then().log().all();
        return json.toString();
    }

    public String APICreateUserNameNumeric(int name, String email, int age) {
        if (this.accessToken == null || this.accessToken.isEmpty()) {
            throw new IllegalStateException("Access token is not available. Please authenticate first.");
        }

        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("email", email);
        json.put("age", age);

        String url = "https://basic-api-challege-2bcb8cd31390.herokuapp.com/user";

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + this.accessToken)
                .body(json.toString())
                .when()
                .post(url);
        response.then().log().all();
        return json.toString();
    }

    public String APICreateUserWithoutAccessToken(String name, String email, int age) {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("email", email);
        json.put("age", age);

        String url = "https://basic-api-challege-2bcb8cd31390.herokuapp.com/user";

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + this.accessToken)
                .body(json.toString())
                .when()
                .post(url);
        response.then().log().all();
        return json.toString();
    }

    public int getUserId() {
        return this.userId;
    }

    public boolean validateJSONResponseCreateUser(String expectedName, String expectedEmail, int expectedAge) {
        String responseBody = response.getBody().asString();
        JSONObject jsonResponse = new JSONObject(responseBody);

        boolean isName = jsonResponse.getString("name").equals(expectedName);
        boolean isEmail = jsonResponse.getString("email").equals(expectedEmail);
        boolean isAge = jsonResponse.getInt("age") == expectedAge;
        boolean isActive = jsonResponse.getBoolean("isActive");

        return isName && isEmail && isAge && isActive;
    }

    public boolean validateStatusCodeIs400Text(String expectedStatusCode) {
        String responseBody = response.getBody().asString();
        JSONObject jsonResponse = new JSONObject(responseBody);
        return jsonResponse.getString("statusCode").equals(expectedStatusCode);
    }

    public void APIUserByDI(int id) {
        if (this.accessToken == null || this.accessToken.isEmpty()) {
            throw new IllegalStateException("Access token is not available. Please authenticate first.");
        }

        String url = "https://basic-api-challege-2bcb8cd31390.herokuapp.com/user/"+id;

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + this.accessToken)
                .when()
                .get(url);
        response.then().log().all();
    }

    public boolean validateID(int expectedID) {
        String responseBody = response.getBody().asString();
        JSONObject jsonResponse = new JSONObject(responseBody);
        return jsonResponse.getInt("id") == expectedID;
    }

    public boolean validateName(String expectedName) {
        String responseBody = response.getBody().asString();
        JSONObject jsonResponse = new JSONObject(responseBody);
        return jsonResponse.getString("name").equals(expectedName);
    }

    public boolean validateEmail(String expectedEmail) {
        String responseBody = response.getBody().asString();
        JSONObject jsonResponse = new JSONObject(responseBody);
        return jsonResponse.getString("email").equals(expectedEmail);
    }

    public boolean validateAge(int expectedAge) {
        String responseBody = response.getBody().asString();
        JSONObject jsonResponse = new JSONObject(responseBody);
        return jsonResponse.getInt("age") == expectedAge;
    }

    public boolean validateIsActive(boolean expectedIsActive) {
        String responseBody = response.getBody().asString();
        JSONObject jsonResponse = new JSONObject(responseBody);

        if (jsonResponse.has("isActive")) {
            boolean isActive = jsonResponse.getBoolean("isActive");
            response.then().log().all();
            return isActive == expectedIsActive;
        } else {
            response.then().log().all();
            return false;
        }
    }

    public boolean validateIsActiveIsFalse() {
        String responseBody = response.getBody().asString();
        JSONObject jsonResponse = new JSONObject(responseBody);

        if (jsonResponse.has("isActive")) {
            boolean isActive = jsonResponse.getBoolean("isActive");
            response.then().log().all();
            return !isActive;
        } else {
            response.then().log().all();
            throw new RuntimeException("Campo 'isActive' no encontrado en la respuesta JSON.");
        }
    }

    public boolean validateIsActiveIsTrue() {
        String responseBody = response.getBody().asString();
        JSONObject jsonResponse = new JSONObject(responseBody);
        if (jsonResponse.has("isActive")) {
            boolean isActive = jsonResponse.getBoolean("isActive");
            response.then().log().all();
            return isActive;
        } else {
            response.then().log().all();
            return false;
        }
    }

    public boolean validateErrorUserById(int value) {
        String responseBody = response.getBody().asString();
        JSONObject jsonResponse = new JSONObject(responseBody);
        String expectedMessage = "User with id " + value + " not found";
        return jsonResponse.getString("message").equals(expectedMessage);
    }

    public String APIUpdateUser(String name, String email, int age, int id) {
        if (this.accessToken == null || this.accessToken.isEmpty()) {
            throw new IllegalStateException("Access token is not available. Please authenticate first.");
        }

        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("email", email);
        json.put("age", age);

        String url = "https://basic-api-challege-2bcb8cd31390.herokuapp.com/user/"+id;

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + this.accessToken)
                .body(json.toString())
                .when()
                .put(url);
        response.then().log().all();
        return json.toString();
    }

    public String APIUpdateUserAgeString(String name, String email, String age, int id) {
        if (this.accessToken == null || this.accessToken.isEmpty()) {
            throw new IllegalStateException("Access token is not available. Please authenticate first.");
        }

        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("email", email);
        json.put("age", age);

        String url = "https://basic-api-challege-2bcb8cd31390.herokuapp.com/user/"+id;

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + this.accessToken)
                .body(json.toString())
                .when()
                .put(url);
        response.then().log().all();
        return json.toString();
    }

    public String APIUpdateUserNameNULL(String email, int age, int id) {
        if (this.accessToken == null || this.accessToken.isEmpty()) {
            throw new IllegalStateException("Access token is not available. Please authenticate first.");
        }

        JSONObject json = new JSONObject();
        json.put("name", JSONObject.NULL);
        json.put("email", email);
        json.put("age", age);

        String url = "https://basic-api-challege-2bcb8cd31390.herokuapp.com/user/"+id;

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + this.accessToken)
                .body(json.toString())
                .when()
                .put(url);
        response.then().log().all();
        return json.toString();
    }

    public String APIUpdateUserNameNumeric(int name, String email, int age, int id) {
        if (this.accessToken == null || this.accessToken.isEmpty()) {
            throw new IllegalStateException("Access token is not available. Please authenticate first.");
        }

        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("email", email);
        json.put("age", age);

        String url = "https://basic-api-challege-2bcb8cd31390.herokuapp.com/user/"+id;

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + this.accessToken)
                .body(json.toString())
                .when()
                .put(url);
        response.then().log().all();
        return json.toString();
    }

    public String APIUpdateUserEmailNumeric(String name, int email, int age, int id) {
        if (this.accessToken == null || this.accessToken.isEmpty()) {
            throw new IllegalStateException("Access token is not available. Please authenticate first.");
        }

        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("email", email);
        json.put("age", age);

        String url = "https://basic-api-challege-2bcb8cd31390.herokuapp.com/user/"+id;

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + this.accessToken)
                .body(json.toString())
                .when()
                .put(url);
        response.then().log().all();
        return json.toString();
    }

    public String APIUpdateUserAgeNULL(String name, String email, int id) {
        if (this.accessToken == null || this.accessToken.isEmpty()) {
            throw new IllegalStateException("Access token is not available. Please authenticate first.");
        }

        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("email", email);
        json.put("age", JSONObject.NULL);

        String url = "https://basic-api-challege-2bcb8cd31390.herokuapp.com/user/"+id;

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + this.accessToken)
                .body(json.toString())
                .when()
                .put(url);
        response.then().log().all();
        return json.toString();
    }

    public void APIDeleteUser(int id) {
        if (this.accessToken == null || this.accessToken.isEmpty()) {
            throw new IllegalStateException("Access token is not available. Please authenticate first.");
        }

        String url = "https://basic-api-challege-2bcb8cd31390.herokuapp.com/user/"+id;

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + this.accessToken)
                .when()
                .get(url);
        response.then().log().all();
    }









}
