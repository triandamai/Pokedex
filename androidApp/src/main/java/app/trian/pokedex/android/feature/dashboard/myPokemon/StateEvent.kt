/*
 * Copyright © 2023 trian.app
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */



package app.trian.pokedex.android.feature.dashboard.myPokemon

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class MyPokemonState(
    val selectedTab:Int=0
) : Parcelable

@Immutable
@Parcelize
data class MyPokemonDataState(
    val a:String=""
) : Parcelable


sealed interface CommunityEvent{

}