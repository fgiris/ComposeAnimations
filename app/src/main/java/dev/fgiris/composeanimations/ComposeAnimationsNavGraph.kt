package dev.fgiris.composeanimations

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.fgiris.composeanimations.home.HomeScreen

object ComposeAnimationsDestinations {
    const val HOME_ROUTE = "home"
    const val ANIMATED_VISIBILITY_ROUTE = "animated_visibility"
    const val ANIMATED_CONTENT_ROUTE = "animated_content"
    const val ANIMATE_AS_STATE_ROUTE = "animate_as_state"
    const val UPDATE_TRANSITION_ROUTE = "update_transition"
    const val REMEMBER_INFINITE_ROUTE = "remember_infinite"
    const val ANIMATABLE_ROUTE = "animatable"
    const val TARGET_BASED_ANIMATION_ROUTE = "target_based_animation"
    const val DECAY_ANIMATION_ROUTE = "decay_animation"
}

@Composable
fun ComposeAnimationsNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = ComposeAnimationsDestinations.HOME_ROUTE
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(ComposeAnimationsDestinations.HOME_ROUTE) {
            HomeScreen()
        }
    }
}