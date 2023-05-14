/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package app.trian.pokedex.android.feature.dashboard.myPokemon

import app.trian.pokedex.android.base.BaseViewModelData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyPokemonViewModel @Inject constructor(
) : BaseViewModelData<MyPokemonState, MyPokemonDataState, CommunityEvent>(MyPokemonState(),MyPokemonDataState()) {
    init {
        handleActions()
    }

    override fun handleActions() = onEvent {}

}