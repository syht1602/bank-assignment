package com.backbase.assignment.ui.db.converters

import androidx.room.TypeConverter
import com.backbase.assignment.ui.models.moviedetail.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters() {

    @TypeConverter
    fun genreToString(genre: List<Genre?>?): String? {
        val type = object : TypeToken<List<Genre?>?>() {}.type
        return Gson().toJson(genre, type)
    }

    @TypeConverter
    fun stringToGenre(string: String?): List<Genre>? {
        val type = object : TypeToken<List<Genre?>?>() {}.type
        return Gson().fromJson<List<Genre>>(string, type)
    }

    @TypeConverter
    fun belongsToCollectionToString(belongsToCollection: BelongsToCollection?): String? {
        val type = object : TypeToken<BelongsToCollection?>() {}.type
        return Gson().toJson(belongsToCollection, type)
    }

    @TypeConverter
    fun stringToBelongsToCollection(string: String?): BelongsToCollection? {
        val type = object : TypeToken<BelongsToCollection?>() {}.type
        return Gson().fromJson<BelongsToCollection>(string, type)
    }

    @TypeConverter
    fun productionCompanyToString(productionCompany: List<ProductionCompany?>?): String? {
        val type = object : TypeToken<List<ProductionCompany?>?>() {}.type
        return Gson().toJson(productionCompany, type)
    }

    @TypeConverter
    fun stringToProductionCompany(string: String): List<ProductionCompany>? {
        val type = object : TypeToken<List<ProductionCompany?>?>() {}.type
        return Gson().fromJson<List<ProductionCompany>>(string, type)
    }

    @TypeConverter
    fun productionCountryToString(productionCountry: List<ProductionCountry?>?): String? {
        val type = object : TypeToken<List<ProductionCountry?>?>() {}.type
        return Gson().toJson(productionCountry, type)
    }

    @TypeConverter
    fun stringToProductionCountry(string: String): List<ProductionCountry>? {
        val type = object : TypeToken<List<ProductionCountry?>?>() {}.type
        return Gson().fromJson<List<ProductionCountry>>(string, type)
    }

    @TypeConverter
    fun spokenLanguageToString(spokenLanguage: List<SpokenLanguage?>?): String? {
        val type = object : TypeToken<List<SpokenLanguage?>?>() {}.type
        return Gson().toJson(spokenLanguage, type)
    }

    @TypeConverter
    fun stringToSpokenLanguage(string: String): List<SpokenLanguage>? {
        val type = object : TypeToken<List<SpokenLanguage?>?>() {}.type
        return Gson().fromJson<List<SpokenLanguage>>(string, type)
    }
}
