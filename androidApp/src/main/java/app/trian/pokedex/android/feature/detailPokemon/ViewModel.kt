/*
 * Copyright © 2023 trian.app
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package app.trian.pokedex.android.feature.detailPokemon

import androidx.lifecycle.SavedStateHandle
import app.trian.pokedex.android.base.BaseViewModelData
import app.trian.pokedex.data.domain.pokemon.GetDetailPokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailPokemonViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getDetailPokemonUseCase: GetDetailPokemonUseCase
) : BaseViewModelData<DetailPokemonState, DetailPokemonDataState, DetailPokemonEvent>(
    DetailPokemonState(),
    DetailPokemonDataState()
) {
    init {
        handleActions()
        val id = savedStateHandle.get<String>(DetailPokemon.argKey).orEmpty()
        val name = savedStateHandle.get<String>(DetailPokemon.argKey2).orEmpty()
        getDetailPokemon(id, name)
    }

    private fun getDetailPokemon(id: String, name: String) = async {
        getDetailPokemonUseCase(id)
            .onEach(
                loading = {},
                error = {
                    showSnackbar(this)
                },
                success = {
                    commitData {
                        copy(
                            pokemonName = if (name.isEmpty() || name == "empty") this@onEach.pokemonName
                            else "$name(${this@onEach.pokemonName})",
                            pokemonImage = this@onEach.pokemonImage,
                            pokemonDescription = this@onEach.pokemonDescription,
                            height = this@onEach.pokemonHeight,
                            weight = this@onEach.pokemonHeight,
                            abilities = this@onEach.pokemonAbilities,
                            category = this@onEach.pokemonCategory,
                            hp = this@onEach.pokemonHp,
                            defense = this@onEach.pokemonDefense,
                            attack = this@onEach.pokemonAttack,
                        )
                    }
                }
            )
    }

    override fun handleActions() = onEvent {}

}