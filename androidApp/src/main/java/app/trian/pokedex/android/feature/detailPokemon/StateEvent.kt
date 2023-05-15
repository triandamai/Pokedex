/*
 * Copyright © 2023 trian.app
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package app.trian.pokedex.android.feature.detailPokemon

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class DetailPokemonState(
    val selectedMenu: Int = 2
) : Parcelable

@Immutable
@Parcelize
data class DetailPokemonDataState(
    val pokemonName:String="",
    val pokemonImage:String="",
    val pokemonDescription:String="",
    val height:String="",
    val weight:String="",
    val category:String="",
    val abilities: @RawValue List<String> = listOf(),
    val hp:Double=0.0,
    val attack:Double=0.0,
    val defense:Double=0.0
) : Parcelable

sealed interface DetailPokemonEvent {
}