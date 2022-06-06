package com.selenium.harunsevinc.seleniumbackend.controller

import com.selenium.harunsevinc.seleniumbackend.data.scrape.CreateScrapeDTO
import com.selenium.harunsevinc.seleniumbackend.data.scrape.Scrape
import com.selenium.harunsevinc.seleniumbackend.service.ScrapeService
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/scrape")
class ScrapeController (val scrapeService:ScrapeService){

    private val logger = KotlinLogging.logger {}

    @GetMapping("/{id}")
    fun getScrapeById(@PathVariable id:String): Any{
        logger.info { "Get for id: $id" }
        val scrape =  scrapeService.findScrapeById(id)
        return if(scrape!=null){
            logger.info { "Scrape found" }
            scrape
        }else{
            logger.info { "Scrape is empty so not found" }
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("/")
    fun createNewScrape(@RequestBody @Validated scrape: CreateScrapeDTO): Scrape? {
        logger.info { "Incoming Scrape $scrape " }
        return scrapeService.createScrape(scrape)
    }

}