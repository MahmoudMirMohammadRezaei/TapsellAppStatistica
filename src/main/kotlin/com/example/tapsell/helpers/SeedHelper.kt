package com.example.tapsell.helpers

import com.example.tapsell.models.AppStatistics
import com.example.tapsell.repositories.AppStatisticsRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.util.*


@Component
class SeedHelper(val appStatisticsRepository: AppStatisticsRepository):CommandLineRunner {
    override fun run(vararg args: String?) {

        this.appStatisticsRepository.deleteAll()

        val appStatisticsList = mutableListOf<AppStatistics>()

        for (i in 1..1000){
            val appStatisticsListItem = GeneratingSeed()
            appStatisticsList.add(appStatisticsListItem)


//            println("appStatisticsListItem ${appStatisticsListItem.reportTime}"
//            +"${appStatisticsListItem.type}"
//            )
        }


        this.appStatisticsRepository.saveAll(appStatisticsList)

        println("Items are saved")
    }


    fun GeneratingSeed(): AppStatistics {

        val year = (2016..2019).random()
        val month = (1..12).random()
        val day = (1..30).random()

        val hour = (1..24).random()
        val minute = (1..60).random()
        val second = (1..60).random()
//        val gregorianCalendar = GregorianCalendar(year, month, day, hour, minute, second)
        val gregorianCalendar = GregorianCalendar(year, month, day)
        val gregorianDate = gregorianCalendar.time

        val type = (1..5).random()

        val videoRequests = (0..10).random()
        val videoClicks = (0..20).random()
        val videoInstalls = (0..5).random()

        val webViewRequests = (0..30).random()
        val webViewClicks = (0..60).random()
        val webViewInstalls = (0..20).random()



        return AppStatistics(
                gregorianDate,
                type,
                videoRequests,
                videoClicks,
                videoInstalls,
                webViewRequests,
                webViewClicks,
                webViewInstalls
        )
    }


}