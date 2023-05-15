/*
 * Copyright © 2023 trian.app
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package app.trian.pokedex.data.model

import app.trian.pokedex.schema.collection.COLLECTION
import app.trian.pokedex.schema.pokemon.POKEMON

data class CollectionModel(
    val collectionId: String = "",
    val nickName: String = "",
    val pokemonId: String = "",
    val pokemonName: String = "",
    val pokemonImage: String = "",
    val pokemonDescription: String = "",
    val pokemonHeight: String = "",
    val pokemonWeight: String = "",
    val pokemonSpeed: Double = 0.0,
    val pokemonHp: Double = 0.0,
    val pokemonAttack: Double = 0.0,
    val pokemonDefense: Double = 0.0,
    val pokemonCategory: String = "",
    val pokemonType: List<String> = listOf(),
    val pokemonWeaknesses: List<String> = listOf(),
    val pokemonEvolutions: List<String> = listOf(),
    val pokemonAbilities: List<String> = listOf()
)


fun COLLECTION.toModel() = CollectionModel(
    pokemonId = pokemonId,
    pokemonName = pokemonName,
    pokemonImage = pokemonImage,
    pokemonDescription = pokemonDescription,
    pokemonHeight = pokemonHeight,
    pokemonWeight = pokemonWeight,
    pokemonHp = pokemonHp,
    pokemonAttack = pokemonAttack,
    pokemonDefense = pokemonDefense,
    pokemonCategory = pokemonCategory,
    pokemonType = pokemonType,
    pokemonWeaknesses = pokemonWeaknesses,
    pokemonEvolutions = pokemonEvolutions,
    pokemonSpeed = pokemonSpeed,
    collectionId = collectionId,
    nickName = pokemonNickName
)

