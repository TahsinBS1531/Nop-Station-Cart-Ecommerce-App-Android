package com.example.nopstationcart.view.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nopstationcart.R
import com.example.nopstationcart.Services.Interfaces.OnCartClickListener
import com.example.nopstationcart.Services.Model.ShoppingCart.productCartItems
import com.example.nopstationcart.view.Product_Shopping_Cart.RemoveCartViewModel

class productCartAdapter(private val itemList: MutableList<productCartItems> , private val viewModel: RemoveCartViewModel): RecyclerView.Adapter<productCartAdapter.MyViewHolder>() {



    lateinit var myListener: OnCartClickListener

    fun setOnItemClick(listener: OnCartClickListener){
        myListener = listener
    }
    inner class MyViewHolder(itemView: View, listener: OnCartClickListener) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.productCartTittle)
        val img: ImageView = itemView.findViewById(R.id.productCartImg)
        val price: TextView = itemView.findViewById(R.id.productCartPrice)
        val quantity:TextView = itemView.findViewById(R.id.CartQuantity)
        val cartRemoveBtn:ImageView =itemView.findViewById(R.id.CartRemoveBtn)
        val decreaseCart:CardView = itemView.findViewById(R.id.decreaseCart)
        val increaseCart:CardView = itemView.findViewById(R.id.increaseCart)

        init {
            decreaseCart.setOnClickListener {
                val productId = itemList[adapterPosition].productId.toString()
                val newQuantity = itemList[adapterPosition].quantity.toInt() - 1
                if (newQuantity >= 0) {
                    listener.onDecreasecart(adapterPosition,productId,newQuantity.toString())
                    if(newQuantity!=0){
                        itemList[adapterPosition].quantity = newQuantity
                        quantity.text = newQuantity.toString()
                    }
                    notifyItemChanged(adapterPosition)
                }
            }
            increaseCart.setOnClickListener {
                val productId = itemList[adapterPosition].productId.toString()
                val newQuantity = itemList[adapterPosition].quantity + 1
                listener.onIncreaseCart(adapterPosition,productId,newQuantity.toString())

                itemList[adapterPosition].quantity = newQuantity
                quantity.text = newQuantity.toString()
                notifyItemChanged(adapterPosition)
            }
            cartRemoveBtn.setOnClickListener {
                listener.onRemoveCart(adapterPosition,itemList[adapterPosition].productId.toString())
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): productCartAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.single_item_product_cart, parent, false)
        return MyViewHolder(itemView,myListener)
    }

    override fun onBindViewHolder(holder: productCartAdapter.MyViewHolder, position: Int) {
        val item = itemList[position]
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