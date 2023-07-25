package com.fridayhouse.mimblu_assignment

// SecondActivity.kt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fridayhouse.mimblu_assignment.MainActivity.Companion.EXTRA_SELECTED_SYMPTOM_IDS
import com.fridayhouse.mimblu_assignment.adapter.MatchOptionAdapter
import com.fridayhouse.mimblu_assignment.data.MatchOption
import com.google.gson.Gson

class SecondActivity : AppCompatActivity() {

    private lateinit var matchOptionAdapter: MatchOptionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


        // Fetch the list of match options from the API or use a dummy data list
        val matchOptions = getDummyMatchOptions()
        // Get the selected symptom IDs from the intent extras
        val selectedSymptomIds = intent.getIntArrayExtra(EXTRA_SELECTED_SYMPTOM_IDS)


        // Get the API response JSON from the intent (replace this with actual API call)
        val apiResponseJson = intent.getStringExtra(EXTRA_API_RESPONSE_JSON)

        // Parse the JSON to create a list of MatchOption objects
        val matchOption = parseApiResponse(apiResponseJson)

       // Set up RecyclerView with the Adapter
        val recyclerViewMatchOptions: RecyclerView = findViewById(R.id.recyclerViewMatchOptions)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewMatchOptions.layoutManager = layoutManager
        matchOptionAdapter = MatchOptionAdapter(matchOptions)
        recyclerViewMatchOptions.adapter = matchOptionAdapter
    }

    // Dummy function to create a list of dummy match options (for testing purposes)
    private fun getDummyMatchOptions(): List<MatchOption> {
        return listOf(
            MatchOption(
                1,
                "Mimblu Plus - 30 days",
                "Unlimited Messaging + Voice Notes\n\nText a therapist anytime, from anywhere!",
                "30",
                "1 Video Session (45 minutes)",
                "3538" // Use string value for final_price
            ),
            MatchOption(
                2,
                "Mimblu Plus - 60 Days",
                "Unlimited messaging + voice notes\n\nText a therapist anytime, from anywhere!",
                "60",
                "2 Video Sessions\n\nEach video session is 45 minutes",
                "6489" // Use string value for final_price
            ),
            // Add more dummy match options as needed
        )
    }


    // Function to parse the API response JSON and create a list of MatchOption objects
    private fun parseApiResponse(apiResponseJson: String?): List<MatchOption> {

        if (apiResponseJson == null) {
            // Handle the case where the JSON is null (e.g., show an error message or return an empty list)
            return emptyList()
        }

        // Use Gson library to deserialize the JSON to a list of MatchOption objects
        return Gson().fromJson(apiResponseJson, Array<MatchOption>::class.java).toList()
    }

    companion object {
        const val EXTRA_API_RESPONSE_JSON = "extra_api_response_json"
    }
}
