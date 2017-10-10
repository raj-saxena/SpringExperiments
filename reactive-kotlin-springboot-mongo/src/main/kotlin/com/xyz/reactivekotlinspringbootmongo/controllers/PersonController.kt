package com.xyz.reactivekotlinspringbootmongo.controllers

import com.xyz.reactivekotlinspringbootmongo.Person
import com.xyz.reactivekotlinspringbootmongo.PersonReactiveRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController("/api/persons")
class PersonController {
    @Autowired
    lateinit var personReactiveRepository: PersonReactiveRepository

    @GetMapping
    fun getPersons(): Flux<Person> {
        return personReactiveRepository.findAll()
    }
}