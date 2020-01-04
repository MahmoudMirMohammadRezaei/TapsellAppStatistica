package com.example.tapsell.services

import com.example.tapsell.helpers.getWeekNumberPersian
import com.example.tapsell.helpers.getYearNumberPersian
import com.example.tapsell.helpers.getYearNumberPersian
import com.example.tapsell.models.AppStatisticsListResponse
import com.example.tapsell.models.AppStatisticsModel
import com.example.tapsell.repositories.AppStatisticsRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import java.util.*


@Service
class AppStatisticsService(val appStatisticsRepository: AppStatisticsRepository,
                           val redisTemplate: RedisTemplate<String, String>,
                           val objectMapper: ObjectMapper) {

    fun getStats(type: Int, startDate: Date, endDate: Date): AppStatisticsListResponse {

        var ListComponies = appStatisticsRepository.findByTypeAndReportTimeBetween(type, startDate, endDate)

        var copaniesReport = ListComponies
                .groupingBy { it.reportTime.getWeekNumberPersian() }
                .aggregate { week, accumulator: AppStatisticsModel?, element, first ->
                    if (first) // first element
                        AppStatisticsModel(week, element.reportTime.getYearNumberPersian()).AddInformation(element)
                    else
                        accumulator!!.AddInformation(element)
                }
                .values
                .mapNotNull { it }
                .sortedWith(compareBy({ it.year }, { it.weekNum }))

        redisTemplate.opsForValue().set("stats-$startDate-$endDate-$type", objectMapper.writeValueAsString(copaniesReport))
        return AppStatisticsListResponse(copaniesReport)

    }
}