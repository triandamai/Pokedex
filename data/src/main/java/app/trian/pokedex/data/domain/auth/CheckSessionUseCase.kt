/*
 * Copyright © 2023 trian.app
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */



package app.trian.pokedex.data.domain.auth

import app.trian.pokedex.data.local.SharedPref
import javax.inject.Inject

class CheckSessionUseCase @Inject constructor(
    private val pref: SharedPref
) {
    operator fun invoke(): Boolean = pref.getIsLoggedIn()
}