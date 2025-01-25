package fr.unica.miage.hassad.pizzapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import fr.unica.miage.hassad.pizzapp.model.Order

@Dao
interface OrderDao {
    @Insert
    suspend fun insertOrder(order: Order)

    @Query("SELECT * FROM orders ORDER BY timestamp DESC")
    fun getAllOrders(): Flow<List<Order>>
}