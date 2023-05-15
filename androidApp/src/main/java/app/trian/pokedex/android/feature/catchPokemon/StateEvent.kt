/*
 * Copyright © 2023 trian.app
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package app.trian.pokedex.android.feature.catchPokemon

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class CatchPokemonState(
    val nickName: String = ""
) : Parcelable

@Immutable
@Parcelize
data class CatchPokemonDataState(
    val pokemonName: String = "",
    val pokemonImage: String = "",
    val pokemonId: String = "",
    val hp: Double = 0.0,
    val defense: Double = 0.0,
    val attack: Double = 0.0
) : Parcelable

sealed interface CatchPokemonEvent {
    object Submit:CatchPokemonEvent
}