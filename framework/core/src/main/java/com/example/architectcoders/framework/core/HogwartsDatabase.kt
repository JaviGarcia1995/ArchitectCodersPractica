package com.example.architectcoders.framework.core

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.architectcoders.framework.wizard.database.WizardEntity
import com.example.architectcoders.framework.wizard.database.WizardsDao


@Database(
    entities = [WizardEntity::class],
    version = 1,
    exportSchema = false
)
abstract class HogwartsDatabase: RoomDatabase() {
    abstract fun wizardDao(): WizardsDao
}