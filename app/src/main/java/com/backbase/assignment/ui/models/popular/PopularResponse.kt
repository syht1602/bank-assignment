package com.backbase.assignment.ui.models.popular

import androidx.databinding.BaseObservable

data class PopularResponse(
    val page: Int,
    val results: List<PopularModel>,
    val total_pages: Int,
    val total_results: Int
) : BaseObservable()
