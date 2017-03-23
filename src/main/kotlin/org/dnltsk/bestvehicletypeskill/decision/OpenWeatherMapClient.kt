package org.dnltsk.bestvehicletypeskill.decision

import com.google.inject.Singleton
import net.aksingh.owmjapis.HourlyForecast
import net.aksingh.owmjapis.OpenWeatherMap
import org.slf4j.LoggerFactory

@Singleton
class OpenWeatherMapClient() {

    private val LOG = LoggerFactory.getLogger(this::class.java)
    private var OWM_API_KEY = "MY-OWM-API-KEY"

    init {
        OWM_API_KEY = System.getenv("OWM_API_KEY")
    }

    fun loadHourlyForecast(cityName: String): HourlyForecast {
        LOG.info("OWM_API_KEY= $OWM_API_KEY")
        val owm = OpenWeatherMap(OWM_API_KEY)
        return owm.hourlyForecastByCityName(cityName)
    }

}