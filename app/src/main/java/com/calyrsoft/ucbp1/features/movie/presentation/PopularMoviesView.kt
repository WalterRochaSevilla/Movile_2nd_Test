package com.calyrsoft.ucbp1.features.movie.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.calyrsoft.ucbp1.features.movie.domain.model.MovieModel

@Composable
fun PopularMoviesView(
    movies: List<MovieModel>,
    onMovieLikeClicked: (Int, Boolean) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(movies.size) { index ->
            CardMovie(
                movie = movies[index],
                onLikeClicked = { movieId, isLiked ->
                    onMovieLikeClicked(movieId, isLiked)
                }
            )
        }
    }
}

@Composable
fun CardMovie(
    movie: MovieModel,
    onLikeClicked: (Int, Boolean) -> Unit
) {
    OutlinedCard(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = movie.pathUrl,
                contentDescription = movie.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(0.7f)
            )

            Text(
                text = movie.title,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth(),
                maxLines = 2
            )

            // Botón de Like
            IconButton(
                onClick = { onLikeClicked(movie.id, movie.isLiked) }
            ) {
                Icon(
                    imageVector = if (movie.isLiked) Icons.Filled.Favorite else Icons.Outlined.Favorite,
                    contentDescription = if (movie.isLiked) "Quitar like" else "Dar like",
                    tint = if (movie.isLiked) Color.Red else Color.Gray
                )
            }
        }
    }
}