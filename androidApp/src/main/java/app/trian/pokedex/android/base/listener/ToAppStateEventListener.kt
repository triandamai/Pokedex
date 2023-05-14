/*
 * Copyright © 2023 trian.app
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */


package app.trian.pokedex.android.base.listener

interface ToAppStateEventListener {
    fun onEvent(eventName:String)

    fun exit()
}