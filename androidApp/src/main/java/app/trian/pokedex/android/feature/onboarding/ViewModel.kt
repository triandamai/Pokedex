/*
 * Copyright © 2023 trian.app
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */


package app.trian.pokedex.android.feature.onboarding

import app.trian.pokedex.android.base.BaseViewModel
import app.trian.pokedex.android.feature.dashboard.home.Home
import app.trian.pokedex.data.domain.auth.CheckSessionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardViewModel @Inject constructor(
    private val checkSessionUseCase: CheckSessionUseCase
) : BaseViewModel<OnboardState, OnboardEvent>(OnboardState()) {
    init {
        handleActions()
        checkName()
    }

    private fun checkName() = async {

        commit {
            copy(
                hasName = checkSessionUseCase()
            )
        }
    }

    override fun handleActions() = onEvent {

    }

}