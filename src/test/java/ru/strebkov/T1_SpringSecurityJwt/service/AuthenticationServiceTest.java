package ru.strebkov.T1_SpringSecurityJwt.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.strebkov.T1_SpringSecurityJwt.service.person.ServicePerson;

@WebMvcTest(controllers = AuthenticationService.class, properties = {"server.ssl.enabled=false"})
@DisplayName("Тест компонента Service")
class AuthenticationServiceTest {

    @MockBean
    ServicePerson servicePerson;
    @MockBean
    JwtServiceAccessToken jwtServiceAccessToken;

    @MockBean
    UserService userService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void signIn() {

    }
}