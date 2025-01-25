package fr.unica.miage.hassad.pizzapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders")
data class Order(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val pizzaName: String,
    val price: Double,
    val extraCheese: Int,
    //val timestamp: Long = System.currentTimeMillis()
    val timestamp: String = java.text.SimpleDateFormat("dd/MM/yyyy", java.util.Locale.getDefault()).format(java.util.Date())

)