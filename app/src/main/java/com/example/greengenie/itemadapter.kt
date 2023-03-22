package com.example.greengenie

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class itemadapter(var context : Context, val itemlist :MutableList<locations>,
                  val lisetner : OnItemClickListener
): RecyclerView.Adapter<itemadapter.itemviewHolder>() {

    inner class itemviewHolder(itemview: View): RecyclerView.ViewHolder(itemview),View.OnClickListener{
        val loc : TextView = itemview.findViewById(R.id.text1)
        val street : TextView = itemview.findViewById(R.id.text2)
        val address : TextView = itemview.findViewById(R.id.text3)
        val catogery : TextView = itemview.findViewById(R.id.text4)
        val fill : TextView = itemview.findViewById(R.id.text5)
        init {
            itemview.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val pos = address.text
            lisetner.onItemClick(pos.toString())
        }

    }
    interface OnItemClickListener{
        fun onItemClick(pos:String)
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
        holder.fill.text = curr.fill_percent
    }



}