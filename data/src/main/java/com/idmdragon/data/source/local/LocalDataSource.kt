package com.idmdragon.data.source.local

import androidx.paging.PagingSource
import com.idmdragon.data.source.local.database.dao.FisheryDao
import com.idmdragon.data.source.local.entity.FisheryEntity
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val fisheryDao: FisheryDao) {
    fun getAllFishery(): Flow<List<FisheryEntity>> =
        fisheryDao.selectAllFishery()

    suspend fun insertListFishery(listPexels: List<FisheryEntity>) =
        fisheryDao.insertListFishery(listPexels)
}