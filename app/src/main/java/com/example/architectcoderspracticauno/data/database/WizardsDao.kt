package com.example.architectcoderspracticauno.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.architectcoderspracticauno.ui.model.WizardModel
import kotlinx.coroutines.flow.Flow

@Dao
interface WizardsDao {
    @Query("SELECT * FROM wizards WHERE house = :house")
    fun getWizardsByHouse(house: String): Flow<List<WizardEntity>>

    @Query("SELECT * FROM wizards WHERE id = :id")
    fun getWizardById(id: String): Flow<WizardEntity?>

    @Query("SELECT COUNT(id) FROM wizards WHERE house = :house")
    suspend fun getHouseCount(house: String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveWizards(wizards: List<WizardEntity>)
}