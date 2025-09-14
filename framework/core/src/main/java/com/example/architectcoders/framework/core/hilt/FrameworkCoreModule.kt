package com.example.architectcoders.framework.core.hilt

import android.app.Application
import androidx.room.Room
import com.example.architectcoders.framework.core.HogwartsClient
import com.example.architectcoders.framework.core.HogwartsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object FrameworkCoreModule {
    @Provides
    @Singleton
    fun provideWizardsDao(db: HogwartsDatabase) = db.wizardDao()

    @Provides
    @Singleton
    fun provideWizardsService(@Named("apiUrl") apiUrl: String) = HogwartsClient(apiUrl).instance
}

@Module
@InstallIn(SingletonComponent::class)
object FrameworkCoreExtrasModule {
    @Provides
    @Singleton
    fun provideDatabase(app: Application) = Room.databaseBuilder(
        app,
        HogwartsDatabase::class.java,
        "wizards.db"
    ).build()

    @Provides
    @Singleton
    @Named("apiUrl")
    fun provideApiUrl(): String = "https://hp-api.onrender.com/api/"
}