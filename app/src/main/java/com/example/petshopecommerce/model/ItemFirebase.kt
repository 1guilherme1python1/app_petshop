package com.example.petshopecommerce.model

import com.example.petshopecommerce.services.FirebaseService
import kotlinx.coroutines.coroutineScope
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class ItemFirebase {
    suspend fun recuperaItem(): List<Item> = suspendCoroutine {continuation ->
        val database = FirebaseService.recuperaDataFirebase()

        database.child("Items").get().addOnSuccessListener {itemSnapshot ->
            val listaItem = mutableListOf<Item>()

            for (item in itemSnapshot.children){
                val itemModel = item.getValue(Item::class.java)

                itemModel?.let {
                    listaItem.add(itemModel)
                }
            }

            continuation.resume(listaItem)
        }.addOnFailureListener {e ->
            e.printStackTrace()
            continuation.resume(emptyList())
        }
    }
}