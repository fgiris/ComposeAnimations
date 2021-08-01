package dev.fgiris.composeanimations

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.fgiris.composeanimations.animationdetails.AnimationDetailsScreen
import dev.fgiris.composeanimations.data.AnimationApiType
import dev.fgiris.composeanimations.home.HomeScreen

object ComposeAnimationsDestinations {
    const val HOME_ROUTE = "home"
    const val ANIMATION_DETAILS_ROUTE = "animation_details"
    const val ANIMATION_DETAILS_KEY = "animation_details_key"
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ComposeAnimationsNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = ComposeAnimationsDestinations.HOME_ROUTE
) {
    val navActions = remember { MainActions(navController) }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(ComposeAnimationsDestinations.HOME_ROUTE) {
            HomeScreen(navActions = navActions)
        }
        composable(
            "${ComposeAnimationsDestinations.ANIMATION_DETAILS_ROUTE}/" +
                    "{${ComposeAnimationsDestinations.ANIMATION_DETAILS_KEY}}"
        ) {
            val animationDetailsKey =
                it.arguments?.getString(ComposeAnimationsDestinations.ANIMATION_DETAILS_KEY)!!
            AnimationDetailsScreen(animationDetailsKey)
        }
    }
}

class MainActions(navController: NavHostController) {
    val navigateToAnimationDetails: ((AnimationApiType) -> Unit) = { animationApiType ->
        navController.navigate(
            ComposeAnimationsDestinations.ANIMATION_DETAILS_ROUTE +
                    "/" + animationApiType.name
        )
    }
}