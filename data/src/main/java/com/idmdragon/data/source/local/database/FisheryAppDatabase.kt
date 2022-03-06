package com.idmdragon.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.idmdragon.data.source.local.database.dao.FisheryDao
import com.idmdragon.data.source.local.entity.AreaEntity
import com.idmdragon.data.source.local.entity.FisheryEntity
import com.idmdragon.data.source.local.entity.SizeEntity

@Database(
    entities = [ FisheryEntity::class, AreaEntity::class, SizeEntity::class],
    version = 1,
    exportSchema = false
)
abstract class FisheryAppDatabase : RoomDatabase() {

    abstract fun fisheryDao(): FisheryDao


}