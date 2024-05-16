package com.example.nopstationcart.view.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nopstationcart.R
import com.example.nopstationcart.model.data.featuredProductsItem
import com.example.nopstationcart.model.data.womenHeelItems

class womenHeelAdapter(var womenHeelArrayList:ArrayList<womenHeelItems>):RecyclerView.Adapter<womenHeelAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): womenHeelAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.women_heel_single_item,parent,false)
        return womenHeelAdapter.MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: womenHeelAdapter.MyViewHolder, position: Int) {
        val currentItem = womenHeelArrayList[position]
        holder.img.setImageResource(currentItem.imgRes)
        holder.price.text = currentItem.price
        holder.ratting.rating = currentItem.rating
        holder.tittle.text = currentItem.tittle
    }

    override fun getItemCount(): Int {
        return womenHeelArrayList.size
    }

    class MyViewHolder(itemview:View):RecyclerView.ViewHolder(itemview){
        val tittle = itemview.findViewById<TextView>(R.id.womenHeeltitle)
        val img = itemview.findViewById<ImageView>(R.id.womenHeelImg)
        val price = itemview.findViewById<TextView>(R.id.womenHeelPrice)
        val ratting = itemview.findViewById<RatingBar>(R.id.womenheelRating)
    }

}