package com.example.greengenie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class RecyclerActivity : AppCompatActivity(), itemadapter.OnItemClickListener{

    private val itemlist : MutableList<locations> = mutableListOf()


    private var realtimefirebasedb : FirebaseDatabase = FirebaseDatabase.getInstance()
    private var realdbfirebase = realtimefirebasedb.reference.child("Waste_Dispose_Locations")
    private lateinit var itemadapter : itemadapter

    lateinit var recycler : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)
        recycler = findViewById(R.id.recyclerv)
        var search = intent.getStringExtra("1").toString()
        Log.d("HOUSEHOLD","{$search}")

        createData(search)
    }
    fun createData(search : String){
        realdbfirebase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                itemlist.clear()
                for(eachuser in snapshot.children){
                    val user = eachuser.getValue(locations::class.java)
                    if(user!=null){
                        if(user.catogery==search) {
                            itemlist.add(user)
                        }
                        //Log.d("println",user.id.toString())
                    }
                    itemadapter = itemadapter(this@RecyclerActivity,itemlist,this@RecyclerActivity)
                    recycler.layoutManager = LinearLayoutManager(this@RecyclerActivity)
                    recycler.adapter = itemadapter


                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }

    override fun onItemClick(pos: String) {
       val intent = Intent(applicationContext,FeedBack_Activity::class.java)
        intent.putExtra("address",pos)
        startActivity(intent)
    }
}