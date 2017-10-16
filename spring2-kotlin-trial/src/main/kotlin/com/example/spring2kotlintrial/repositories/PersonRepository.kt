package com.example.spring2kotlintrial.repositories

import com.example.spring2kotlintrial.models.Person
import org.springframework.data.mongodb.repository.MongoRepository

interface PersonRepository : MongoRepository<Person, String>