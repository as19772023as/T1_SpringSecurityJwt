package ru.strebkov.T1_SpringSecurityJwt.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.strebkov.T1_SpringSecurityJwt.domain.model.Role;
import ru.strebkov.T1_SpringSecurityJwt.domain.model.User;
import ru.strebkov.T1_SpringSecurityJwt.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@DisplayName("Тест компонента Service")
class UserServiceTest {
    @Autowired
    UserRepository repository;
    private UserService userService;
    private User user;
    private static final String username = "AAnnddrreeyy";
    private static final String email = "test@test.ru";
    private static final String password = "123456";
    private static final Role role = Role.ROLE_USER;


    @BeforeAll
    public static void initSuite() {
        System.out.println("Running Test");
    }

    @AfterAll
    public static void completeSuite() {
        System.out.println("Test END");
    }

    @BeforeEach
    public void initTest() {
        System.out.println("Starting new Test");
        userService = new UserService(repository);
        user = User.builder()
                .username(username)
                .email(email)
                .password(password)
                .role(role)
                .build();

        repository.save(user);
    }

    @AfterEach
    public void finalizeTest() {
        System.out.println("New test complete:");

        repository.deleteById(user.getId());
    }


    @Test
    @DisplayName("Тест на проверку исключения при попытке сохранить пользователя " +
            "с существующим именем или email в базу")
    void createTestTest() {
        assertThrows(RuntimeException.class, () -> {
            userService.create(user);
        });
    }

    @Test
    @DisplayName("Тест на проверку получения пользователя по имени")
    void getByUsernameTest() {
        String testUserName = username;
        User testUser = repository.findByUsername(testUserName).get();

        assertEquals(testUser.getUsername(), testUserName);
    }

    @Test
    @DisplayName("Тест на проверку исключения при отсутствии пользователя в базе")
    void getByUsernameTestException() {
        String testUserName = "NotUserName";

        assertThrows(UsernameNotFoundException.class, () -> {
            userService.getByUsername(testUserName);
        });
    }

    @Test
    void userDetailsServiceTest() {
        String testUserName = username;
        User testUser = repository.findByUsername(testUserName).get();

        assertEquals(testUser.getUsername(), testUserName);
    }
}