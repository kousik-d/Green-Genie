package com.example.greengenie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.FirebaseDatabase

class add_edit_locations : AppCompatActivity() ,AdapterView.OnItemSelectedListener{

    lateinit var addlocation :Button

    lateinit var res : String
    lateinit var arrayAdapter: ArrayAdapter<CharSequence>

    private var realtimedatabase = FirebaseDatabase.getInstance()
    private var rdbreferance = realtimedatabase.reference.child("Waste_Dispose_Locations");
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_locations)
        createarrayAdpt()
        addlocation = findViewById(R.id.addlocation)
        val editloc = findViewById<Button>(R.id.editlocation)
        editloc.setOnClickListener {
            val intent= Intent(applicationContext,RecyclerActivity::class.java)
            intent.putExtra("1","None")
            startActivity(intent)
        }

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
        val catogeryedt : Spinner = dialogview.findViewById(R.id.catogery)

        catogeryedt.onItemSelectedListener = this

        catogeryedt.adapter = arrayAdapter

        val addbtn :Button = dialogview.findViewById(R.id.ADDbutton)
        addbtn.setOnClickListener {
            alertdialog.dismiss()
            if(addressed1.text.isNotEmpty() && streetedt.text.isNotEmpty() && localityedt.text.isNotEmpty()) {
                val id = addressed1.text.toString()
                val location = locations(
                    res, addressed1.text.toString(),
                    streetedt.text.toString(), localityedt.text.toString(), "0%"
                )
                rdbreferance.child(id).setValue(location).addOnCompleteListener {

                    if (it.isSuccessful) {
                        Toast.makeText(applicationContext, "Added", Toast.LENGTH_SHORT).show()
                    } else {


                    }

                }
            }
        }
    }

    private fun createarrayAdpt() {
        arrayAdapter = ArrayAdapter.createFromResource(this,
            R.array.waste_category,
            android.R.layout.simple_spinner_item
        )
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        if(p0!=null){
            res = p0.getItemAtPosition(p2).toString()
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }
}