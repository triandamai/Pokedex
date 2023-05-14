/*
 * Copyright © 2023 trian.app
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package app.trian.pokedex.android.feature.catchPokemon

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import app.trian.pokedex.android.ApplicationState
import app.trian.pokedex.android.base.BaseMainApp
import app.trian.pokedex.android.base.UIWrapper

object CatchPokemon {
    const val routeName = "CatchPokemon"
}

fun NavGraphBuilder.routeCatchPokemon(
    state: ApplicationState,
) {
    composable(CatchPokemon.routeName) {
        ScreenCatchPokemon(appState = state)
    }
}

@Composable
internal fun ScreenCatchPokemon(
    appState: ApplicationState,
) = UIWrapper<CatchPokemonViewModel>(appState = appState) {

}

@Preview
@Composable
fun PreviewScreenCatchPokemon() {
    BaseMainApp {
        ScreenCatchPokemon(it)
    }
}