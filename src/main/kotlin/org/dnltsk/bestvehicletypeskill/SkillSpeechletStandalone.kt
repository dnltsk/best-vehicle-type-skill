package org.dnltsk.bestvehicletypeskill

import com.amazon.speech.slu.Intent
import com.amazon.speech.slu.Slot
import com.google.inject.Guice
import org.slf4j.LoggerFactory

class SkillSpeechletStandalone() {

    companion object {
        val LOG = LoggerFactory.getLogger(this::class.java)
        @JvmStatic
        fun main(args: Array<String>) {
            if (args.isEmpty()) {
                throw IllegalArgumentException("Please add a city name!")
            }
            SkillSpeechletStandalone().run(args[0])
        }
    }

    fun run(cityName: String) {
        val skillSpeechlet = Guice.createInjector(SkillSpeechletModule()).getInstance(SkillSpeechlet::class.java)

        val intent = Intent.builder()
                .withName("FindBestVehicleType")
                .withSlots(
                        mutableMapOf(Pair(
                                "cityName",
                                Slot.builder().withName("cityName").withValue(cityName).build()))
                )
                .build()
        skillSpeechlet.handleFindBestVehicleTypeIntent(intent)
    }


}