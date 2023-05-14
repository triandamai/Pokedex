/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package app.trian.pokedex.android.feature.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import app.trian.pokedex.android.ApplicationState
import app.trian.pokedex.android.base.BaseMainApp
import app.trian.pokedex.android.base.UIWrapper
import app.trian.pokedex.android.components.button.ButtonPrimary
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

object Onboard {
    const val routeName = "Onboard"


}

fun NavGraphBuilder.routeOnboard(
    state: ApplicationState,
) {
    composable(Onboard.routeName) {
        ScreenOnboard(appState = state)
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
internal fun ScreenOnboard(
    appState: ApplicationState,
) = UIWrapper<OnboardViewModel>(appState = appState) {

    val pagerState = rememberPagerState(
        initialPage = 0
    )

    val percentage by remember {
        derivedStateOf {
            when (pagerState.currentPage) {
                0 -> 0.25f
                1 -> 0.50f
                2 -> 0.75f
                3 -> 1f
                else -> 0.1f
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        LinearProgressIndicator(
            progress = percentage,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .padding(
                    top = 40.dp,
                    start = 20.dp,
                    end = 20.dp
                )
                .height(8.dp),
            strokeCap = StrokeCap.Round
        )
        ButtonPrimary(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(
                    bottom = 40.dp,
                    start = 20.dp,
                    end = 20.dp
                ),
            text = "Get Started",
        ) {

        }
    }
}

@Preview
@Composable
fun PreviewScreenOnboard() {
    BaseMainApp {
        ScreenOnboard(it)
    }
}