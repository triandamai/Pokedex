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

class ReleaseMyPokemonUseCase @Inject constructor(
    private val db: Database,
) {
    operator fun invoke(
        collectionId: String
    ) = flow {
        emit(Response.Loading)
        db.collectionQueries.deletByCollectionId(collectionId)

        emit(Response.Result("Success"))
    }.flowOn(Dispatchers.IO)
}