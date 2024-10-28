package com.example.kotlin_data2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kotlin_data2.ui.theme.Kotlin_Data2Theme

//class CardInfo : ComponentActivity(){
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            Kotlin_Data2Theme {
//                Scaffold (
//                    modifier = Modifier.fillMaxSize()
//                ){
//                    it->CardInfo(name="", phone = "", onclickCard = {}, modifier = Modifier.padding(it))
//                }
//            }
//        }
//    }
//}

@Composable
fun CardInfo(name: String, phone: String, onclickCard:()->Unit){
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        onClick = onclickCard
    ) {
        Row (
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Row (
                modifier = Modifier.weight(2f)
            ){
                Box (
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .background(color = Color.LightGray),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = name[0].toString().uppercase(),
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.size(10.dp))
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = name,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = phone,
                        color = Color.Gray
                    )
                }
            }
            IconButton(
                onClick = {
                    var list = ContactRepository().contacts
                    ContactRepository().deleteContact()
                }
            ) {
                Icon(imageVector = Icons.Filled.Delete, contentDescription = "")
            }
        }
    }
}

//@Preview
//@Composable
//fun HienThi_CardInfo(modifier: Modifier=Modifier){
//    CardInfo()
//}