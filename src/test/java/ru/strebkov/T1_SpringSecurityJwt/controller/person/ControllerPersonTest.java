package ru.strebkov.T1_SpringSecurityJwt.controller.person;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import ru.strebkov.T1_SpringSecurityJwt.service.JwtServiceAccessToken;
import ru.strebkov.T1_SpringSecurityJwt.service.UserService;
import ru.strebkov.T1_SpringSecurityJwt.service.person.ServicePerson;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.strebkov.T1_SpringSecurityJwt.domain.model.Role.ROLE_USER;


@WebMvcTest(controllers = ControllerPerson.class, properties = {"server.ssl.enabled=false"})
@DisplayName("Тест компонента Controller")
class ControllerPersonTest {
    @MockBean
    ServicePerson servicePerson;
    @MockBean
    JwtServiceAccessToken jwtServiceAccessToken;

    @MockBean
    UserService userService;

    @Autowired
    MockMvc mockMvc;


    @BeforeAll
    public static void initSuite() {
        System.out.println("___Running Tests___");
    }

    @AfterAll
    public static void completeSuite() {
        System.out.println("___END Tests___");
    }

    @BeforeEach
    public void initTest() {
        System.out.println("___Starting new Test___");
    }

    @AfterEach
    public void finalizeTest() {
        System.out.println("___New test end___");
    }

    @Test
    @WithAnonymousUser
    @DisplayName("Тест, если рользователь не авторизирован ->  статус ответа = 401")
    void givenRequestIsAnonymous_whenGetGreet_thenUnauthorized() throws Exception {
        mockMvc.perform(get("/persons/all"))
                .andExpect(status().isUnauthorized());
    }


    @Test
    @DisplayName("Тест, если рользователи не найдены ->  статус ответа = 204 isNoContent")
    void givenUserIsNotGrantedWithRoleAuthorizedPersonnel_whenGetSecuredRoute_thenForbidden() throws Exception {
        mockMvc.perform(get("/persons/all").with(SecurityMockMvcRequestPostProcessors.jwt()
                        .authorities(new SimpleGrantedAuthority(ROLE_USER.name()))))
                .andExpect(status().isNoContent());
    }
}