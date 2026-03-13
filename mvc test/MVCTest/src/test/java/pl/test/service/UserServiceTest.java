package pl.test.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    UserService service = new UserService();

    @Test
    void passwordTooShort() {
        boolean result = service.validatePassword("123");
        assertFalse(result);
    }

    @Test
    void passwordCorrect() {
        boolean result = service.validatePassword("12345678");
        assertTrue(result);
    }

    @Test
    void validateUserWithShortPassword() {
        boolean result = service.validateUser(new pl.test.model.User("admin", "123"));
        assertFalse(result);
    }

    @Test
    void validateUserWithCorrectPassword() {
        boolean result = service.validateUser(new pl.test.model.User("admin", "12345678"));
        assertTrue(result);
    }
}