package fr.unica.miage.hassad.pizzapp.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import fr.unica.miage.hassad.pizzapp.model.Pizza
import fr.unica.miage.hassad.pizzapp.viewmodel.CartViewModel

@Composable
fun PizzaDetail(pizza: Pizza, cartViewModel: CartViewModel, navController: NavController, modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val animatedAlpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    val extraCheese = remember { mutableStateOf(50) }
    val pricePerCheeseUnit = 0.5
    val totalPrice by remember { derivedStateOf { pizza.price + (extraCheese.value * pricePerCheeseUnit) } }

    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    cartViewModel.addPizzaToCart(pizza, extraCheese.value)
                    navController.navigate("menu")
                },
                containerColor = Color(0xFFB71C1C)
            ) {
                Icon(
                    imageVector = Icons.Filled.ShoppingCart,
                    contentDescription = "Add to cart",
                    tint = Color.White
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
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
            Image(
                painter = painterResource(id = pizza.image),
                contentDescription = pizza.name,
                modifier = Modifier
                    .size(300.dp)
                    .alpha(animatedAlpha)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = pizza.name,
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "${String.format("%.2f", totalPrice)} €",
                style = MaterialTheme.typography.headlineSmall.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Extra cheese: ${extraCheese.value}g",
                color = Color.White
            )

            Slider(
                value = extraCheese.value.toFloat(),
                onValueChange = { extraCheese.value = it.toInt() },
                valueRange = 0f..100f,
                colors = SliderDefaults.colors(
                    thumbColor = Color.White,
                    activeTrackColor = Color.White,
                    inactiveTrackColor = Color.White.copy(alpha = 0.5f)
                )
            )
        }
    }
}