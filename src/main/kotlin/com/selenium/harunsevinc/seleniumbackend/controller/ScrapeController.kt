package com.selenium.harunsevinc.seleniumbackend.controller

import com.selenium.harunsevinc.seleniumbackend.data.scrape.CreateScrapeDTO
import com.selenium.harunsevinc.seleniumbackend.data.scrape.Scrape
import com.selenium.harunsevinc.seleniumbackend.data.scrape.StartScrapeDTO
import com.selenium.harunsevinc.seleniumbackend.repository.ScrapeRepository
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
class ScrapeController (val scrapeService:ScrapeService, val repository: ScrapeRepository){

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

    @PostMapping("/{id}/start")
    fun startScrape(@PathVariable id:String,@RequestBody @Validated startScrape: StartScrapeDTO):Any{
        val scrape = repository.findById(id)
        if(scrape!=null){
            logger.info { "Starting Scrape process for id:$id" }
            scrapeService.startScrape(scrape,startScrape)
        }
        return HttpStatus.OK
    }

    @PostMapping("/")
    fun createNewScrape(@RequestBody @Validated scrape: CreateScrapeDTO): Scrape? {
        logger.info { "Incoming Scrape $scrape " }
        return scrapeService.createScrape(scrape)
    }

}