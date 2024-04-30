package com.example.petshopecommerce.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.petshopecommerce.R
import com.example.petshopecommerce.model.Banner

class BannerAdapter(
    private val lista: List<Banner>
) : Adapter<BannerAdapter.BannerViewHolder>() {
    inner class BannerViewHolder(
        private val _itemView: View
    ) : ViewHolder(_itemView){

        private val _itemSlide: ImageView = _itemView.findViewById(R.id.imgItemBanner)

        fun bind(item : Banner){
            var requestOptions = RequestOptions()
            requestOptions =  requestOptions.transform(CenterCrop())

            Glide
                .with(itemView)
                .load(item.url)
                .apply(requestOptions)
                .into(_itemSlide)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val itemView = layoutInflater.inflate(
            R.layout.item_banner,
            parent,
            false
        )

        return BannerViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        val item = lista[position]

        holder.bind(item)
    }
}