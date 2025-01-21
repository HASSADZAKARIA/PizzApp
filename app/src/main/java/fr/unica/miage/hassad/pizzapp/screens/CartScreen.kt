package fr.unica.miage.hassad.pizzapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.unica.miage.hassad.pizzapp.viewmodel.CartViewModel
import fr.unica.miage.hassad.pizzapp.model.Pizza

@Composable
fun CartScreen(cartViewModel: CartViewModel) {
    val cartItems = cartViewModel.cartItems.collectAsState()
    val totalPrice = cartViewModel.totalPrice.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Your Cart", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        cartItems.value.forEach { pizza: Pizza ->
            Text(text = "${pizza.name} - ${pizza.price} €")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Total: ${totalPrice.value} €", style = MaterialTheme.typography.headlineSmall)
    }
}