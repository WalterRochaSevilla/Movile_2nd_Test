package com.calyrsoft.ucbp1.features.movie.data.datasource

import com.calyrsoft.ucbp1.features.movie.data.database.dao.IMovieDao
import com.calyrsoft.ucbp1.features.movie.data.database.entity.MovieEntity

class MovieLocalDataSource(
    private val movieDao: IMovieDao
) {

    suspend fun saveMovies(movies: List<MovieEntity>) {
        movieDao.insertMovies(movies)
    }

    suspend fun updateMovieLike(movieId: Int, isLiked: Boolean, timestamp: Long) {
        val movie = movieDao.getMovieById(movieId)
        movie?.let {
            val updatedMovie = it.copy(isLiked = isLiked, likeTimestamp = timestamp)
            movieDao.updateMovie(updatedMovie)
        }
    }

    suspend fun getMoviesOrderedByLike(): List<MovieEntity> {
        return movieDao.getMoviesOrderedByLike()
    }
}