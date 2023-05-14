/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package app.trian.pokedex.data.model.user

import kotlinx.serialization.Serializable

@Serializable
data class UserPermissionResponse (
    val permissionId: String,
    val permissionName: String,
    val permissionType: String,
    val permissionGroup: String,
    val createdAt: String,
    val updatedAt: String,
    val deleted: Boolean
)
