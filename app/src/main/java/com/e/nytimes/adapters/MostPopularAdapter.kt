package com.e.nytimes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.e.nytimes.R
import com.e.nytimes.data.model.Result
import kotlinx.android.synthetic.main.item_most_popular.view.*

class MostPopularAdapter : RecyclerView.Adapter<MostPopularAdapter.ViewHolder>()  {

    val diffCallback = object : DiffUtil.ItemCallback<Result>(){

        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem

        }

    }

    val differ = AsyncListDiffer(this , diffCallback)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_most_popular, parent , false))

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = differ.currentList[position]

        holder.itemView.apply {

            txt_title.text = item.title
            txt_section.text = item.abstract

            txt_date.text = StringBuilder()
                .append(item.publishedDate)

        }
    }


    class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView)

}