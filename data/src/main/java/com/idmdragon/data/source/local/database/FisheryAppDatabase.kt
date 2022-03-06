package com.idmdragon.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.idmdragon.data.source.local.database.dao.FisheryDao
import com.idmdragon.data.source.local.entity.AreaEntity
import com.idmdragon.data.source.local.entity.FisheryEntity

@Database(
    entities = [ FisheryEntity::class, AreaEntity::class ],
    version = 1,
    exportSchema = false
)
abstract class FisheryAppDatabase : RoomDatabase() {

    abstract fun fisheryDao(): FisheryDao


}