package com.selenium.harunsevinc.seleniumbackend.service

import com.selenium.harunsevinc.seleniumbackend.data.scrape.CreateScrapeDTO
import com.selenium.harunsevinc.seleniumbackend.data.scrape.Scrape
import com.selenium.harunsevinc.seleniumbackend.repository.ScrapeRepository
import mu.KotlinLogging
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ScrapeService(val repository:ScrapeRepository,
                    val seleniumService: SeleniumService ) {

    private val logger = KotlinLogging.logger {}

    fun createScrape(scrape:CreateScrapeDTO): Scrape? {
        logger.info { "Saving scrape..." }
        return  repository.save(Scrape(
            url = scrape.url,
            xpath = scrape.xpath,
            created = LocalDateTime.now()))
    }

    fun findScrapeById(id: String): Scrape? {
        logger.info { "Searching scrape" }
        return repository.findById(id)
    }

    fun startScrape(scrape:Scrape){
        logger.info { "Starting scraping process..." }
        seleniumService.scrapeWithFireFox(scrape)
    }
}