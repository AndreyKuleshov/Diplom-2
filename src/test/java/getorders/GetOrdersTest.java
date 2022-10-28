package getorders;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Test;

public class GetOrdersTest extends GetOrdersBaseClass {
    @Test
    @DisplayName("Get orders. Authorized user")
    @Description("Success")
    public void getOrderTest() {
        order.createOrder(userData.getAccessToken(), body);
        Assert.assertTrue(order.getOrdersList(userData.getAccessToken())
                .statusCode(HttpStatus.SC_OK)
                .and()
                .extract().body().jsonPath().getBoolean(SUCCESS_PATH));
    }
    @Test
    @DisplayName("Get one order - check that there is only one order in response")
    @Description("Success")
    public void getOneOrderTest() {
        order.createOrder(userData.getAccessToken(), body);
        Assert.assertEquals(1, order.getOrdersList(userData.getAccessToken())
                .statusCode(HttpStatus.SC_OK)
                .and()
                .extract().body().jsonPath().getList(ORDERS_PATH).size());
    }
    @Test
    @DisplayName("Get five orders - check that there are five orders in response")
    @Description("Success")
    public void getFiveOrdersTest() {
        order.createOrder(userData.getAccessToken(), body);
        order.createOrder(userData.getAccessToken(), body);
        order.createOrder(userData.getAccessToken(), body);
        order.createOrder(userData.getAccessToken(), body);
        order.createOrder(userData.getAccessToken(), body);
        Assert.assertEquals(5, order.getOrdersList(userData.getAccessToken())
                .statusCode(HttpStatus.SC_OK)
                .and()
                .extract().body().jsonPath().getList(ORDERS_PATH).size());
    }
    @Test
    @DisplayName("Get order. Unauthorized user")
    @Description("Failure")
    public void getOrderUnauthorizedTest() {
        order.createOrder(userData.getAccessToken(), body);
        Assert.assertFalse(order.getOrdersList()
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .and()
                .extract().body().jsonPath().getBoolean(SUCCESS_PATH));
    }

}
