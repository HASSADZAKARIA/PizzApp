import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.unica.miage.hassad.pizzapp.model.Pizza
import fr.unica.miage.hassad.pizzapp.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DataSourceViewModel : ViewModel() {
    private val _pizzas = MutableStateFlow<List<Pizza>>(emptyList())
    val pizzas: StateFlow<List<Pizza>> = _pizzas

    init {
        loadPizzas()
    }

    private fun loadPizzas() {
        viewModelScope.launch {
            _pizzas.value = listOf(
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
    }

    fun getPizza(id: Int): Pizza {
        return _pizzas.value[id]
    }
}