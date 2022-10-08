package user;

import lombok.Data;

@Data
public class UserUpdateName {
    private String name;

    public UserUpdateName(String name) {
        this.name = name;
    }
}
