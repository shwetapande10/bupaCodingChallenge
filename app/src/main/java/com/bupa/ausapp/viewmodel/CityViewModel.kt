package  com.bupa.ausapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bupa.ausapp.data.City
import com.bupa.ausapp.service.CityService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityViewModel @Inject constructor(private val cityService: CityService) : ViewModel() {
    private val _cities = MutableLiveData<List<City>>()
    val cities: LiveData<List<City>> get() = _cities

    init { fetchCities() }

    private fun fetchCities() = viewModelScope.launch {
        _cities.value = cityService.getCities()
    }
}
