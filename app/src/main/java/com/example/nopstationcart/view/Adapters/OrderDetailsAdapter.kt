package com.example.nopstationcart.view.Adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nopstationcart.R
import com.example.nopstationcart.Services.Interfaces.ItemClickListener
import com.example.nopstationcart.Services.Model.OrderDetailsItem

class OrderDetailsAdapter(
    private var orderDetails: List<OrderDetailsItem>
) : RecyclerView.Adapter<OrderDetailsAdapter.MyViewHolder>() {

    private lateinit var myListener: ItemClickListener

    fun setOnItemClick(listener: ItemClickListener) {
        myListener = listener
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateOrderDetails(newOrderDetails: List<OrderDetailsItem>) {
        orderDetails = newOrderDetails
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OrderDetailsAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.order_details_single_item, parent, false)
        return MyViewHolder(itemView, myListener)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(
        holder: OrderDetailsAdapter.MyViewHolder,
        position: Int
    ) {
        val currentItem = orderDetails[position]
        holder.orderId.text = "Order Date: ${currentItem.orderDate}"
        holder.totalProducts.text = "Total Amount: ${currentItem.orderTotal}"
        holder.userName.text = "Order Id: ${currentItem.orderId}"
        holder.userEmail.text = "Total Products: ${currentItem.totalProducts}"
    }

    override fun getItemCount(): Int {
        return orderDetails.size
    }

    class MyViewHolder(itemView: View, listener: ItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val userName: TextView = itemView.findViewById(R.id.OrderDetUserName)
        val userEmail: TextView = itemView.findViewById(R.id.OrderDetUserEmail)
        val orderId: TextView = itemView.findViewById(R.id.OrderDetOrderId)
        val totalProducts: TextView = itemView.findViewById(R.id.OrderDetTotallProducts)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }
}
