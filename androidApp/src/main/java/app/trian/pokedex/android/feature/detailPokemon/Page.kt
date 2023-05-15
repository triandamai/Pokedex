/*
 * Copyright © 2023 trian.app
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package app.trian.pokedex.android.feature.detailPokemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import app.trian.pokedex.android.ApplicationState
import app.trian.pokedex.android.R
import app.trian.pokedex.android.base.BaseMainApp
import app.trian.pokedex.android.base.UIWrapper
import app.trian.pokedex.android.ui.BackgroundCard
import app.trian.pokedex.android.ui.OnSurface
import app.trian.pokedex.android.ui.Surface
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size

object DetailPokemon {
    const val routeName = "DetailPokemon"
    const val argKey = "id"
    fun routeName() = "DetailPokemon/{${argKey}}"

    val menus = listOf(
        "Abilities",
        "Evolution",
        "Overview",
    )
}

fun NavGraphBuilder.routeDetailPokemon(
    state: ApplicationState,
) {
    composable(
        DetailPokemon.routeName(),
        arguments = listOf(
            navArgument(DetailPokemon.argKey) {
                type = NavType.StringType
            }
        )
    ) {
        ScreenDetailPokemon(appState = state)
    }
}

@Composable
internal fun ScreenDetailPokemon(
    appState: ApplicationState,
) = UIWrapper<DetailPokemonViewModel>(appState = appState) {
    val dataState by uiDataState.collectAsState()
    val state by uiState.collectAsState()
    with(appState) {
        setupTopAppBar {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = {
                        navigateUp()
                    }) {
                        Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "")
                    }
                },
                backgroundColor = Surface,
                elevation = 0.dp
            )
        }
    }
    Row(
        modifier = Modifier
            .fillMaxSize(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.Top
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(
                    fraction = 0.2f
                )
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            DetailPokemon.menus.forEachIndexed { index, menu ->
                Text(
                    text = menu,
                    modifier = Modifier
                        .rotate(-90f)
                        .clickable(
                            enabled = true,
                            onClick = {
                                commit {
                                    copy(
                                        selectedMenu = index
                                    )
                                }
                            }
                        ),
                    color = if (state.selectedMenu == index) MaterialTheme.colors.primary
                    else OnSurface
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            LazyColumn(content = {
                item {
                    Spacer(modifier = Modifier.height(30.dp))
                }
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(
                                fraction = 0.3f
                            )
                    ) {
                        Text(
                            text = dataState.pokemonName,
                            textAlign = TextAlign.Right,
                            modifier = Modifier.align(
                                Alignment.BottomEnd
                            ),
                            style = MaterialTheme.typography.h1,
                            color = BackgroundCard.copy(alpha = 0.7f),
                            fontWeight = FontWeight.Bold,
                            maxLines = 1
                        )

                        Image(
                            painter = rememberAsyncImagePainter(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(dataState.pokemonImage)
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
                                    200.dp
                                )
                        )
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(18.dp))
                }
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                end = 16.dp
                            )
                    ) {
                        Text(
                            text = dataState.pokemonName,
                            style = MaterialTheme.typography.body1,
                            fontWeight = FontWeight.Medium,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = dataState.pokemonDescription,
                            style = MaterialTheme.typography.subtitle2,
                            fontWeight = FontWeight.Normal,
                            color = OnSurface
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = "Height",
                                    style = MaterialTheme.typography.subtitle2,
                                    fontWeight = FontWeight.Normal,
                                    color = OnSurface
                                )
                                Text(
                                    text = dataState.height,
                                    style = MaterialTheme.typography.subtitle2,
                                    fontWeight = FontWeight.Medium,
                                    color = MaterialTheme.colors.primary
                                )
                            }
                            Column {
                                Text(
                                    text = "Weight",
                                    style = MaterialTheme.typography.subtitle2,
                                    fontWeight = FontWeight.Normal,
                                    color = OnSurface
                                )
                                Text(
                                    text = dataState.weight,
                                    style = MaterialTheme.typography.subtitle2,
                                    fontWeight = FontWeight.Medium,
                                    color = MaterialTheme.colors.primary
                                )
                            }
                            Column {
                                Text(
                                    text = "Category",
                                    style = MaterialTheme.typography.subtitle2,
                                    fontWeight = FontWeight.Normal,
                                    color = OnSurface
                                )
                                Text(
                                    text = dataState.category,
                                    style = MaterialTheme.typography.subtitle2,
                                    fontWeight = FontWeight.Medium,
                                    color = MaterialTheme.colors.primary
                                )
                            }
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(18.dp))
                }
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                end = 16.dp
                            )
                    ) {
                        Text(
                            text = "Abilities",
                            style = MaterialTheme.typography.body1,
                            fontWeight = FontWeight.Medium,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(14.dp))
                        FlowRow {
                            dataState.abilities.forEach {
                                Row(
                                    modifier = Modifier
                                        .clip(
                                            MaterialTheme.shapes.small
                                        )
                                        .background(
                                            BackgroundCard
                                        )
                                        .clickable {  }
                                        .padding(
                                            horizontal = 8.dp,
                                            vertical = 2.dp
                                        )

                                ) {
                                    Text(
                                        text = "#${it}",
                                        style = MaterialTheme.typography.body1,
                                        fontWeight = FontWeight.Medium,
                                        color = OnSurface
                                    )
                                }
                            }
                        }

                    }
                }
                item {
                    Spacer(modifier = Modifier.height(18.dp))
                }
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                end = 16.dp
                            )
                    ) {
                        Text(
                            text = "Base Stats",
                            style = MaterialTheme.typography.body1,
                            fontWeight = FontWeight.Medium,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Hp",
                            style = MaterialTheme.typography.body2,
                            fontWeight = FontWeight.Normal,
                            color = OnSurface
                        )
                        LinearProgressIndicator(
                            progress = ((dataState.hp.toFloat() / 100) * 100) / 100,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp),
                            color = MaterialTheme.colors.primary,
                            backgroundColor = BackgroundCard,
                            strokeCap = StrokeCap.Round
                        )
                        Text(
                            text = "Attack",
                            style = MaterialTheme.typography.body2,
                            fontWeight = FontWeight.Normal,
                            color = OnSurface
                        )
                        LinearProgressIndicator(
                            progress = ((dataState.attack.toFloat() / 100) * 100) / 100,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp),
                            color = MaterialTheme.colors.primary,
                            backgroundColor = BackgroundCard,
                            strokeCap = StrokeCap.Round
                        )
                        Text(
                            text = "Defense",
                            style = MaterialTheme.typography.body2,
                            fontWeight = FontWeight.Normal,
                            color = OnSurface
                        )
                        LinearProgressIndicator(
                            progress = ((dataState.defense.toFloat() / 100) * 100) / 100,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp),
                            color = MaterialTheme.colors.primary,
                            backgroundColor = BackgroundCard,
                            strokeCap = StrokeCap.Round
                        )
                    }
                }
            })
        }
    }
}

@Preview
@Composable
fun PreviewScreenDetailPokemon() {
    BaseMainApp {
        ScreenDetailPokemon(it)
    }
}