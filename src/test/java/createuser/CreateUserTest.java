package createuser;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Test;

public class CreateUserTest extends CreateUserBaseClass {
    @Test
    @DisplayName("Create user")
    @Description("Success")
    public void createUserTest() {
        ValidatableResponse response = userClient.createUser(user);
        userClient.cleanUp(response);
        Assert.assertTrue(response.statusCode(HttpStatus.SC_OK)
                .and()
                .extract().body().jsonPath().getBoolean(SUCCESS_PATH));
    }
    @Test
    @DisplayName("Create two identical users")
    @Description("Failure")
    public void createIdenticalUsersTest() {
        ValidatableResponse response = userClient.createUser(user);
        ValidatableResponse response2 = userClient.createUser(user);
        userClient.cleanUp(response);
        userClient.cleanUp(response2);
        Assert.assertFalse(response2.statusCode(HttpStatus.SC_FORBIDDEN)
                .and()
                .extract().body().jsonPath().getBoolean(SUCCESS_PATH));
    }
    @Test
    @DisplayName("Create user with empty email")
    @Description("Failure")
    public void createUserWithEmptyEmailTest() {
        ValidatableResponse response = userClient.createUser(userWithEmptyEmail);
        userClient.cleanUp(response);
        Assert.assertFalse(response.statusCode(HttpStatus.SC_FORBIDDEN)
                .and()
                .extract().body().jsonPath().getBoolean(SUCCESS_PATH));
    }
    @Test
    @DisplayName("Create user with empty password")
    @Description("Failure")
    public void createUserWithEmptyPasswordTest() {
        ValidatableResponse response = userClient.createUser(userWithEmptyPassword);
        userClient.cleanUp(response);
        Assert.assertFalse(response.statusCode(HttpStatus.SC_FORBIDDEN)
                .and()
                .extract().body().jsonPath().getBoolean(SUCCESS_PATH));
    }
    @Test
    @DisplayName("Create user with empty name")
    @Description("Failure")
    public void createUserWithEmptyNameTest() {
        ValidatableResponse response = userClient.createUser(userWithEmptyName);
        userClient.cleanUp(response);
        Assert.assertFalse(response.statusCode(HttpStatus.SC_FORBIDDEN)
                .and()
                .extract().body().jsonPath().getBoolean(SUCCESS_PATH));
    }
    @Test
    @DisplayName("Create user without email")
    @Description("Failure")
    public void createUserWithoutEmailTest() {
        ValidatableResponse response = userClient.createUser(userWithoutEmail);
        userClient.cleanUp(response);
        Assert.assertFalse(response.statusCode(HttpStatus.SC_FORBIDDEN)
                .and()
                .extract().body().jsonPath().getBoolean(SUCCESS_PATH));
    }
    @Test
    @DisplayName("Create user without password")
    @Description("Failure")
    public void createUserWithoutPasswordTest() {
        ValidatableResponse response = userClient.createUser(userWithoutPassword);
        userClient.cleanUp(response);
        Assert.assertFalse(response.statusCode(HttpStatus.SC_FORBIDDEN)
                .and()
                .extract().body().jsonPath().getBoolean(SUCCESS_PATH));
    }
    @Test
    @DisplayName("Create user without name")
    @Description("Failure")
    public void createUserWithoutNameTest() {
        ValidatableResponse response = userClient.createUser(userWithoutName);
        userClient.cleanUp(response);
        Assert.assertFalse(response.statusCode(HttpStatus.SC_FORBIDDEN)
                .and()
                .extract().body().jsonPath().getBoolean(SUCCESS_PATH));
    }
}
