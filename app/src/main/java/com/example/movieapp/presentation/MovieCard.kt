package com.example.movieapp.presentation

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.movieapp.R
import com.example.movieapp.model.Search


@Composable
fun MovieCard(movies: Search, index: Int, onItemClicked: (Int) -> Unit) {

    val isAnimate by remember { mutableStateOf(false) }
    val transition = rememberInfiniteTransition(label = "")
    val rotate by transition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing)
        ), label = ""
    )

    ElevatedCard(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        onClick = {
            onItemClicked.invoke(index)
        },
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
    ) {

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomStart) {
            AsyncImage(
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .run { if (isAnimate) rotate(rotate) else this },
                model = movies.Poster,
                contentDescription = movies.Title,
                error = painterResource(R.drawable.ic_launcher_background),
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .drawBehind { drawRect(color = Color(0x59000000)) }
                    .padding(8.dp),
            ) {
                Text(
                    text = movies.Title,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    ),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = movies.Year,
                    style = MaterialTheme.typography.titleMedium.copy(color = Color.White),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )

            }
        }


    }
}
