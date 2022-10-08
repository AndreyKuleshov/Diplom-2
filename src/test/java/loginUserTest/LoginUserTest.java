package loginUserTest;

import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Test;
import user.UserCredentials;

public class LoginUserTest extends LoginUserBaseClass {
    @Test
    public void loginExistentUserTest() {
        Assert.assertTrue(userClient.loginUser(creds).statusCode(HttpStatus.SC_OK)
                .and()
                .extract().body().jsonPath().getBoolean(SUCCESS_PATH));
    }
    @Test
    public void loginWrongEmailTest() {
        Assert.assertFalse(userClient.loginUser(UserCredentials.setUserCredentials(WRONG_EMAIL, user.getPassword()))
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .and()
                .extract().body().jsonPath().getBoolean(SUCCESS_PATH));
    }
    @Test
    public void loginWrongPasswordTest() {
        Assert.assertFalse(userClient.loginUser(UserCredentials.setUserCredentials(user.getEmail(), WRONG_PASSWORD))
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .and()
                .extract().body().jsonPath().getBoolean(SUCCESS_PATH));
    }
    @Test
    public void loginMissingEmailTest() {
        Assert.assertFalse(userClient.loginUser(UserCredentials.setUserCredentials(null, user.getPassword()))
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .and()
                .extract().body().jsonPath().getBoolean(SUCCESS_PATH));
    }
    @Test
    public void loginMissingPasswordTest() {
        Assert.assertFalse(userClient.loginUser(UserCredentials.setUserCredentials(user.getEmail(), null))
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .and()
                .extract().body().jsonPath().getBoolean(SUCCESS_PATH));
    }

}
