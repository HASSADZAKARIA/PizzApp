package fr.unica.miage.hassad.pizzapp

import DataSourceViewModel
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.unica.miage.hassad.pizzapp.screens.CartScreen
import fr.unica.miage.hassad.pizzapp.screens.PizzaMenu
import fr.unica.miage.hassad.pizzapp.screens.WelcomeScreen
import fr.unica.miage.hassad.pizzapp.screens.PizzaDetail


@kotlinx.serialization.Serializable
object PizzaList

@kotlinx.serialization.Serializable
data class PizzaRoute(val idPizza: Int)

@Preview
@Composable
fun MyApp() {
    val navController = rememberNavController()
    val dataSourceViewModel: DataSourceViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "welcome"
    ) {
        composable("welcome") {
            WelcomeScreen(navController = navController)
        }
        composable("menu") {
            PizzaMenu(
                menu = dataSourceViewModel.pizzas.collectAsState().value,
                modifier = Modifier.fillMaxSize().padding(16.dp),
                navController = navController
            )
        }
        composable("cart") {
            CartScreen()
        }
        composable<PizzaRoute> { backstackEntry ->
            val pizzaRoute = backstackEntry.toRoute<PizzaRoute>()
            PizzaDetail(pizza = dataSourceViewModel.getPizza(pizzaRoute.idPizza))
        }
    }
}