package fr.unica.miage.hassad.pizzapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import fr.unica.miage.hassad.pizzapp.data.OrderDatabase
import fr.unica.miage.hassad.pizzapp.model.Order
import fr.unica.miage.hassad.pizzapp.model.Pizza
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CartViewModel(application: Application) : AndroidViewModel(application) {
    private val orderDao = OrderDatabase.getDatabase(application).orderDao()

    private val _cartItems = MutableStateFlow<List<Pizza>>(emptyList())
    val cartItems: StateFlow<List<Pizza>> = _cartItems

    private val _totalPrice = MutableStateFlow(0.0)
    val totalPrice: StateFlow<Double> = _totalPrice

    fun addPizzaToCart(pizza: Pizza, extraCheese: Int) {
        viewModelScope.launch {
            val updatedPizza = pizza.copy(price = pizza.price + extraCheese * 0.5)
            _cartItems.value = _cartItems.value + listOf(updatedPizza)
            _totalPrice.value = _cartItems.value.sumOf { it.price }

            // Save the order to the database
            val order = Order(
                pizzaName = updatedPizza.name,
                price = updatedPizza.price,
                extraCheese = extraCheese
            )
            orderDao.insertOrder(order)
        }
    }
}