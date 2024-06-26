package com.example.nopstationcart.view.Single_Category_Page

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
import com.example.nopstationcart.Services.Interfaces.categoryDetailsOnClicklistener
import com.example.nopstationcart.Services.Model.CategoryList.Product

class CategoryDetailsAdapter(
    private val itemList: List<Product>
) : RecyclerView.Adapter<CategoryDetailsAdapter.ViewHolder>() {

    lateinit var myListener: categoryDetailsOnClicklistener
    fun setOnItemClick(listener: categoryDetailsOnClicklistener){
        myListener = listener
    }

    class ViewHolder(itemView: View, listener: categoryDetailsOnClicklistener) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.categoryDetailsTittle)
        val imageView: ImageView = itemView.findViewById(R.id.categoryDetailsImg)
        val itemPrice:TextView = itemView.findViewById(R.id.categoryDetailsPrice)
        val rating:RatingBar = itemView.findViewById(R.id.categoryRatingBar)
        val cartBtn:ImageButton = itemView.findViewById(R.id.categoryDetailsCartBtn)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
            cartBtn.setOnClickListener {
                listener.onCartBtnClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.category_details_single_item, parent, false)
        return ViewHolder(itemView,myListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]

        holder.titleTextView.text = item.Name
        Glide.with(holder.itemView.context)
            .load(item.PictureModels[0].FullSizeImageUrl)
            .into(holder.imageView)
        holder.itemPrice.text = item.ProductPrice.Price
        val total_review = item.ReviewOverviewModel.TotalReviews
        println("Total Reviews: $total_review")
        if(total_review>0){
            val review = item.ReviewOverviewModel.RatingSum/total_review
            holder.rating.rating = review.toFloat()
        }else{
            holder.rating.rating = 0f
        }
    }

    override fun getItemCount(): Int = itemList.size
}
