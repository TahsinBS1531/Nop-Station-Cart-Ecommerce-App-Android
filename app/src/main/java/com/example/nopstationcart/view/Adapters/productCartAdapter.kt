package com.example.nopstationcart.view.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nopstationcart.R
import com.example.nopstationcart.Services.Model.ShoppingCart.productCartItems

class productCartAdapter(private val itemList: List<productCartItems> ): RecyclerView.Adapter<productCartAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.productCartTittle)
        val img: ImageView = itemView.findViewById(R.id.productCartImg)
        val price: TextView = itemView.findViewById(R.id.productCartPrice)
        val quantity:TextView = itemView.findViewById(R.id.CartQuantity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): productCartAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.single_item_product_cart, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: productCartAdapter.MyViewHolder, position: Int) {
        holder.title.text = itemList[position].tittle
        holder.price.text = itemList[position].price
        holder.quantity.text = itemList[position].quantity.toString()
        Glide.with(holder.itemView.context)
            .load(itemList[position].imageResID)
            .into(holder.img)

    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}