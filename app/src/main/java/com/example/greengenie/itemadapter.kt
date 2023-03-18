package com.example.greengenie

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.greengenie.uiactivties.feedback

class itemadapter(var context : Context, val itemlist :MutableList<locations>): RecyclerView.Adapter<itemadapter.itemviewHolder>() {

    inner class itemviewHolder(itemview: View): RecyclerView.ViewHolder(itemview){
        val loc : TextView = itemview.findViewById(R.id.text1)
        val street : TextView = itemview.findViewById(R.id.text2)
        val address : TextView = itemview.findViewById(R.id.text3)
        val catogery : TextView = itemview.findViewById(R.id.text4)
        val feedbackbtn : Button = itemview.findViewById(R.id.feedbackbtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemviewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.item_activity,parent,false)
        return itemviewHolder(item)
    }

    override fun getItemCount() = itemlist.size

    override fun onBindViewHolder(holder: itemviewHolder, position: Int) {
        val curr = itemlist[position]
        holder.loc.text = curr.locality.toString()
        holder.street.text = curr.street.toString()
        holder.address.text = curr.address.toString()
        holder.catogery.text = curr.catogery.toString()


    }
}