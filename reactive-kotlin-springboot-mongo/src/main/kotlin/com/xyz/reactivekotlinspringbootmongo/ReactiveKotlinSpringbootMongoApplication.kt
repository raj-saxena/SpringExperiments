package com.xyz.reactivekotlinspringbootmongo

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class ReactiveKotlinSpringbootMongoApplication

fun main(args: Array<String>) {
    SpringApplication.run(ReactiveKotlinSpringbootMongoApplication::class.java, *args)
}
