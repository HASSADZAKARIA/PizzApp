import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import fr.unica.miage.hassad.pizzapp.model.Pizza
import fr.unica.miage.hassad.pizzapp.viewmodel.CartViewModel
import fr.unica.miage.hassad.pizzapp.viewmodel.OrderViewModel

@Composable
fun CartScreen(cartViewModel: CartViewModel, navController: NavController, extraCheese: Int) {
    val cartItems = cartViewModel.cartItems.collectAsState()
    val totalPrice = cartViewModel.totalPrice.collectAsState()
    val orderViewModel: OrderViewModel = viewModel()

    val infiniteTransition = rememberInfiniteTransition()
    val animatedAlpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
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
            text = "Your Cart",
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
            items(cartItems.value) { pizza: Pizza ->
                CartItem(pizza = pizza)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Total: ${totalPrice.value} €",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            ),
            modifier = Modifier.alpha(animatedAlpha)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                // Save the order to the database
//                cartItems.value.forEach { pizza ->
//                    val order = Order(
//                        pizzaName = pizza.name,
//                        price = pizza.price,
//                        extraCheese = extraCheese
//                    )
//                    orderViewModel.insertOrder(order)
//                }
                // Navigate back to the welcome screen
                navController.navigate("welcome")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB71C1C))
        ) {
            Text(text = "Payer", color = Color.White)
        }
    }
}

@Composable
fun CartItem(pizza: Pizza) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.8f))
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = pizza.image),
                contentDescription = pizza.name,
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = pizza.name, style = MaterialTheme.typography.bodyLarge)
                Text(text = "${pizza.price} €", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}