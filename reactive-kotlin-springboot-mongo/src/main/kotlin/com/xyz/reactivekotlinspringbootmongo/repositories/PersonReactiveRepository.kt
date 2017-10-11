package com.xyz.reactivekotlinspringbootmongo.repositories

import com.xyz.reactivekotlinspringbootmongo.models.Person
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Mono

interface PersonReactiveRepository : ReactiveMongoRepository<Person, String> {
    fun findByFirstName(name: String): Mono<Person>
}