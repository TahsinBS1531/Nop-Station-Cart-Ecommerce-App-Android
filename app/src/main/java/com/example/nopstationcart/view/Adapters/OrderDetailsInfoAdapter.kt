package com.example.nopstationcart.view.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nopstationcart.R
import com.example.nopstationcart.Services.Model.CategoryList.CategorySingleItem
import com.example.nopstationcart.Services.Model.ShoppingCart.productCartItems
import com.example.nopstationcart.databinding.SingleItemOrderDetailsInfoBinding

class OrderDetailsInfoAdapter(
    private val productsList: Array<productCartItems>
) : RecyclerView.Adapter<OrderDetailsInfoAdapter.CategoryViewHolder>() {

    lateinit var binding: SingleItemOrderDetailsInfoBinding

    interface OnItemClickListener {
        fun onItemClick(product: productCartItems)
    }

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImg = binding.orderDetailsInfoImg
        val productTitle = binding.orderDetailsInforTitle
        val productPrice = binding.orderDetailsInfoProductPrice
        val productOldPrice = binding.orderDetailsInfoOldPrice
        val orderTotal = binding.orderDeatilsInfoRatingBar

//        init {
//            itemView.setOnClickListener {
//                listener.onItemClick(productsList[adapterPosition])
//            }
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        binding = SingleItemOrderDetailsInfoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_item_recycle1, parent, false)
        return CategoryViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val products = productsList[position]
        holder.productTitle.text = products.tittle
        holder.productPrice.text = products.price
        holder.productOldPrice.text = "Quantity - ${products.quantity}"
        val subtotal = products.orderTotal?:0.0
        holder.orderTotal.text = "Order Total : ${subtotal}"

        Glide.with(holder.itemView.context)
            .load(products.imageResID)
            .into(holder.productImg)
    }

    override fun getItemCount(): Int = productsList.size
}
