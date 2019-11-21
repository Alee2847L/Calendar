package com.example.calendaraplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class Adapteriamge(var context:Context, var list: ArrayList<image>) : RecyclerView.Adapter<Adapteriamge.ViewHolder>(){
    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): Adapteriamge.ViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.activity_content_, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: Adapteriamge.ViewHolder, position: Int) {
        val item= list.get(position)

        holder.foto.setImageURI(AppConstants.fileUri)
    }

    class ViewHolder (var view: View): RecyclerView.ViewHolder(view){
        var foto : ImageView

        init {
            this.foto=view.findViewById(R.id.imageViewseleccion)
        }

        }
}