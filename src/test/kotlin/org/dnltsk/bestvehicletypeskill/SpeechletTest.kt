package org.dnltsk.bestvehicletypeskill

import org.dnltsk.bestvehicletypeskill.decision.OpenWeatherMapClient
import org.dnltsk.bestvehicletypeskill.decision.Speech
import org.dnltsk.bestvehicletypeskill.decision.VehicleTypeRuleset
import org.junit.Assert.*
import org.junit.Test
import org.slf4j.LoggerFactory

class SpeechletTest{

    val LOG = LoggerFactory.getLogger(this::class.java)

    @Test
    fun speechlet_test() {
        val speechletResponse = Speechlet(
                OpenWeatherMapClient(),
                VehicleTypeRuleset(),
                Speech()
        ).handleIntent("Chiclayo")
        LOG.info("speechletResponse: $speechletResponse")
    }
}