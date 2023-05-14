/*
 * Copyright © 2023 trian.app
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */



package app.trian.pokedex.data.remote.pokemon

import io.ktor.resources.Resource

@Resource("/v1/auth")
class PokemonApi {
    @Resource("sign-in-email")
    class GetPokemon(val parent: PokemonApi = PokemonApi())

    @Resource("sign-in-google")
    class GetAbilities(val parent: PokemonApi = PokemonApi())

}