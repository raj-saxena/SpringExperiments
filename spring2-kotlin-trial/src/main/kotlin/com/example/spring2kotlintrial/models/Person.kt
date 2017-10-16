package com.example.spring2kotlintrial.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Person(val firstName: String, val lastName: String, @Id var id:String? = null)