package com.xyz.reactivekotlinspringbootmongo

import com.xyz.reactivekotlinspringbootmongo.handlers.PersonHandler
import com.xyz.reactivekotlinspringbootmongo.repositories.PersonReactiveRepository
import com.xyz.reactivekotlinspringbootmongo.routers.PersonRouter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.config.EnableWebFlux

@Configuration
@EnableWebFlux
class ApplicationConfig {

    @Autowired
    lateinit var personReactiveRepository: PersonReactiveRepository

    @Bean
    internal fun personHandler() = PersonHandler(personReactiveRepository)

    @Bean
    internal fun personRouter() = PersonRouter(personHandler()).doRoute()
}