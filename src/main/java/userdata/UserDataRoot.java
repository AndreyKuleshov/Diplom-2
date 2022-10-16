package userdata;

import lombok.Data;

@Data
public class UserDataRoot {
    private boolean success;
    private Object user;
    private String accessToken;
    private String refreshToken;
    private String message;
}
