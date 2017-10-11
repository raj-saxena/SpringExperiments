package com.xyz.reactivekotlinspringbootmongo

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Mono

interface PersonReactiveRepository : ReactiveMongoRepository<Person, String> {
    fun findByFirstName(name: String): Mono<Person>
}