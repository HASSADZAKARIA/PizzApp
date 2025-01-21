package fr.unica.miage.hassad.pizzapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.unica.miage.hassad.pizzapp.R
import fr.unica.miage.hassad.pizzapp.model.Pizza
import fr.unica.miage.hassad.pizzapp.viewmodel.CartViewModel

@Composable
fun PizzaDetail(pizza: Pizza, cartViewModel: CartViewModel, modifier: Modifier = Modifier) {
    val extraCheese = remember { mutableStateOf(50) }

    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                cartViewModel.addPizzaToCart(pizza, extraCheese.value)
            }) {
                Icon(
                    imageVector = Icons.Filled.ShoppingCart,
                    contentDescription = "Add to cart"
                )
            }
        }) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = pizza.image),
                contentDescription = pizza.name,
                modifier = Modifier
                    .size(400.dp)
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text(text = pizza.name, style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.size(16.dp))
            Text(text = pizza.price.toString() + " €", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.size(16.dp))
            Text(text = "Extra cheese: ${extraCheese.value}g")
            Slider(
                value = extraCheese.value.toFloat(),
                onValueChange = { extraCheese.value = it.toInt() },
                valueRange = 0f..100f
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PizzaDetailPreview() {
    PizzaDetail(pizza = Pizza("Margherita", 8.0, image = R.drawable.pizza1), cartViewModel = CartViewModel())
}