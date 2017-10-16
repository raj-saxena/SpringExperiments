package com.example.spring2kotlintrial.services

import com.example.spring2kotlintrial.models.Person
import org.springframework.stereotype.Service

@Service
class PersonService {
    fun getPersons(): List<Person> {
        return emptyList()
    }

    fun addPerson(person: Person): Person {
        return person
    }
}