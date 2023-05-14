/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package app.trian.pokedex.android

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import app.trian.pokedex.android.feature.splashScreen.Splash

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
        routeSignUp(
            state = applicationState
        )
        routeCompleteProfile(
            state = applicationState
        )
        routeHome(
            state = applicationState
        )
        routeCommunity(
            state = applicationState
        )
        routeBudget(
            state = applicationState
        )
        routeReport(
            state = applicationState
        )
        routeCreateBudget(
            state = applicationState
        )
        routeInputPin(
            state = applicationState
        )
        routeChangePassword(
            state = applicationState
        )
        routeResultCreateBudget(
            state = applicationState
        )
        routeResetPassword(
            state = applicationState
        )
        routeCreateNewPassword(
            state = applicationState
        )
        routeCheckEmailResetPassword(
            state = applicationState
        )
        routeCreateTransaction(
            state = applicationState
        )
        routeTutorialBudget(
            state = applicationState
        )
        routeCreateAccount(
            state = applicationState
        )
        routeListAccount(

            state = applicationState
        )
        routeDetailTransaction(
            state = applicationState
        )
        routeEditTransaction(
            state = applicationState
        )
        routeCreateAccountSaving(
            state = applicationState
        )
        routeEditTransaction(
            state = applicationState
        )
        routeCreatePost(
            state = applicationState
        )
        routeEmailVerification(
            state = applicationState
        )
        routeListTransaction(
            state = applicationState
        )
    }
}