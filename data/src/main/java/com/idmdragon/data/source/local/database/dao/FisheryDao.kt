package com.idmdragon.data.source.local.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.idmdragon.data.source.local.entity.AreaEntity
import com.idmdragon.data.source.local.entity.FisheryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FisheryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListFishery(fisheryEntities: List<FisheryEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFishery(fishery: FisheryEntity)

    @Query("SELECT * FROM FisheryEntity ORDER BY timestamp ASC")
    fun selectAllFishery(): Flow<List<FisheryEntity>>

    @Query("SELECT * FROM AreaEntity")
    fun selectAllArea(): Flow<List<AreaEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArea(areaEntity: List<AreaEntity>)

}