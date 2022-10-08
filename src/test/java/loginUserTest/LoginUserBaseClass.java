package loginUserTest;

import UserData.UserDataRoot;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import user.User;
import user.UserClient;
import user.UserCredentials;

public abstract class LoginUserBaseClass {
    static User user;
    static UserCredentials creds;
    static UserDataRoot userData;
    static UserClient userClient = new UserClient();
    final String SUCCESS_PATH = "success";
    final String WRONG_EMAIL = "X_X";
    final String WRONG_PASSWORD = "XXX";

    @BeforeClass
    public static void setUp() {
        user = User.createRandomUser();
        creds = UserCredentials.getCredentialsFromUser(user);
        userData = userClient.deserializeResponse(userClient.createUser(user));
    }
    @AfterClass
    public static void cleanUp() {
        if (userData.isSuccess()) {
            userClient.deleteUser(userData.getAccessToken());
        }
    }

}
