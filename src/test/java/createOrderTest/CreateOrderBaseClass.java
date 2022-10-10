package createOrderTest;

import UserData.UserDataRoot;
import orders.CreateOrderBody;
import orders.OrdersClient;
import org.junit.After;
import org.junit.Before;
import user.User;
import user.UserClient;
import user.UserCredentials;

import java.util.List;

public abstract class CreateOrderBaseClass {
    OrdersClient order = new OrdersClient();
    List<String> ingredients = OrdersClient.getIngredientsIds();
    CreateOrderBody body = new CreateOrderBody(ingredients);
    User user;
    UserCredentials creds;
    UserDataRoot userData;
    UserClient userClient = new UserClient();
    final String SUCCESS_PATH = "success";
    final List<String> WRONG_HASH = List.of("XXX");



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
