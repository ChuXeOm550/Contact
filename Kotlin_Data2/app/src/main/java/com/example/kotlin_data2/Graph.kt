package com.example.kotlin_data2

import android.content.Context

object Graph{
    lateinit var db: ContactDatabase
        private set
    val repository by lazy {
        ContactRepository(
            db.dao
        )
    }
    fun provider(context: Context){
        db=ContactDatabase.getDatabase(context=context)
    }
}