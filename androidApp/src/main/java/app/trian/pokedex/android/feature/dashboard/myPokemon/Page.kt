/*
 * Copyright © 2023 trian.app
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */



package app.trian.pokedex.android.feature.dashboard.myPokemon

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
import app.trian.pokedex.android.components.DashboardBottomNavigationMenu
import app.trian.pokedex.android.components.ItemMyPokemon
import app.trian.pokedex.android.components.ItemPokemon
import app.trian.pokedex.android.components.bottomSheet.BottomSheetCatchPokemon
import app.trian.pokedex.android.feature.detailPokemon.DetailPokemon
import app.trian.pokedex.android.ui.OnSurface

object MyPokemon {
    const val routeName = "Community"
}

fun NavGraphBuilder.routeMyPokemon(
    state: ApplicationState,
) {
    composable(MyPokemon.routeName) {
        ScreenMyPokemon(appState = state)
    }
}

@Composable
internal fun ScreenMyPokemon(
    appState: ApplicationState,
) = UIWrapper<MyPokemonViewModel>(appState = appState) {
    val dataState by uiDataState.collectAsState()
    val state by uiState.collectAsState()
    with(appState) {
        setupTopAppBar {
            TopAppBarDashboardMyPokemon(
                name = state.fullName,
                onExit = {
                    dispatch(MyPokemonEvent.SignOut)
                }
            )
        }
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
                dispatch(MyPokemonEvent.Catch)
            }

        })
    }
    LazyColumn(
        contentPadding = PaddingValues(
            horizontal = 8.dp
        ),
        content = {
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
            if(dataState.myPokemon.isEmpty()){
                item {
                    Text(
                        text = "You don't have collection yet...",
                        style = MaterialTheme.typography.h5,
                        color = MaterialTheme.colors.primary,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            gridItems(
                dataState.myPokemon,
                columnCount = 2
            ) {
                ItemMyPokemon(
                    pokemonName = it.nickName,
                    image = it.pokemonImage,
                    hp = it.pokemonHp,
                    defense = it.pokemonDefense,
                    attack = it.pokemonAttack,
                    onClick = {
                        navigateSingleTop(
                            DetailPokemon.routeName,
                            it.pokemonId
                        )
                    },
                    onOption = {
                        dispatch(MyPokemonEvent.Release(it.collectionId))
                    }
                )
            }
        }
    )
}

@Composable
fun TopAppBarDashboardMyPokemon(
    name: String = "",
    onExit: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .padding(
                    horizontal = 16.dp
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = name,
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
        IconButton(onClick = onExit) {
            Icon(
                imageVector = Icons.Outlined.ExitToApp,
                contentDescription = "",
                tint = Color.White
            )
        }
    }

}

@Preview
@Composable
fun PreviewScreenMyPokemon() {
    BaseMainApp(
        topAppBar = {
            TopAppBarDashboardMyPokemon()
        }
    ) {
        ScreenMyPokemon(it)
    }
}