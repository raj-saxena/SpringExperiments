package com.example.spring2kotlintrial.services

import com.example.spring2kotlintrial.models.Person
import com.example.spring2kotlintrial.repositories.PersonRepository
import org.springframework.stereotype.Service

@Service
class PersonService(val personRepository: PersonRepository) {
    fun getPersons(): List<Person> {
        return personRepository.findAll()
    }

    fun addPerson(person: Person): Person {
        return personRepository.save(person)
    }
}