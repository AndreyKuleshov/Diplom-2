package createUserTest;

import UserData.UserDataRoot;
import io.restassured.response.ValidatableResponse;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import user.User;
import user.UserClient;

public abstract class CreateUserBaseClass {
    static User user;
    static User userWithEmptyEmail;
    static User userWithEmptyPassword;
    static User userWithEmptyName;
    static User userWithoutEmail;
    static User userWithoutPassword;
    static User userWithoutName;
    static UserDataRoot userData;
    static UserClient userClient = new UserClient();
    static ValidatableResponse response;
    final String SUCCESS_PATH = "success";
    @BeforeClass
    public static void setUp() {
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

    @AfterClass
    public static void cleanUp() {
        if (userData.isSuccess()) {
            userClient.deleteUser(userData.getAccessToken());
        }
    }
}
