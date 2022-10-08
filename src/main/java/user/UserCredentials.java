package user;

import lombok.Data;

@Data
public class UserCredentials {
    private String email;
    private String password;

    public UserCredentials(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public static UserCredentials getCredentialsFromUser(User user) {
        return new UserCredentials(user.getEmail(), user.getPassword());
    }
    public static UserCredentials setUserCredentials(String email, String password) {
        return new UserCredentials(email, password);
    }
}
