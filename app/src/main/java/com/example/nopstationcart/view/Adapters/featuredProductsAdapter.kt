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
import com.example.nopstationcart.model.interfaces.featuredProductsItemClickListener
import com.example.nopstationcart.model.interfaces.womenHeelOnItemClickListener

class featuredProductsAdapter(var featuredProducts:ArrayList<featuredProductsItem>):RecyclerView.Adapter<featuredProductsAdapter.MyViewHolder>() {


    lateinit var myListener: featuredProductsItemClickListener

    fun setOnItemClick(listener: featuredProductsItemClickListener){
        myListener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): featuredProductsAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.featured_products_single_item,parent,false)
        return featuredProductsAdapter.MyViewHolder(itemView,myListener)
    }

    override fun onBindViewHolder(holder: featuredProductsAdapter.MyViewHolder, position: Int) {
        val currentItem = featuredProducts[position]
        holder.img.setImageResource(currentItem.imgRes)
        holder.price.text = currentItem.price
        holder.ratting.rating = currentItem.rating
        holder.tittle.text = currentItem.tittle
    }

    override fun getItemCount(): Int {
        return featuredProducts.size
    }

    class MyViewHolder(itemview:View, listener:featuredProductsItemClickListener):RecyclerView.ViewHolder(itemview){
        val tittle = itemview.findViewById<TextView>(R.id.featuredTittle)
        val img = itemview.findViewById<ImageView>(R.id.featuredImg)
        val price = itemview.findViewById<TextView>(R.id.featuredPrice)
        val ratting = itemview.findViewById<RatingBar>(R.id.featuredRatingBar)

        init {
            itemview.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

}