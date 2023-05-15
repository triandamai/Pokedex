/*
 * Copyright © 2023 trian.app
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */


package app.trian.pokedex.android.feature.dashboard.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import app.trian.pokedex.android.ApplicationState
import app.trian.pokedex.android.base.BaseMainApp
import app.trian.pokedex.android.base.UIWrapper
import app.trian.pokedex.android.base.extensions.bottomNavigationListener
import app.trian.pokedex.android.base.extensions.gridItems
import app.trian.pokedex.android.base.listener.BottomNavigationListener
import app.trian.pokedex.android.components.DashboardBottomNavigation
import app.trian.pokedex.android.components.DashboardBottomNavigationMenu
import app.trian.pokedex.android.components.ItemPokemon
import app.trian.pokedex.android.components.bottomSheet.BottomSheetCatchPokemon
import app.trian.pokedex.android.feature.detailPokemon.DetailPokemon
import app.trian.pokedex.android.rememberApplicationState
import app.trian.pokedex.android.ui.OnSurface

object Home {
    const val routeName = "Home"
}

fun NavGraphBuilder.routeHome(
    state: ApplicationState
) {
    composable(Home.routeName) {
        ScreenHome(appState = state)

    }
}

@Composable
internal fun ScreenHome(
    appState: ApplicationState
) = UIWrapper<HomeViewModel>(
    appState = appState
) {
    val state by uiState.collectAsState(initial = HomeState())
    val dataState by uiDataState.collectAsState(initial = HomeDataState())



    with(appState) {
        hideTopAppBar()
        setupBottomSheet {
            BottomSheetCatchPokemon(
                onClose = {
                    hideBottomSheet()
                }
            )
        }
        bottomNavigationListener(object : BottomNavigationListener {
            override fun onRefresh(item: DashboardBottomNavigationMenu) {
                // remove empty

            }

            override fun onNavigate(item: DashboardBottomNavigationMenu) {
                navigateSingleTop(item.route)
            }

            override fun onFab() {
                showBottomSheet()
                dispatch(HomeEvent.Catch)
            }

        })
    }
    LazyColumn(
        content = {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 16.dp
                        ),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = dataState.fullName,
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colors.primary
                    )

                    Text(
                        text = "Pokemon Trainer",
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight.Medium,
                        color = OnSurface
                    )

                }
            }
            gridItems(
                dataState.pokemons,
                columnCount = 2
            ) {
                ItemPokemon(
                    pokemonName = it.pokemonName,
                    image = it.pokemonImage,
                    hp = it.pokemonHp,
                    defense = it.pokemonDefense,
                    attack = it.pokemonAttack,
                    onClick = {
                        navigateSingleTop(
                            DetailPokemon.routeName,
                            it.pokemonId
                        )
                    }
                )
            }
        }
    )

}


@Preview
@Composable
fun PreviewScreenHome() {
    BaseMainApp(
        bottomBar = {
            DashboardBottomNavigation(currentRoute = Home.routeName)
        }
    ) {
        ScreenHome(rememberApplicationState())
    }

}