package com.example.petshopecommerce.model

import com.example.petshopecommerce.services.FirebaseService
import java.lang.Exception
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class CategoryFirebase {
    suspend fun recuperaCategorias() : List<Category> = suspendCoroutine {continuation ->

        val database =  FirebaseService.recuperaDataFirebase()

        database.child("Category").get().addOnSuccessListener {categorySnapshot ->
            val categorias = mutableListOf<Category>()

            for (cat in categorySnapshot.children){
                val categoriaModel = cat.getValue(Category::class.java)

                categoriaModel?.let {
                    categorias.add(it)
                }
            }
            continuation.resume(categorias)

        }.addOnFailureListener {e : Exception ->
            e.printStackTrace()
            continuation.resume(emptyList())
        }






    }
}