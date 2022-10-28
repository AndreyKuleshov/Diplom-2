package loginuser;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Test;
import user.UserCredentials;

public class LoginUserTest extends LoginUserBaseClass {
    @Test
    @DisplayName("Login existent user")
    @Description("Success")
    public void loginExistentUserTest() {
        Assert.assertTrue(userClient.loginUser(creds).statusCode(HttpStatus.SC_OK)
                .and()
                .extract().body().jsonPath().getBoolean(SUCCESS_PATH));
    }
    @Test
    @DisplayName("Login user with wrong email")
    @Description("Failure")
    public void loginWrongEmailTest() {
        Assert.assertFalse(userClient.loginUser(UserCredentials.setUserCredentials(WRONG_EMAIL, user.getPassword()))
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .and()
                .extract().body().jsonPath().getBoolean(SUCCESS_PATH));
    }
    @Test
    @DisplayName("Login user with wrong password")
    @Description("Failure")
    public void loginWrongPasswordTest() {
        Assert.assertFalse(userClient.loginUser(UserCredentials.setUserCredentials(user.getEmail(), WRONG_PASSWORD))
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .and()
                .extract().body().jsonPath().getBoolean(SUCCESS_PATH));
    }
    @Test
    @DisplayName("Login user without email")
    @Description("Failure")
    public void loginMissingEmailTest() {
        Assert.assertFalse(userClient.loginUser(UserCredentials.setUserCredentials(null, user.getPassword()))
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .and()
                .extract().body().jsonPath().getBoolean(SUCCESS_PATH));
    }
    @Test
    @DisplayName("Login user without password")
    @Description("Failure")
    public void loginMissingPasswordTest() {
        Assert.assertFalse(userClient.loginUser(UserCredentials.setUserCredentials(user.getEmail(), null))
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .and()
                .extract().body().jsonPath().getBoolean(SUCCESS_PATH));
    }

}
