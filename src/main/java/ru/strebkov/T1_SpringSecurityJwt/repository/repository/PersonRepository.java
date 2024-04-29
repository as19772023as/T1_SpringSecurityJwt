package ru.strebkov.T1_SpringSecurityJwt.repository.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.strebkov.T1_SpringSecurityJwt.domain.model.person.Person;


@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
