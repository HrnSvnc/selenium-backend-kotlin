package com.selenium.harunsevinc.seleniumbackend.controller

import com.selenium.harunsevinc.seleniumbackend.data.scrape.CreateScrapeDTO
import com.selenium.harunsevinc.seleniumbackend.repository.ScrapeRepository
import com.selenium.harunsevinc.seleniumbackend.service.ScrapeService
import mu.KotlinLogging
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/scrape")
class ScrapeController (val scrapeService:ScrapeService, val repository:ScrapeRepository){

    private val logger = KotlinLogging.logger {}

    @PostMapping("/")
    fun createNewScrape(@RequestBody scrape: CreateScrapeDTO): CreateScrapeDTO? {
        logger.info { "Incoming Scrape $scrape " }
        return scrapeService.createScrape(scrape)
    }

}