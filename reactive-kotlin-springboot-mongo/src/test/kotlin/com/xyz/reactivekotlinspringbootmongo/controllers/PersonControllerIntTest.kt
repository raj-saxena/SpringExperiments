package com.xyz.reactivekotlinspringbootmongo.controllers

import com.xyz.reactivekotlinspringbootmongo.models.Person
import com.xyz.reactivekotlinspringbootmongo.repositories.PersonReactiveRepository
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToFlux
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Flux
import reactor.test.test
import java.time.LocalDate
import java.util.*

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class PersonControllerIntTest {
    @LocalServerPort
    var port: Int? = null

    lateinit var webClient: WebClient

    @Autowired
    lateinit var personReactiveRepository: PersonReactiveRepository

    private val personFoo = Person("Foo", "Foo", LocalDate.now().minusDays(1))
    private val personBar = Person("Bar", "Bar", LocalDate.now().minusDays(10))
    private val personBaz = Person("Baz", "Baz", LocalDate.now().minusDays(100))

    @BeforeAll
    fun setup() {
        webClient = WebClient.create("http://localhost:$port")

        personReactiveRepository.saveAll(Flux.just(personFoo, personBar, personBaz))
                .then()
                .block()
    }

    @Test
    internal fun `get all persons`() {
        val persons = webClient.get().uri("/api/persons")
                .accept(APPLICATION_JSON)
                .retrieve()
                .bodyToFlux<Person>()
                .sort(Comparator.comparing(Person::firstName))

        persons.test()
                .expectNext(personBar)
                .expectNext(personBaz)
                .expectNext(personFoo)
                .verifyComplete()
    }

    @Test
    internal fun `should find person by firstName`() {
        val person = webClient.get().uri("/api/person?firstName=Foo")
                .accept(APPLICATION_JSON)
                .retrieve()
                .bodyToMono<Person>()

        person.test()
                .expectNext(personFoo)
                .verifyComplete()
    }

    @AfterAll
    internal fun tearDown() {
        personReactiveRepository.deleteAll()
    }
}