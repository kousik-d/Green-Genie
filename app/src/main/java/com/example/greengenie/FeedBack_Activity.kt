package com.example.greengenie

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.database.FirebaseDatabase

class FeedBack_Activity : AppCompatActivity() ,AdapterView.OnItemSelectedListener{
    lateinit var spinner : Spinner
    lateinit var submitbtn : Button
    lateinit var result_spinner : String
    private val CHANNEL_ID ="1"


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
            if(result_spinner=="70%-100%"){
                if(extra!=null) {
                    createNotification(extra)
                }
            }
            if(extra!=null) {
                search_data_in_db(extra)
            }
        }


    }

    private fun createNotification(extra: String) {
        val builder = NotificationCompat.Builder(this@FeedBack_Activity,CHANNEL_ID)

        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            val channel = NotificationChannel(CHANNEL_ID,"1",NotificationManager.IMPORTANCE_DEFAULT)
            val manager : NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            manager.createNotificationChannel(channel)

            builder.setSmallIcon(R.drawable.bell)
                .setContentTitle("Green Giene")
                .setContentText("The Bin is Full at Location -{$extra}")

        }else{

            builder.setSmallIcon(R.drawable.bell)
                .setContentTitle("title")
                .setContentText("The Bin is Full {$extra}")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        }
        val notificationManagercompat = NotificationManagerCompat.from(this@FeedBack_Activity)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }else {
            notificationManagercompat.notify(1, builder.build())
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