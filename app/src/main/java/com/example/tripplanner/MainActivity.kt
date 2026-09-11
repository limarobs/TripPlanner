package com.example.tripplanner

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var departureDateNumber = 0
    private var returnDateNumber = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val countrySpinner = findViewById<Spinner>(R.id.countrySpinner)
        val stateSpinner = findViewById<Spinner>(R.id.stateSpinner)
        val citySpinner = findViewById<Spinner>(R.id.citySpinner)
        val departureDateInput = findViewById<EditText>(R.id.departureDateInput)
        val returnDateInput = findViewById<EditText>(R.id.returnDateInput)
        val continueButton = findViewById<Button>(R.id.continueButton)

        val countries = arrayOf("Selecione o país", "Brasil", "Portugal")

        fun setSpinnerOptions(spinner: Spinner, options: Array<String>) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, options)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        fun getStatesForCountry(countryName: String): Array<String> {
            return when (countryName) {
                "Brasil" -> arrayOf(
                    "Selecione o estado",
                    "São Paulo - SP",
                    "Rio de Janeiro - RJ",
                    "Rio Grande do Sul - RS"
                )
                "Portugal" -> arrayOf("Selecione o estado", "Lisboa", "Porto")
                else -> arrayOf("Escolha primeiro o país")
            }
        }

        fun getCitiesForState(stateName: String): Array<String> {
            return when (stateName) {
                "São Paulo - SP" -> arrayOf("Selecione a cidade", "São Paulo", "Campinas")
                "Rio de Janeiro - RJ" -> arrayOf("Selecione a cidade", "Rio de Janeiro", "Niterói")
                "Rio Grande do Sul - RS" -> arrayOf("Selecione a cidade", "Porto Alegre", "Santiago")
                "Lisboa" -> arrayOf("Selecione a cidade", "Lisboa", "Sintra")
                "Porto" -> arrayOf("Selecione a cidade", "Porto", "Vila Nova de Gaia")
                else -> arrayOf("Escolha primeiro o estado")
            }
        }

        setSpinnerOptions(countrySpinner, countries)
        setSpinnerOptions(stateSpinner, getStatesForCountry(""))
        setSpinnerOptions(citySpinner, getCitiesForState(""))
        stateSpinner.isEnabled = false
        citySpinner.isEnabled = false

        countrySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                setSpinnerOptions(stateSpinner, getStatesForCountry(countrySpinner.selectedItem.toString()))
                setSpinnerOptions(citySpinner, getCitiesForState(""))
                stateSpinner.isEnabled = position != 0
                citySpinner.isEnabled = false
            }

            override fun onNothingSelected(parent: AdapterView<*>) = Unit
        }

        stateSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                setSpinnerOptions(citySpinner, getCitiesForState(stateSpinner.selectedItem.toString()))
                citySpinner.isEnabled = position != 0
            }

            override fun onNothingSelected(parent: AdapterView<*>) = Unit
        }

        departureDateInput.setOnClickListener {
            val calendar = java.util.Calendar.getInstance()

            DatePickerDialog(
                this,
                { _, year, month, day ->
                    departureDateNumber = year * 10000 + (month + 1) * 100 + day
                    departureDateInput.setText(String.format("%02d/%02d/%d", day, month + 1, year))
                },
                calendar.get(java.util.Calendar.YEAR),
                calendar.get(java.util.Calendar.MONTH),
                calendar.get(java.util.Calendar.DAY_OF_MONTH)
            ).show()
        }

        returnDateInput.setOnClickListener {
            val calendar = java.util.Calendar.getInstance()

            DatePickerDialog(
                this,
                { _, year, month, day ->
                    returnDateNumber = year * 10000 + (month + 1) * 100 + day
                    returnDateInput.setText(String.format("%02d/%02d/%d", day, month + 1, year))
                },
                calendar.get(java.util.Calendar.YEAR),
                calendar.get(java.util.Calendar.MONTH),
                calendar.get(java.util.Calendar.DAY_OF_MONTH)
            ).show()
        }

        continueButton.setOnClickListener {
            if (countrySpinner.selectedItemPosition == 0 ||
                stateSpinner.selectedItemPosition == 0 ||
                citySpinner.selectedItemPosition == 0
            ) {
                Toast.makeText(this, "Preencha país, estado e cidade", Toast.LENGTH_SHORT).show()
            } else if (departureDateNumber == 0 || returnDateNumber == 0) {
                Toast.makeText(this, "Escolha as duas datas", Toast.LENGTH_SHORT).show()
            } else if (returnDateNumber < departureDateNumber) {
                Toast.makeText(this, "A volta deve ser depois da ida", Toast.LENGTH_SHORT).show()
            } else {
                val destination = "${citySpinner.selectedItem} - ${stateSpinner.selectedItem}, ${countrySpinner.selectedItem}"
                val activityIntent = Intent(this, ActivitiesActivity::class.java)

                activityIntent.putExtra("destination", destination)
                activityIntent.putExtra("departureDate", departureDateInput.text.toString())
                activityIntent.putExtra("returnDate", returnDateInput.text.toString())

                startActivity(activityIntent)
            }
        }
    }
}
