/*
 * Copyright © 2023 trian.app
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package app.trian.pokedex.data.domain.pokemon

import app.trian.pokedex.data.common.Response
import app.trian.pokedex.data.model.PokemonModel
import app.trian.pokedex.data.model.toModel
import app.trian.pokedex.data.remote.pokemon.PokemonDataSource
import com.bluehabit.budgetku.db.Database
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SyncPokemonUseCase @Inject constructor(
    private val db: Database,
    private val dataSource: PokemonDataSource
) {
    operator fun invoke() = flow {
        emit(Response.Loading)
        when (val result = dataSource.getPokemon()) {
            is Response.Error -> {
                val data = db.pokemonQueries.getAll().executeAsList()
                if (data.isNotEmpty()) {
                    emit(Response.Result(data.map { it.toModel() }))
                } else {
                    emit(result)
                }

            }

            Response.Loading -> emit(Response.Loading)
            is Response.Result -> {
                val transform = db.transactionWithResult {
                    result.data.map {
                        val exist = db.pokemonQueries
                            .getById(it.id)
                            .executeAsOneOrNull()
                        if (exist == null) {
                            db.pokemonQueries.insertPokemon(
                                pokemonId = it.id,
                                pokemonImage = it.imageurl,
                                pokemonWeaknesses = it.weaknesses,
                                pokemonEvolutions = it.evolutions,
                                pokemonDescription = it.xdescription,
                                pokemonWeight = it.weight,
                                pokemonHeight = it.height,
                                pokemonHp = it.hp.toDouble(),
                                pokemonDefense = it.defense.toDouble(),
                                pokemonCategory = it.category,
                                pokemonAttack = it.attack.toDouble(),
                                pokemonName = it.name,
                                pokemonAbilities = it.abilities,
                                pokemonSpeed = it.speed.toDouble(),
                                pokemonType = it.typeofpokemon
                            )
                        }
                        it.toModel()
                    }
                }
                emit(Response.Result(transform))
            }
        }
    }.flowOn(Dispatchers.IO)
}