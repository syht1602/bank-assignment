package com.backbase.assignment.ui.interfaces

import com.backbase.assignment.ui.models.popular.PopularModel

interface IPopularAdapter {
    fun loadMore()
    fun movieItemClick(movieId : PopularModel)
}
