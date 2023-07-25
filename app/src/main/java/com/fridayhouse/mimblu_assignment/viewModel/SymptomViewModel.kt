package com.fridayhouse.mimblu_assignment.viewModel

// SymptomViewModel.kt

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fridayhouse.mimblu_assignment.api.ApiService
import com.fridayhouse.mimblu_assignment.data.ApiResponse
import com.fridayhouse.mimblu_assignment.data.SymptomItem
import kotlinx.coroutines.launch
import retrofit2.Retrofit

import retrofit2.converter.gson.GsonConverterFactory

class SymptomViewModel : ViewModel() {

    // Retrofit instance for API calls
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://dev.mimblu.com/mimblu-yii2-1552/api/user/") // Replace with your API base URL
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // Your API service interface. Replace 'YourApiService' with your actual API service interface.
    private val apiService: ApiService = retrofit.create(ApiService::class.java)

    // LiveData for the list of symptoms
    private val _symptomsLiveData = MutableLiveData<List<SymptomItem>>()
    val symptomsLiveData: LiveData<List<SymptomItem>> get() = _symptomsLiveData

    // LiveData for the state of "Show me my matches" button
    private val _isButtonEnabled = MutableLiveData<Boolean>()
    val isButtonEnabled: LiveData<Boolean> get() = _isButtonEnabled

    // Use this handler to post the update after the layout computation is completed
    private val handler = Handler(Looper.getMainLooper())

    init {
        // Initialize the LiveData
        _symptomsLiveData.value = emptyList()
        _isButtonEnabled.value = false
    }

    // Function to fetch the symptom list from the API
    fun fetchSymptomsFromApi() {
        viewModelScope.launch {
            try {
                val apiResponse = apiService.getSymptoms() // Call the suspend function directly
                Log.d("SymptomViewModel", "API Response: $apiResponse")
                val symptomsList = parseApiResponse(apiResponse)
                Log.d("SymptomViewModel", "Parsed Symptoms List: $symptomsList")
                _symptomsLiveData.value = symptomsList
                updateButtonState(symptomsList)
            } catch (e: Exception) {
                // Handle the error here
                Log.e("SymptomViewModel", "Error fetching symptoms: ${e.message}")
            }
        }
    }

    private fun parseApiResponse(apiResponse: ApiResponse): List<SymptomItem> {
        val symptoms = apiResponse.symptoms
        if (symptoms == null) {
            Log.e("SymptomViewModel", "API Response contains null symptoms list")
            return emptyList()
        }
        return symptoms
    }

    // Function to handle checkbox selection and update the "Show me my matches" button state
    fun updateButtonState(symptoms: List<SymptomItem>) {
        val anySymptomSelected = symptoms.any { it.isSelected }
        _isButtonEnabled.value = anySymptomSelected

        // Log statement to check if the button state is updated correctly
        Log.d("SymptomViewModel", "Button state updated: isEnabled=$anySymptomSelected")
        Log.d("SymptomViewModel", "Current button state: ${_isButtonEnabled.value}")
    }

    // Function to update the selected state of a symptom
    fun updateSymptomSelection(symptomId: Int, isSelected: Boolean) {
        val currentList = _symptomsLiveData.value ?: emptyList()
        val updatedList = currentList.map {
            if (it.id == symptomId) {
                it.copy(isSelected = isSelected)
            } else {
                it
            }
        }
        _symptomsLiveData.value = updatedList

        // Log statement to check if the symptom selection is updated correctly
        Log.d("SymptomViewModel", "Symptom selection updated: symptomId=$symptomId, isSelected=$isSelected")

        // Call updateButtonState with a delay to avoid updating while RecyclerView is computing a layout or scrolling
        handler.postDelayed({
            updateButtonState(symptomsLiveData.value ?: emptyList())
        }, 100)

        // Log statement to check the current value of _isButtonEnabled LiveData
        Log.d("SymptomViewModel", "Current button state: ${_isButtonEnabled.value}")
    }

    fun getSelectedSymptomIds(): List<Int> {
        val selectedSymptoms = _symptomsLiveData.value?.filter { it.isSelected }
        return selectedSymptoms?.map { it.id } ?: emptyList()
    }

    // Dummy function to create a list of dummy symptoms (for testing purposes)
    private fun getDummySymptoms(): List<SymptomItem> {
        return listOf(
            SymptomItem(1, "Stress at work/home", 1, "2021-08-18 15:04:34", 0, false, 1),
            SymptomItem(2, "Specific phobia", 1, "2021-08-19 09:49:40", 0, false, 1),
            SymptomItem(3, "Social anxiety", 1, "2021-08-19 09:49:58", 0, false, 1),
            // Add more dummy symptoms as needed
        )
    }
}

