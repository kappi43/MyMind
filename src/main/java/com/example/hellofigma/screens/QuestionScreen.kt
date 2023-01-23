package com.example.hellofigma.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.BottomNavigation
import androidx.compose.material.RadioButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hellofigma.Question
import com.example.hellofigma.ScreenType
import com.example.hellofigma.questionbuttonback.QuestionButtonBack
import com.example.hellofigma.questionbuttonnext.QuestionButtonNext
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.material.Card
import androidx.compose.ui.graphics.Color


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionScreen(question: Question, nextButtonClickedCallback: () -> Unit = {}, previousButtonClickedCallback: () -> Unit = {}, screenType: ScreenType) {
    Scaffold( bottomBar = { QuestionBarBottomPanel(nextButtonClickedCallback, previousButtonClickedCallback, screenType) } ) {
            innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            questionScreenContent(question)
        }
    }
}

@Composable
private fun questionScreenContent(question: Question) {
    Column(verticalArrangement = Arrangement.Center, modifier = Modifier
        .selectableGroup()
        .padding(all = 5.dp)) {
        Row() {
            Spacer(modifier = Modifier.width(30.dp))
            Card(elevation = 3.dp, modifier = Modifier
                .fillMaxWidth(0.9f)
                .border(BorderStroke(width = 1.dp, color = Color.Black))) {
                Box(modifier = Modifier.padding(all = 2.dp)) {
                    Text(text = question.questionText, fontSize = 20.sp, textAlign = TextAlign.Center)
                }
            }
        }

        Spacer(modifier = Modifier.height(30.dp))
        var selectedItem by remember {
            mutableStateOf(question.answers[0])
        }
        question.answers.forEach { answerText ->
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .selectable(
                    selected = (selectedItem == answerText),
                    onClick = {
                        selectedItem = answerText
                        question.resp = answerText
                    },
                    role = Role.RadioButton
                )
                .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Text(text = answerText)
                Spacer(Modifier.weight(1f))
                RadioButton( modifier = Modifier.padding(end = 16.dp),
                    selected = (selectedItem == answerText),
                    onClick = null)
            }
        }
    }
}

@Composable
fun QuestionBarBottomPanel(
    nextButtonClickedCallback: () -> Unit = {},
    previousButtonClickedCallback: () -> Unit = {},
    barType: ScreenType
) {
    when(barType) {
        ScreenType.Normal -> MiddleQuestionBottomBar(
            previousButtonClickedCallback,
            nextButtonClickedCallback
        )
        ScreenType.First -> FirstQuestionBarBottomPanel(nextButtonClickedCallback)
        ScreenType.Last -> LastQuestionBarBottomPanel(previousButtonClickedCallback)
    }
}

@Composable
private fun MiddleQuestionBottomBar(
    previousButtonClickedCallback: () -> Unit,
    nextButtonClickedCallback: () -> Unit
) {
    BottomNavigation {
        QuestionButtonBack(onQuestionButtonBackTapped = previousButtonClickedCallback)
        Spacer(modifier = Modifier.width(10.dp))
        QuestionButtonNext(onQuestionButtonNextTapped = nextButtonClickedCallback)
    }
}

@Composable
fun FirstQuestionBarBottomPanel(
    nextButtonClickedCallback: () -> Unit = {}
) {
    BottomNavigation {
        Spacer(modifier = Modifier.width(10.dp))
        QuestionButtonNext(onQuestionButtonNextTapped = nextButtonClickedCallback)
    }
}

@Composable
fun LastQuestionBarBottomPanel(
    previousButtonClickedCallback: () -> Unit = {}
) {
    BottomNavigation {
        QuestionButtonBack(onQuestionButtonBackTapped = previousButtonClickedCallback)
        Spacer(modifier = Modifier.width(10.dp))
        QuestionButtonNext()
    }
}



