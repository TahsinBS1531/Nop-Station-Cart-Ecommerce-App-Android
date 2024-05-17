package com.example.nopstationcart.view.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nopstationcart.R
import com.example.nopstationcart.model.data.CategoryItem
import com.example.nopstationcart.model.data.singleCategoryItem
import com.example.nopstationcart.model.interfaces.onItemClickListener

class singleCategoryListAdapter(var categoryArrayList: ArrayList<singleCategoryItem>) :
    RecyclerView.Adapter<singleCategoryListAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.category_single_item,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem  = categoryArrayList[position]
        holder.tittle.text = currentItem.text
        holder.img.setImageResource(currentItem.imageRes)
    }

    override fun getItemCount(): Int {
        return categoryArrayList.size
    }

    class MyViewHolder(itemview:View):RecyclerView.ViewHolder(itemview){
        val tittle = itemview.findViewById<TextView>(R.id.singleCategoryTittle2)
        val img = itemview.findViewById<ImageView>(R.id.singleCategoryImg2)
    }

}