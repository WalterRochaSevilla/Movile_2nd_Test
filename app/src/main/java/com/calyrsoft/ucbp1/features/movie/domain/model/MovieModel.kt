package com.calyrsoft.ucbp1.features.movie.domain.model

data class MovieModel(
    val id: Int,
    val pathUrl: String,
    val title: String,
    val isLiked: Boolean = false,
    val likeTimestamp: Long = 0
)