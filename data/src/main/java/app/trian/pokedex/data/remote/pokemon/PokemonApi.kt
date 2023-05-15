/*
 * Copyright © 2023 trian.app
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */



package app.trian.pokedex.data.remote.pokemon

import io.ktor.resources.Resource

@Resource("Pokedex/8e015c161a8bd88122f5688e376002e353e9a099/assets/")
class PokemonApi {
    @Resource("pokemons.json")
    class GetPokemon(val parent: PokemonApi = PokemonApi())

    @Resource("abilites.json")
    class GetAbilities(val parent: PokemonApi = PokemonApi())

}