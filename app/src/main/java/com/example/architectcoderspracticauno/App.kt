package com.example.architectcoderspracticauno

import android.app.Application
import androidx.room.Room
import com.example.architectcoderspracticauno.framework.database.WizardsDatabase

class App: Application() {
    lateinit var db: WizardsDatabase
        private set

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            this,
            WizardsDatabase::class.java,
            "wizards.db"
        ).build()
    }
}