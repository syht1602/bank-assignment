package com.backbase.assignment.ui.models.moviedetail

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "genres_table")
@Parcelize
data class Genre(
    @PrimaryKey
    val id: Int,
    val name: String
) : Parcelable
