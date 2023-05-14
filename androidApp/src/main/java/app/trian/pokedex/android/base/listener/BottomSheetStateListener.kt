/*
 * Copyright © 2023 trian.app
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */



package app.trian.pokedex.android.base.listener

import androidx.compose.material.ModalBottomSheetValue

fun interface BottomSheetStateListener {
    fun onStateChanges(state: ModalBottomSheetValue)
}