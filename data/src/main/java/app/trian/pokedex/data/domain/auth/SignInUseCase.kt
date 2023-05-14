/*
 * Copyright © 2023 trian.app
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */


package app.trian.pokedex.data.domain.auth

import app.trian.pokedex.data.common.Response
import app.trian.pokedex.data.remote.pokemon.AuthDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val authDataSource: AuthDataSource
) {
    operator fun invoke(
        email: String,
        password: String
    ) = flow {
        emit(Response.Loading)
        val result = authDataSource.signInWithEmail(email, password)
        emit(result)
    }.flowOn(Dispatchers.IO)
}