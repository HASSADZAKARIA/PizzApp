package fr.unica.miage.hassad.pizzapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.unica.miage.hassad.pizzapp.ui.theme.PizzAppTheme
import fr.unica.miage.hassad.pizzapp.viewmodel.CartViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val cartViewModel: CartViewModel = viewModel()
            PizzAppTheme {
                MyApp(cartViewModel)
            }
        }
    }
}