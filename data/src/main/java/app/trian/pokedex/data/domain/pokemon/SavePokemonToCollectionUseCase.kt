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
import java.util.UUID
import javax.inject.Inject

class SavePokemonToCollectionUseCase @Inject constructor(
    private val db: Database,
) {
    operator fun invoke(
        pokemonId: String,
        nickName: String
    ) = flow {
        emit(Response.Loading)
        val detailPokemon = db.pokemonQueries.getById(pokemonId).executeAsOneOrNull()
        db.collectionQueries.insertPokemon(
            collectionId = UUID.randomUUID().toString(),
            pokemonId = detailPokemon?.pokemonId.orEmpty(),
            pokemonImage = detailPokemon?.pokemonImage.orEmpty(),
            pokemonWeaknesses = detailPokemon?.pokemonWeaknesses.orEmpty(),
            pokemonEvolutions = detailPokemon?.pokemonEvolutions.orEmpty(),
            pokemonDescription = detailPokemon?.pokemonDescription.orEmpty(),
            pokemonWeight = detailPokemon?.pokemonWeight.orEmpty(),
            pokemonHeight = detailPokemon?.pokemonHeight.orEmpty(),
            pokemonHp = detailPokemon?.pokemonHp ?: 0.0,
            pokemonDefense = detailPokemon?.pokemonDefense ?: 0.0,
            pokemonCategory = detailPokemon?.pokemonCategory.orEmpty(),
            pokemonAttack = detailPokemon?.pokemonAttack ?: 0.0,
            pokemonName = detailPokemon?.pokemonName.orEmpty(),
            pokemonAbilities = detailPokemon?.pokemonAbilities.orEmpty(),
            pokemonSpeed = detailPokemon?.pokemonSpeed ?: 0.0,
            pokemonType = detailPokemon?.pokemonType.orEmpty(),
            pokemonNickName = nickName
        )
        emit(Response.Result("Success"))
    }.flowOn(Dispatchers.IO)
}