package com.test.myapp.controllers;

import com.test.myapp.exception.CantCreateUserExeption;
import com.test.myapp.exception.CantCreateUserExeption;
import com.test.myapp.models.Person;
import com.test.myapp.services.PeopleService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/peoplerest")
public class PeopleRESTController {

    private final PeopleService peopleService;
    Logger Logger = LoggerFactory.getLogger(PeopleRESTController.class);

    @Autowired
    public PeopleRESTController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping()
    public List<Person> index() {
        log.debug("[GET PERSON CONTROLLER]: " + " all user list requested");
        return peopleService.findAll();
    }

    @GetMapping("/{id}")
    public Person show(@PathVariable("id") int id) {
        return peopleService.findOne(id);
    }

    @PostMapping()
    public ResponseEntity<String> create(@RequestBody @Valid Person person,
                                 BindingResult bindingResult) throws CantCreateUserExeption {
        if (bindingResult.hasErrors()) {
           Logger.info("[POST PERSON CONTROLLER]: error, " + bindingResult);
           return ResponseEntity.badRequest().body("Stop write shit");
        }
        peopleService.save(person);
        return ResponseEntity.created(URI.
                create("")).body("Userid " + person.getId() + " created");
    }
    /*
    {
    "username":"userREST",
    "yearOfBirth":1900,
    "password":"123",
    "role":"ROLE_USER"
    }
     */


}
