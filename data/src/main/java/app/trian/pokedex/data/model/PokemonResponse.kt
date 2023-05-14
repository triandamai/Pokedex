/*
 * Copyright © 2023 trian.app
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package app.trian.pokedex.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonResponse (
    val name: String,
    val id: String,
    val imageurl: String,
    val xdescription: String,
    val ydescription: String,
    val height: String,
    val category: String,
    val weight: String,
    val typeofpokemon: List<String>,
    val weaknesses: List<String>,
    val evolutions: List<String>,
    val abilities: List<String>,
    val hp: Long,
    val attack: Long,
    val defense: Long,

    @SerialName("special_attack")
    val specialAttack: Long,

    @SerialName("special_defense")
    val specialDefense: Long,

    val speed: Long,
    val total: Long,

    @SerialName("male_percentage")
    val malePercentage: String,

    @SerialName("female_percentage")
    val femalePercentage: String,

    val genderless: Long,
    val cycles: String,

    @SerialName("egg_groups")
    val eggGroups: String,

    val evolvedfrom: String,
    val reason: String,

    @SerialName("base_exp")
    val baseExp: String
)