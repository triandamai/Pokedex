/*
 * Copyright © 2023 trian.app
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */


package app.trian.pokedex.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalConfiguration
import app.trian.pokedex.android.base.BaseMainApp
import app.trian.pokedex.android.base.EventListener
import app.trian.pokedex.android.base.extensions.listenChanges
import app.trian.pokedex.android.base.listener.ToAppStateEventListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var eventListener: EventListener
    private lateinit var appState: ApplicationState

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        eventListener = EventListener()
        setContent {
            appState = rememberApplicationState(
                event = eventListener
            )
            val localConfig = LocalConfiguration.current
            LaunchedEffect(key1 = appState.router, block = {
                appState.listenChanges(
                    ctx = this@MainActivity,
                    config = localConfig
                )
            })
            BaseMainApp(appState = appState) {
                AppNavigation(applicationState = it)
            }
        }
        listenFromChild()
    }

    private fun listenFromChild() {
        eventListener.addOnEventListener(object : ToAppStateEventListener {
            override fun onEvent(eventName: String) {
                TODO("Not yet implemented")
            }

            override fun exit() {
                finish()
            }

        })
    }
}

