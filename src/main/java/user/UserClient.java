package user;

import userdata.UserDataRoot;
import config.BaseClient;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

public class UserClient extends BaseClient {
    protected final String CREATE = "auth/register";
    protected final String LOGIN = "auth/login";
    protected final String USER_ENDPOINT = "auth/user";
    protected final String EMPTY_JSON_BODY = "{}";

    @Step("Create user")
    public ValidatableResponse createUser(User user) {
        return getSpec()
                .body(user)
                .post(CREATE)
                .then();
    }
    @Step("Delete user")
    public void deleteUser(String token) {
        getSpec(token).delete(USER_ENDPOINT);
    }
    @Step("Login user")
    public ValidatableResponse loginUser(UserCredentials creds) {
        return getSpec()
                .body(creds)
                .post(LOGIN)
                .then();
    }
    @Step("Update user")
    public ValidatableResponse updateUser(String token, UserUpdate info) {
        return getSpec(token)
                .body(info)
                .patch(USER_ENDPOINT)
                .then();
    }
    @Step("Update user's name")
    public ValidatableResponse updateUserName(String token, UserUpdateName info) {
        return getSpec(token)
                .body(info)
                .patch(USER_ENDPOINT)
                .then();
    }
    @Step("Update user's email")
    public ValidatableResponse updateUserEmail(String token, UserUpdateEmail info) {
        return getSpec(token)
                .body(info)
                .patch(USER_ENDPOINT)
                .then();
    }
    @Step("Update nothing")
    public ValidatableResponse updateUserNothing(String token) {
        return getSpec(token)
                .body(EMPTY_JSON_BODY)
                .patch(USER_ENDPOINT)
                .then();
    }
    public UserDataRoot deserializeResponse(ValidatableResponse response) {
        return response.extract().body().as(UserDataRoot.class);
    }
    @Step("Delete user if necessary")
    public void cleanUp(ValidatableResponse response) {
        UserDataRoot userData = deserializeResponse(response);
        if (userData.isSuccess()) {
            deleteUser(userData.getAccessToken());
        }
    }

}
