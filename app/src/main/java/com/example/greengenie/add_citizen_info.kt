package com.example.greengenie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class add_citizen_info : AppCompatActivity() {

    lateinit var addhouse : Button
    lateinit var currentHouse : Button

    lateinit var firebaseAuth: FirebaseAuth



    private var realtimedatabase = FirebaseDatabase.getInstance()
    private var rdbreferance = realtimedatabase.reference.child("Citizens_data");
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_citizen_info)
        addhouse = findViewById(R.id.addhouse)
        currentHouse = findViewById(R.id.currenthouse)
        firebaseAuth = FirebaseAuth.getInstance()

        addhouse.setOnClickListener {

            createDialog()
        }
    }

    private fun createDialog() {
        val dialogview = LayoutInflater.from(this).inflate(R.layout.citizen_registration_alert, null)
        val builder = AlertDialog.Builder(this)
        with(builder) {
            setView(dialogview)
            setTitle("Citizen Detalis")
        }
        val alertdialog = builder.show()
        val caddress : EditText = dialogview.findViewById(R.id.citizenaddressEt)
        var cname = dialogview.findViewById<EditText>(R.id.citizenNameEt)
        val cpass = dialogview.findViewById<EditText>(R.id.citizenpassEt)
        val cstreet = dialogview.findViewById<EditText>(R.id.citizenstreetEt)
        val clocality = dialogview.findViewById<EditText>(R.id.citizenLocalityEt)
        val addbtn = dialogview.findViewById<Button>(R.id.ADDcitizenbutton)


        addbtn.setOnClickListener {
            alertdialog.dismiss()
            val id = rdbreferance.push().key.toString()
            val citizen = citizens(cname.text.toString(),cpass.text.toString(),caddress.text.toString(),cstreet.text.toString(),clocality.text.toString())
            createlogin(cname.text.toString(),cpass.text.toString())
            rdbreferance.child(id).setValue(citizen).addOnCompleteListener {

                if(it.isSuccessful){
                    Toast.makeText(applicationContext,"Added", Toast.LENGTH_SHORT).show()
                }else{

                }
            }
        }

    }

    private fun createlogin(cemail: String, cpassword: String) {
        var signup_email = cemail + "@gmail.com"
        var signup_password = cpassword

        firebaseAuth.createUserWithEmailAndPassword(signup_email,signup_password).addOnCompleteListener {

            if(it.isSuccessful){
                Toast.makeText(applicationContext,"Login's Created",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,it.exception.toString(), Toast.LENGTH_LONG).show()
            }
        }
    }


}