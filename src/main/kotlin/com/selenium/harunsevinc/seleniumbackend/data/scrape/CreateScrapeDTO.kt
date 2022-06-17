package com.selenium.harunsevinc.seleniumbackend.data.scrape

import com.selenium.harunsevinc.seleniumbackend.data.enums.FindByCriteria

data class CreateScrapeDTO(val url:String,val selectorPath:String?, val findByCriteria: FindByCriteria?) {
}
