package updateUserTest;

import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Test;
import user.*;

import static org.hamcrest.Matchers.equalTo;

public class UpdateUserTest extends UpdateUserBaseClass {
    @Test
    public void updateUserAllFieldsTest() {
        String email = randomString().toLowerCase();
        String name = randomString();
        userClient.updateUser(userData.getAccessToken(), new UserUpdate(email, name))
                .statusCode(HttpStatus.SC_OK)
                .and()
                .body(EMAIL_PATH, equalTo(email))
                .and()
                .body(NAME_PATH, equalTo(name));
    }
    @Test
    public void updateUserEmailTest() {
        String email = randomString().toLowerCase();
        userClient.updateUserEmail(userData.getAccessToken(), new UserUpdateEmail(email))
                .statusCode(HttpStatus.SC_OK)
                .and()
                .body(EMAIL_PATH, equalTo(email))
                .and()
                .body(NAME_PATH, equalTo(user.getName()));
    }
    @Test
    public void updateUserNameTest() {
        String name = randomString();
        userClient.updateUserName(userData.getAccessToken(), new UserUpdateName(name))
                .statusCode(HttpStatus.SC_OK)
                .and()
                .body(EMAIL_PATH, equalTo(user.getEmail()))
                .and()
                .body(NAME_PATH, equalTo(name));
    }
    @Test
    public void updateNothingTest() {
        userClient.updateUserNothing(userData.getAccessToken())
                .statusCode(HttpStatus.SC_OK)
                .and()
                .body(EMAIL_PATH, equalTo(user.getEmail()))
                .and()
                .body(NAME_PATH, equalTo(user.getName()));
    }
    @Test
    public void updateUserWithExistingEmail() {
        userTwo = User.createRandomUser();
        credsUserTwo = UserCredentials.getCredentialsFromUser(userTwo);
        userTwoData = userClient.deserializeResponse(userClient.createUser(userTwo));
        Assert.assertFalse(userClient.updateUserEmail(userData.getAccessToken(), new UserUpdateEmail(credsUserTwo.getEmail()))
                .statusCode(HttpStatus.SC_FORBIDDEN)
                .and()
                .extract().jsonPath().getBoolean(SUCCESS_PATH));
        userClient.deleteUser(userTwoData.getAccessToken());
    }
    @Test
    public void updateUserAllFieldsUnauthorizedTest() {
        String email = randomString().toLowerCase();
        String name = randomString();
        Assert.assertFalse(userClient.updateUser("", new UserUpdate(email, name))
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .and()
                .extract().jsonPath().getBoolean(SUCCESS_PATH));
    }
    @Test
    public void updateUserEmailUnauthorizedTest() {
        String email = randomString().toLowerCase();
        Assert.assertFalse(userClient.updateUserEmail("", new UserUpdateEmail(email))
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .and()
                .extract().jsonPath().getBoolean(SUCCESS_PATH));
    }
    @Test
    public void updateUserNameUnauthorizedTest() {
        String name = randomString();
        Assert.assertFalse(userClient.updateUserName("", new UserUpdateName(name))
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .and()
                .extract().jsonPath().getBoolean(SUCCESS_PATH));
    }
    @Test
    public void updateNothingUnauthorizedTest() {
        Assert.assertFalse(userClient.updateUserNothing("")
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .and()
                .extract().jsonPath().getBoolean(SUCCESS_PATH));
    }

}
