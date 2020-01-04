package com.example.tapsell.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "AppStatistics")
class AppStatistics(

         var reportTime: Date,
         var type: Int,
        private val videoRequest:Int,
        private val webViewRequest: Int,
        private val videoClicks: Int,
        private val webViewClicks: Int,
        private val videoInstalls: Int,
        private val webViewInstalls:Int
) {

    @Id
    var id: String? = null

    fun sumRequest():Int = videoRequest + webViewRequest
    fun sumClicks():Int = videoClicks + webViewClicks
    fun sumInstalls():Int = videoInstalls + webViewInstalls

}