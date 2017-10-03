package com.example.spring2kotlintrial.api

import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AppControllerIntTest {    @Autowired
lateinit var testRestTemplate: TestRestTemplate

    @Test
    fun `should say hello world integration test`() {
        val result = testRestTemplate.getForEntity("/", String::class.java)

        MatcherAssert.assertThat(result, CoreMatchers.`is`(CoreMatchers.notNullValue()))
        MatcherAssert.assertThat(result.statusCode, CoreMatchers.`is`(HttpStatus.OK))
        MatcherAssert.assertThat(result.body, CoreMatchers.`is`("Hello World!"))
    }
}