package com.idmdragon.data.source.local.database.dao

import androidx.paging.PagingSource
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.idmdragon.data.source.local.entity.AreaEntity
import com.idmdragon.data.source.local.entity.FisheryEntity
import com.idmdragon.data.source.local.entity.SizeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FisheryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListFishery(fisheryEntities: List<FisheryEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFishery(fishery: FisheryEntity)

    @Query("DELETE FROM FisheryEntity")
    fun clearFishery()

    @RawQuery(observedEntities = [FisheryEntity::class])
    fun selectAllFishery(query: SupportSQLiteQuery): Flow<List<FisheryEntity>>

    @Query("SELECT * FROM AreaEntity")
    fun selectAllArea(): Flow<List<AreaEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArea(areaEntity: List<AreaEntity>)

    @Query("SELECT size FROM SizeEntity")
    fun selectAllSize(): Flow<List<SizeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSize(areaEntity: List<SizeEntity>)

    @Query("SELECT * FROM FisheryEntity WHERE komoditas LIKE '%'||:query||'%'")
    fun searchItem(query: String): Flow<List<FisheryEntity>>
}