package com.example.nopstationcart.view.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nopstationcart.R
import com.example.nopstationcart.model.data.bestSellingItem
import com.example.nopstationcart.model.interfaces.bestSellingProductsItemClick

class bestSellingAdapters(var bestSellingtArrayList: ArrayList<bestSellingItem>):RecyclerView.Adapter<bestSellingAdapters.MyViewHolder>(){

    lateinit var myListener: bestSellingProductsItemClick
    fun setOnItemClick(listener:bestSellingProductsItemClick){
        myListener = listener
    }

    class MyViewHolder(itemview:View, listener:bestSellingProductsItemClick):RecyclerView.ViewHolder(itemview){
        val tittle = itemview.findViewById<TextView>(R.id.bestSellingTittle)
        val img = itemview.findViewById<ImageView>(R.id.bestSellingImg)
        val price= itemView.findViewById<TextView>(R.id.bestSellingPrice)
        init {
            itemview.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.best_selling_single_item,parent,false)
        return MyViewHolder(itemView,myListener)
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