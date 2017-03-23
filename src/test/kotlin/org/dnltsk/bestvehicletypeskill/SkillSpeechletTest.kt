package org.dnltsk.bestvehicletypeskill

import org.assertj.core.api.AssertionsForClassTypes.assertThat
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
    @InjectMocks
    lateinit var skillSpeechlet: SkillSpeechlet

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `initial dummy test`() {
        skillSpeechlet.handleIntent("Reading")
        assertThat(true).isTrue()
    }
}