package com.test.myapp.services;

import com.test.myapp.models.Person;
import com.test.myapp.repositories.PeopleRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final PeopleRepository peopleRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void register(Person person) {

        person.setPassword(passwordEncoder.encode(person.getPassword())); //encoding pwd
        person.setRole("ROLE_USER"); //setting default role
        peopleRepository.save(person);
    }

}
