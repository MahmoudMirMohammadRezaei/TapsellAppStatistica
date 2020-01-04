package com.example.tapsell.models

import java.io.Serializable
class AppStatisticsModel(var weekNum:Int, var year:Int):Serializable {

    var requests: Int = 0

    var clicks:Int = 0
    var Installs:Int = 0


    fun AddInformation(appStatistics: AppStatistics):AppStatisticsModel{
        this.requests += appStatistics.sumRequest()
        this.clicks += appStatistics.sumClicks()
        this.Installs += appStatistics.sumInstalls()

        return  this
    }



}