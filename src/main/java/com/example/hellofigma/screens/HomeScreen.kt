package com.example.hellofigma.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import io.github.boguszpawlowski.composecalendar.SelectableCalendar
import io.github.boguszpawlowski.composecalendar.day.DayState
import io.github.boguszpawlowski.composecalendar.selection.DynamicSelectionState
import java.util.*


@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
fun HomeScreen(day: @Composable BoxScope.(DayState<DynamicSelectionState>) -> Unit = { MyDay(it) }){
    Column {
        SelectableCalendar(dayContent = day)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyDay(dayState: DayState<DynamicSelectionState>) {
    var showDialog =  remember { mutableStateOf(false) }
    var isComplete =  remember { mutableStateOf(false) }
    val selectionState = dayState.selectionState
    val date = dayState.date
    val currentDay = dayState.isCurrentDay
    Card(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(2.dp),
        elevation = if (dayState.isFromCurrentMonth) 4.dp else 0.dp,
        border = null,
        contentColor = Color.Black
    ) {
        Box(
            modifier = Modifier
                .clickable {
                    showDialog.value = true
                    selectionState.onDateSelected(date)
                }
                .background(if (currentDay && isComplete.value.not()) MaterialTheme.colors.secondary else {
                    if (isComplete.value.not() && (date < java.time.LocalDate.now())){
                        Color.Red
                    }else{
                        if(isComplete.value){
                            MaterialTheme.colors.primary
                        }else {
                            Color.White
                        }
                    }
                }),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = date.dayOfMonth.toString())
        }
    }
    if (showDialog.value){
        openDialog( setShowDialog = {
            showDialog.value = it
        }, setIsComplete = {
            isComplete.value = it
        })
    }
}

@Composable
fun openDialog(setShowDialog: (Boolean) -> Unit, setIsComplete: (Boolean) -> Unit) {
    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White
        ){
            Column(modifier = Modifier.padding(20.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Exercsise for today:",
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontFamily = FontFamily.Default,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "",
                        tint = colorResource(android.R.color.darker_gray),
                        modifier = Modifier
                            .width(30.dp)
                            .height(30.dp)
                            .clickable { setShowDialog(false) }
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Do a short yoga. Short, 5 minute yoga can help you with body tension and clearing your mind",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = FontFamily.Default
                    )
                )
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(onClick = {
                    }) {
                        Text(text = "Fill the PHQ9")
                    }
                    Button(onClick = {
                        setIsComplete(true)
                    }) {
                        Text(text = "Mark complete")
                    }
                }
            }
        }
    }
}
