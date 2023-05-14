/*
 * Copyright © 2023 trian.app
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package app.trian.pokedex.android.feature.detailPokemon

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class DetailPokemonState(
    val a: String = ""
) : Parcelable

@Immutable
@Parcelize
data class DetailPokemonDataState(
    val a: String = ""
) : Parcelable

sealed interface DetailPokemonEvent {
}