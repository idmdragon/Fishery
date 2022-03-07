package com.idmdragon.data.source.local

import androidx.sqlite.db.SupportSQLiteQuery
import com.idmdragon.data.source.local.database.dao.FisheryDao
import com.idmdragon.data.source.local.entity.AreaEntity
import com.idmdragon.data.source.local.entity.FisheryEntity
import com.idmdragon.data.source.local.entity.SizeEntity
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val fisheryDao: FisheryDao) {

    fun getAllFishery(query: SupportSQLiteQuery): Flow<List<FisheryEntity>> =
        fisheryDao.selectAllFishery(query)

    suspend fun insertListFishery(listPexels: List<FisheryEntity>) =
        fisheryDao.insertListFishery(listPexels)

    fun getAllArea(): Flow<List<AreaEntity>> =
        fisheryDao.selectAllArea()

    suspend fun insertArea(listAreaEntity: List<AreaEntity>) =
        fisheryDao.insertArea(listAreaEntity)

    fun getAllSize(): Flow<List<SizeEntity>> =
        fisheryDao.selectAllSize()

    suspend fun insertSize(listSizeEntity: List<SizeEntity>) =
        fisheryDao.insertSize(listSizeEntity)

    fun searchItem(query: String): Flow<List<FisheryEntity>> =
        fisheryDao.searchItem(query)

}