/*
 * Copyright © 2023 trian.app
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package app.trian.pokedex.android.feature.catchPokemon

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import app.trian.pokedex.android.ApplicationState
import app.trian.pokedex.android.base.BaseMainApp
import app.trian.pokedex.android.base.UIWrapper
import app.trian.pokedex.android.components.ItemPokemon
import app.trian.pokedex.android.components.button.ButtonPrimary
import app.trian.pokedex.android.ui.OnSurface

object CatchPokemon {
    const val routeName = "CatchPokemon"
    const val argKey = "id"
    fun routeName() = "$routeName/{$argKey}"
}

fun NavGraphBuilder.routeCatchPokemon(
    state: ApplicationState,
) {
    composable(
        CatchPokemon.routeName(),
        arguments = listOf(
            navArgument(CatchPokemon.argKey) {
                type = NavType.StringType
            }
        )
    ) {
        ScreenCatchPokemon(appState = state)
    }
}

@Composable
internal fun ScreenCatchPokemon(
    appState: ApplicationState,
) = UIWrapper<CatchPokemonViewModel>(appState = appState) {
    val state by uiState.collectAsState()
    val dataState by uiDataState.collectAsState()

    with(appState){
        hideTopAppBar()
        hideBottomAppBar()
    }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    all = 16.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Yaay..."
                )
                Text(
                    text = "You have caught a pokemon"
                )

                ItemPokemon(
                    pokemonName = dataState.pokemonName,
                    hp = dataState.hp,
                    defense = dataState.defense,
                    attack = dataState.attack,
                    image = dataState.pokemonImage

                )
            }

            TextField(
                placeholder = {
                    Text(
                        text = "Write Nickname Here...",
                        color = OnSurface
                    )
                },
                value = state.nickName,
                onValueChange = {
                    commit {
                        copy(
                            nickName = it
                        )
                    }

                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colors.surface
                ),
                modifier = Modifier.fillMaxWidth(),
            )

            ButtonPrimary(text = "Save to Collection") {
                hideKeyboard()
                dispatch(CatchPokemonEvent.Submit)
            }
        }
        IconButton(onClick = { navigateUp() }) {
            Icon(
                imageVector = Icons.Outlined.Close,
                contentDescription = ""
            )
        }
    }

}

@Preview
@Composable
fun PreviewScreenCatchPokemon() {
    BaseMainApp {
        ScreenCatchPokemon(it)
    }
}