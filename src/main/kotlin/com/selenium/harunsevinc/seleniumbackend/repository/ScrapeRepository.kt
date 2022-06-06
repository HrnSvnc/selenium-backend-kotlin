package com.selenium.harunsevinc.seleniumbackend.repository

import com.selenium.harunsevinc.seleniumbackend.data.scrape.Scrape
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.http.HttpStatus

interface ScrapeRepository: MongoRepository<Scrape,Long> {
    fun findById(id:String):Scrape?
    fun findAllByStatus(httpStatus:HttpStatus): Iterable<Scrape>
}