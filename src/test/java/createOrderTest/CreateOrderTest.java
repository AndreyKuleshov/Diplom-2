package createOrderTest;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import orders.CreateOrderBody;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class CreateOrderTest extends CreateOrderBaseClass {

    @Test
    @DisplayName("Create order with ingredients. Authorized user")
    @Description("Success")
    public void createOrderWithIngredientsAuthorizedTest() {
        Assert.assertTrue(order.createOrder(userData.getAccessToken(), body)
                .statusCode(HttpStatus.SC_OK)
                .and()
                .extract().body().jsonPath().getBoolean(SUCCESS_PATH));
    }
    @Test
    @DisplayName("Create order without ingredients. Authorized user")
    @Description("Failure")
    public void createOrderWithoutIngredientsAuthorizedTest() {
        Assert.assertFalse(order.createOrder(userData.getAccessToken(), new CreateOrderBody(new ArrayList<>()))
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .and()
                .extract().body().jsonPath().getBoolean(SUCCESS_PATH));
    }
    @Test
    @DisplayName("Create order with null ingredients. Authorized user")
    @Description("Failure")
    public void createOrderWithNullIngredientsAuthorizedTest() {
        Assert.assertFalse(order.createOrder(userData.getAccessToken(), new CreateOrderBody(null))
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .and()
                .extract().body().jsonPath().getBoolean(SUCCESS_PATH));
    }
    @Test
    @DisplayName("Create order with wrong ingredients. Authorized user")
    @Description("Failure")
    public void createOrderWithWrongHashOfIngredientsAuthorizedTest() {
        order.createOrder(userData.getAccessToken(), new CreateOrderBody(WRONG_HASH))
                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }
    @Test
    @DisplayName("Check the number of ingredients in the order. Authorized user")
    @Description("Success")
    public void ingredientsNumberTest() {
        Assert.assertEquals(ingredients.size(), order.getIngredientsFromOrder(userData.getAccessToken(), body).size());
    }
    @Test
    @DisplayName("Create order with ingredients. Unauthorized user")
    @Description("Success")
    public void createOrderWithIngredientsUnauthorizedTest() {
        Assert.assertTrue(order.createOrder(body)
                .statusCode(HttpStatus.SC_OK)
                .and()
                .extract().body().jsonPath().getBoolean(SUCCESS_PATH));
    }
    @Test
    @DisplayName("Create order without ingredients. Unauthorized user")
    @Description("Failure")
    public void createOrderWithoutIngredientsUnauthorizedTest() {
        Assert.assertFalse(order.createOrder(new CreateOrderBody(new ArrayList<>()))
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .and()
                .extract().body().jsonPath().getBoolean(SUCCESS_PATH));
    }
    @Test
    @DisplayName("Create order with null ingredients. Unauthorized user")
    @Description("Failure")
    public void createOrderWithNullIngredientsUnauthorizedTest() {
        Assert.assertFalse(order.createOrder(new CreateOrderBody(null))
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .and()
                .extract().body().jsonPath().getBoolean(SUCCESS_PATH));
    }
    @Test
    @DisplayName("Create order with wrong ingredients. Unauthorized user")
    @Description("Failure")
    public void createOrderWithWrongHashOfIngredientsUnauthorizedTest() {
        order.createOrder(new CreateOrderBody(WRONG_HASH))
                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }

}
