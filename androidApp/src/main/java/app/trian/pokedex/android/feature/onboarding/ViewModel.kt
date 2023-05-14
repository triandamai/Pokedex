/*
 * Copyright © 2023 trian.app
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */


package app.trian.pokedex.android.feature.onboarding

import app.trian.pokedex.android.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardViewModel @Inject constructor(
) : BaseViewModel<OnboardState, OnboardEvent>(OnboardState()) {
    init {
        handleActions()
    }

    private fun calculatePager(page: Int) = async {
        val percentage = when (page) {
            0 -> 0.1f
            1 -> 0.2f
            2 -> 0.5f
            3 -> 0.7f
            4 -> 1f
            else -> 0.1f
        }
        commit { copy(percentage = percentage) }
    }

    override fun handleActions() = onEvent {
        when (it) {
            is OnboardEvent.PagerChanges -> calculatePager(it.page)
        }
    }

}