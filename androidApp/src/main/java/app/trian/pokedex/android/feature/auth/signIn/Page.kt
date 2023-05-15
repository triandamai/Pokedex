/*
 * Copyright © 2023 trian.app
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */



package app.trian.pokedex.android.feature.auth.signIn

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import app.trian.pokedex.android.ApplicationState
import app.trian.pokedex.android.base.BaseMainApp
import app.trian.pokedex.android.base.UIWrapper
import app.trian.pokedex.android.components.AnnotationTextItem
import app.trian.pokedex.android.components.TextWithAction
import app.trian.pokedex.android.components.button.ButtonPrimary
import app.trian.pokedex.android.components.input.FormInput
import app.trian.pokedex.android.feature.dashboard.home.Home
import app.trian.pokedex.android.rememberApplicationState
import app.trian.pokedex.android.ui.OnSurface
import com.airbnb.lottie.compose.LottieAnimation

object SignIn {
    const val routeName = "SignIn"
}

fun NavGraphBuilder.routeSignIn(
    state: ApplicationState,
) {
    composable(SignIn.routeName) {
        ScreenSignIn(
            state
        )
    }
}

@Composable
internal fun ScreenSignIn(appState: ApplicationState) = UIWrapper<SignInViewModel>(
    appState = appState
) {
    val state by uiState.collectAsState()

    with(appState){
        hideTopAppBar()
        hideBottomAppBar()
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                MaterialTheme.colors.surface
            )
            .padding(
                horizontal = 16.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Welcome To",
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Medium,
                color = OnSurface
            )
            Text(
                text = "Pokedex",
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colors.primary
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth(

                )
                .fillMaxHeight(
                    fraction = 0.5f
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            FormInput(
                placeholder = "Input your name",
                label = "Full Name",
                value = state.fullName,
                onChange = {
                    commit {
                        copy(fullName = it)
                    }
                }
            )

        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ButtonPrimary(
                text = "Start",
            ) {
                hideKeyboard()
                dispatch(SignInEvent.Submit)
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}


@Preview
@Composable
fun PreviewScreenSignIn() {
    BaseMainApp {
        ScreenSignIn(
            appState = rememberApplicationState()
        )
    }
}

