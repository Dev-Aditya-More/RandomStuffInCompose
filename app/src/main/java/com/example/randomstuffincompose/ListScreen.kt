package com.example.randomstuffincompose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ListScreen(onClick : (String) -> Unit){

    LazyColumn {

        repeat(50){
            item{
                Text(
                  modifier = Modifier
                      .clickable{
                          onClick("Item $it")
                      }
                      .padding(16.dp)
                      .background(if (it % 2 == 0) Color.LightGray else Color.White),

                    text = "Item $it",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}