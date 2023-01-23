package com.example.hellofigma

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hellofigma.screens.HomeScreen
import com.example.hellofigma.screens.OptionsScreen
import com.example.hellofigma.screens.QuestionScreen
import com.example.hellofigma.screens.StatsScreen
import com.example.hellofigma.ui.theme.Theme
import com.example.hellofigma.welcomescreen.WelcomeScreen
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {

    private var currentQuestion = 0

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Theme {
                ShowWelcomeScreen()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun dispatchQuestionScreen(){
        setContent {
            Theme {
                if (questions.size == 1) {
                    QuestionScreen(
                        question = questions[currentQuestion],
                        nextButtonClickedCallback = { showScaffolding() },
                        screenType = ScreenType.First
                    )
                } else {
                    when (currentQuestion) {
                        0 -> QuestionScreen(
                            question = questions[currentQuestion],
                            nextButtonClickedCallback = { nextQuestion() },
                            screenType = ScreenType.First
                        )
                        questions.size - 1 -> QuestionScreen(
                            question = questions[currentQuestion],
                            nextButtonClickedCallback = { showScaffolding() },
                            previousButtonClickedCallback = { previousQuestion() },
                            screenType = ScreenType.Normal
                        )
                        else -> QuestionScreen(
                            question = questions[currentQuestion],
                            nextButtonClickedCallback = { nextQuestion() },
                            previousButtonClickedCallback = { previousQuestion() },
                            screenType = ScreenType.Normal
                        )
                    }
                }
            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun ShowWelcomeScreen(){
        var showWelcomeAnimation by remember { mutableStateOf(true) }
        LaunchedEffect(key1 = Unit){
            delay(5000)
            showWelcomeAnimation = false
        }
        WelcomeScreen()
        if (!showWelcomeAnimation){
            dispatchQuestionScreen()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun showScaffolding() {
        setContent {
            Theme {
                Scaffolding()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    @Preview
    fun Scaffolding(){
        val navController = rememberNavController()
        Scaffold( bottomBar = { BottomBar(navController) } ) {
                innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                NavHost(navController = navController, startDestination = BottomNavItems.Home.route){
                    composable(BottomNavItems.Home.route) { HomeScreen() }
                    composable(BottomNavItems.Options.route) { OptionsScreen() }
                    composable(BottomNavItems.Stats.route) { StatsScreen() }
                }
            }
        }
    }

    @Composable
    private fun BottomBar(navController: NavHostController) {
        BottomNavigation {
            navItems.forEach { screen ->
                BottomNavigationItem(
                    icon = { Icon(imageVector = screen.icon, contentDescription = screen.name) },
                    label = { Text(text = screen.name) },
                    onClick = onBottomButtonClick(navController, screen),
                    selected = false
                )
            }
        }
    }

    @Composable
    private fun onBottomButtonClick(
        navController: NavHostController,
        screen: BottomNavItems
    ): () -> Unit = {
        navController.navigate(screen.route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun nextQuestion(){
        ++currentQuestion
        dispatchQuestionScreen()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun previousQuestion(){
        --currentQuestion
        dispatchQuestionScreen()
    }
}