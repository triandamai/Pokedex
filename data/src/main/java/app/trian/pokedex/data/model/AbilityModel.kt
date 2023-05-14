/*
 * Copyright © 2023 trian.app
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package app.trian.pokedex.data.model

import app.trian.pokedex.schema.ability.ABILITY

data class AbilityModel(
    val abilityId: String = "",
    val abilityName: String = "",
    val abilityDescription: String = ""
)

fun ABILITY.toModel() = AbilityModel(
    abilityId = abilityId,
    abilityName = abilityName,
    abilityDescription = abilityDescription
)


fun AbilityResponse.toModel() = AbilityModel(
    abilityId = id.toString(),
    abilityName = name,
    abilityDescription = description
)
