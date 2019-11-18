package com.example.calendaraplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class Adapteriamge(var list: ArrayList<image>) : RecyclerView.Adapter<Adapteriamge.ViewHolder>(){
    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v=LayoutInflater.from(parent?.context).inflate(R.layout.activity_content_,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(list[position])
    }

    class ViewHolder (view: View): RecyclerView.ViewHolder(view){
        fun bindItems(data:image){
            val img:ImageView=itemView.findViewById(R.id.imageView8)

            Glide.with(itemView.context).load(data.listaimagenes).into(img)
        }
    }
}