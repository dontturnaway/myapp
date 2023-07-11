package com.test.myapp.services;

import com.test.myapp.DTO.PersonDTO;
import com.test.myapp.models.Person;
import com.test.myapp.repositories.PeopleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor //no need to use constructor
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findOne(int id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public Person save(PersonDTO personDTO) {
        Person person = Person.builder()
                .username(personDTO.getUsername())
                .yearOfBirth(personDTO.getYearOfBirth())
                .password(personDTO.getPassword())
                .role(personDTO.getRole())
                .dateCreated(new java.sql.Timestamp(System.currentTimeMillis()))
                .build();
        return peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, PersonDTO personDTO) {
        Person updatedPerson = Person.builder()
                .username(personDTO.getUsername())
                .yearOfBirth(personDTO.getYearOfBirth())
                .password(personDTO.getPassword())
                .role(personDTO.getRole())
                .id(id)
                .build();
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }
}
