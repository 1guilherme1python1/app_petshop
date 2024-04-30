package com.example.petshopecommerce.services

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

object FirebaseService {
    fun recuperaDataFirebase() : DatabaseReference{
        return FirebaseDatabase.getInstance().reference
    }
}