package com.backbase.assignment.ui.models.nowplaying

data class NowPlayingResponse(
    val dates: Dates,
    val page: Int,
    val results: List<NowPlayingModel>,
    val total_pages: Int,
    val total_results: Int
)