package org.dnltsk.bestvehicletypeskill.buildins

import com.amazon.speech.speechlet.SpeechletResponse
import com.amazon.speech.ui.PlainTextOutputSpeech
import com.amazon.speech.ui.SimpleCard
import com.google.inject.Singleton
import org.slf4j.LoggerFactory


@Singleton
class HelpIntentSpeech{

    val LOG = LoggerFactory.getLogger(this::class.java)

    fun getHelpSpeech(): SpeechletResponse{
        val title = "Bester Fahrzeugtyp"

        val speechText = StringBuilder()
        speechText.append("Frage einfach wie Du am besten zu einem Ziel kommst. ")
        speechText.append("<break time=\"1s\"/> ")
        speechText.append("Zum Beispiel: \"Womit fahre ich am besten nach Berlin?\" ")
        speechText.append("<break time=\"1s\"/> ")
        speechText.append("Oder: \"Wie soll ich in New York fahren?\"")
        LOG.info(speechText.toString())

        // Create the Simple card content.
        val card = SimpleCard()
        card.title = title
        card.content = speechText.toString()

        // Create the plain text output.
        val speech = PlainTextOutputSpeech()
        speech.text = speechText.toString()

        return SpeechletResponse.newTellResponse(speech, card)
    }

}