package com.example.greengenie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.firebase.database.FirebaseDatabase

class FeedBack_Activity : AppCompatActivity() ,AdapterView.OnItemSelectedListener{
    lateinit var spinner : Spinner
    lateinit var submitbtn : Button
    lateinit var result_spinner : String


    private var firebasedb = FirebaseDatabase.getInstance()
    private var realdb = firebasedb.reference.child("Waste_Dispose_Locations")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed_back)
        spinner = findViewById(R.id.report_spinner)
        submitbtn = findViewById(R.id.submit_button)

        var arrayadapter = ArrayAdapter.createFromResource(
            this,R.array.report_options,android.R.layout.simple_spinner_item
        )

        arrayadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayadapter

        spinner.onItemSelectedListener = this
        val extra = intent.getStringExtra("address")
        submitbtn.setOnClickListener {
            if(extra!=null) {
                search_data_in_db(extra)
            }
        }

    }

    private fun search_data_in_db(extra: String) {
        realdb.child(extra).get().addOnSuccessListener {
            if(it.exists()){
                val loc = locations(
                    it.child("catogery").value.toString(),
                    it.child("address").value.toString(),
                    it.child("street").value.toString(),
                    it.child("locality").value.toString(),
                    result_spinner
                )
                add_new_data_to_db(loc,extra)
            }
        }
    }

    private fun add_new_data_to_db(loc: locations, extra: String) {

        realdb.child(extra).removeValue().addOnCompleteListener {

            if(!it.isSuccessful){
                Toast.makeText(applicationContext,"Please Try Again",Toast.LENGTH_SHORT).show()
            }
        }
        realdb.child(extra).setValue(loc).addOnCompleteListener {

            if(it.isSuccessful){
                Toast.makeText(applicationContext,"Thank you",Toast.LENGTH_LONG).show()
            }
        }

    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        if(p0!=null) {
            result_spinner = p0.getItemAtPosition(p2).toString()
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }
}