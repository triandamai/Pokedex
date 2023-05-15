/*
 * Copyright © 2023 trian.app
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */


package app.trian.pokedex.android.feature.dashboard.home

import android.os.Parcelable
import app.trian.pokedex.data.model.PokemonModel
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class HomeState(
    val a: String = ""
) : Parcelable

@Immutable
@Parcelize
data class HomeDataState(
    val pokemons: @RawValue List<PokemonModel> = listOf(),
    val fullName: String = ""
) : Parcelable

sealed interface HomeEvent {
    object Catch:HomeEvent
}