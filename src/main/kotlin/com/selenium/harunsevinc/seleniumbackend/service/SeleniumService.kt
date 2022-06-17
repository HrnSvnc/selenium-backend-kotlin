package com.selenium.harunsevinc.seleniumbackend.service

import com.selenium.harunsevinc.seleniumbackend.data.enums.FindByCriteria
import com.selenium.harunsevinc.seleniumbackend.data.enums.ScrapeStatus
import com.selenium.harunsevinc.seleniumbackend.data.scrape.Scrape
import com.selenium.harunsevinc.seleniumbackend.repository.ScrapeRepository
import mu.KotlinLogging
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.edge.EdgeDriver
import org.openqa.selenium.edge.EdgeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.remote.RemoteWebDriver
import org.springframework.stereotype.Service

@Service
class SeleniumService(val repository: ScrapeRepository) {

    private val logger = KotlinLogging.logger {}

    fun scrapeWithFireFox(scrape: Scrape, headless: Boolean) {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/webdriver/geckodriver.exe")
        val driver = FirefoxDriver(FirefoxOptions().setHeadless(headless ?: false))
        logger.info { "Scraping with Firefox started" }
        executeScrape(driver,scrape)
        logger.info { "Scraping with Firefox finished" }
        driver.quit()
    }

    fun scrapeWithChrome(scrape: Scrape, headless: Boolean) {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/webdriver/chromedriver.exe")
        val driver = ChromeDriver(ChromeOptions().setHeadless(headless ?: false))
        logger.info { "Scraping with Chrome started" }
        executeScrape(driver,scrape)
        logger.info { "Scraping with Chrome finished" }
        driver.quit()
    }

    fun scrapeWithEdge(scrape: Scrape, headless: Boolean) {
        System.setProperty("webdriver.edge.driver", "src/main/resources/webdriver/msedgedriver.exe")
        val driver = EdgeDriver(EdgeOptions().setHeadless(headless ?: false))
        driver.get(scrape.url)
        logger.info { "Scraping with Edge started" }
        executeScrape(driver,scrape)
        logger.info { "Scraping with Edge finished" }
        driver.quit()
    }

    private fun executeScrape(driver:RemoteWebDriver,scrape:Scrape){
        try {
            driver.get(scrape.url)
        } catch (e: Exception) {
            logger.warn { "Something while getting the given url went wrong" }
            scrape.status =ScrapeStatus.Failed
            repository.save(scrape)
        }
        if (scrape.findByCriteria != null) {
            val webElement: WebElement = selectFindByElementCriteria(scrape, driver)
            scrape.data = webElement.text
            scrape.status =ScrapeStatus.Finished
            repository.save(scrape)
        } else {
            scrape.data = driver.pageSource
            scrape.status =ScrapeStatus.Finished
            repository.save(scrape)
        }
    }

    private fun selectFindByElementCriteria(scrape: Scrape, driver: RemoteWebDriver): WebElement {
         when (scrape.findByCriteria) {
            FindByCriteria.className -> {
                logger.info { "Searching with className ${scrape.selectorPath}" }
                return driver.findElement(By.className(scrape.selectorPath))
            }
            FindByCriteria.cssSelector ->{
                logger.info { "Searching with cssSelector ${scrape.selectorPath}" }
                return driver.findElement(By.cssSelector(scrape.selectorPath))
            }
            FindByCriteria.xpath -> {
                logger.info { "Searching with XPATH: ${scrape.selectorPath}"}
               return driver.findElement(By.xpath(scrape.selectorPath))
            }
            FindByCriteria.id -> {
                logger.info { "Searching with Id: ${scrape.selectorPath}"}
                driver.findElement(By.id(scrape.selectorPath))
            }
            FindByCriteria.tagName -> {
                logger.info { "Searching with TagName: ${scrape.selectorPath}"}
                driver.findElement(By.tagName(scrape.selectorPath))
            }
             else -> {
                 //this never should happen
                 driver.findElement(By.name(scrape.selectorPath))
             }
         }
        //this also never should happen
        return driver.findElement(By.name(scrape.selectorPath))
    }


}