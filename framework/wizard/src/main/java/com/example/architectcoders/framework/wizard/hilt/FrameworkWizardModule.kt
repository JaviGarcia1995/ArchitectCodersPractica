package com.example.architectcoders.framework.wizard.hilt

import com.example.architectcoders.domain.wizard.data.LocalWizardsDataSource
import com.example.architectcoders.domain.wizard.data.RemoteWizardsDataSource
import com.example.architectcoders.framework.wizard.database.RoomWizardsDataSource
import com.example.architectcoders.framework.wizard.network.ServerWizardsDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class FrameworkWizardModule {
    @Binds
    abstract fun bindRemoteWizardsDataSource(
        serverWizardsDataSource: ServerWizardsDataSource
    ): RemoteWizardsDataSource

    @Binds
    abstract fun bindLocalWizardsDataSource(
        roomWizardsDataSource: RoomWizardsDataSource
    ): LocalWizardsDataSource
}