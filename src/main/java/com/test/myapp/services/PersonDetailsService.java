package com.test.myapp.services;

import com.test.myapp.models.Person;
import com.test.myapp.repositories.PeopleRepository;
import com.test.myapp.security.PersonDetails;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonDetailsService implements UserDetailsService {

    private final PeopleRepository peopleRepository;
//
//    @Autowired
//    public PersonDetailsService(PeopleRepository peopleRepository) {
//        this.peopleRepository = peopleRepository;
//    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Person> person = peopleRepository.findByUsername(s);

        if (person.isEmpty())
            throw new UsernameNotFoundException("User not found!");
            System.out.println("PersonDefailsService: user not found");

        System.out.println("PersonDefailsService: user found");
        return new PersonDetails(person.get());
    }
}
