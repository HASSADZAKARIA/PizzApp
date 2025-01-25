package fr.unica.miage.hassad.pizzapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import fr.unica.miage.hassad.pizzapp.data.OrderDatabase
import fr.unica.miage.hassad.pizzapp.model.Order
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class OrderViewModel(application: Application) : AndroidViewModel(application) {
    private val orderDao = OrderDatabase.getDatabase(application).orderDao()
    val orders: Flow<List<Order>> = orderDao.getAllOrders()

    fun insertOrder(order: Order) {
        viewModelScope.launch {
            orderDao.insertOrder(order)
        }
    }
}