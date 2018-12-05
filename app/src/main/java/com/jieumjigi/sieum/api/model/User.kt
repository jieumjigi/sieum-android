package com.jieumjigi.sieum.api.model

data class User (
        val uid : String,
        val name : String,
        val profileImageUrl : String,
        val snsUrl : String,
        val introduce : String,
        val level : Int
)