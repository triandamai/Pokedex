/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package app.trian.pokedex.android.feature.dashboard.myPokemon

import app.trian.pokedex.android.base.BaseViewModelData
import app.trian.pokedex.android.feature.catchPokemon.CatchPokemon
import app.trian.pokedex.data.domain.auth.GetProfileUseCase
import app.trian.pokedex.data.domain.auth.SignOutUseCase
import app.trian.pokedex.data.domain.pokemon.CatchPokemonUseCase
import app.trian.pokedex.data.domain.pokemon.GetMyPokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyPokemonViewModel @Inject constructor(
    private val catchPokemonUseCase: CatchPokemonUseCase,
    private val getMyPokemonUseCase: GetMyPokemonUseCase,
    private val signOutUseCase: SignOutUseCase,
    private val getProfileUseCase: GetProfileUseCase
) : BaseViewModelData<MyPokemonState, MyPokemonDataState, MyPokemonEvent>(
    MyPokemonState(),
    MyPokemonDataState()
) {
    init {
        handleActions()
        getMyPokemon()
        getProfile()
    }

    private fun getProfile() = async {
        getProfileUseCase()
            .onEach(
                loading = {},
                error = {},
                success = {
                    commit {
                        copy(
                            fullName = this@onEach
                        )
                    }
                }
            )
    }

    private fun getMyPokemon() = async {
        getMyPokemonUseCase()
            .onEach(
                loading = {},
                error = {
                    showSnackbar(this)
                },
                success = {
                    commitData {
                        copy(
                            myPokemon = this@onEach
                        )
                    }
                }
            )

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

    private fun signOut() = async {
        signOutUseCase()
            .onEach(
                loading = {},
                error = {
                    showSnackbar(this)
                },
                success = {
                    exit()
                }
            )
    }

    override fun handleActions() = onEvent {
        when (it) {
            MyPokemonEvent.Catch -> catchPokemon()
            MyPokemonEvent.SignOut -> signOut()
        }
    }

}