package com.example.spring2kotlintrial.controllers

import com.example.spring2kotlintrial.models.Person
import com.example.spring2kotlintrial.services.PersonService
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.hasItem
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.verify
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType.APPLICATION_JSON_UTF8
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@RunWith(SpringRunner::class)
@WebMvcTest(controllers = arrayOf(PersonController::class))
class PersonControllerTest {
    @Autowired
    lateinit var mapper: ObjectMapper
    @Autowired
    lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var personService: PersonService

    private val firstPerson = Person("firstPerson", "firstPerson")

    @Test
    internal fun `should get all the persons`() {
        val secondPerson = Person("secondPerson", "secondPerson")
        val persons = arrayListOf(firstPerson, secondPerson)
        given(personService.getPersons()).willReturn(persons)

        val mvcResult = mockMvc.perform(get("/persons")
                .accept(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk)
                .andReturn()


        Mockito.verify(personService).getPersons()
        val actualPersons = mapper.readValue<List<Person>>(mvcResult.response.contentAsString)
        assertThat(actualPersons.size, `is`(2))
        assertThat(actualPersons, hasItem(firstPerson))
        assertThat(actualPersons, hasItem(secondPerson))
    }

    @Test
    fun `should add a person`() {
        given(personService.addPerson(firstPerson)).willReturn(firstPerson)

        val mvcResult = mockMvc.perform(post("/person")
                .content(mapper.writeValueAsString(firstPerson))
                .contentType(APPLICATION_JSON_UTF8)
                .accept(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk)
                .andReturn()

        val addedPerson = mapper.readValue<Person>(mvcResult.response.contentAsString)
        verify(personService).addPerson(firstPerson)
        assertThat(addedPerson, `is`(firstPerson))
    }
}