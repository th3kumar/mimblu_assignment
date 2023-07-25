package com.fridayhouse.mimblu_assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fridayhouse.mimblu_assignment.adapter.SymptomAdapter
import com.fridayhouse.mimblu_assignment.viewModel.SymptomViewModel
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: SymptomViewModel
    private lateinit var symptomAdapter: SymptomAdapter

    companion object {
        const val EXTRA_SELECTED_SYMPTOM_IDS = "extra_selected_symptom_ids"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Initialize ViewModel using ViewModelProvider
        viewModel = ViewModelProvider(this).get(SymptomViewModel::class.java)

        // Set up RecyclerView with the Adapter
        val recyclerViewSymptoms: RecyclerView = findViewById(R.id.recyclerViewSymptoms)
        recyclerViewSymptoms.layoutManager = LinearLayoutManager(this)
        symptomAdapter = SymptomAdapter(viewModel)
        recyclerViewSymptoms.adapter = symptomAdapter

        // Observe changes in the symptomsLiveData
        viewModel.symptomsLiveData.observe(this, { symptoms ->
            symptomAdapter.setItems(symptoms)
        })

        // Observe changes in the isButtonEnabled LiveData
        viewModel.isButtonEnabled.observe(this, { isEnabled ->
            val buttonShowMatches: Button = findViewById(R.id.buttonShowMatches)
            buttonShowMatches.isEnabled = isEnabled
            // Log statement to check the current value of _isButtonEnabled LiveData
            Log.d("MainActivity", "Current button state: $isEnabled")
        })


        // Fetch symptoms from the API
        viewModel.fetchSymptomsFromApi()


        // Handle "Show me my matches" button click
        val buttonShowMatches: Button = findViewById(R.id.buttonShowMatches)
        buttonShowMatches.setOnClickListener {
            // Get the selected symptom IDs from the ViewModel
            val selectedSymptomIds = viewModel.getSelectedSymptomIds()

            // Create an Intent to start SecondActivity
            val intent = Intent(this, SecondActivity::class.java)

            // Pass the selected symptom IDs as an extra to SecondActivity
            intent.putExtra(MainActivity.EXTRA_SELECTED_SYMPTOM_IDS, selectedSymptomIds.toIntArray())

            // Start SecondActivity
            startActivity(intent)
        }
    }
}
