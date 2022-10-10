package updateUserTest;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Test;
import user.*;

import static org.hamcrest.Matchers.equalTo;

public class UpdateUserTest extends UpdateUserBaseClass {
    @Test
    @DisplayName("Update user. All fields")
    @Description("Success")
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
    @DisplayName("Update user's email. Name stays unchanged")
    @Description("Success")
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
    @DisplayName("Update user's name. Email stays unchanged")
    @Description("Success")
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
    @DisplayName("Update nothing. Name and email stay unchanged")
    @Description("Success")
    public void updateNothingTest() {
        userClient.updateUserNothing(userData.getAccessToken())
                .statusCode(HttpStatus.SC_OK)
                .and()
                .body(EMAIL_PATH, equalTo(user.getEmail()))
                .and()
                .body(NAME_PATH, equalTo(user.getName()));
    }
    @Test
    @DisplayName("Update user's with email that had been used by another user")
    @Description("Failure")
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
    @DisplayName("Update user. Unauthorized")
    @Description("Failure")
    public void updateUserAllFieldsUnauthorizedTest() {
        String email = randomString().toLowerCase();
        String name = randomString();
        Assert.assertFalse(userClient.updateUser("", new UserUpdate(email, name))
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .and()
                .extract().jsonPath().getBoolean(SUCCESS_PATH));
    }
    @Test
    @DisplayName("Update user's email. Unauthorized")
    @Description("Failure")
    public void updateUserEmailUnauthorizedTest() {
        String email = randomString().toLowerCase();
        Assert.assertFalse(userClient.updateUserEmail("", new UserUpdateEmail(email))
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .and()
                .extract().jsonPath().getBoolean(SUCCESS_PATH));
    }
    @Test
    @DisplayName("Update user's name. Unauthorized")
    @Description("Failure")
    public void updateUserNameUnauthorizedTest() {
        String name = randomString();
        Assert.assertFalse(userClient.updateUserName("", new UserUpdateName(name))
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .and()
                .extract().jsonPath().getBoolean(SUCCESS_PATH));
    }
    @Test
    @DisplayName("Update nothing. Unauthorized")
    @Description("Failure")
    public void updateNothingUnauthorizedTest() {
        Assert.assertFalse(userClient.updateUserNothing("")
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .and()
                .extract().jsonPath().getBoolean(SUCCESS_PATH));
    }

}
