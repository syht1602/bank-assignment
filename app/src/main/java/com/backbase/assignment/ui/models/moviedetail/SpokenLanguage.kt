package com.backbase.assignment.ui.models.moviedetail

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "spoken_languages_table")
@Parcelize
data class SpokenLanguage(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val english_name: String,
    val iso_639_1: String,
    val name: String
) : Parcelable
