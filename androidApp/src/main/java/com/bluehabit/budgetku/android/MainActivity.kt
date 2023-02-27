/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalConfiguration
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.base.EventListener
import com.bluehabit.budgetku.android.components.BaseBottomAppBar
import com.bluehabit.budgetku.android.components.BaseBottomSheet
import com.bluehabit.budgetku.android.components.BaseSnackbar
import com.bluehabit.budgetku.android.components.BaseTopAppBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var eventListener: EventListener
    private lateinit var appState: ApplicationState

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            eventListener = EventListener()
            appState =  rememberApplicationState(
                event =  eventListener
            )
            val localConfig = LocalConfiguration.current

            LaunchedEffect(key1 = appState.router, block = {
                appState.listenChanges(
                    ctx = this@MainActivity,
                    config = localConfig
                )
            })
            BaseMainApp(
                appState = appState,
                topAppBar = {
                    BaseTopAppBar(state = it)
                },
                bottomBar = {
                    BaseBottomAppBar(state = it)
                },
                snackbarBar = {
                    BaseSnackbar(state = it)
                },
                bottomSheet = {
                    BaseBottomSheet(state = it)
                }
            ) {
                AppNavigation(applicationState = it)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        eventListener.clear()
        appState.clear()
    }
}
