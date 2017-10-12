package com.xyz.reactivekotlinspringbootmongo.controllers

import com.xyz.reactivekotlinspringbootmongo.models.Person
import com.xyz.reactivekotlinspringbootmongo.repositories.PersonReactiveRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class PersonController {
    @Autowired
    lateinit var personReactiveRepository: PersonReactiveRepository

    @GetMapping("/api/persons")
    fun getPersons(): Flux<Person> {
        return personReactiveRepository.findAll()
    }

    @GetMapping("/api/person")
    fun getPerson(@RequestParam firstName: String): Mono<Person> {
        return personReactiveRepository.findByFirstName(firstName)
    }

    @PostMapping("/api/person")
    @ResponseStatus(HttpStatus.CREATED)
    fun addPerson(@RequestBody person: Person) {
        personReactiveRepository.save(person)
    }
}