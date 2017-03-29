package org.dnltsk.bestvehicletypeskill

import com.amazon.speech.slu.Intent
import com.amazon.speech.speechlet.*
import com.amazon.speech.ui.PlainTextOutputSpeech
import com.amazon.speech.ui.SimpleCard
import com.google.inject.Inject
import com.google.inject.Singleton
import org.dnltsk.bestvehicletypeskill.buildins.HelpIntentSpeech
import org.dnltsk.bestvehicletypeskill.decision.OpenWeatherMapClient
import org.dnltsk.bestvehicletypeskill.decision.Speech
import org.dnltsk.bestvehicletypeskill.decision.VehicleTypeRuleset
import org.slf4j.LoggerFactory

@Singleton
class SkillSpeechlet @Inject constructor(
        private val openWeatherMapClient: OpenWeatherMapClient,
        private val vehicleTypeRuleset: VehicleTypeRuleset,
        private val speech: Speech,
        private val helpIntentSpeech: HelpIntentSpeech
) : Speechlet {

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
        val intent = request.intent
        val intentName = intent.getName()
        LOG.info("onIntent() intentName: $intentName")
        when(intentName){
            "FindBestVehicleType" -> return handleFindBestVehicleTypeIntent(intent)
            "AMAZON.HelpIntent" -> return helpIntentSpeech.getHelpSpeech()
            else -> return helpIntentSpeech.getHelpSpeech()
        }
    }

    fun handleFindBestVehicleTypeIntent(intent: Intent): SpeechletResponse {
        val cityNameSlot = intent.getSlot(CITY_NAME_SLOT_NAME)
        LOG.info("cityNameSlot: ${cityNameSlot.value}")
        val cityName = cityNameSlot.value
        if(cityName == null){
            return helpIntentSpeech.getHelpSpeech()
        }
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

            val card = SimpleCard()
            card.title = "Bester Fahrzeugtyp"
            val speechText = "Willkommen zum Besten Fahrzeugtyp Skill"
            card.content = speechText
            val speech = PlainTextOutputSpeech()
            speech.text = speechText
            return SpeechletResponse.newTellResponse(speech, card)
        }

}
