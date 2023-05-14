/*
 * Copyright © 2023 trian.app
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */



package app.trian.pokedex.data.remote.pokemon

import app.trian.pokedex.data.common.safeApiCall
import app.trian.pokedex.data.local.SharedPref
import io.ktor.client.HttpClient
import io.ktor.client.plugins.resources.get
import io.ktor.client.request.get
import javax.inject.Inject

class AuthDataSource @Inject constructor(
    private val httpClient: HttpClient,
    private val pref: SharedPref
) {
    suspend fun getPokemon(
    ) = safeApiCall<Any> {
       httpClient.get(PokemonApi.GetPokemon())
    }


}