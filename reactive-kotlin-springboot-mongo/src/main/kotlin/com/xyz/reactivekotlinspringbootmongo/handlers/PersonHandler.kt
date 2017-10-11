package com.xyz.reactivekotlinspringbootmongo.handlers

import com.xyz.reactivekotlinspringbootmongo.repositories.PersonReactiveRepository
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Mono

internal class PersonHandler(private val personReactiveRepository: PersonReactiveRepository) {

    fun getPersons(request: ServerRequest) = ok().body(personReactiveRepository.findAll())

    fun getPersonByName(request: ServerRequest) = request.queryParam("firstName").map { ok().body(personReactiveRepository.findByFirstName(it)) } .orElse(ok().body(Mono.empty()))
}