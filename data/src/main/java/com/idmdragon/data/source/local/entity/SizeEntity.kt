package com.idmdragon.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SizeEntity(
    @PrimaryKey
    val size: String
)