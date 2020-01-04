package com.example.tapsell.controllers

import com.example.tapsell.controllers.model.ReportRequest
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
class TapsellController(val appStatisticsRepository: AppStatisticsRepository,
                        val appstatisticsService: AppStatisticsService) {


    @GetMapping("/reportAll")
    fun ReportAll(): MutableList<AppStatistics> {
        return appStatisticsRepository.findAll()

    }

    @GetMapping("/reportWithTime")
    fun ReportWithTime(@RequestBody request: ReportRequest): AppStatisticsListResponse {


        return appstatisticsService.getStats(
                request.type,
                request.startDate.ConvertToGregorian(),
                request.endDate.ConvertToGregorian()
        )
    }


}