package org.dnltsk.bestvehicletypeskill

import com.google.inject.Guice
import org.slf4j.LoggerFactory

class SkillSpeechletStandalone() {

    companion object {
        val LOG = LoggerFactory.getLogger(this::class.java)
        @JvmStatic
        fun main(args: Array<String>) {
            if (args.size == 0) {
                throw IllegalArgumentException("Please add a city name!")
            }
            SkillSpeechletStandalone().run(args[0])
        }
    }

    fun run(cityName: String) {
        val skillSpeechlet = Guice.createInjector(SkillSpeechletModule()).getInstance(SkillSpeechlet::class.java)
        skillSpeechlet.handleIntent(cityName)
    }


}