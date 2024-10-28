package com.example.kotlin_data2

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface Contact_Dao{
    @Query("Select * From Contact")
    fun getAllContacts(): Flow<List<Contact>>

    @Query("Select * From Contact Where Id = :contactId")
    fun getContactById(contactId: Int): Flow<Contact>

    @Query("Select * From Contact where Phone=:tim or FullName like '%'||:tim||'%'")
    fun searchContact(tim:String):Flow<List<Contact>>

    @Insert
    suspend fun insertContact(contact: Contact)

    @Update
    suspend fun updateContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)
}