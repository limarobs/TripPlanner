package com.example.tripplanner

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ActivitiesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activities)

        val destination = intent.getStringExtra("destination")
        val departureDate = intent.getStringExtra("departureDate")
        val returnDate = intent.getStringExtra("returnDate")

        val sightseeingCheckBox = findViewById<CheckBox>(R.id.sightseeingCheckBox)
        val museumCheckBox = findViewById<CheckBox>(R.id.museumCheckBox)
        val restaurantCheckBox = findViewById<CheckBox>(R.id.restaurantCheckBox)
        val beachCheckBox = findViewById<CheckBox>(R.id.beachCheckBox)
        val tripSummaryText = findViewById<TextView>(R.id.tripSummaryText)
        val summaryButton = findViewById<Button>(R.id.summaryButton)

        tripSummaryText.text = "$destination\n$departureDate até $returnDate"

        summaryButton.setOnClickListener {
            val selectedActivities = mutableListOf<String>()

            if (sightseeingCheckBox.isChecked) selectedActivities.add("Passeio turístico")
            if (museumCheckBox.isChecked) selectedActivities.add("Visitar museu")
            if (restaurantCheckBox.isChecked) selectedActivities.add("Ir a restaurantes")
            if (beachCheckBox.isChecked) selectedActivities.add("Visitar praia")

            val confirmationIntent = Intent(this, ConfirmationActivity::class.java)

            confirmationIntent.putExtra("destination", destination)
            confirmationIntent.putExtra("departureDate", departureDate)
            confirmationIntent.putExtra("returnDate", returnDate)
            confirmationIntent.putExtra("activities", selectedActivities.joinToString("\n"))

            startActivity(confirmationIntent)
        }
    }
}
