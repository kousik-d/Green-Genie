package com.example.greengenie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.FirebaseDatabase

class add_edit_locations : AppCompatActivity() {

    lateinit var addlocation :Button
    lateinit var editlocation : Button

    private var realtimedatabase = FirebaseDatabase.getInstance()
    private var rdbreferance = realtimedatabase.reference.child("Waste_Dispose_Locations");
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_locations)
        addlocation = findViewById(R.id.addlocation)
        addlocation.setOnClickListener {
            createDialog()
        }
    }

    private fun createDialog() {
        val dialogview = LayoutInflater.from(this).inflate(R.layout.add_data_locations, null)
        val builder = AlertDialog.Builder(this)
        with(builder) {
            setView(dialogview)
            setTitle("Location Detalis")
        }
        val alertdialog = builder.show()
        val addressed1 :EditText = dialogview.findViewById(R.id.addressEt)
        val streetedt : EditText = dialogview.findViewById(R.id.streetEt)
        val localityedt : EditText = dialogview.findViewById(R.id.LocalityEt)
        val catogeryedt : EditText = dialogview.findViewById(R.id.catogeryEt)
        val addbtn :Button = dialogview.findViewById(R.id.ADDbutton)
        addbtn.setOnClickListener {
            alertdialog.dismiss()
            val id = rdbreferance.push().key.toString()
            val location = locations(
                catogeryedt.text.toString(),addressed1.text.toString(),
                streetedt.text.toString(),localityedt.text.toString())
            rdbreferance.child(id).setValue(location).addOnCompleteListener {

                if(it.isSuccessful){
                    Toast.makeText(applicationContext,"Added",Toast.LENGTH_SHORT).show()
                }else{


                }

            }

        }






    }
}