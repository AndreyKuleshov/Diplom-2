package updateUserTest;

import UserData.UserDataRoot;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import user.User;
import user.UserClient;
import user.UserCredentials;

public abstract class UpdateUserBaseClass {
    static User user;
    static User userTwo;
    static UserCredentials creds;
    static UserCredentials credsUserTwo;
    static UserDataRoot userData;
    static UserDataRoot userTwoData;
    static UserClient userClient = new UserClient();
    final String EMAIL_PATH = "user.email";
    final String NAME_PATH = "user.name";
    final String SUCCESS_PATH = "success";


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
    protected String randomString() {
        return RandomStringUtils.randomAlphanumeric(10);
    }
}
