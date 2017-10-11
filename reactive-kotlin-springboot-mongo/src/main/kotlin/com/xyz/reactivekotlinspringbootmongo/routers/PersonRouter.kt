package com.xyz.reactivekotlinspringbootmongo.routers

import com.xyz.reactivekotlinspringbootmongo.handlers.PersonHandler
import org.springframework.http.MediaType.APPLICATION_JSON_UTF8
import org.springframework.web.reactive.function.server.router

internal class PersonRouter(private val personHandler: PersonHandler) {
    private companion object {
        const private val API_PATH = "/api"
        const val PERSON_PATH = "/person"
        const val PERSONS_PATH = "/persons"
    }

    fun doRoute() = router {
        (accept(APPLICATION_JSON_UTF8) and API_PATH).nest {
            GET(PERSONS_PATH)(personHandler::getPersons)
            GET(PERSON_PATH)(personHandler::getPersonByName)
        }
    }
}