package org.dnltsk.bestvehicletypeskill.decision

import com.google.inject.Singleton
import net.aksingh.owmjapis.HourlyForecast
import org.dnltsk.bestvehicletypeskill.model.Decision
import org.dnltsk.bestvehicletypeskill.model.RainType
import org.dnltsk.bestvehicletypeskill.model.RainType.*
import org.dnltsk.bestvehicletypeskill.model.VehicleType.*

@Singleton
class VehicleTypeRuleset {

    fun findBestVehicleType(weather: HourlyForecast): Decision {
        val rainIndensities = extractRainIntensities(weather)
        if (rainIndensities.contains(HEAVY)) {
            return Decision(CAR, HEAVY)
        }
        if (rainIndensities.contains(MODERATE)) {
            return Decision(CAR, MODERATE)
        }
        if (rainIndensities.contains(LIGHT)) {
            return Decision(BUS, LIGHT)
        }
        return Decision(BICYCLE, NONE)
    }

    private fun extractRainIntensities(weather: HourlyForecast): List<RainType> {
        val rainIntensities = mutableListOf<RainType>()
        for (i in 0..3) {
            val forecastInstance = weather.getForecastInstance(i)
            if (forecastInstance.hasWeatherInstance()) {
                val weather = forecastInstance.getWeatherInstance(0)
                rainIntensities.add(
                        when (weather.weatherDescription) {
                            "light rain" -> LIGHT
                            "moderate rain" -> MODERATE
                            "heavy intensity rain" -> HEAVY
                            else -> NONE
                        }
                )
            }

        }
        return rainIntensities
    }

}