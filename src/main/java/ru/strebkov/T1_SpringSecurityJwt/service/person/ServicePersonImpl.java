package ru.strebkov.T1_SpringSecurityJwt.service.person;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.strebkov.T1_SpringSecurityJwt.domain.model.person.Person;
import ru.strebkov.T1_SpringSecurityJwt.exception.NoSuchPersonEndpointException;
import ru.strebkov.T1_SpringSecurityJwt.repository.repository.PersonRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServicePersonImpl implements ServicePerson{
    private final PersonRepository repository;

    @Override
    @Transactional
    public List<Person> getAllPerson() {
        return new ArrayList<>(repository.findAll());
    }

    @Override
    @Transactional
    public Optional<Person> getPersonById(Long id) {
        return Optional.ofNullable(repository.findById(id)
                .orElseThrow(() -> new NoSuchPersonEndpointException("Такого пользователя в базе нет")));
    }

    @Override
    @Transactional
    public void deleteMyPersonById(Long id) {
        repository.deleteById(id);
    }
}