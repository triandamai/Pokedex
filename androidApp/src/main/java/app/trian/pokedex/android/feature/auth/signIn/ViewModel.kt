/*
 * Copyright © 2023 trian.app
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */



package app.trian.pokedex.android.feature.auth.signIn

import app.trian.pokedex.android.base.BaseViewModel
import app.trian.pokedex.android.feature.dashboard.home.Home
import app.trian.pokedex.data.domain.auth.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
) : BaseViewModel<SignInState, SignInEvent>(SignInState()) {

    init {
        handleActions()
    }

    private fun signIn() = asyncWithState {
        signInUseCase(fullName)
            .onEach(
                loading = {},
                error = {
                    showSnackbar(this)
                },
                success = {
                    navigateAndReplaceAll(Home.routeName)
                }
            )
    }


    override fun handleActions() = onEvent {
        when (it) {
            SignInEvent.Submit -> signIn()
        }
    }
}


