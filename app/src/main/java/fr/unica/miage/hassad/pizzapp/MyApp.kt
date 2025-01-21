package fr.unica.miage.hassad.pizzapp
import fr.unica.miage.hassad.pizzapp.screens.PizzaDetail


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import DataSource
import fr.unica.miage.hassad.pizzapp.screens.PizzaMenu


@kotlinx.serialization.Serializable
object PizzaList
//Route vers le menu

@kotlinx.serialization.Serializable
data class PizzaRoute(val idPizza: Int)
//Route vers une pizza en particulier

@Preview
@Composable
fun MyApp() {
    var navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = PizzaList
    ) {
        composable<PizzaList> {
            PizzaMenu(
                menu = DataSource().loadPizzas(),
                modifier = Modifier.fillMaxSize().padding(16.dp),
                navController = navController
            )
        }
        composable<PizzaRoute> { backstackEntry ->
            val pizzaRoute = backstackEntry.toRoute<PizzaRoute>()
            PizzaDetail(pizza = DataSource().loadPizza(pizzaRoute.idPizza))
        }
    }
}