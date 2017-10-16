package com.example.spring2kotlintrial.controllers

import com.example.spring2kotlintrial.models.Person
import com.example.spring2kotlintrial.services.PersonService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PersonController(val personService: PersonService) {
    @GetMapping("/persons")
    fun getPersons(): List<Person> {
        return personService.getPersons()
    }

    @PostMapping("/person")
    fun addPerson(@RequestBody person: Person): Person {
        return personService.addPerson(person)
    }
}