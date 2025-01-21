package fr.unica.miage.hassad.pizzapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import fr.unica.miage.hassad.pizzapp.R
import DataSource
import fr.unica.miage.hassad.pizzapp.PizzaRoute
import fr.unica.miage.hassad.pizzapp.model.Pizza
import fr.unica.miage.hassad.pizzapp.ui.theme.PizzAppTheme

@Composable
fun PizzaCard(
    pizza: Pizza,
    onClickPizza: () -> Unit = {},
    modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        onClick = onClickPizza
    ) {
        Column(modifier = modifier) {
            Text(
                text = pizza.name,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.headlineLarge
            )
            Image(
                painter = painterResource(id = pizza.image),
                contentDescription = pizza.name,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = pizza.price.toString()+" €",
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PizzaCardPreview() {
    PizzAppTheme {
        PizzaCard(pizza = Pizza("Margherita", 8.0, image = R.drawable.pizza1))
    }
}

@Composable
fun PizzaMenu(menu: List<Pizza>, modifier: Modifier = Modifier, navController: NavController) {
    LazyColumn {
        items(menu) { pizza ->
            PizzaCard(
                pizza = pizza,
                onClickPizza = {
                    navController.navigate(route = PizzaRoute(menu.indexOf(pizza)))
                },
                modifier = modifier.padding(16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PizzaMenuPreview() {
    PizzAppTheme {
        PizzaMenu(menu = DataSource().loadPizzas(), navController = rememberNavController())
    }
}