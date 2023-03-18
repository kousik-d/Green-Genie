package com.example.greengenie.uiactivties

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.greengenie.Householdlogin
import com.example.greengenie.R
import com.example.greengenie.garbagelogin

class welcomeScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_screen)
        val householdbtn : Button = findViewById(R.id.Householdbtn)
        val garabge : Button = findViewById(R.id.garabgecollect)
        householdbtn.setOnClickListener {

            val intent = Intent(applicationContext,Householdlogin::class.java)
            startActivity(intent)

        }
        garabge.setOnClickListener {

            val intent = Intent(applicationContext, garbagelogin::class.java)
            startActivity(intent)

        }

    }
}