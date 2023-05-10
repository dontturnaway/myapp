package com.test.myapp.services;

import com.test.myapp.models.Person;
import com.test.myapp.repositories.PeopleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    private final PeopleRepository peopleRepository;
    private  final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(PeopleRepository peopleRepository, PasswordEncoder passwordEncoder) {
        this.peopleRepository = peopleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword())); //encoding pwd
        person.setRole("ROLE_USER"); //setting default role
        peopleRepository.save(person);
    }

}
