package fr.unica.miage.hassad.pizzapp.model

class Pizza(
    val name: String,
    val price: Double,
    val image: Int,
) {
    fun copy(name: String = this.name, price: Double = this.price, image: Int = this.image): Pizza {
        return Pizza(name, price, image)
    }
}