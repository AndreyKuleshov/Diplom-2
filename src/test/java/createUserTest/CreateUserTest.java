package createUserTest;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Test;

public class CreateUserTest extends CreateUserBaseClass {
    @Test
    @DisplayName("Create user")
    @Description("Success")
    public void createUserTest() {
        Assert.assertTrue(response.statusCode(HttpStatus.SC_OK)
                .and()
                .extract().body().jsonPath().getBoolean(SUCCESS_PATH));
    }
    @Test
    @DisplayName("Create two identical users")
    @Description("Failure")
    public void createIdenticalUsersTest() {
        Assert.assertFalse(userClient.createUser(user).statusCode(HttpStatus.SC_FORBIDDEN)
                .and()
                .extract().body().jsonPath().getBoolean(SUCCESS_PATH));
    }
    @Test
    @DisplayName("Create user with empty email")
    @Description("Failure")
    public void createUserWithEmptyEmailTest() {
        Assert.assertFalse(userClient.createUser(userWithEmptyEmail).statusCode(HttpStatus.SC_FORBIDDEN)
                .and()
                .extract().body().jsonPath().getBoolean(SUCCESS_PATH));
    }
    @Test
    @DisplayName("Create user with empty password")
    @Description("Failure")
    public void createUserWithEmptyPasswordTest() {
        Assert.assertFalse(userClient.createUser(userWithEmptyPassword).statusCode(HttpStatus.SC_FORBIDDEN)
                .and()
                .extract().body().jsonPath().getBoolean(SUCCESS_PATH));
    }
    @Test
    @DisplayName("Create user with empty name")
    @Description("Failure")
    public void createUserWithEmptyNameTest() {
        Assert.assertFalse(userClient.createUser(userWithEmptyName).statusCode(HttpStatus.SC_FORBIDDEN)
                .and()
                .extract().body().jsonPath().getBoolean(SUCCESS_PATH));
    }
    @Test
    @DisplayName("Create user without email")
    @Description("Failure")
    public void createUserWithoutEmailTest() {
        Assert.assertFalse(userClient.createUser(userWithoutEmail).statusCode(HttpStatus.SC_FORBIDDEN)
                .and()
                .extract().body().jsonPath().getBoolean(SUCCESS_PATH));
    }
    @Test
    @DisplayName("Create user without password")
    @Description("Failure")
    public void createUserWithoutPasswordTest() {
        Assert.assertFalse(userClient.createUser(userWithoutPassword).statusCode(HttpStatus.SC_FORBIDDEN)
                .and()
                .extract().body().jsonPath().getBoolean(SUCCESS_PATH));
    }
    @Test
    @DisplayName("Create user without name")
    @Description("Failure")
    public void createUserWithoutNameTest() {
        Assert.assertFalse(userClient.createUser(userWithoutName).statusCode(HttpStatus.SC_FORBIDDEN)
                .and()
                .extract().body().jsonPath().getBoolean(SUCCESS_PATH));
    }
}
