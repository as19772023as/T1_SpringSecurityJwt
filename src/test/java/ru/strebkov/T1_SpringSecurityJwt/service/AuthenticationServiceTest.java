package ru.strebkov.T1_SpringSecurityJwt.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.strebkov.T1_SpringSecurityJwt.controller.person.ControllerPerson;
import ru.strebkov.T1_SpringSecurityJwt.service.person.ServicePerson;

import static org.junit.jupiter.api.Assertions.*;
@WebMvcTest(controllers = AuthenticationService.class, properties = {"server.ssl.enabled=false"})
@DisplayName("Тест компонента Service")
class AuthenticationServiceTest {

    @MockBean
    ServicePerson servicePerson;
    @MockBean
    JwtService jwtService;

    @MockBean
    UserService userService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void signIn() {

    }
}