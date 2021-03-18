package com.example.hackahtondemo.util.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hackahtondemo.R
import com.example.hackahtondemo.util.api.model.ApiResponse
import kotlinx.android.synthetic.main.custom_item.view.*

class CustomAdapter(var arrayList: List<ApiResponse>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(collectionResponse: ApiResponse) {
            itemView.text_title.text = collectionResponse.title
            itemView.text_content_description.text = collectionResponse.handle
            /*Picasso.get()
                .load("https://image.tmdb.org/t/p/w92" + movie.poster_path)
                .into(itemView.findViewById(R.id.ivPoster) as ImageView)*/
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.custom_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(arrayList[position])
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}