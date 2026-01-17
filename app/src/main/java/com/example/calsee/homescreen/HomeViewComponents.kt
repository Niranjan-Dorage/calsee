package com.example.calsee.homescreen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.text.isDigit


@Composable
fun inputField(viewModel: HomeViewModel,){
        TextField(
            value = if ( viewModel.result == "") viewModel.displayText else viewModel.result,
            onValueChange = { },
            readOnly = true,
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                cursorColor = Color.White,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            ),
            modifier = Modifier
                .padding()
                .padding(bottom = 64.dp)
                .horizontalScroll(rememberScrollState(), reverseScrolling = true),
            textStyle = TextStyle(
                fontSize = 48.sp,
                fontWeight = FontWeight.W500,
                textAlign = TextAlign.End,
                color = Color.White
            )
        )
    }

@Composable
fun keypad(viewModel: HomeViewModel, context: Context){
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = Modifier
            .padding()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),

        ) {
        items(viewModel.buttonList.count()) { index ->
            val item = viewModel.buttonList[index]
            Box(
                contentAlignment = Alignment.Center,
            ) {
                Button(
                    onClick = {
                        viewModel.result = ""
                        if (item.text == "ac") {
                            viewModel.displayText = ""
                            viewModel.result = ""
                        } else if (!item.isNum && viewModel.displayText.length > 1 && !viewModel.displayText[viewModel.displayText.length - 1].isDigit())
                            Toast.makeText(
                                context,
                                "you are an idiot",
                                Toast.LENGTH_SHORT
                            ).show()
                        else if (item.isNum)
                            viewModel.displayText += "${item.text}"
                        else if (!item.isNum && viewModel.displayText.isEmpty())
                            Toast.makeText(
                                context,
                                "you are an idiot",
                                Toast.LENGTH_SHORT
                            ).show()
                        else if (item.text != "=") {
                            viewModel.displayText += " ${item.text} "
                        } else if (item.text == "=") {
                            viewModel.result =
                                viewModel.calculateResult(viewModel.displayText)
                        }

                    },
                    colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                        containerColor = if (item.isNum) Color(0xFA283637) else Color.White
                    ),
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .clip(CircleShape)
                            .aspectRatio(1f)
                ) {
                    Text(
                        text = "${item.text}",
                        fontSize = 24.sp,
                        color = if (item.isNum) Color.White else Color(
                            0xFA283637
                        ),
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}