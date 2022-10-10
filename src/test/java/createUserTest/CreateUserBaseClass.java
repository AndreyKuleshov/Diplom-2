package createUserTest;

import UserData.UserDataRoot;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import user.User;
import user.UserClient;

public abstract class CreateUserBaseClass {
    User user;
    User userWithEmptyEmail;
    User userWithEmptyPassword;
    User userWithEmptyName;
    User userWithoutEmail;
    User userWithoutPassword;
    User userWithoutName;
    UserDataRoot userData;
    UserClient userClient = new UserClient();
    ValidatableResponse response;
    final String SUCCESS_PATH = "success";
    @Before
    public void setUp() {
        user = User.createRandomUser();
        userWithEmptyEmail = User.createRandomUserWithEmptyEmail();
        userWithEmptyPassword = User.createRandomUserWithEmptyPassword();
        userWithEmptyName = User.createRandomUserWithEmptyName();
        userWithoutEmail = User.createRandomUserWithoutEmail();
        userWithoutPassword = User.createRandomUserWithoutPassword();
        userWithoutName = User.createRandomUserWithoutName();
        response = userClient.createUser(user);
        userData = userClient.deserializeResponse(response);
    }

    @After
    public void cleanUp() {
        if (userData.isSuccess()) {
            userClient.deleteUser(userData.getAccessToken());
        }
    }
}
