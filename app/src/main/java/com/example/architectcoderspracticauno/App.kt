package com.example.architectcoderspracticauno

import android.app.Application
import androidx.room.Room
import com.example.architectcoders.framework.core.HogwartsDatabase

class App: Application() {
    lateinit var db: HogwartsDatabase
        private set

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            this,
            HogwartsDatabase::class.java,
            "wizards.db"
        ).build()
    }
}