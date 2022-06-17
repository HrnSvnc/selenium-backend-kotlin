package com.selenium.harunsevinc.seleniumbackend.data.scrape

import com.selenium.harunsevinc.seleniumbackend.data.enums.FindByCriteria
import com.selenium.harunsevinc.seleniumbackend.data.enums.ScrapeStatus
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
data class Scrape(
    @Id()
    var id:String?=null,
    var url: String,
    var selectorPath:String?=null,
    var data:String?=null,
    var status:ScrapeStatus?=null,
    var created:LocalDateTime,
    var findByCriteria: FindByCriteria?=null)

