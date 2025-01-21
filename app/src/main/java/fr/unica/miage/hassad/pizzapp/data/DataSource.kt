
import fr.unica.miage.hassad.pizzapp.model.Pizza
import fr.unica.miage.hassad.pizzapp.R

class DataSource {
    fun loadPizzas(): List<Pizza> {
        return listOf(
            Pizza("Margherita", 8.0, R.drawable.pizza1),
            Pizza("Capricciosa", 9.0, R.drawable.pizza2),
            Pizza("Diavola", 10.0, R.drawable.pizza3),
            Pizza("Quattro Stagioni", 11.0, R.drawable.pizza4),
            Pizza("Marinara", 7.0, R.drawable.pizza5),
            Pizza("Pepperoni", 10.0, R.drawable.pizza6),
            Pizza("Prosciutto", 10.0, R.drawable.pizza7),
            Pizza("Frutti di Mare", 13.0, R.drawable.pizza8)
        )
    }
    fun loadPizza(id: Int): Pizza{
        return loadPizzas()[id]
    }
}