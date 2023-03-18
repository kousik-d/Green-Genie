package com.example.greengenie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.greengenie.uiactivties.housholdActivity
import com.google.firebase.auth.FirebaseAuth

class Householdlogin : AppCompatActivity() {
    lateinit var householdnameedt : EditText
    lateinit var householdpassedt : EditText

    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_householdlogin)
        householdnameedt = findViewById(R.id.emailEt)
        householdpassedt = findViewById(R.id.passET)
        val signin : Button = findViewById(R.id.signinbutton)

        firebaseAuth = FirebaseAuth.getInstance()

        signin.setOnClickListener {
            var email = householdnameedt.text.toString()
            val pass = householdpassedt.text.toString()

            email = email+"@gmail.com"

            if(email.isNotEmpty() && pass.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if(it.isSuccessful) {
                        val intent = Intent(applicationContext, housholdActivity::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(applicationContext,"Invalid Credentials",Toast.LENGTH_LONG).show()
                    }
                }
            }else{
                Toast.makeText(applicationContext,"Invalid credentials",Toast.LENGTH_SHORT).show()

            }

        }

    }
}