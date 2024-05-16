package com.example.nopstationcart.view.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nopstationcart.R
import com.example.nopstationcart.model.data.bestSellingItem

class bestSellingAdapters(var bestSellingtArrayList: ArrayList<bestSellingItem>):RecyclerView.Adapter<bestSellingAdapters.MyViewHolder>(){

    class MyViewHolder(itemview:View):RecyclerView.ViewHolder(itemview){
        val tittle = itemview.findViewById<TextView>(R.id.bestSellingTittle)
        val img = itemview.findViewById<ImageView>(R.id.bestSellingImg)
        val price= itemView.findViewById<TextView>(R.id.bestSellingPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.best_selling_single_item,parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return bestSellingtArrayList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = bestSellingtArrayList[position]
        holder.tittle.text = currentItem.tittle
        holder.img.setImageResource(currentItem.imageRes)
        holder.price.text = currentItem.price
    }
}