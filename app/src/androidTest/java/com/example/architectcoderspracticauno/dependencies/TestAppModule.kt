package com.example.architectcoderspracticauno.dependencies

import android.app.Application
import androidx.room.Room
import com.example.architectcoders.framework.core.HogwartsDatabase
import com.example.architectcoders.framework.core.hilt.FrameworkCoreExtrasModule
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [FrameworkCoreExtrasModule::class]
)
object TestAppModule {
    @Provides
    @Singleton
    fun provideDatabase(app: Application): HogwartsDatabase {
        val db = Room.inMemoryDatabaseBuilder(
            app,
            HogwartsDatabase::class.java
        ).build()
        return db
    }
}