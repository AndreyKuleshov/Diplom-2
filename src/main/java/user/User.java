package user;

import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;
@Data
public class User {
    private String email;
    private String password;
    private String name;

    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }
    public static User createRandomUser() {
        return new User(
                (RandomStringUtils.randomAlphanumeric(10) + "@yandex.ru").toLowerCase(),
                RandomStringUtils.randomAlphanumeric(10),
                RandomStringUtils.randomAlphabetic(10)
        );
    }
    public static User createRandomUserWithEmptyEmail() {
        return new User(
                "",
                RandomStringUtils.randomAlphanumeric(10),
                RandomStringUtils.randomAlphabetic(10)
        );
    }
    public static User createRandomUserWithEmptyPassword() {
        return new User(
                RandomStringUtils.randomAlphanumeric(10) + "@yandex.ru",
                "",
                RandomStringUtils.randomAlphabetic(10)
        );
    }
    public static User createRandomUserWithEmptyName() {
        return new User(
                RandomStringUtils.randomAlphanumeric(10) + "@yandex.ru",
                RandomStringUtils.randomAlphanumeric(10),
                ""
        );
    }
    public static User createRandomUserWithoutEmail() {
        return new User(
                null,
                RandomStringUtils.randomAlphanumeric(10),
                RandomStringUtils.randomAlphabetic(10)
        );
    }
    public static User createRandomUserWithoutPassword() {
        return new User(
                RandomStringUtils.randomAlphanumeric(10) + "@yandex.ru",
                null,
                RandomStringUtils.randomAlphabetic(10)
        );
    }
    public static User createRandomUserWithoutName() {
        return new User(
                RandomStringUtils.randomAlphanumeric(10) + "@yandex.ru",
                RandomStringUtils.randomAlphanumeric(10),
                null
        );
    }
}
