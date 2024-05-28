package com.example.nopstationcart.view.Adapters

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nopstationcart.R
import com.example.nopstationcart.Services.Model.Remove_Cart.FormValue
import com.example.nopstationcart.Services.Model.Remove_Cart.RemoveCartRequest
import com.example.nopstationcart.Services.Model.ShoppingCart.productCartItems
import com.example.nopstationcart.viewmodel.RemoveCartViewModel

class productCartAdapter(private val itemList: MutableList<productCartItems> , private val viewModel: RemoveCartViewModel): RecyclerView.Adapter<productCartAdapter.MyViewHolder>() {


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.productCartTittle)
        val img: ImageView = itemView.findViewById(R.id.productCartImg)
        val price: TextView = itemView.findViewById(R.id.productCartPrice)
        val quantity:TextView = itemView.findViewById(R.id.CartQuantity)
        val cartRemoveBtn:ImageView =itemView.findViewById(R.id.CartRemoveBtn)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): productCartAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.single_item_product_cart, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: productCartAdapter.MyViewHolder, position: Int) {
        val item = itemList[position]
        holder.title.text = itemList[position].tittle
        holder.price.text = itemList[position].price
        holder.quantity.text = itemList[position].quantity.toString()
        Glide.with(holder.itemView.context)
            .load(itemList[position].imageResID)
            .into(holder.img)

        val productID = item.productId
        val form = FormValue("removefromcart",productID.toString())
        val request = RemoveCartRequest(listOf(form))

        holder.cartRemoveBtn.setOnClickListener {
            viewModel.getTheCartRemoved(request)
            viewModel.response.observeForever {result->
                result.onSuccess {
                    itemList.removeAt(position)
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position,itemCount)
                }.onFailure {
                    Toast.makeText(holder.itemView.context, "Failed to remove item",Toast.LENGTH_LONG).show()
                }
            }
            println("${item.tittle} is clicked")
        }

    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}