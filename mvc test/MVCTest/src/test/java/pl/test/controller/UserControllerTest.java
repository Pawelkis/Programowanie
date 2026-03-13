package pl.test.controller;

import org.junit.jupiter.api.Test;
import pl.test.model.User;
import pl.test.repository.UserRepository;
import pl.test.service.UserService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @Test
    void shouldCallRepositorySave() {
        UserRepository repo = mock(UserRepository.class);
        UserService service = new UserService();
        UserController controller = new UserController(repo, service);

        User user = new User("admin", "12345678");
        controller.saveUser(user);

        verify(repo).save(user);
    }

    @Test
    void shouldNotCallRepositoryForInvalidUser() {
        UserRepository repo = mock(UserRepository.class);
        UserService service = new UserService();
        UserController controller = new UserController(repo, service);

        User user = new User("admin", "123"); // za krótkie hasło
        boolean result = controller.saveUser(user);

        assertFalse(result);
        verify(repo, never()).save(any());
    }

    @Test
    void shouldHandleDatabaseError() {
        UserRepository repo = mock(UserRepository.class);
        UserService service = new UserService();
        UserController controller = new UserController(repo, service);

        User user = new User("admin", "12345678");
        doThrow(new RuntimeException()).when(repo).save(user);

        boolean result = controller.saveUser(user);

        assertFalse(result);
    }
}