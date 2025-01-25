package fr.unica.miage.hassad.pizzapp.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.unica.miage.hassad.pizzapp.viewmodel.OrderViewModel
import fr.unica.miage.hassad.pizzapp.model.Order

@Composable
fun OrderHistoryScreen(orderViewModel: OrderViewModel) {
    val orders = orderViewModel.orders.collectAsState(initial = emptyList())

    val infiniteTransition = rememberInfiniteTransition()
    val animatedAlpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFB71C1C), Color(0xFFF5F5F5))
                )
            )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Order History",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            ),
            modifier = Modifier
                .padding(bottom = 32.dp)
                .alpha(animatedAlpha)
        )
        LazyColumn {
            items(orders.value) { order: Order ->
                OrderItem(order = order)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun OrderItem(order: Order) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.8f))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Pizza: ${order.pizzaName}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Price: ${order.price} €", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Extra Cheese: ${order.extraCheese}g", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Date: ${order.timestamp}", style = MaterialTheme.typography.bodySmall)
        }
    }
}