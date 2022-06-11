package com.selenium.harunsevinc.seleniumbackend.service

import com.selenium.harunsevinc.seleniumbackend.data.scrape.Scrape
import mu.KotlinLogging
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.edge.EdgeDriver
import org.openqa.selenium.edge.EdgeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions
import org.springframework.stereotype.Service

@Service
class SeleniumService (){

    private val logger = KotlinLogging.logger {}

    fun scrapeWithFireFox(scrape:Scrape){
        System.setProperty("webdriver.gecko.driver","src/main/resources/webdriver/geckodriver.exe")
        val options = FirefoxOptions()
        options.setHeadless(true)
        val driver = FirefoxDriver(options)
        logger.info { "Scraping with Firefox started" }
        driver.get(scrape.url)
        val webElement: WebElement = driver.findElement(By.xpath(scrape.xpath?.first() ?:""))
        logger.info { "Scraping with Firefox finished" }
        logger.info { webElement.text }
        driver.quit()
    }

    fun scrapeWithChrome(scrape:Scrape){
        System.setProperty("webdriver.chrome.driver","src/main/resources/webdriver/chromedriver.exe")
        val options = ChromeOptions()
        options.setHeadless(true)
        val driver = ChromeDriver(options)
        logger.info { "Scraping with Chrome started" }
        driver.get(scrape.url)
        val webElement:WebElement = driver.findElement(By.xpath(scrape.xpath?.first() ?:""))
        logger.info { "Scraping with Chrome started" }
        logger.info { webElement.text }
        driver.quit()
    }

    fun scrapeWithEdge(scrape:Scrape){
        System.setProperty("webdriver.edge.driver","src/main/resources/webdriver/msedgedriver.exe")
        val options = EdgeOptions()
        options.setHeadless(true)
        val driver = EdgeDriver(options)
        logger.info { "Scraping with Edge started" }
        driver.get(scrape.url)
        val webElement:WebElement = driver.findElement(By.xpath(scrape.xpath?.first() ?:""))
        logger.info { "Scraping with Edge started" }
        logger.info { webElement.text }
        driver.quit()

    }


}