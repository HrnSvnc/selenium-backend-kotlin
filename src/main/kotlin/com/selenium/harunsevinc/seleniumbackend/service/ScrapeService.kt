package com.selenium.harunsevinc.seleniumbackend.service

import com.selenium.harunsevinc.seleniumbackend.data.enums.Browser
import com.selenium.harunsevinc.seleniumbackend.data.enums.ScrapeStatus
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
        if(scrape.findByCriteria == null && scrape.selectorPath == null){
            return  repository.save(Scrape(
                url = scrape.url,
                created = LocalDateTime.now(),
                status = ScrapeStatus.Created))
        }else{
            return  repository.save(Scrape(
                url = scrape.url,
                created = LocalDateTime.now(),
                findByCriteria = scrape.findByCriteria,
                selectorPath =  scrape.selectorPath,
                status = ScrapeStatus.Created))
        }
    }

    fun findScrapeById(id: String): Scrape? {
        logger.info { "Searching scrape" }
        return repository.findById(id)
    }

    fun startScrape(scrape:Scrape, browser: Browser, headless:Boolean){
        logger.info { "Starting scraping process..." }
        return when(browser){
            Browser.firefox->seleniumService.scrapeWithFireFox(scrape,headless)
            Browser.chrome->seleniumService.scrapeWithChrome(scrape,headless)
            Browser.edge->seleniumService.scrapeWithEdge(scrape,headless)
        }
    }
}