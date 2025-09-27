package com.calyrsoft.ucbp1.features.movie.data.mapper

import com.calyrsoft.ucbp1.features.movie.data.api.dto.MovieDto
import com.calyrsoft.ucbp1.features.movie.data.database.entity.MovieEntity
import com.calyrsoft.ucbp1.features.movie.domain.model.MovieModel

fun MovieDto.toModel(): MovieModel {
    return MovieModel(
        id = id,
        pathUrl = "https://image.tmdb.org/t/p/w185$pathUrl",
        title = title
    )
}

fun MovieModel.toEntity(): MovieEntity {
    return MovieEntity(
        id = id,
        posterPath = pathUrl,
        title = title,
        isLiked = isLiked,
        likeTimestamp = likeTimestamp
    )
}

fun MovieEntity.toModel(): MovieModel {
    return MovieModel(
        id = id,
        pathUrl = posterPath,
        title = title,
        isLiked = isLiked,
        likeTimestamp = likeTimestamp
    )
}