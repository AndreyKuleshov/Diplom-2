package getorders;

import userdata.UserDataRoot;
import orders.CreateOrderBody;
import orders.OrdersClient;
import org.junit.After;
import org.junit.Before;
import user.User;
import user.UserClient;
import user.UserCredentials;

public abstract class GetOrdersBaseClass {
    static OrdersClient order = new OrdersClient();
    static CreateOrderBody body = new CreateOrderBody(OrdersClient.getIngredientsIds());
    User user;
    UserCredentials creds;
    UserDataRoot userData;
    UserClient userClient = new UserClient();
    final String SUCCESS_PATH = "success";
    final String ORDERS_PATH = "orders";



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
