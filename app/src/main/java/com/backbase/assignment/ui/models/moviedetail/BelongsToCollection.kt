package com.backbase.assignment.ui.models.moviedetail

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "belongs_to_collections_table")
@Parcelize
data class BelongsToCollection(
    @PrimaryKey
    val id: Int,
    val backdrop_path: String,
    val name: String,
    val poster_path: String
) : Parcelable
