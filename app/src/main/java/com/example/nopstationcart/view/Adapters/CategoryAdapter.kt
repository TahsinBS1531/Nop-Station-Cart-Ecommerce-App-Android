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

class CategoryAdapter(
    private val categoryList: List<CategorySingleItem>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(category: CategorySingleItem)
    }

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryName: TextView = itemView.findViewById(R.id.categoryTxt)
        val categoryImage: ImageView = itemView.findViewById(R.id.categoryImg)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(categoryList[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_item_recycle1, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categoryList[position]
        holder.categoryName.text = category.tittle
        // Use an image loading library like Glide or Picasso
        Glide.with(holder.itemView.context)
            .load(category.imageRes)
            .into(holder.categoryImage)
    }

    override fun getItemCount(): Int = categoryList.size
}
