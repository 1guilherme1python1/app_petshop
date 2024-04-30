package com.example.petshopecommerce.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.petshopecommerce.R
import com.example.petshopecommerce.model.Category

class CategoryAdapter(
    private val lista: List<Category>
) : Adapter<CategoryAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(itemView: View)
        : ViewHolder(itemView){

            private val imgCategoria = itemView.findViewById<ImageView>(R.id.imgItemCategoria)
            private val textCategoria = itemView.findViewById<TextView>(R.id.txtItemCategoria)

            fun bind(item: Category) {
                Glide.with(itemView)
                    .load(item.picUrl)
                    .into(imgCategoria)

                textCategoria.text = item.title
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val itemView = layoutInflater.inflate(
            R.layout.item_categoria,
            parent,
            false
        )

        return CategoryViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = lista[position]

        holder.bind(item)
    }
}