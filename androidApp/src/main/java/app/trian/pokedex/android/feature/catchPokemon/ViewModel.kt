/*
 * Copyright © 2023 trian.app
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package app.trian.pokedex.android.feature.catchPokemon

import app.trian.pokedex.android.base.BaseViewModelData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CatchPokemonViewModel @Inject constructor(
) : BaseViewModelData<CatchPokemonState, CatchPokemonDataState, CatchPokemonEvent>(
    CatchPokemonState(),
    CatchPokemonDataState()
) {
    init {
        handleActions()
    }

    override fun handleActions() = onEvent {}

}