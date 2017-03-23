package org.dnltsk.bestvehicletypeskill

import org.dnltsk.bestvehicletypeskill.decision.OpenWeatherMapClient
import org.dnltsk.bestvehicletypeskill.decision.Speech
import org.dnltsk.bestvehicletypeskill.decision.VehicleTypeRuleset

class SpeechletStandalone() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            if (args.size == 0) {
                throw IllegalArgumentException("Please add a city name!")
            }
            SpeechletStandalone().run(args[0])
        }
    }

    fun run(cityName: String) {
        Speechlet(
                OpenWeatherMapClient(),
                VehicleTypeRuleset(),
                Speech()
        ).handleIntent(cityName)
    }


}