package com.example.calsee.homescreen

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calsee.ui.theme.CalseeTheme

@Composable
fun HomeScreenView(context: Context, viewModel: HomeViewModel) {
    CalseeTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Color(0xFA283637)
        ) { innerPadding ->
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 32.dp),
            ) {
                Text(
                    text = viewModel.displayText,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding(16.dp),
                    color = Color.White
                )
                inputField(viewModel)
                keypad(viewModel, context)
            }
        }
    }
}