/*
 * Copyright © 2023 trian.app
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package app.trian.pokedex.data.domain.pokemon

import app.trian.pokedex.data.common.Response
import app.trian.pokedex.data.model.toModel
import com.bluehabit.budgetku.db.Database
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CatchPokemonUseCase @Inject constructor(
    private val db: Database,
) {
    operator fun invoke() = flow {
        emit(Response.Loading)
        kotlinx.coroutines.delay(1000)
        val pokemon = db.pokemonQueries.getCount().executeAsOne()
        val value = when (val getRandom = (0 until pokemon).random()) {
            in 0..9 -> {
                "#00$getRandom"
            }
            in 10..99 -> {
                "#0$getRandom"
            }
            else -> "#$getRandom"
        }

        val getDetail = db.pokemonQueries.getById(value).executeAsOneOrNull()

        if (getDetail == null) {
            emit(Response.Error("Cannot Found Pokemon"))
        } else {
            emit(Response.Result(getDetail.toModel()))
        }
    }.flowOn(Dispatchers.IO)
}