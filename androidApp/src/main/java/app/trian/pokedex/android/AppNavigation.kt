/*
 * Copyright © 2023 trian.app
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */



package app.trian.pokedex.android

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import app.trian.pokedex.android.feature.auth.signIn.routeSignIn
import app.trian.pokedex.android.feature.dashboard.home.routeHome
import app.trian.pokedex.android.feature.dashboard.myPokemon.routeMyPokemon
import app.trian.pokedex.android.feature.onboarding.routeOnboard
import app.trian.pokedex.android.feature.splashScreen.Splash
import app.trian.pokedex.android.feature.splashScreen.routeSplash

@Composable
fun AppNavigation(
    applicationState: ApplicationState
) {
    NavHost(
        navController = applicationState.router,
        startDestination = Splash.routeName
    ) {
        routeSplash(
            state = applicationState
        )
        routeOnboard(
            state = applicationState
        )
        routeSignIn(
            state = applicationState
        )


        routeHome(
            state = applicationState
        )
        routeMyPokemon(
            state = applicationState
        )
    }
}