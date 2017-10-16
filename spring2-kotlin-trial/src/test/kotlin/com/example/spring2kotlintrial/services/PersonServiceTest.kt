package com.example.spring2kotlintrial.services

import com.example.spring2kotlintrial.models.Person
import com.example.spring2kotlintrial.repositories.PersonRepository
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations.initMocks

class PersonServiceTest {
    @Mock
    lateinit var personRepository: PersonRepository

    @InjectMocks
    lateinit var personService: PersonService

    @Before
    fun setUp() {
        initMocks(this)
    }

    private val person = Person("firstPerson", "firstPerson")
    private val anotherPerson = Person("anotherPerson", "anotherPerson")

    @Test
    fun `should get all persons from repository`() {
        `when`(personRepository.findAll()).thenReturn(arrayListOf(person, anotherPerson))

        personService.getPersons()

        verify(personRepository).findAll()
    }

    @Test
    fun `should save person in repository`() {
        `when`(personRepository.save(person)).thenReturn(person)

        personService.addPerson(person)

        verify(personRepository).save(person)
    }
}