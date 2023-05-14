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
    val a: String = ""
) : Parcelable

@Immutable
@Parcelize
data class CatchPokemonDataState(
    val a: String = ""
) : Parcelable

sealed interface CatchPokemonEvent {
}