package com.eric.week3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eric.week3.ui.theme.Week3Theme
import kotlinx.coroutines.delay

class WordMatching : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Week3Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    view(Modifier.padding(innerPadding))
                }
            }
        }
    }
}
enum class gameState {
    MAINMENU,
    COUNTDOWN,
    RUNNING,
    GAMEOVER
}

enum class Mode {
    COLOR,
    TEXT
}

enum class ColorName {
    PURPLE,
    RED,
    YELLOW,
    BLUE,
    GREEN

}


val Poppins = FontFamily(
    Font(R.font.poppins_regular),
    Font(
        R.font.poppins_bold,
        FontWeight.Bold
    )
)
data class Round(
    val word: ColorName,
    val color: ColorName,
    val mode: Mode
)
fun nextRound(): Round {
    return Round(
        word = ColorName.entries.random(),
        color = ColorName.entries.random(),
        mode = Mode.entries.random()
    )
}
@Composable
fun view(modifier: Modifier = Modifier) {

    var text1 by rememberSaveable { mutableStateOf("WELCOME\nTO\nCOLOR WORD MATCHING") }
    var buttonText by rememberSaveable { mutableStateOf("Start Game") }
    var state by rememberSaveable { mutableStateOf(gameState.MAINMENU) }
    var timeLeft by rememberSaveable { mutableIntStateOf(5) }
    var correctAns by rememberSaveable { mutableIntStateOf(0) }
    var bestScore by rememberSaveable { mutableIntStateOf(0) }
    var mistakeCount by rememberSaveable { mutableIntStateOf(0) }
    var countMistakeLimitation by rememberSaveable { mutableIntStateOf(3) }
    var round by remember { mutableStateOf(nextRound()) }


    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = if (state == gameState.MAINMENU || state == gameState.COUNTDOWN){
            Arrangement.spacedBy(20.dp, alignment = Alignment.CenterVertically)}
        else {
            Arrangement.SpaceBetween
        },
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(249, 249, 249))
    ) {
        if (state == gameState.GAMEOVER) {
            text1 = "Game Over!"
        }

        var text1Color = when (state) {
            gameState.MAINMENU -> Color.DarkGray
            gameState.COUNTDOWN -> Color.DarkGray
            gameState.RUNNING -> {
                when (round.color) {
                    ColorName.RED -> Color.Red
                    ColorName.BLUE -> Color.Blue
                    ColorName.GREEN -> Color.Green
                    ColorName.YELLOW -> Color.Yellow
                    ColorName.PURPLE -> Color.Magenta
                }
            }
            gameState.GAMEOVER -> Color.DarkGray
        }

        var text1Size = when (state) {
            gameState.MAINMENU -> 20.sp
            gameState.COUNTDOWN -> 20.sp
            gameState.RUNNING -> 40.sp
            gameState.GAMEOVER -> 30.sp
        }

        LaunchedEffect(state) {
            if (state == gameState.COUNTDOWN) {

                text1 = "3"
                delay(1000)

                text1 = "2"
                delay(1000)

                text1 = "1"
                delay(1000)

                text1 = "Start!"
                delay(700)

                state = gameState.RUNNING

            }
        }

        LaunchedEffect(state, round) {
            if (state == gameState.RUNNING) {
                timeLeft = 5

                while (timeLeft > 0) {
                    delay(1000)
                    timeLeft--
                }

                mistakeCount++

                if (mistakeCount >= 3) {
                    state = gameState.GAMEOVER
                } else {
                    round = nextRound()
                }
            }
        }
        if (state == gameState.RUNNING || state == gameState.GAMEOVER) {

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth().height(280.dp).padding(horizontal = 40.dp).padding(top = 100.dp)
            ) {
                if (state == gameState.RUNNING) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            "Mode: ${round.mode.name}",
                            fontFamily = Poppins,
                            color = Color.DarkGray,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.ExtraLight,
                            modifier = Modifier.padding(end = 60.dp)
                        )

                        Text(
                            "✅ $correctAns",
                            fontFamily = Poppins,
                            color = Color.DarkGray,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.ExtraLight,
                            modifier = Modifier.padding(end = 15.dp)
                        )
                        Text(
                            "❌ $mistakeCount/$countMistakeLimitation",
                            fontFamily = Poppins,
                            color = Color.DarkGray,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.ExtraLight
                        )
                    }
                }
            }
        }


        if (state == gameState.RUNNING) {
            Text(
                "${timeLeft} s",
                fontFamily = Poppins,
                color = Color.DarkGray,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.ExtraLight
            )
        }

        val randomColor = remember(round) {
            ColorName.entries.random()
        }

        Text (
            if (state == gameState.MAINMENU){
                text1
            } else if (state == gameState.RUNNING){
                when (round.mode) {
                    Mode.TEXT -> round.word.name
                    Mode.COLOR -> randomColor.toString()
                }
            } else {
            text1
        },
            fontFamily = Poppins,
            color = text1Color,
            fontSize = text1Size,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.ExtraLight,
            lineHeight = 40.sp

        )
        if (state == gameState.GAMEOVER) {
            Text ("You're Score\n$correctAns",
                color = Color.DarkGray,
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.ExtraLight,
                modifier = Modifier.padding(top = 70.dp))

            Text ("Best Score\n$bestScore",
                color = Color.DarkGray,
                fontSize = 15.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.ExtraLight)
        }

        Row (
            horizontalArrangement = when (state) {
                gameState.MAINMENU -> Arrangement.Center
                gameState.COUNTDOWN -> Arrangement.Center
                gameState.RUNNING -> Arrangement.SpaceBetween
                gameState.GAMEOVER -> Arrangement.Center
            },
            verticalAlignment = when (state) {
                gameState.MAINMENU -> Alignment.CenterVertically
                gameState.COUNTDOWN -> Alignment.CenterVertically
                gameState.RUNNING -> Alignment.CenterVertically
                gameState.GAMEOVER -> Alignment.Top
            },
            modifier = when (state) {
                gameState.MAINMENU -> Modifier.fillMaxWidth()
                gameState.COUNTDOWN -> Modifier.fillMaxWidth()
                gameState.RUNNING -> Modifier.fillMaxWidth().height(300.dp).padding(horizontal = 50.dp).padding(bottom = 150.dp)
                gameState.GAMEOVER -> Modifier.fillMaxWidth().fillMaxHeight()
            }
        )
        {
            val correct = correctAnswer(round)
            val answers = remember(round) {
                listOf(
                    correct, wrongAnswer(correct)).shuffled()
            }

            fun checkAnswer(answer: ColorName) {
                val correctAnswer = when (round.mode) {
                    Mode.COLOR -> round.color
                    Mode.TEXT -> round.word
                }

                if (answer == correctAnswer) {
                    correctAns++
                } else {
                    mistakeCount++

                    if (mistakeCount >= 3) {
                        state = gameState.GAMEOVER
                        return
                    }
                }

                if (correctAns >= bestScore){
                    bestScore = correctAns
                }

                round = nextRound()
                timeLeft = 5
            }

            if (state == gameState.MAINMENU || state == gameState.RUNNING) {
                Button(
                    onClick = {
                        when (state) {
                            gameState.MAINMENU -> {
                                state = gameState.COUNTDOWN
                            }
                            gameState.RUNNING -> {
                                checkAnswer(answers[0])
                            }
                            else -> {}

                        } },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(186, 200, 209))
                        ) {
                    when (state) {
                        gameState.MAINMENU -> {
                            buttonText = "Start Game"
                        }
                        gameState.RUNNING -> {
                            buttonText = answers[0].name
                        }
                        else -> {}
                    }
                    Text(
                        buttonText
                    )

                }
            }
                    if (state == gameState.RUNNING) {
                        Button(
                            onClick = {
                                when (state) {
                                    gameState.RUNNING -> {
                                        checkAnswer(answers[1])
                                    }
                                    else -> {}

                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(186, 200, 209))
                        ) {
                            Text(answers[1].name)
                        }
                    }
            if (state == gameState.GAMEOVER) {
                Column (
                    Modifier.fillMaxWidth().padding(20.dp).fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {

                    Button(
                        onClick = {
                            state = gameState.COUNTDOWN
                            correctAns = 0
                            mistakeCount = 0
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(186, 200, 209))
                    ) {
                        Text("Restart Game")
                    }

                    Button(
                        onClick = {
                            state = gameState.MAINMENU
                            correctAns = 0
                            mistakeCount = 0
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(186, 200, 209))
                    ) {
                        Text("Exit")
                    }
                }
            }
        }
    }

        when (state) {
            gameState.MAINMENU -> {
                text1 = "WELCOME\nTO\nCOLOR WORD MATCHING"
                buttonText= "Start Game"
            }
            gameState.COUNTDOWN -> {

            }

            gameState.RUNNING -> {}
            gameState.GAMEOVER -> {}
        }
    }
fun wrongAnswer(correct: ColorName): ColorName {
    return ColorName.entries.filter { it != correct }.random()
}
fun correctAnswer(round: Round): ColorName {
    return when (round.mode) {
        Mode.COLOR -> round.color
        Mode.TEXT -> round.word
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    Week3Theme{
        com.eric.week3.view()
    }
}

@Composable
fun Theme(content: @Composable () -> Unit) {
    TODO("Not yet implemented")
}
