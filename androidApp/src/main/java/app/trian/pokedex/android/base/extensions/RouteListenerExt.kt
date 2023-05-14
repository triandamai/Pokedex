/*
 * Copyright © 2023 trian.app
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */



package app.trian.pokedex.android.base.extensions

import android.content.Context
import android.content.res.Configuration
import app.trian.pokedex.android.ApplicationState
import app.trian.pokedex.android.components.menus

fun ApplicationState.listenChanges(
    ctx: Context,
    config: Configuration
) = this.router.addOnDestinationChangedListener { _, destination, _ ->
    currentRoute = destination.route.orEmpty()
    if (currentRoute in menus.map { it.route }) showBottomBar()
    else hideBottomBar()
}
