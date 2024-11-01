package com.example.kotlin_data2

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SearchContactViewModel(
    private val contactRepository: ContactRepository=Graph.repository
) : ViewModel() {
    var state by mutableStateOf(SearchContactState())
        private set

    init {
        getAllContacts()
    }

    private fun getAllContacts() {
        viewModelScope.launch {
            contactRepository.contacts.collectLatest {
                state = state.copy(
                    contacts = it
                )
            }
        }
    }

//    fun deleteContact(contact: Contact) {
//        viewModelScope.launch {
//            contactRepository.deleteContact(contact=contact)
//        }
//    }
//
//    fun getContactByID(id: Int){
//        viewModelScope.launch{
//            contactRepository.getContactByID(id).collectLatest {
//                state=state.copy(
//                    contactFound = it
//                )
//            }
//        }
//    }

    fun searchContact(thaomai: String){
        state = state.copy(
            tim = thaomai
        )
        viewModelScope.launch {
            contactRepository.searchContact(state.tim).collectLatest {
                state=state.copy(
                    contacts = it
                )
            }
        }
    }
}


data class SearchContactState(
    val contacts: List<Contact> = emptyList(),
    val contactFound: Contact = Contact(),
    val tim: String = ""
)