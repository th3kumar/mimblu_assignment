package com.fridayhouse.mimblu_assignment.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fridayhouse.mimblu_assignment.api.ApiService
import com.fridayhouse.mimblu_assignment.data.MatchOption
import com.fridayhouse.mimblu_assignment.data.MatchOptionsApiResponse
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MatchOptionViewModel : ViewModel() {

    // LiveData to hold the list of MatchOptions
    private val _matchOptionsLiveData = MutableLiveData<List<MatchOption>>()
    val matchOptionsLiveData: LiveData<List<MatchOption>> get() = _matchOptionsLiveData

    // Function to fetch match options from the API
    fun fetchMatchOptionsFromApi() {
        // Create a Retrofit instance
        val retrofit = Retrofit.Builder()
            .baseUrl("http://dev.mimblu.com/mimblu-yii2-1552/api/plan/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Create an instance of the ApiService interface
        val apiService: ApiService = retrofit.create(ApiService::class.java)

        // Make the API call using the getMatchOptions function
        viewModelScope.launch {
            try {
                val apiResponse = apiService.getMatchOptions() // Call the suspend function directly
                val matchOptionsList = parseApiResponse(apiResponse)
                _matchOptionsLiveData.value = matchOptionsList
            } catch (e: Exception) {
                // Handle the error here
                Log.e("MatchOptionViewModel", "Error fetching match options: ${e.message}")
            }
        }
    }

    // Function to parse the API response JSON and create a list of MatchOption objects
    private fun parseApiResponse(apiResponse: MatchOptionsApiResponse): List<MatchOption> {
        val matchOption = apiResponse.matchOptions
        if (matchOption == null) {
            return emptyList()
        }

        return matchOption
    }




}
