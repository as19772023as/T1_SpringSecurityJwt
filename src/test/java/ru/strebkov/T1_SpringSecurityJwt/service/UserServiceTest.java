package ru.strebkov.T1_SpringSecurityJwt.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.strebkov.T1_SpringSecurityJwt.domain.model.Role;
import ru.strebkov.T1_SpringSecurityJwt.domain.model.User;
import ru.strebkov.T1_SpringSecurityJwt.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.config.http.MatcherType.mvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest

//@RunWith(SpringRunner.class)
//@SpringBootTest
        //@AutoConfigureMockMvc
@DisplayName("Тест компонента Service")
class UserServiceTest {
   // @MockBean
    UserRepository userRepository;
   // @MockBean
    UserService userService;

   // @Autowired
    private MockMvc mvc;

//    @Test
//    void createTest() {
//        User user = User.builder()
//                .id(1L)
//                .username("Andrey")
//                .email("andrey@mail.ru")
//                .password("123")
//                .role(Role.ROLE_USER)
//                .build();
//
//        Mockito.when(userRepository.existsByUsername("Oly")).thenReturn(true);
//
//        Mockito.when(userService.create(user)).thenThrow(new RuntimeException("Пользователь с таким именем уже существует"));
//
//
//
//    }

//    @Test
//    public void shouldGenerateAuthToken() throws Exception {
//        String token = TokenAuthenticationService.createToken("john");
//
//        assertNotNull(token);
//        mvc.perform(MockMvcRequestBuilders.get("/test").header("Authorization", token)).andExpect(status().isOk());
//    }

}