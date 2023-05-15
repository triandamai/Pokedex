/*
 * Copyright © 2023 trian.app
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package app.trian.pokedex.android.feature.catchPokemon

import androidx.lifecycle.SavedStateHandle
import app.trian.pokedex.android.base.BaseViewModelData
import app.trian.pokedex.data.domain.pokemon.GetDetailPokemonUseCase
import app.trian.pokedex.data.domain.pokemon.SavePokemonToCollectionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CatchPokemonViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getDetailPokemonUseCase: GetDetailPokemonUseCase,
    private val savePokemonToCollectionUseCase: SavePokemonToCollectionUseCase
) : BaseViewModelData<CatchPokemonState, CatchPokemonDataState, CatchPokemonEvent>(
    CatchPokemonState(),
    CatchPokemonDataState()
) {
    init {
        handleActions()
        val id = savedStateHandle.get<String>(CatchPokemon.argKey).orEmpty()
        getDetailPokemon(id)
    }

    private fun getDetailPokemon(pokemonId: String) = async {
        getDetailPokemonUseCase(pokemonId)
            .onEach(
                loading = {

                },
                error = {
                    showSnackbar(this)
                },
                success = {
                    commitData {
                        copy(
                            pokemonName = this@onEach.pokemonName,
                            pokemonImage = this@onEach.pokemonImage,
                            hp = this@onEach.pokemonHp,
                            defense = this@onEach.pokemonDefense,
                            attack = this@onEach.pokemonAttack,
                            pokemonId = this@onEach.pokemonId
                        )
                    }
                }
            )
    }

    private fun saveToMyPokemon() = async {
        val nickName = uiState.value.nickName
        val pokemonId = uiDataState.value.pokemonId
        savePokemonToCollectionUseCase(
            nickName = nickName,
            pokemonId = pokemonId
        )
            .onEach(
                loading = {},
                error = {
                    showSnackbar(this)
                },
                success = {
                    navigateUp()
                }
            )
    }

    override fun handleActions() = onEvent {
        when (it) {
            CatchPokemonEvent.Submit -> saveToMyPokemon()
        }
    }

}