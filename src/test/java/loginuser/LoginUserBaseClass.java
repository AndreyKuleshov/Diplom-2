package loginuser;

import userdata.UserDataRoot;
import org.junit.After;
import org.junit.Before;
import user.User;
import user.UserClient;
import user.UserCredentials;

public abstract class LoginUserBaseClass {
    User user;
    UserCredentials creds;
    UserDataRoot userData;
    UserClient userClient = new UserClient();
    final String SUCCESS_PATH = "success";
    final String WRONG_EMAIL = "X_X";
    final String WRONG_PASSWORD = "XXX";

    @Before
    public void setUp() {
        user = User.createRandomUser();
        creds = UserCredentials.getCredentialsFromUser(user);
        userData = userClient.deserializeResponse(userClient.createUser(user));
    }
    @After
    public void cleanUp() {
        if (userData.isSuccess()) {
            userClient.deleteUser(userData.getAccessToken());
        }
    }

}
