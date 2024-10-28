package com.example.kotlin_data2

import android.provider.ContactsContract.CommonDataKinds.Phone
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact (
    @PrimaryKey(autoGenerate = true)
    val Id: Int=0,
    @ColumnInfo
    val Phone: String="",
    @ColumnInfo
    val FullName: String="",
    @ColumnInfo
    val Email: String=""
)