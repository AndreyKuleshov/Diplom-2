package user;

import lombok.Data;

@Data
public class UserUpdateEmail {
    private String email;

    public UserUpdateEmail(String email) {
        this.email = email;
    }
}
