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

class GetProfileUseCase @Inject constructor(
    private val sharedPref: SharedPref
) {
    operator fun invoke() = flow {
        emit(Response.Loading)
        emit(Response.Result(sharedPref.getFullName()))
    }.flowOn(Dispatchers.IO)
}