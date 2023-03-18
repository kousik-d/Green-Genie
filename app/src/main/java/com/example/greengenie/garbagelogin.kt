package com.example.greengenie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.greengenie.uiactivties.garbageActivity
import com.example.greengenie.uiactivties.housholdActivity
import com.google.firebase.auth.FirebaseAuth

class garbagelogin : AppCompatActivity() {

    lateinit var garbagename : EditText
    lateinit var garbageempid : EditText
    lateinit var signin : Button
    lateinit var firebaseAuth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_garbagelogin)

        signin = findViewById(R.id.gbsigninbutton)
        garbagename = findViewById(R.id.garbageEt)
        garbageempid = findViewById(R.id.garbagepassET)

        firebaseAuth = FirebaseAuth.getInstance()
        signin.setOnClickListener {
            var email = garbagename.text.toString()
            val pass = garbageempid.text.toString()

            email = email+"@gmail.com"

            if(email.isNotEmpty() && pass.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if(it.isSuccessful) {
                        val intent = Intent(applicationContext, garbageActivity::class.java)
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