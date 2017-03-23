package org.dnltsk.bestvehicletypeskill.decision

import net.aksingh.owmjapis.OpenWeatherMap
import org.junit.Test

class OpenWeatherMapClientTest {

    @Test
    fun owm_test() {
        val owm = OpenWeatherMap("059fe7fbf4d168cea8a9f939a2e8309b")
        OpenWeatherMapClient().loadHourlyForecast("Chiclayo")
        //OpenWeatherMapClient().loadHourlyForecast("London")
        //OpenWeatherMapClient().loadHourlyForecast("Berlin")
    }
}