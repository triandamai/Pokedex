/*
 * Copyright © 2023 trian.app
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */



package app.trian.pokedex.android.components

import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.pokedex.android.R
import app.trian.pokedex.android.base.BaseMainApp
import app.trian.pokedex.android.feature.dashboard.myPokemon.MyPokemon
import app.trian.pokedex.android.feature.dashboard.home.Home
import app.trian.pokedex.android.ui.Grey500

sealed class DashboardBottomNavigationMenu(
    val route: String = "",
    val name: Int = R.string.label_nav_report_dashboard,
    val iconActive: Int = R.drawable.nav_home_active,
    val iconInactive: Int = R.drawable.nav_home_inactive
) {
    object MenuHome : DashboardBottomNavigationMenu(
        route = Home.routeName,
        name = R.string.label_nav_home_dashboard,
        iconActive = R.drawable.nav_home_active,
        iconInactive = R.drawable.nav_home_inactive
    )

    object MenuMyPokemon : DashboardBottomNavigationMenu(
        route = MyPokemon.routeName,
        name = R.string.label_nav_mypokemon_dashboard,
        iconActive = R.drawable.nav_mypokemon_active,
        iconInactive = R.drawable.nav_mypokemon_inactive
    )
}

var menus = listOf(
    DashboardBottomNavigationMenu.MenuHome,
    DashboardBottomNavigationMenu.MenuMyPokemon,
)


@Composable
fun DashboardBottomNavigation(
    currentRoute: String,
    onRefresh: (DashboardBottomNavigationMenu) -> Unit = {},
    onClick: (DashboardBottomNavigationMenu) -> Unit = {}
) {
    BottomAppBar(
        backgroundColor = MaterialTheme.colors.surface,
        cutoutShape = null,
        modifier = Modifier.height(65.dp)
    ) {

        menus.forEach {
            BottomNavigationItem(
                selected = currentRoute == it.route,
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = Grey500,
                onClick = {
                    if (currentRoute != it.route) onClick(it)
                    else onRefresh(it)
                },
                icon = {
                    Icon(
                        painter = painterResource(
                            id = if (currentRoute == it.route) it.iconActive
                            else it.iconInactive
                        ),
                        contentDescription = stringResource(id = it.name)
                    )
                },
                label = {
                    Text(text = stringResource(id = it.name))
                }
            )
        }
    }

}

@Preview
@Composable
fun PreviewDashboardBottomNavigation() {
    BaseMainApp(
        bottomBar = {
            DashboardBottomNavigation(
                currentRoute = "",
                onClick = {}
            )
        }
    )
}