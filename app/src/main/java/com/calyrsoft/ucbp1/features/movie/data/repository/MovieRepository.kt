package com.calyrsoft.ucbp1.features.movie.data.repository

import com.calyrsoft.ucbp1.features.movie.data.datasource.MovieLocalDataSource
import com.calyrsoft.ucbp1.features.movie.data.datasource.MovieRemoteDataSource
import com.calyrsoft.ucbp1.features.movie.data.mapper.toEntity
import com.calyrsoft.ucbp1.features.movie.data.mapper.toModel
import com.calyrsoft.ucbp1.features.movie.domain.model.MovieModel
import com.calyrsoft.ucbp1.features.movie.domain.repository.IMoviesRepository

class MovieRepository(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource
): IMoviesRepository {

    override suspend fun fetchPopularMovies(): Result<List<MovieModel>> {
        return try {
            // Obtener datos de la API
            val remoteResult = movieRemoteDataSource.fetchPopularMovies()

            remoteResult.fold(
                onSuccess = { movies ->
                    // Guardar en base de datos local
                    movieLocalDataSource.saveMovies(movies.map { it.toEntity() })

                    // Obtener datos ordenados por like de la base de datos
                    val localMovies = movieLocalDataSource.getMoviesOrderedByLike()
                    Result.success(localMovies.map { it.toModel() })
                },
                onFailure = { error ->
                    // Si falla la API, intentar obtener datos locales
                    val localMovies = movieLocalDataSource.getMoviesOrderedByLike()
                    if (localMovies.isNotEmpty()) {
                        Result.success(localMovies.map { it.toModel() })
                    } else {
                        Result.failure(error)
                    }
                }
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updateMovieLike(movieId: Int, isLiked: Boolean) {
        val timestamp = System.currentTimeMillis()
        movieLocalDataSource.updateMovieLike(movieId, isLiked, timestamp)
    }
}