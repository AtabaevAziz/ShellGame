package com.example.shellgame

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shellgame.ui.theme.ShellGameTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShellGameTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Games()
                }
            }
        }
    }
}

@Composable
fun Games() {
    val appMode = remember{ mutableStateOf(0) }
    Scaffold(topBar = { TopAppBar(title = { Text(text = "Shell Game") }) }) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            val winOrFail = remember { mutableStateOf("") }
            if (appMode.value > -1 ) {

                val textMissionPhase = when (appMode.value % 2) {
                    1 -> winOrFail.value
                    else -> "Найди Шарик!"
                }
                Text(
                    text = textMissionPhase, Modifier.padding(vertical = 50.dp)
                )
            }

            if (appMode.value > 0) {
                val repeatButton = when (appMode.value % 2) {
                    1 -> "Сыграть еще раз"
                    else -> null
                }
                if (repeatButton != null) {
                    Button(
                        onClick = { appMode.value++ }
                    ) {
                        Text(
                            text = repeatButton
                        )
                    }
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
            ) {
                val choice = arrayOf("Пусто", "Шарик")
                val firstRandomChoice = choice.random()
                val secondChoice = if (firstRandomChoice == "Шарик") {
                    "Пусто"
                } else {
                    choice.random()
                }

                val thirdChoice = if (firstRandomChoice == "Пусто" && secondChoice == "Пусто") {
                    "Шарик"
                } else {
                    "Пусто"
                }


                if (appMode.value > -1) {
                    val ballOrEmpty = when (appMode.value % 2) {
                        1 -> firstRandomChoice
                        else -> "----"
                    }
                    Button(
                        onClick = {
                            winOrFail.value = if (firstRandomChoice == "Шарик") {
                                "Выиграл"
                            } else {
                                "Проиграл!"
                            }
                            appMode.value++

                        },
                        Modifier.padding(15.dp)

                    ) {
                        Text(
                            text = ballOrEmpty
                        )
                    }
                }
                if (appMode.value > -1) {
                    val ballOrEmpty = when (appMode.value % 2) {
                        1 -> secondChoice
                        else -> "----"
                    }
                    Button(
                        onClick = {
                            winOrFail.value = if (secondChoice == "Шарик") {
                                "Выиграл"
                            } else {
                                "Проиграл!"
                            }
                            appMode.value++

                        },
                        Modifier.padding(15.dp)

                    ) {
                        Text(
                            text = ballOrEmpty
                        )
                    }
                }
                if (appMode.value > -1) {
                    val ballOrEmpty = when (appMode.value % 2) {
                        1 -> thirdChoice
                        else -> "----"
                    }
                    Button(
                        onClick = {
                            winOrFail.value = if (thirdChoice == "Шарик") {
                                "Выиграл"
                            } else {
                                "Проиграл!"
                            }
                            appMode.value++

                        },
                        Modifier.padding(15.dp)
                    ) {
                        Text(
                            text = ballOrEmpty
                        )
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun DefaultPreview() {
    ShellGameTheme {
        Games()
    }
}