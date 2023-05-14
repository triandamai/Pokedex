/*
 * Copyright © 2023 trian.app
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package app.trian.pokedex.data.model

import kotlinx.serialization.Serializable

@Serializable
data class AbilityResponse(
    val id: Long,
    val name: String,
    val description: String,
    val longDescription: String,
    val generationIntroduced: String,
    val pokemonFirstAbility: List<String>,
    val pokemonSecondAbility: List<String>,
    val pokemonHiddenAbility: List<String>
)
