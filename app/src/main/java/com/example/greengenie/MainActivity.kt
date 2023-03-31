package com.example.greengenie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    lateinit var exit : FloatingActionButton
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        firebaseAuth = FirebaseAuth.getInstance()
        exit = findViewById(R.id.floatingActionButton2)

        exit.setOnClickListener {
            userlogout()
        }

    }

    private fun userlogout() {
            if(firebaseAuth.currentUser != null){
                firebaseAuth.signOut()
                val intent = Intent(applicationContext,Householdlogin::class.java)
                startActivity(intent)
            }
    }
}