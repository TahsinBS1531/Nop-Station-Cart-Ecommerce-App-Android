package com.example.nopstationcart.view.Single_Category_Page

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nopstationcart.R
import com.example.nopstationcart.model.data.categoryDetailsItem
import com.example.nopstationcart.model.interfaces.categoryDetailsOnClicklistener

class CategoryDetailsAdapter(
    private val itemList: List<categoryDetailsItem>
) : RecyclerView.Adapter<CategoryDetailsAdapter.ViewHolder>() {

    lateinit var myListener: categoryDetailsOnClicklistener
    fun setOnItemClick(listener:categoryDetailsOnClicklistener){
        myListener = listener
    }

    class ViewHolder(itemView: View, listener: categoryDetailsOnClicklistener) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.categoryDetailsTittle)
        val imageView: ImageView = itemView.findViewById(R.id.categoryDetailsImg)
        val itemPrice:TextView = itemView.findViewById(R.id.categoryDetailsPrice)
        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.category_details_single_item, parent, false)
        return ViewHolder(itemView,myListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.titleTextView.text = item.tittle
        holder.imageView.setImageResource(item.imageResID)
        holder.itemPrice.text = item.itemPrice
    }

    override fun getItemCount(): Int = itemList.size
}
