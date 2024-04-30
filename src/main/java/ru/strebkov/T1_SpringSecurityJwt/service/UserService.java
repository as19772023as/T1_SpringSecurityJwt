package ru.strebkov.T1_SpringSecurityJwt.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.strebkov.T1_SpringSecurityJwt.domain.model.Role;
import ru.strebkov.T1_SpringSecurityJwt.domain.model.User;
import ru.strebkov.T1_SpringSecurityJwt.repository.UserRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    // Сохранение пользователя
    public User save(User user) {
        return repository.save(user);
    }


   // Создание пользователя
    public User create(User user) {
        if (repository.existsByUsername(user.getUsername())) {
            // Заменить на свои исключения
            throw new RuntimeException("Пользователь с таким именем уже существует");
        }
        if (repository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Пользователь с таким email уже существует");
        }

        return save(user);
    }

    public User getByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }

     //Получение пользователя по имени пользователя
     // Нужен для Spring Security
    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }


    /**
     * Выдача прав администратора текущему пользователю
     * <p>
     * Нужен для демонстрации
     */
    @Deprecated
    public void getAdmin() {
        var user = getCurrentUser();
        user.setRole(Role.ROLE_ADMIN);
        save(user);
    }

   // Получение текущего пользователя
    public User getCurrentUser() {
        // Получение имени пользователя из контекста Spring Security
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }

}
