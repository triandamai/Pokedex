/*
 * Copyright © 2023 trian.app
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */


package app.trian.pokedex.android.feature.dashboard.home

import app.trian.pokedex.android.base.BaseViewModelData
import app.trian.pokedex.android.feature.catchPokemon.CatchPokemon
import app.trian.pokedex.data.domain.auth.GetProfileUseCase
import app.trian.pokedex.data.domain.pokemon.CatchPokemonUseCase
import app.trian.pokedex.data.domain.pokemon.SyncPokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val syncPokemonUseCase: SyncPokemonUseCase,
    private val getProfileUseCase: GetProfileUseCase,
    private val catchPokemonUseCase: CatchPokemonUseCase
) : BaseViewModelData<HomeState, HomeDataState, HomeEvent>(HomeState(), HomeDataState()) {
    init {
        handleActions()
        getProfile()
        getPokemon()
    }

    private fun catchPokemon() = async {
        catchPokemonUseCase()
            .onEach(
                loading = {},
                error = {
                    hideBottomSheet()
                    showSnackbar(this)
                },
                success = {
                    hideBottomSheet()
                    navigateSingleTop(
                        CatchPokemon.routeName,
                        this@onEach.pokemonId
                    )
                }
            )
    }

    private fun getProfile() = async {
        getProfileUseCase()
            .onEach(
                loading = {},
                error = {
                    showSnackbar(this)
                },
                success = {
                    commitData {
                        copy(
                            fullName = this@onEach
                        )
                    }
                }
            )
    }

    private fun getPokemon() = async {
        syncPokemonUseCase()
            .onEach(
                loading = {},
                error = {
                    showSnackbar(this)
                },
                success = {
                    commitData {
                        copy(
                            pokemons = this@onEach
                        )
                    }
                }
            )
    }

    override fun handleActions() = onEvent {
        when (it) {
            HomeEvent.Catch -> catchPokemon()
        }
    }
}