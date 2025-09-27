package com.calyrsoft.ucbp1.features.movie.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.calyrsoft.ucbp1.features.movie.data.database.entity.MovieEntity

@Dao
interface IMovieDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovies(movies: List<MovieEntity>)
    @Update
    suspend fun updateMovie(movie: MovieEntity)
    @Query("SELECT * FROM movies ORDER BY is_liked DESC, like_timestamp DESC")
    suspend fun getMoviesOrderedByLike(): List<MovieEntity>
    @Query("SELECT * FROM movies WHERE id = :movieId")
    suspend fun getMovieById(movieId: Int): MovieEntity?
    @Query("SELECT * FROM movies WHERE is_liked = 1")
    suspend fun getLikedMovies(): List<MovieEntity>
}