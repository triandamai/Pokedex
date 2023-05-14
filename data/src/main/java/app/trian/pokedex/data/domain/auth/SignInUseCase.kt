/*
 * Copyright © 2023 trian.app
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */


package app.trian.pokedex.data.domain.auth

import app.trian.pokedex.data.common.Response
import app.trian.pokedex.data.local.SharedPref
import app.trian.pokedex.data.remote.pokemon.PokemonDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val sharedPref: SharedPref
) {
    operator fun invoke(
        fullName: String
    ) = flow {
        emit(Response.Loading)
        sharedPref.setIsLoggedIn(true)
        sharedPref.setFullName(fullName)
        emit(Response.Result("Success save name"))
    }.flowOn(Dispatchers.IO)
}