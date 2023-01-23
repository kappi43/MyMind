package com.example.hellofigma.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alorma.compose.settings.ui.SettingsCheckbox
import com.alorma.compose.settings.ui.SettingsGroup
import com.alorma.compose.settings.ui.SettingsMenuLink
import com.alorma.compose.settings.ui.SettingsSwitch

@Composable
fun OptionsScreen(){
    Column {
        Card(elevation = 3.dp, modifier = Modifier
            .fillMaxWidth(1f)
            .border(BorderStroke(width = 1.dp, color = Color.Gray))) {
            Box(modifier = Modifier.padding(all = 2.dp)) {
                Text(text ="Options", fontSize = 24.sp, textAlign = TextAlign.Center)
            }
        }
        SettingsGroup(title = {Text(text = "General")}) {
            SettingsSwitch(
                icon = { Icon(imageVector = Icons.Default.Warning, contentDescription = "Wifi") },
                title = { Text(text = "Hello") },
                subtitle = { Text(text = "This is a longer text") },
                onCheckedChange = { newValue -> },
            )
            SettingsCheckbox(
                icon = { Icon(imageVector = Icons.Default.Warning, contentDescription = "Wifi") },
                title = { Text(text = "Dark mode") },
                subtitle = { Text(text = "When checked - dark mode is on") },
                onCheckedChange = { newValue -> },
            )
            SettingsMenuLink(
                icon = {
                    Icon(
                        imageVector = Icons.Default.AccountBox,
                        contentDescription = "Wifi"
                    )
                },
                title = { Text(text = "Retake Questionairre") },
                subtitle = { Text(text = "Take the PHQ-9 again for today") },
                onClick = {},
            )
        }
        SettingsGroup(title = {Text(text = "Data & storage")}) {
            SettingsMenuLink(
                icon = { Icon(imageVector = Icons.Default.AccountBox, contentDescription = "Wifi") },
                title = { Text(text = "Clear app data") },
                subtitle = { Text(text = "Clear all app data. Will reset all progress in the app") },
                onClick = {},
            )
        }
    }

}