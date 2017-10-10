package com.xyz.reactivekotlinspringbootmongo

import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface PersonReactiveRepository : ReactiveMongoRepository<Person, String>