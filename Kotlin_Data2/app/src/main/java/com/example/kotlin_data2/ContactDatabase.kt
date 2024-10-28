package com.example.kotlin_data2

import android.content.Context
import android.provider.CalendarContract.Instances
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.time.Instant

@Database(entities = [Contact::class], version = 1)
abstract class ContactDatabase : RoomDatabase() {
    abstract val dao: Contact_Dao

    companion object{
        @Volatile
        private var Instance : ContactDatabase? = null

        fun getDatabase(context: Context): ContactDatabase{
            return Instance ?: synchronized(this){
                Room.databaseBuilder(
                    context,
                    ContactDatabase::class.java,
                    "contact_db"
                )
                    .build().also { Instance=it }
            }
        }
    }
}

class ContactRepository(private val contactDao: Contact_Dao){
    val contacts=contactDao.getAllContacts()
    fun getContactByID(id: Int)=contactDao.getContactById(id)

    fun searchContact(tim: String)=contactDao.searchContact(tim)

    suspend fun deleteContact(contact: Contact){
        contactDao.deleteContact(contact)
    }

    suspend fun insertContact(contact: Contact){
        contactDao.insertContact(contact)
    }

    suspend fun updateContact(contact: Contact){
        contactDao.updateContact(contact)
    }
}