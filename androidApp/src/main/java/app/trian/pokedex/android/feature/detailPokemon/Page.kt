/*
 * Copyright © 2023 trian.app
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package app.trian.pokedex.android.feature.detailPokemon

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import app.trian.pokedex.android.ApplicationState
import app.trian.pokedex.android.base.BaseMainApp
import app.trian.pokedex.android.base.UIWrapper

object DetailPokemon {
    const val routeName = "DetailPokemon"
}

fun NavGraphBuilder.routeDetailPokemon(
    state: ApplicationState,
) {
    composable(DetailPokemon.routeName) {
        ScreenDetailPokemon(appState = state)
    }
}

@Composable
internal fun ScreenDetailPokemon(
    appState: ApplicationState,
) = UIWrapper<DetailPokemonViewModel>(appState = appState) {

}

@Preview
@Composable
fun PreviewScreenDetailPokemon() {
    BaseMainApp {
        ScreenDetailPokemon(it)
    }
}