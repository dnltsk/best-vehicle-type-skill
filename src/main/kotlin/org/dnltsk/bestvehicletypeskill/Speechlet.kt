package org.dnltsk.bestvehicletypeskill

import com.amazon.speech.speechlet.*
import com.amazon.speech.ui.PlainTextOutputSpeech
import com.amazon.speech.ui.Reprompt
import com.amazon.speech.ui.SimpleCard
import org.dnltsk.bestvehicletypeskill.decision.OpenWeatherMapClient
import org.dnltsk.bestvehicletypeskill.decision.Speech
import org.dnltsk.bestvehicletypeskill.decision.VehicleTypeRuleset
import org.slf4j.LoggerFactory

class Speechlet constructor(
        private val openWeatherMapClient: OpenWeatherMapClient,
        private val vehicleTypeRuleset: VehicleTypeRuleset,
        private val speech: Speech
) : com.amazon.speech.speechlet.Speechlet {

    private val LOG = LoggerFactory.getLogger(this::class.java)

    private val CITY_NAME_SLOT_NAME = "cityName"

    override fun onSessionStarted(request: SessionStartedRequest, session: Session) {
        LOG.info("onSessionStarted: $request, $session")
    }

    override fun onSessionEnded(sessionEndedRequest: SessionEndedRequest, session: Session) {
        LOG.info("onSessionEnded: $sessionEndedRequest, $session")
    }

    override fun onLaunch(request: LaunchRequest, session: Session): SpeechletResponse {
        LOG.info("onLaunch: $request, $session")
        return welcomeResponse
    }

    override fun onIntent(request: IntentRequest, session: Session): SpeechletResponse {
        LOG.info("onIntent: $request, $session")
        val cityNameSlot = request.intent.getSlot(CITY_NAME_SLOT_NAME)
        LOG.info("cityNameSlot: $cityNameSlot")
        val cityName = cityNameSlot.value
        return handleIntent(cityName)
    }

    fun handleIntent(cityName: String): SpeechletResponse {
        val weather = openWeatherMapClient.loadHourlyForecast(cityName)
        val decision = vehicleTypeRuleset.findBestVehicleType(weather)
        return speech.getSpeech(decision)
    }


    /**
     * Creates and returns a `SpeechletResponse` with a welcome message.
     * Create the Simple card content.
     * Create the plain text output.
     * Create reprompt
     *
     * @return SpeechletResponse spoken and visual response for the given intent
     */
    private val welcomeResponse: SpeechletResponse
        get() {
            val speechText = "Willkommen zum Besten Fahrzeugtyp Skill"
            val card = SimpleCard()
            card.title = "Bester Fahrzeugtyp"
            card.content = speechText
            val speech = PlainTextOutputSpeech()
            speech.text = speechText
            val reprompt = Reprompt()
            reprompt.outputSpeech = speech
            return SpeechletResponse.newAskResponse(speech, reprompt, card)
        }

}
