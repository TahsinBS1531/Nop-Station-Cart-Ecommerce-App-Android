package com.example.nopstationcart.view.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nopstationcart.R
import com.example.nopstationcart.Services.Model.featuredProductsItem
import com.example.nopstationcart.Services.Interfaces.featuredProductsItemClickListener
import com.example.nopstationcart.Services.Model.Home_Page.Featured_Products.featuredProductsItem2

class featuredProductsAdapter(var featuredProducts:ArrayList<featuredProductsItem2>):RecyclerView.Adapter<featuredProductsAdapter.MyViewHolder>() {


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
       //holder.img.setImageURI(currentItem.image)
        holder.price.text = currentItem.price
        holder.tittle.text = currentItem.tittle
        holder.ratting.rating = currentItem.rating
        Glide.with(holder.itemView.context)
            .load(currentItem.image)
            .into(holder.img)
    }

    override fun getItemCount(): Int {
        return featuredProducts.size
    }

    class MyViewHolder(itemview:View, listener: featuredProductsItemClickListener):RecyclerView.ViewHolder(itemview){
        val tittle = itemview.findViewById<TextView>(R.id.featuredTittle)
        val img = itemview.findViewById<ImageView>(R.id.featuredImg)
        val price = itemview.findViewById<TextView>(R.id.featuredPrice)
        val ratting = itemview.findViewById<RatingBar>(R.id.featuredRatingBar)
        val addToCart = itemview.findViewById<ImageButton>(R.id.featuredProductsAddToCart)

        init {
            itemview.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
            addToCart.setOnClickListener{
                listener.onCartBtnClick(adapterPosition)
            }
        }
    }

}