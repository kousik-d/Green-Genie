package com.example.greengenie.uiactivties

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.*
import com.example.greengenie.R

class feedback : AppCompatActivity() , AdapterView.OnItemSelectedListener {

    private lateinit var reportSpinner: Spinner
    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)

        reportSpinner = findViewById(R.id.report_spinner)
        submitButton = findViewById(R.id.submit_button)

        ArrayAdapter.createFromResource(
            this,
            R.array.report_options,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            reportSpinner.adapter = adapter
        }


        reportSpinner.onItemSelectedListener = this

        submitButton.setOnClickListener {
            // Show a success message
            Toast.makeText(this, "Feedback submitted successfully", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val selectedItem = p0!!.getItemAtPosition(p2).toString()
        Toast.makeText(this, "Selected item: $selectedItem", Toast.LENGTH_SHORT).show()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

}