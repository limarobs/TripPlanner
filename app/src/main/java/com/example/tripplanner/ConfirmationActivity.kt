package com.example.tripplanner

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ConfirmationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)

        val destination = intent.getStringExtra("destination")
        val departureDate = intent.getStringExtra("departureDate")
        val returnDate = intent.getStringExtra("returnDate")
        val activities = intent.getStringExtra("activities")

        val destinationText = findViewById<TextView>(R.id.destinationText)
        val dateText = findViewById<TextView>(R.id.dateText)
        val activitiesText = findViewById<TextView>(R.id.activitiesText)

        destinationText.text = "Destino: $destination"
        dateText.text = "Período: $departureDate até $returnDate"
        activitiesText.text = if (activities.isNullOrEmpty()) {
            "Atividades: nenhuma atividade escolhida"
        } else {
            "Atividades:\n$activities"
        }
    }
}
