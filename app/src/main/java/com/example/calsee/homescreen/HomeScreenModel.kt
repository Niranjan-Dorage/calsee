package com.example.calsee.homescreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class HomeViewModel: ViewModel() {
    var displayText by  mutableStateOf("")
    var result by mutableStateOf("")
    val buttonList = listOf(
        Button(false, "ac"),
        Button(false, "X"),
        Button(false, "%"),
        Button(false, "/"),
        Button(true, "7"),
        Button(true, "8"),
        Button(true, "9"),
        Button(false, "X"),
        Button(true, "4"),
        Button(true, "5"),
        Button(true, "6"),
        Button(false, "-"),
        Button(true, "1"),
        Button(true, "2"),
        Button(true, "3"),
        Button(false, "+"),
        Button(false, "-"),
        Button(true, "0"),
        Button(true, "."),
        Button(false, "="),
    )
    class Button(isNum: Boolean, text: String) {
        val isNum = isNum
        val text = text
    }

    fun calculateResult(displayText: String): String {
        var temp = 0
        var index = 0
        val intList = mutableListOf<Int>()
        val symbolList = mutableListOf<Char>()

        for (i in displayText) {
            if (i.isDigit()) {
                temp = i.digitToInt() + temp * 10
                if (index == displayText.length - 1)
                    intList.add(temp)
            } else if (i == ' ' && displayText[index - 1].isDigit()) {
                intList.add(temp)
                temp = 0
            } else if (i != ' ' && !i.isDigit())
                symbolList.add(i)

            index++
        }
        index = 0
        while (symbolList.contains('X') || symbolList.contains('/')) {
            if (symbolList[index] == 'X') {
                intList[index] *= intList[index + 1]
                intList.removeAt(index + 1)
                symbolList.removeAt(index)
            } else if (symbolList[index] == '/') {
                intList[index] /= intList[index + 1]
                intList.removeAt(index + 1)
                symbolList.removeAt(index)
            } else
                index++
        }
        index = 0
        while (symbolList.contains('+') || symbolList.contains('-')) {
            if (symbolList[index] == '+') {
                intList[index] += intList[index + 1]
                intList.removeAt(index + 1)
                symbolList.removeAt(index)
            } else if (symbolList[index] == '-') {
                intList[index] -= intList[index + 1]
                intList.removeAt(index + 1)
                symbolList.removeAt(index)
            } else
                index++
        }
        return intList[0].toString()
    }
}

