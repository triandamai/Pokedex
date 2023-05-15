/*
 * Copyright © 2023 trian.app
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */



package app.trian.pokedex.android.feature.dashboard.myPokemon

import android.os.Parcelable
import app.trian.pokedex.data.model.CollectionModel
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class MyPokemonState(
    val fullName:String=""
) : Parcelable

@Immutable
@Parcelize
data class MyPokemonDataState(
    val myPokemon:@RawValue List<CollectionModel> = listOf()
) : Parcelable


sealed interface MyPokemonEvent{
    object Catch:MyPokemonEvent

    object SignOut:MyPokemonEvent
}