package com.example.greengenie.uiactivties

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.greengenie.R
import com.example.greengenie.add_citizen_info
import com.example.greengenie.add_edit_locations

class garbageActivity : AppCompatActivity() {


    lateinit var add_edit_location : Button
    lateinit var add_citizen : Button
    lateinit var discount_managemnt : Button
    lateinit var total_analytics : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_garbage)

        add_edit_location = findViewById(R.id.location)
        add_citizen = findViewById(R.id.citizen)
        discount_managemnt = findViewById(R.id.discount)
        total_analytics = findViewById(R.id.analytics)

        add_edit_location.setOnClickListener {
            val intent = Intent(applicationContext,add_edit_locations::class.java)
            startActivity(intent)

        }
        add_citizen.setOnClickListener{
            val intent = Intent(applicationContext, add_citizen_info::class.java)
            startActivity(intent)
        }
    }
}