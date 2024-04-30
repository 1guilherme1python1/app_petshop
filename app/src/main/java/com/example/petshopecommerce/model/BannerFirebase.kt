package com.example.petshopecommerce.model

import com.example.petshopecommerce.services.FirebaseService
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class BannerFirebase {
    suspend fun recuperaBanner() : List<Banner> = suspendCoroutine {continuation ->
        val dataBase = FirebaseService.recuperaDataFirebase()

        dataBase.child("Banner").get().addOnSuccessListener {bannerSnapshot ->
            val banners = mutableListOf<Banner>()
            for (banner in bannerSnapshot.children){
                val bannerUrl = banner.getValue(Banner::class.java)
                bannerUrl?.let {
                    banners.add(it)
                }

            }
            continuation.resume(banners)
        }.addOnFailureListener { exception ->
            exception.printStackTrace()
            continuation.resume(emptyList())
        }
    }
}