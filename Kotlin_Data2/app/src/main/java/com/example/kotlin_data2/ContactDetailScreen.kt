package com.example.kotlin_data2

import android.annotation.SuppressLint
import android.provider.ContactsContract.CommonDataKinds.Phone
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ContactDetailScreen(navController: NavHostController, id: Int=-1) {
    var viewModel= viewModel<ContactDetailViewModel>(
        factory = ContactDetailViewModelFactor(id)
    )
    var contactState=viewModel.state
    Scaffold (
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.LightGray
                ),
                title = {
                    Text(
                        text = if(id<0) "Thêm mới liên hệ"
                        else "Cập nhật liên hệ"
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Filled.ArrowBackIosNew, contentDescription = null)
                    }
                }
            )
        }
    ){
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            OutlinedTextField(
                value = contactState.fullname,
                onValueChange = viewModel::onChangeFullName,
                label = { Text(text = "Họ và tên") },
            )
            OutlinedTextField(
                value = contactState.phone,
                onValueChange = viewModel::onChangePhone,
                label = { Text(text = "Số điện thoại") },
            )
            OutlinedTextField(
                value = contactState.email,
                onValueChange = viewModel::onChangeEmail,
                label = { Text(text = "Email") },
            )
            Button(
                onClick = {
                    if(id>0){
                        var contact=Contact(
                            id, contactState.phone,
                            contactState.fullname,
                            contactState.email
                        )
                        viewModel.updateContact(contact)
                    } else{
                        var contact = Contact(
                            Phone = contactState.phone,
                            FullName = contactState.fullname,
                            Email = contactState.email
                        )
                        viewModel.insertContact(contact)
                    }
                },
                shape = RoundedCornerShape(10)
            ) {
                Text(text = "Lưu")
            }
        }
    }
}