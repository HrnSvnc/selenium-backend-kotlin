package com.selenium.harunsevinc.seleniumbackend.controller

import com.selenium.harunsevinc.seleniumbackend.data.scrape.CreateScrapeDTO
import com.selenium.harunsevinc.seleniumbackend.data.scrape.Scrape
import com.selenium.harunsevinc.seleniumbackend.service.ScrapeService
import mu.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/scrape")
class ScrapeController (val scrapeService:ScrapeService){

    private val logger = KotlinLogging.logger {}

    @GetMapping("/{id}")
    fun getScrapeById(@PathVariable id:String):Scrape?{
        logger.info { "Get for id: $id" }
        return scrapeService.findScrapeById(id)
    }

    @PostMapping("/")
    fun createNewScrape(@RequestBody scrape: CreateScrapeDTO): Scrape? {
        logger.info { "Incoming Scrape $scrape " }
        return scrapeService.createScrape(scrape)
    }

}