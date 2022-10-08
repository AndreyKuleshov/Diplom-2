package user;

import UserData.UserDataRoot;
import config.BaseClient;
import io.restassured.response.ValidatableResponse;

public class UserClient extends BaseClient {
    protected final String CREATE = "auth/register";
    protected final String LOGIN = "auth/login";
    protected final String USER_ENDPOINT = "auth/user";
    protected final String EMPTY_JSON_BODY = "{}";

    public ValidatableResponse createUser(User user) {
        return getSpec()
                .body(user)
                .post(CREATE)
                .then();
    }
    public ValidatableResponse deleteUser(String token) {
        return getSpec(token)
                .delete(USER_ENDPOINT)
                .then();
    }
    public ValidatableResponse loginUser(UserCredentials creds) {
        return getSpec()
                .body(creds)
                .post(LOGIN)
                .then();
    }
    public ValidatableResponse updateUser(String token, UserUpdate info) {
        return getSpec(token)
                .body(info)
                .patch(USER_ENDPOINT)
                .then();
    }
    public ValidatableResponse updateUserName(String token, UserUpdateName info) {
        return getSpec(token)
                .body(info)
                .patch(USER_ENDPOINT)
                .then();
    }
    public ValidatableResponse updateUserEmail(String token, UserUpdateEmail info) {
        return getSpec(token)
                .body(info)
                .patch(USER_ENDPOINT)
                .then();
    }
    public ValidatableResponse updateUserNothing(String token) {
        return getSpec(token)
                .body(EMPTY_JSON_BODY)
                .patch(USER_ENDPOINT)
                .then();
    }
    public UserDataRoot deserializeResponse(ValidatableResponse response) {
        return response.extract().body().as(UserDataRoot.class);
    }
}
