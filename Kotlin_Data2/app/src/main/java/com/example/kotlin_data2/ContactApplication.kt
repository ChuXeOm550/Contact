package com.example.kotlin_data2

import android.app.Application

class ContactApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provider(this)
    }
}