package user;

import lombok.Data;

@Data
public class UserUpdate {
    private String email;
    private String name;

    public UserUpdate(String email, String name) {
        this.email = email;
        this.name = name;
    }
}
