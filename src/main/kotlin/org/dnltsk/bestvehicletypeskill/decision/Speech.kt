package org.dnltsk.bestvehicletypeskill.decision

import com.amazon.speech.speechlet.SpeechletResponse
import com.amazon.speech.ui.PlainTextOutputSpeech
import com.amazon.speech.ui.Reprompt
import com.amazon.speech.ui.SimpleCard
import com.google.inject.Singleton
import org.dnltsk.bestvehicletypeskill.model.Decision
import org.dnltsk.bestvehicletypeskill.model.RainType.*
import org.dnltsk.bestvehicletypeskill.model.VehicleType
import org.slf4j.LoggerFactory

@Singleton
class Speech(){

    private val LOG = LoggerFactory.getLogger(this::class.java)

    fun getSpeech(decision: Decision): SpeechletResponse {
        val title = "Bester Fahrzeugtyp"

        val vehicleSpeech = getVehicleSpeech(decision)
        val rainIntensitySpeech = getIntensitySpeech(decision)
        val speechText = "Fahre mit einem $vehicleSpeech weil es $rainIntensitySpeech regnen wird."
        println(speechText)//TODO: LOG!

        // Create the Simple card content.
        val card = SimpleCard()
        card.title = title
        card.content = speechText

        // Create the plain text output.
        val speech = PlainTextOutputSpeech()
        speech.text = speechText

        // Create reprompt
        val reprompt = Reprompt()
        reprompt.outputSpeech = speech

        return SpeechletResponse.newAskResponse(speech, reprompt, card)
    }

    private fun getVehicleSpeech(decision: Decision): String {
        return when(decision.vehicleType){
            VehicleType.CAR -> "Auto" // car
            VehicleType.BUS -> "Bus" // bus
            VehicleType.BICYCLE -> "Fahrrad" // bicycle
        }
    }

    private fun getIntensitySpeech(decision: Decision): String {
        return when(decision.reason){
            HEAVY -> "extrem" // extreme
            MODERATE -> "moderat" // moderate
            LIGHT -> "leicht" // light
            NONE -> "nicht" // not
        }
    }

}