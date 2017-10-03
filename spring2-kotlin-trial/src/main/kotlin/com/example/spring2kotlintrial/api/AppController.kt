package com.example.spring2kotlintrial.api

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AppController {

    @RequestMapping("/")
    fun sayHello() = "Hello World!"
}