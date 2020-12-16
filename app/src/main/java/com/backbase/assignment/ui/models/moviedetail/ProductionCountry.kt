package com.backbase.assignment.ui.models.moviedetail

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "production_countries_table")
@Parcelize
data class ProductionCountry(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val iso_3166_1: String,
    val name: String
) : Parcelable
