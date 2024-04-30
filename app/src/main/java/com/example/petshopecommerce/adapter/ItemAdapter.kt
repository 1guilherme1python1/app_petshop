package com.example.petshopecommerce.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.petshopecommerce.R
import com.example.petshopecommerce.model.Item

class ItemAdapter(
    private val lista: List<Item>
) : Adapter<ItemAdapter.ItemViewHolder>() {
    inner class ItemViewHolder(itemView: View)
        : ViewHolder(itemView){

            val imgProduto = itemView.findViewById<ImageView>(R.id.imgListaProdutos)
            val textPrecoProduto = itemView.findViewById<TextView>(R.id.textPrecoListProduct)
            val textDescricaoProduto = itemView.findViewById<TextView>(R.id.txtDescricaListaProduto)
            val textRatingProduto = itemView.findViewById<TextView>(R.id.txtRatingListProduct)

            fun bind(item: Item){
                textPrecoProduto.text = String.format("R$ %.2f", item.price.toFloat())
                textDescricaoProduto.text = item.description
                textRatingProduto.text = item.rating.toString()

                Glide.with(imgProduto)
                    .load(item.picUrl[0])
                    .into(imgProduto)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutManager = LayoutInflater.from(parent.context)

        val itemView = layoutManager.inflate(
            R.layout.item_produto,
            parent,
            false
        )

        return ItemViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = lista[position]

        holder.bind(item)
    }
}