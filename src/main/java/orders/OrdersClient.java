package orders;

import config.BaseClient;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import java.util.ArrayList;
import java.util.List;

public class OrdersClient extends BaseClient {
    protected final String ORDERS_ENDPOINT = "orders";
    protected static final String GET_INGREDIENTS = "ingredients";
    private static final String INGREDIENTS_PATH = "order.ingredients";

    @Step("Create order. Authorized user")
    public ValidatableResponse createOrder(String token, CreateOrderBody body) {
        return getSpec(token)
                .body(body)
                .when()
                .post(ORDERS_ENDPOINT)
                .then();
    }
    @Step("Create order. Unauthorized user")
    public ValidatableResponse createOrder(CreateOrderBody body) {
        return getSpec()
                .body(body)
                .when()
                .post(ORDERS_ENDPOINT)
                .then();
    }
    @Step("Get ingredients from the order")
    public List<Ingredient> getIngredientsFromOrder(String token, CreateOrderBody body) {
        return createOrder(token, body)
                .extract().jsonPath().getList(INGREDIENTS_PATH, Ingredient.class);
    }
    @Step("Get orders list. Authorized user")
    public ValidatableResponse getOrdersList(String token) {
        return getSpec(token)
                .when()
                .get(ORDERS_ENDPOINT)
                .then();
    }
    @Step("Get orders list. Unauthorized user")
    public ValidatableResponse getOrdersList() {
        return getSpec()
                .when()
                .get(ORDERS_ENDPOINT)
                .then();
    }

    private static List<Ingredient> getIngredients() {
        return getSpec()
                .when()
                .get(GET_INGREDIENTS)
                .then()
                .extract()
                .jsonPath()
                .getList("data", Ingredient.class);
    }
    @Step("Get Ingredients ids from the list of available ingredients")
    public static List<String> getIngredientsIds() {
        List<Ingredient> ingredients = getIngredients();
        List<String> ids = new ArrayList<>();
        for (Ingredient ingredient : ingredients) {
            ids.add(ingredient.get_id());
        }
        return ids;
    }

}
