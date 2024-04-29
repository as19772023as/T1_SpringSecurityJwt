package ru.strebkov.T1_SpringSecurityJwt.controller.person;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.strebkov.T1_SpringSecurityJwt.domain.model.person.Person;
import ru.strebkov.T1_SpringSecurityJwt.service.person.ServicePerson;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/persons")
public class ControllerPerson {

    private final ServicePerson service;

    @GetMapping("/hi")
    public ResponseEntity<String> hello(){
        return new ResponseEntity<>("Hello, Welcome!!!  У нас много интересного, пройдите рагистрацию!!!",
                HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> listTasks = new ArrayList<Person>(service.getAllPerson());
        if (listTasks.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listTasks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Person>> getPersonById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(service.getPersonById(id), HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deletePersonById(@PathVariable("id") Long id) {
        service.deleteMyPersonById(id);
        return new ResponseEntity<String>("Задача удалена успешно!.", HttpStatus.OK);
    }

}
