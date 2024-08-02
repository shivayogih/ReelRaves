package com.findmore.reelraves.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromString(value: String): List<String> {
        return value.split(",").map { it }
    }

    @TypeConverter
    fun listToString(list: List<String>): String {
        return list.joinToString(",")
    }


    @TypeConverter
    fun fromGenreIdsList(genreIds: List<Int>?): String? {
        return genreIds?.let { Gson().toJson(it) }
    }

    @TypeConverter
    fun toGenreIdsList(genreIdsString: String?): List<Int>? {
        return genreIdsString?.let {
            val listType = object : TypeToken<List<Int>>() {}.type
            Gson().fromJson(it, listType)
        }
    }
}