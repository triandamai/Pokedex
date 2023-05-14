/*
 * Copyright © 2023 trian.app
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */



package app.trian.pokedex.data.remote.pokemon

import app.trian.pokedex.data.common.safeApiCall
import app.trian.pokedex.data.local.SharedPref
import app.trian.pokedex.data.model.AbilityResponse
import app.trian.pokedex.data.model.PokemonResponse
import io.ktor.client.HttpClient
import io.ktor.client.plugins.resources.get
import javax.inject.Inject

class PokemonDataSource @Inject constructor(
    private val httpClient: HttpClient,
    private val pref: SharedPref
) {
    suspend fun getPokemon(
    ) = safeApiCall<List<PokemonResponse>> {
        httpClient.get(PokemonApi.GetPokemon())
    }

    suspend fun getAbility(
    ) = safeApiCall<List<AbilityResponse>> {
        httpClient.get(PokemonApi.GetAbilities())
    }


}