package com.idmdragon.domain.utils

import androidx.sqlite.db.SimpleSQLiteQuery
import com.idmdragon.domain.model.Area

object FilterUtils {

    fun getFilteredQuery(querySize: String, queryArea: Area): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM FisheryEntity " +
                "WHERE KOMODITAS IS NOT NULL " +
                "AND PRICE IS NOT NULL ")

        if (querySize.isNotEmpty()){
            simpleQuery.append("AND size = $querySize ")
        }
        if (queryArea.city.isNotEmpty() && queryArea.province.isNotEmpty()){
            simpleQuery.append("AND area_kota = \"${queryArea.city}\" AND area_provinsi = \"${queryArea.province}\" ")
        }

        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}