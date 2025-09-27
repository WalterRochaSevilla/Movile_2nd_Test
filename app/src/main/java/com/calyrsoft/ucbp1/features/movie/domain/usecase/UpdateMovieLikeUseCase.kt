package com.calyrsoft.ucbp1.features.movie.domain.usecase

import com.calyrsoft.ucbp1.features.movie.domain.repository.IMoviesRepository

class UpdateMovieLikeUseCase(
    private val movieRepository: IMoviesRepository
) {
    suspend operator fun invoke(movieId: Int, isLiked: Boolean) {
        movieRepository.updateMovieLike(movieId, isLiked)
    }
}