package com.example.architectcoderspracticauno

import androidx.test.rule.GrantPermissionRule
import com.example.architectcoders.domain.wizard.data.HogwartsRepository
import com.example.architectcoders.domain.wizard.entities.WandModel
import com.example.architectcoders.framework.wizard.database.WandEntity
import com.example.architectcoders.framework.wizard.database.WizardEntity
import com.example.architectcoders.framework.wizard.database.WizardsDao
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class ExampleInstrumentedTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var hogwartsRepository: HogwartsRepository

    @Inject
    lateinit var wizardsDao: WizardsDao

    @Before
    fun setup() {
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