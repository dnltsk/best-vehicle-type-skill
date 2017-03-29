package org.dnltsk.bestvehicletypeskill

import com.amazon.speech.slu.Intent
import com.amazon.speech.slu.Slot
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.dnltsk.bestvehicletypeskill.buildins.HelpIntentSpeech
import org.dnltsk.bestvehicletypeskill.decision.OpenWeatherMapClient
import org.dnltsk.bestvehicletypeskill.decision.Speech
import org.dnltsk.bestvehicletypeskill.decision.VehicleTypeRuleset
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class SkillSpeechletTest {

    @Mock
    lateinit var owmClient: OpenWeatherMapClient
    @Mock
    lateinit var vehicleTypeRuleset: VehicleTypeRuleset
    @Mock
    lateinit var speech: Speech
    @Mock
    lateinit var helpIntentSpeech: HelpIntentSpeech
    @InjectMocks
    lateinit var skillSpeechlet: SkillSpeechlet

    lateinit var berlinIntent: Intent

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        berlinIntent = Intent.builder()
                .withName("FindBestVehicleType")
                .withSlots(
                        mutableMapOf(Pair(
                                "cityName",
                                Slot.builder().withName("cityName").withValue("Berlin,DE").build()))
                )
                .build()
    }

    @Test
    fun `initial dummy test`() {
        skillSpeechlet.handleFindBestVehicleTypeIntent(berlinIntent)
        assertThat(true).isTrue()
    }
}