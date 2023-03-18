package com.example.greengenie.uiactivties

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.greengenie.MainActivity
import com.example.greengenie.R
import com.example.greengenie.RecyclerActivity
import com.example.greengenie.locations
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.FirebaseDatabase

class housholdActivity : AppCompatActivity() {

    lateinit var organicbtn : ImageView
    lateinit var recyablebtn : ImageView
    lateinit var toxicbtn : ImageView
    lateinit var electronicbtn : ImageView
    lateinit var searchedt : EditText
    lateinit var searchbtn : Button
    lateinit var menubtn : FloatingActionButton



    private var realtimefirebasedb : FirebaseDatabase = FirebaseDatabase.getInstance()
    private var realdbfirebase = realtimefirebasedb.reference.child("Users")




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_houshold)

        organicbtn = findViewById(R.id.organic)
        recyablebtn = findViewById(R.id.recyclable)
        toxicbtn = findViewById(R.id.toxic)
        electronicbtn = findViewById(R.id.electronic)
        searchedt=findViewById(R.id.searchET)
        searchbtn = findViewById(R.id.searchbutton)
        menubtn = findViewById(R.id.floatingActionButton)

        searchbtn.setOnClickListener {
            val searchres= searchedt.text.toString()
            val res = getWasteType(searchres)
            Toast.makeText(applicationContext,"Search Result ${res}",Toast.LENGTH_LONG).show()
        }
        menubtn.setOnClickListener {

            val intent = Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
        }
        organicbtn.setOnClickListener {
            val intent = Intent(applicationContext,RecyclerActivity::class.java);
            intent.putExtra("1","Organic")
            startActivity(intent)
        }
        recyablebtn.setOnClickListener {
            val intent = Intent(applicationContext,RecyclerActivity::class.java);
            intent.putExtra("1","Recycable")
            startActivity(intent)
        }
        toxicbtn.setOnClickListener {
            val intent = Intent(applicationContext,RecyclerActivity::class.java);
            intent.putExtra("1","Toxic")
            startActivity(intent)
        }
        electronicbtn.setOnClickListener {
            val intent = Intent(applicationContext,RecyclerActivity::class.java);
            intent.putExtra("1","Electronic")
            startActivity(intent)
        }
    }
    fun getWasteType(input: String): String {
        val organicWasteTypes = arrayOf("fruit", "vegetable", "meat", "eggshell", "coffee", "tea", "bread", "pasta", "nut", "oil", "cheese", "grass", "leaves", "twig", "branch", "log", "sawdust", "cardboard", "paper", "cotton", "wool", "hair")
        val recyclableWasteTypes = arrayOf("plastic", "metal", "glass", "paper", "cardboard")
        val electronicWasteTypes = arrayOf("computer", "phone", "tablet", "TV", "printer")
        val toxicWasteTypes = arrayOf("paint", "batteries", "pesticides", "cleaning")

        for (type in organicWasteTypes) {
            if (input.contains(type)) {
                return "Organic waste"
            }
        }

        for (type in recyclableWasteTypes) {
            if (input.contains(type)) {
                return "Recyclable waste"
            }
        }

        for (type in electronicWasteTypes) {
            if (input.contains(type)) {
                return "Electronic waste"
            }
        }

        for (type in toxicWasteTypes) {
            if (input.contains(type)) {
                return "Toxic waste"
            }
        }

        return "Unknown waste type"
    }
}