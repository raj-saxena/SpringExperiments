package com.example.spring2kotlintrial.repositories

import com.example.spring2kotlintrial.models.Person
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.hasItem
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@DataMongoTest
class PersonRepositoryIntTest {
    @Autowired
    lateinit var personRepository: PersonRepository

    private val person = Person("firstPerson", "firstPerson")
    private val anotherPerson = Person("anotherPerson", "anotherPerson")

    @Before
    fun setUp() {
        personRepository.saveAll(arrayListOf(person, anotherPerson))
    }

    @Test
    fun `should get persons from Mongo`() {
        val persons = personRepository.findAll()

        assertThat(persons.size, `is`(2))
        assertThat(persons, hasItem(person))
        assertThat(persons, hasItem(anotherPerson))
    }
}