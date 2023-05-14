/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package app.trian.pokedex.data.model.user

import kotlinx.serialization.Serializable

@Serializable
data class UserProfileResponse (
    val userId: String,
    val userFullName: String,
    val userCountryCode: String,
    val userDateOfBirth: String,
    val userProfilePicture: String,
    val createdAt: String,
    val updatedAt: String
)