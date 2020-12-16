package com.backbase.assignment.ui.models.moviedetail

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "production_companies_table")
@Parcelize
data class ProductionCompany(
    @PrimaryKey
    val id: Int,
    val logo_path: String,
    val name: String,
    val origin_country: String
) : Parcelable
