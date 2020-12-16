package com.backbase.assignment.ui.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object Utils {
    /**
     * Convert date follow by requirement
     *
     * @param date
     */
    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    @SuppressLint("SimpleDateFormat")
    @JvmStatic
    fun dateConverter(date: String): String {
        return try {
            val from = SimpleDateFormat("yyyy-MM-dd")
            val to = SimpleDateFormat("MMMM dd, yyyy", Locale.US)
            to.format(from.parse(date))
        } catch (e: Exception) {
            "-"
        }
    }

    /**
     * Convert time to required format.
     *
     * @param time
     */
    @JvmStatic
    fun runtimeConverter(time: Float): String {
        return try {
            val hour = (time / 60).toInt()
            val minute = (time % 60).toInt()
            if (hour == 0 && minute == 0) "-" else "${hour}h ${minute}m"
        } catch (e: Exception) {
            "-"
        }
    }

    /**
     * Convert time to required format.
     *
     * @param date
     * @param time
     */
    @JvmStatic
    fun releaseDatetimeConverter(date: String, time: Float): String {
        return "${dateConverter(date)}  -  ${runtimeConverter(time)}"
    }

    /**
     * Image Url Generator.
     *
     * @param url
     */
    @JvmStatic
    fun imageUrlGenerator(url: String): String {
        return "${Constants.IMAGE_ROOT_URL}${url}"
    }
}