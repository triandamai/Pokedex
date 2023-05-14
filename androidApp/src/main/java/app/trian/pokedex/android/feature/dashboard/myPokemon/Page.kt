/*
 * Copyright © 2023 trian.app
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */



package app.trian.pokedex.android.feature.dashboard.myPokemon

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import app.trian.pokedex.android.ApplicationState
import app.trian.pokedex.android.base.BaseMainApp
import app.trian.pokedex.android.base.UIWrapper
import app.trian.pokedex.android.base.extensions.bottomNavigationListener
import app.trian.pokedex.android.base.listener.BottomNavigationListener
import app.trian.pokedex.android.components.DashboardBottomNavigationMenu

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
    val state by uiState.collectAsState()
    val dataState by uiDataState.collectAsState()

    with(appState) {
        setupTopAppBar {
            TopAppBarDashboardCommunity(
                selected = state.selectedTab,
                onCreatePost = {

                },
                onSelect = {
                    commit {
                        copy(
                            selectedTab = it
                        )
                    }
                }
            )
        }
        setupBottomSheet {

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
            }

        })
    }
    LazyColumn(
        content = {

        }
    )
}

@Composable
fun TopAppBarDashboardCommunity(
    selected: Int = 0,
    onCreatePost: () -> Unit = {},
    onSelect: (Int) -> Unit = {}
) {



}

@Preview
@Composable
fun PreviewScreenMyPokemon() {
    BaseMainApp(
        topAppBar = {
            TopAppBarDashboardCommunity()
        }
    ) {
        ScreenMyPokemon(it)
    }
}