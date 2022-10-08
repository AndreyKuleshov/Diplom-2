package createUserTest;

import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Test;

public class CreateUserTest extends CreateUserBaseClass {
    @Test
    public void createUserTest() {
        Assert.assertTrue(response.statusCode(HttpStatus.SC_OK)
                .and()
                .extract().body().jsonPath().getBoolean(SUCCESS_PATH));
    }
    @Test
    public void createIdenticalUsersTest() {
        Assert.assertFalse(userClient.createUser(user).statusCode(HttpStatus.SC_FORBIDDEN)
                .and()
                .extract().body().jsonPath().getBoolean(SUCCESS_PATH));
    }
    @Test
    public void createUserWithEmptyEmailTest() {
        Assert.assertFalse(userClient.createUser(userWithEmptyEmail).statusCode(HttpStatus.SC_FORBIDDEN)
                .and()
                .extract().body().jsonPath().getBoolean(SUCCESS_PATH));
    }
    @Test
    public void createUserWithEmptyPasswordTest() {
        Assert.assertFalse(userClient.createUser(userWithEmptyPassword).statusCode(HttpStatus.SC_FORBIDDEN)
                .and()
                .extract().body().jsonPath().getBoolean(SUCCESS_PATH));
    }
    @Test
    public void createUserWithEmptyNameTest() {
        Assert.assertFalse(userClient.createUser(userWithEmptyName).statusCode(HttpStatus.SC_FORBIDDEN)
                .and()
                .extract().body().jsonPath().getBoolean(SUCCESS_PATH));
    }
    @Test
    public void createUserWithoutEmailTest() {
        Assert.assertFalse(userClient.createUser(userWithoutEmail).statusCode(HttpStatus.SC_FORBIDDEN)
                .and()
                .extract().body().jsonPath().getBoolean(SUCCESS_PATH));
    }
    @Test
    public void createUserWithoutPasswordTest() {
        Assert.assertFalse(userClient.createUser(userWithoutPassword).statusCode(HttpStatus.SC_FORBIDDEN)
                .and()
                .extract().body().jsonPath().getBoolean(SUCCESS_PATH));
    }
    @Test
    public void createUserWithoutNameTest() {
        Assert.assertFalse(userClient.createUser(userWithoutName).statusCode(HttpStatus.SC_FORBIDDEN)
                .and()
                .extract().body().jsonPath().getBoolean(SUCCESS_PATH));
    }
}
