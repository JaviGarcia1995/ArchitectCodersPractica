package com.example.architectcoderspracticauno

import com.example.architectcoders.domain.wizard.data.HogwartsRepository
import com.example.architectcoders.domain.wizard.data.RemoteWizardsDataSource
import com.example.architectcoders.framework.wizard.database.WandEntity
import com.example.architectcoders.framework.wizard.database.WizardEntity
import com.example.architectcoders.framework.wizard.database.WizardsDao
import com.example.architectcoderspracticauno.data.server.MockWebServerRule
import com.example.architectcoderspracticauno.data.server.fromJson
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

// This class tests the configuration of Hilt, Room and MockWebServer for tests
@HiltAndroidTest
class ExampleInstrumentedTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val mockWebServerRule = MockWebServerRule()

    @Inject
    lateinit var hogwartsRepository: HogwartsRepository

    @Inject
    lateinit var remoteDataSource: RemoteWizardsDataSource

    @Inject
    lateinit var wizardsDao: WizardsDao

    @Before
    fun setup() {
        mockWebServerRule.server.enqueue(
            MockResponse().fromJson("wizards_gryffindor.json")
        )

        hiltRule.inject()
    }

    @Test
    fun repository_fetchWizardsByHouse_returnsWizards() {
        runBlocking {
            val wizards = hogwartsRepository.fetchWizardsByHouse("Gryffindor").first()
            assertTrue(wizards.isNotEmpty())
        }
    }

    @Test
    fun check_4_wizards_db() = runTest {
        wizardsDao.saveWizards(buildDatabaseWizards("1", "2", "3", "4"))
        val wizards = wizardsDao.getWizardsByHouse("Gryffindor").first()
        assertEquals(4, wizards.size)
    }

    @Test
    fun check_mock_server_is_working() = runTest {
        val wizards = remoteDataSource.fetchWizardsSortedByHouse("Gryffindor")

        assertEquals("Harry Potter", wizards[0].name)
    }
}

fun buildDatabaseWizards(vararg id: String) = id.map {
    WizardEntity(
        id = it,
        name = "Wizard $id",
        actor = "Actor $id",
        house = "Gryffindor",
        image = "https://example.com/image$id.jpg",
        patronus = "Patronus $id",
        wand = WandEntity(
            wood = "Wood $id",
            core = "Core $id",
            length = 123.45
        ),
        isFavorite = false
    )
}