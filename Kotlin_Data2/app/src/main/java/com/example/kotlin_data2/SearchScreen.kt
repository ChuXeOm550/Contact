package com.example.kotlin_data2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Pets
import androidx.compose.material.icons.rounded.QrCode2
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.kotlin_data2.ui.theme.Kotlin_Data2Theme


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavController){

    var viewModel: SearchContactViewModel = viewModel(modelClass = SearchContactViewModel::class.java)
    var SearchContactState=viewModel.state
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.LightGray
                ),
                title = {
                    TextField(
                        value = SearchContactState.tim,
                        onValueChange = viewModel::searchContact,
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text(text = "Tìm kiếm") },
                        leadingIcon = {
                            IconButton(onClick = {}) {
                                Icon(imageVector = Icons.Rounded.Search, contentDescription = "")
                            }
                        },
                        trailingIcon = {
                            Row{
                                IconButton(onClick = {}) {
                                    Icon(Icons.Rounded.Pets, contentDescription = "")
                                }
                                IconButton(onClick = {}) {
                                    Icon(Icons.Rounded.Settings, contentDescription = "")
                                }
                            }
                        },
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                        )
                    )
                }
            )
        },

        floatingActionButton = {
            IconButton(
                onClick = {
                    navController.navigate(Screen.Detail.route)
                },
                colors= IconButtonDefaults.iconButtonColors(
                    containerColor = Color.LightGray
                ),
                modifier = Modifier.clip(CircleShape)
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Thêm danh bạ")
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) {
        LazyColumn(
            contentPadding = it,
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(SearchContactState.contacts){ con->
                con
                CardInfo(name = con.FullName, phone = con.Phone, onclickCard = {
                    navController.navigate(
                        Screen.Detail.route+"?id=${con.Id}"
                    )
                },
                    onDelete = {}
                )
            }
//            item {
//                CardInfo(name = "Phan Thảo", phone = "0785763022",{})
//            }
//            item {
//                CardInfo(name = "Thành", phone = "0785763022",{})
//            }
        }
    }
}
