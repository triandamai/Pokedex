/*
 * Copyright © 2023 trian.app
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */



package app.trian.pokedex.android.base.listener

import app.trian.pokedex.android.components.DashboardBottomNavigationMenu

interface BottomNavigationListener {
    fun onRefresh(item: DashboardBottomNavigationMenu)

    fun onNavigate(item: DashboardBottomNavigationMenu)

    fun onFab()
}