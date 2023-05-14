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

class SyncAbilityUseCase @Inject constructor(
    private val db: Database,
    private val dataSource: PokemonDataSource
) {
    operator fun invoke() = flow {
        emit(Response.Loading)
        when (val result = dataSource.getAbility()) {
            is Response.Error -> emit(result)
            Response.Loading -> emit(Response.Loading)
            is Response.Result -> {
                val transform = db.transactionWithResult {
                    result.data.map {
                        val exist = db.abilityQueries
                            .getById(it.id.toString())
                            .executeAsOneOrNull()
                        if (exist == null) {
                            db.abilityQueries
                                .insertAbility(
                                    abilityId = it.id.toString(),
                                    abilityName = it.name,
                                    abilityDescription = it.description
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