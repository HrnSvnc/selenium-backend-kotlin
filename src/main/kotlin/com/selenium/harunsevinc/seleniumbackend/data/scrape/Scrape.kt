package com.selenium.harunsevinc.seleniumbackend.data.scrape

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.http.HttpStatus
import java.time.LocalDateTime

@Document
data class Scrape(
    @Id()
    var id:String?=null,
    var url: String,
    var xpath:Iterable<String>?=null,
    var data:Iterable<String>?=null,
    var status:HttpStatus?=null,
    var created:LocalDateTime)

