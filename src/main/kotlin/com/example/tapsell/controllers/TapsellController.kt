package com.example.tapsell.controllers

import com.example.tapsell.helpers.ConvertToGregorian
import com.example.tapsell.helpers.ConvertToGregorian
import com.example.tapsell.models.AppStatistics
import com.example.tapsell.models.AppStatisticsListResponse
import com.example.tapsell.repositories.AppStatisticsRepository
import com.example.tapsell.services.AppStatisticsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/Tapsell")
class TapsellController(val appStatisticsRepository: AppStatisticsRepository, val appstatisticsService: AppStatisticsService) {

    @GetMapping("/HelloWorld")
    fun HelloWorld(): String {
        return "HelloWorld"
    }


    @GetMapping("/reportAll")
    fun ReportAll(): MutableList<AppStatistics> {
        return appStatisticsRepository.findAll()

    }

    @GetMapping("/reportWithTime")
    fun ReportWithTime(@RequestBody inputs: dataclass): AppStatisticsListResponse {


         return appstatisticsService.getStats(
                inputs.type,
                inputs.startDate.ConvertToGregorian(),
                inputs.endDate.ConvertToGregorian()
        )
    }

    data class dataclass(var type: Int, var startDate: String, var endDate: String)

}