/*
 * Copyright © 2023 trian.app
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */



package app.trian.pokedex.android.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.pokedex.android.R
import app.trian.pokedex.android.base.BaseMainApp
import app.trian.pokedex.android.base.extensions.formatToRupiah
import app.trian.pokedex.android.ui.BackgroundCard
import app.trian.pokedex.android.ui.Grey100
import app.trian.pokedex.android.ui.Grey700
import app.trian.pokedex.android.ui.OnSurface
import app.trian.pokedex.android.ui.PokedexTheme
import app.trian.pokedex.android.ui.Yellow200
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import java.math.BigDecimal

@Composable
fun ItemPokemon(
    image: String = "",
    pokemonName: String = "",
    hp: Double = 0.0,
    defense: Double = 0.0,
    attack: Double = 0.0,
    onClick: () -> Unit = {}
) {
    val ctx = LocalContext.current
    val currentWidth = ctx
        .resources
        .displayMetrics.widthPixels.dp /
            LocalDensity.current.density
    val cardWidth = (currentWidth / 2)
    Box(
        modifier = Modifier
            .width(cardWidth)
            .height(cardWidth + 40.dp)
            .padding(
                all = 8.dp
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = 30.dp,
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(MaterialTheme.shapes.medium)
                    .background(BackgroundCard)
                    .clickable(
                        enabled = true,
                        onClick = onClick
                    )
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 16.dp
                    ),
                verticalArrangement = Arrangement.Bottom
            ) {

                Text(
                    text = pokemonName,
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold,
                    color = OnSurface
                )
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Hp",
                    style = MaterialTheme.typography.body2,
                    fontWeight = FontWeight.Normal,
                    color = OnSurface
                )
                LinearProgressIndicator(
                    progress = ((hp.toFloat() / 100) * 100) / 100,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp),
                    color = MaterialTheme.colors.primary,
                    backgroundColor = MaterialTheme.colors.surface,
                    strokeCap = StrokeCap.Round
                )
                Text(
                    text = "Defense",
                    style = MaterialTheme.typography.body2,
                    fontWeight = FontWeight.Normal,
                    color = OnSurface
                )
                LinearProgressIndicator(
                    progress = ((defense.toFloat() / 100) * 100) / 100,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp),
                    color = MaterialTheme.colors.primary,
                    backgroundColor = MaterialTheme.colors.surface,
                    strokeCap = StrokeCap.Round
                )
                Text(
                    text = "Attack",
                    style = MaterialTheme.typography.body2,
                    fontWeight = FontWeight.Normal,
                    color = OnSurface
                )
                LinearProgressIndicator(
                    progress = ((attack.toFloat() / 100) * 100) / 100,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp),
                    color = MaterialTheme.colors.primary,
                    backgroundColor = MaterialTheme.colors.surface,
                    strokeCap = StrokeCap.Round
                )
            }
        }

        Image(
            painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(image)
                    .size(Size.ORIGINAL)
                    .placeholder(R.drawable.dummy_pokemn)
                    .error(R.drawable.dummy_pokemn)
                    .build()
            ),
            contentDescription = "",
            modifier = Modifier
                .align(
                    Alignment.TopCenter
                )
                .size(
                    cardWidth / 2
                )
        )
    }
}

@Preview
@Composable
fun PreviewItemAccount() {
    PokedexTheme {

        ItemPokemon(
            pokemonName = "Balbasur",
            image = "https://assets.pokemon.com/assets/cms2/img/pokedex/full/001.png"
        )

    }
}