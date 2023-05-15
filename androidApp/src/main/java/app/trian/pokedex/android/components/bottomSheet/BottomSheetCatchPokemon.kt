/*
 * Copyright © 2023 trian.app
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package app.trian.pokedex.android.components.bottomSheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.pokedex.android.R
import app.trian.pokedex.android.ui.OnSurface
import app.trian.pokedex.android.ui.PokedexTheme
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun BottomSheetCatchPokemon(
    onClose:()->Unit={}
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.ripple))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )
    BaseBottomSheet(
        showButtonConfirmation = false,
        onDismiss = onClose
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LottieAnimation(
                composition = composition,
                progress = { progress },
                modifier = Modifier
                    .size(100.dp)
            )
            Text(
                text = "Catching...",
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Medium,
                color = OnSurface
            )

        }
    }
}

@Preview
@Composable
fun PreviewBottomSheetCatchPokemon() {
    PokedexTheme {
        BottomSheetCatchPokemon()
    }
}