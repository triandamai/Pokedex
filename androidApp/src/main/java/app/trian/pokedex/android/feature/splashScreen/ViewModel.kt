/*
 * Copyright © 2023 trian.app
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */



package app.trian.pokedex.android.feature.splashScreen

import app.trian.pokedex.android.base.BaseViewModel
import app.trian.pokedex.android.feature.dashboard.home.Home
import app.trian.pokedex.android.feature.onboarding.Onboard
import app.trian.pokedex.data.domain.auth.CheckSessionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val checkSessionUseCase: CheckSessionUseCase
) : BaseViewModel<SplashState, SplashEvent>(SplashState()) {

    init {
        handleActions()
    }

    private fun checkIfUserLoggedIn() = async {
        if (checkSessionUseCase()) navigateAndReplaceAll(Home.routeName)
        else
            navigateAndReplaceAll(Onboard.routeName)
    }


    override fun handleActions() = onEvent {
        when (it) {
            SplashEvent.CheckSession -> checkIfUserLoggedIn()
        }
    }
}