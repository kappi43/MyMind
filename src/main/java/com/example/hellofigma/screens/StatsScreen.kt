package com.example.hellofigma.screens

import android.content.res.Resources
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hellofigma.statscard.StatsCard
import com.google.relay.compose.ColumnScopeInstanceImpl.weight
import com.himanshoe.charty.horizontalbar.HorizontalBarChart
import com.himanshoe.charty.horizontalbar.model.HorizontalBarData
import com.himanshoe.charty.line.CurveLineChart
import com.himanshoe.charty.line.model.LineData

val horizontalBarData = listOf(
    HorizontalBarData(10F, "A"),
    HorizontalBarData(20F, "B"),
    HorizontalBarData(75F, "C"),
    HorizontalBarData(40F, "D"),
    HorizontalBarData(10F, "E"),
    HorizontalBarData(20F, "F"),
)
@Preview
@Composable
fun StatsScreen(){
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .weight(1f, false)

    ) {

        Card(elevation = 3.dp, modifier = Modifier
            .fillMaxWidth(1f)
            .border(BorderStroke(width = 1.dp, color = Color.Gray))) {
            Box(modifier = Modifier.padding(all = 2.dp)) {
               Text(text ="Statistics", fontSize = 24.sp, textAlign = TextAlign.Center)
            }
        }

        StatsCard(
            modifier = Modifier
                .height((Resources.getSystem().displayMetrics.heightPixels / 14).dp)
                .padding(vertical = 10.dp),
            statsCardBackgroundColor = MaterialTheme.colors.primary,
            messageText = "Zmiana wyniku PHQ-9 do ostatniego wypełnienia",
            valueText = "4",
            percentageText = "-10%"
        ) {

        }
        StatsCard(
            modifier = Modifier
                .height((Resources.getSystem().displayMetrics.heightPixels / 14).dp)
                .padding(vertical = 10.dp),
            statsCardBackgroundColor = MaterialTheme.colors.secondary,
            messageText = "Ilość wypełnionych kwestionariuszy",
            valueText = "4",
            percentageText = ""
        ) {
        }
        Text(text ="Diagrams", fontSize = 24.sp, textAlign = TextAlign.Center)
        HorizontalBarChart(
            modifier = Modifier
                .size(width = 500.dp, height = 300.dp)
                .padding(20.dp),
            colors = listOf(Color(0xFFFDC830), Color(0xFFF37335)),
            horizontalBarData = horizontalBarData,
            onBarClick = {
            }
        )
    }
}