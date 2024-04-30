package ru.strebkov.T1_SpringSecurityJwt.service.person;

import ru.strebkov.T1_SpringSecurityJwt.domain.model.person.Person;

import java.util.List;
import java.util.Optional;


public interface ServicePerson {
    List<Person> getAllPerson();

    Optional<Person> getPersonById(Long id);

    void deleteMyPersonById(Long id);

}