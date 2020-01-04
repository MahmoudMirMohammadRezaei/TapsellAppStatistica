package com.example.tapsell.repositories

import com.example.tapsell.models.AppStatistics
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AppStatisticsRepository:MongoRepository<AppStatistics, String> {
    fun findByTypeAndReportTimeBetween(type:Int, startDate: Date, endDate: Date):List<AppStatistics>
}