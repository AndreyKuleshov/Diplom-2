package createuser;

import user.User;
import user.UserClient;

public abstract class CreateUserBaseClass {
    User user = User.createRandomUser();
    User userWithEmptyEmail = User.createRandomUserWithEmptyEmail();
    User userWithEmptyPassword = User.createRandomUserWithEmptyPassword();
    User userWithEmptyName = User.createRandomUserWithEmptyName();
    User userWithoutEmail = User.createRandomUserWithoutEmail();
    User userWithoutPassword = User.createRandomUserWithoutPassword();
    User userWithoutName = User.createRandomUserWithoutName();
    UserClient userClient = new UserClient();
    final String SUCCESS_PATH = "success";
}
