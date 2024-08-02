package com.findmore.reelraves.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genre")
data class Genre(

    @PrimaryKey val id: Int,
    val name: String,
    var selected: Boolean = false
)