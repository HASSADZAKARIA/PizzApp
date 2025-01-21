package fr.unica.miage.hassad.pizzapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.unica.miage.hassad.pizzapp.model.Pizza
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CartViewModel : ViewModel() {
    private val _cartItems = MutableStateFlow<List<Pizza>>(emptyList())
    val cartItems: StateFlow<List<Pizza>> = _cartItems

    private val _totalPrice = MutableStateFlow(0.0)
    val totalPrice: StateFlow<Double> = _totalPrice

    fun addPizzaToCart(pizza: Pizza, extraCheese: Int) {
        viewModelScope.launch {
            val updatedPizza = pizza.copy(price = pizza.price + extraCheese * 0.5)
            _cartItems.value = _cartItems.value + listOf(updatedPizza)
            _totalPrice.value = _cartItems.value.sumOf { it.price }
        }
    }
}