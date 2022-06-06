package com.selenium.harunsevinc.seleniumbackend.service

import com.selenium.harunsevinc.seleniumbackend.data.scrape.CreateScrapeDTO
import com.selenium.harunsevinc.seleniumbackend.data.scrape.Scrape
import com.selenium.harunsevinc.seleniumbackend.repository.ScrapeRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ScrapeService(val repository:ScrapeRepository) {

    fun createScrape(scrape:CreateScrapeDTO): Scrape? {
        return  repository.save(Scrape(
            url = scrape.url,
            xpath = scrape.xpath,
            created = LocalDateTime.now()))
    }


    fun findScrapeById(id:String):Scrape?{
        return repository.findById(id)
    }
}