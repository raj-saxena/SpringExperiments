package com.example.spring2kotlintrial.utils

import com.example.spring2kotlintrial.models.Person
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.hasItem
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.util.Arrays.asList

@RunWith(SpringRunner::class)
@SpringBootTest
class JsonUtilsTest {
    @Autowired
    lateinit var mapper: ObjectMapper

    private val firstPerson = Person("firstPerson", "firstPerson")
    private val secondPerson = Person("secondPerson", "secondPerson")

    @Test
    fun `should convert collection to json`() {
        val persons = asList(firstPerson, secondPerson)
        val expectedJson = """
            [{"firstName":"firstPerson","lastName":"firstPerson"},{"firstName":"secondPerson","lastName":"secondPerson"}]
        """.trimIndent()

        val json = mapper.writeValueAsString(persons)

        assertThat(json, `is`(expectedJson))
    }

    @Test
    fun `should convert json to collection`() {
        val json = """
            [
            {"firstName":"firstPerson","lastName":"firstPerson"},
            {"firstName":"secondPerson","lastName":"secondPerson"}
            ]
        """.trimIndent()

        val actualPersons = mapper.readValue<List<Person>>(json)

        assertThat(actualPersons.size, `is`(2))
        assertThat(actualPersons, hasItem(firstPerson))
        assertThat(actualPersons, hasItem(secondPerson))
    }
}
