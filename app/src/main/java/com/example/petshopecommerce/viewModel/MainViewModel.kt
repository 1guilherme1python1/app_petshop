package com.example.petshopecommerce.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petshopecommerce.model.Banner
import com.example.petshopecommerce.model.BannerFirebase
import com.example.petshopecommerce.model.Category
import com.example.petshopecommerce.model.CategoryFirebase
import com.example.petshopecommerce.model.Item
import com.example.petshopecommerce.model.ItemFirebase
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    val listaBanner = MutableLiveData<List<Banner>>()
    val listaCategorias = MutableLiveData<List<Category>>()
    val listaItem = MutableLiveData<List<Item>>()

    fun recuperarBanners(){
        val bannerFirebase = BannerFirebase()

        viewModelScope.launch {
            val banners = bannerFirebase.recuperaBanner()

            listaBanner.postValue(banners)
        }
    }

    fun recuperarCategorias(){
        val categoriasFirebase = CategoryFirebase()

        viewModelScope.launch {
            val categorias = categoriasFirebase.recuperaCategorias()

            listaCategorias.postValue(categorias)
        }
    }

    fun recuepraItem(){
        val itemFirebase = ItemFirebase()

        viewModelScope.launch {
            val items = itemFirebase.recuperaItem()

            listaItem.postValue(items)
        }
    }
}