package com.example.calendaraplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.text.FieldPosition

class Adapteriamge(var list: ArrayList<image>) : RecyclerView.Adapter<Adapteriamge.ViewImage>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewImage {
        val v=LayoutInflater.from(parent.context).inflate(R.layout.content_item,parent,false)
        return ViewImage(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Adapteriamge.ViewImage, position: Int) {
        holder.bindItems(list[position])
    }

    class ViewImage (view: View): RecyclerView.ViewHolder(view){
        fun bindItems(data:image){
            val img:ImageView=itemView.findViewById(R.id.imageView8)

            Glide.with(itemView.context).load(data.listaimagenes)
        }
    }
}