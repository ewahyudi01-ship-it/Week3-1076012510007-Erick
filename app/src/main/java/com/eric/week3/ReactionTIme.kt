package com.eric.week3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.delay
import androidx.compose.material3.ButtonDefaults
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eric.week3.ui.theme.Week3Theme
import kotlin.random.Random

class ReactionTime : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Week3Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Test(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

enum class GameState {
    INITIAL,
    WAITING,
    FAILED,
    GO,
    TRIAL_COMPLETE,
    FINISHED
}

@Composable
fun Test(modifier: Modifier = Modifier) {

    var gameState by rememberSaveable { mutableStateOf(GameState.INITIAL) }
    var textAtas by rememberSaveable{ mutableStateOf("Reaction") }
    var textBawah by rememberSaveable{ mutableStateOf("Test") }
    var buttonText by rememberSaveable{ mutableStateOf("Click to start")  }
    var trial by rememberSaveable{ mutableIntStateOf(1) }

    var startTime by rememberSaveable {
        mutableLongStateOf(0L)
    }

    var timeTrial1 by rememberSaveable {
        mutableLongStateOf(0L)
    }

    var timeTrial2 by rememberSaveable {
        mutableLongStateOf(0L)
    }

    var timeTrial3 by rememberSaveable {
        mutableLongStateOf(0L)
    }
    val averageReactionTime =  calculateAverage(timeTrial1, timeTrial2, timeTrial3)

    val backgroundColor = when (gameState) {
        GameState.INITIAL -> Color(113, 205, 250)
        GameState.WAITING -> Color(218, 218, 218)
        GameState.GO -> Color(75, 175, 79)
        GameState.FAILED -> Color(254, 68, 69)
        GameState.TRIAL_COMPLETE -> Color(75, 175, 79)
        GameState.FINISHED -> {
            if (averageReactionTime < 180) {
                Color(0, 230, 118)
            } else if (averageReactionTime in 180..<280) {
                Color(33, 150, 243)
            } else if (averageReactionTime in 280..<450) {
                Color(255, 151, 0)
            } else if (averageReactionTime >= 450) {
                Color(254, 87, 34)
            } else {
                Color(254, 87, 34)
            }

        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(30.dp, alignment = Alignment.CenterVertically),
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor).padding(50.dp)

    ) {

        LaunchedEffect(gameState) {
            if (gameState == GameState.WAITING) {
                val randomDelay = Random.nextLong(500, 4501)
                delay(randomDelay)

                startTime = System.currentTimeMillis()
                gameState = GameState.GO
            }
        }

    when (gameState) {
        GameState.INITIAL->{
            textAtas = "Reaction"
            textBawah = "Test"
        }
        GameState.WAITING -> {
            textAtas = "Get Ready"
            textBawah = "Wait for green light"
            buttonText ="DON'T CLICK YET!"
        }
        GameState.GO -> {
            textAtas="GO!"
            textBawah="CLICK NOW!"
            buttonText ="TAP AS FAST AS YOU CAN!"

        }
        GameState.FAILED -> {
            textAtas="FAIL!"
            textBawah="You clicked too early, TRY TO READ THE RULE BRO"
            buttonText ="TRY AGAIN!"

        }
        GameState.TRIAL_COMPLETE -> {
            textAtas="Trial $trial Complete!"
            textBawah =
                when (trial) {
                    1 -> {
                        "Time: $timeTrial1 ms"
                    }

                    2 -> {
                        "Time: $timeTrial2 ms"
                    }

                    3 -> {
                        "Time: $timeTrial3 ms"
                    }

                    else -> {}
                } as String

            buttonText =
                (if (trial < 3) {
                    "Continue to ${trial + 1}"
                } else {
                    ""
                })
        }

        GameState.FINISHED -> {

            buttonText="Click to Start New Test"

            if (averageReactionTime < 180) {
                textAtas="DANG YOU ARE SO FAST BRO!"
                textBawah="AVERAGE: $averageReactionTime ms"
            } else if (averageReactionTime in 180..<280) {
                textAtas="YOUR REFLEX IS GOOD"
                textBawah="AVERAGE: $averageReactionTime ms"
            } else if (averageReactionTime in 280..<450) {
                textAtas="MEH LIKE OTHER PERSON"
                textBawah="AVERAGE: $averageReactionTime ms"
            } else if (averageReactionTime >= 450) {
                textAtas="YOU LIKE A SNAIL BRO"
                textBawah="AVERAGE: $averageReactionTime ms"
            }

        }
        else -> {}
    }

        Text(textAtas, fontSize = 29.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold

            )

        Image(
            painter = when (gameState) {
                GameState.INITIAL -> painterResource(id = R.drawable.outline_bolt_24)
                GameState.WAITING -> painterResource(id = R.drawable.outline_crisis_alert_24)
                GameState.FAILED -> painterResource(id = R.drawable.outline_close_24)
                GameState.GO -> painterResource(id = R.drawable.outline_directions_run_24)
                GameState.TRIAL_COMPLETE -> painterResource(id = R.drawable.outline_check_small_24)
                GameState.FINISHED -> {
                    if (averageReactionTime < 180) {
                        painterResource(id = R.drawable._20250917_080739_2_removebg_preview)
                    } else if (averageReactionTime in 180..<280) {
                        painterResource(id = R.drawable._20250917_080744_2_removebg_preview)
                    } else if (averageReactionTime in 280..<450) {
                        painterResource(id = R.drawable._20250917_080749_2_removebg_preview)
                    } else if (averageReactionTime >= 450) {
                        painterResource(id = R.drawable._20250917_080754_2_removebg_preview)
                    } else {
                        painterResource(id = R.drawable._20250917_080754_2_removebg_preview)
                    }
                }

                else -> {}
            } as Painter,
            modifier = Modifier.size(150.dp),
            contentDescription = "Setting icon",
            colorFilter = if (gameState != GameState.FINISHED) {
                ColorFilter.tint(Color.White)
            } else {
                null
            }
        )

        Text(textBawah, fontSize = 19.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )

        Button(
            onClick = {
                when (gameState) {
                    GameState.INITIAL -> {
                        gameState = GameState.WAITING
                    }
                    GameState.WAITING -> {
                        gameState = GameState.FAILED
                    }


                    GameState.GO -> {
                        var reactionTime = System.currentTimeMillis() - startTime

                        when (trial) {
                            1 -> timeTrial1 = reactionTime
                            2 -> timeTrial2 = reactionTime
                            3 -> timeTrial3 = reactionTime
                        }
                        if (trial != 3) {
                        gameState = GameState.TRIAL_COMPLETE
                        } else {
                            gameState = GameState.FINISHED
                        }
                    }

                    GameState.FAILED -> {
                        gameState = GameState.WAITING
                    }

                    GameState.TRIAL_COMPLETE -> {
                        if (trial < 3) {
                            trial++
                            gameState = GameState.WAITING
                        } else {
                            gameState = GameState.FINISHED
                        }
                    }

                    GameState.FINISHED -> {
                        gameState = GameState.INITIAL
                        trial = 1
                        timeTrial1 = 0
                        timeTrial2 = 0
                        timeTrial3 = 0
                    }

                    else -> { }
                }


            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
            elevation = null
        ) {
            Text(buttonText,fontSize = 14.sp,
                color = Color.White

            )
        }
        if (timeTrial1 > 0 && gameState != GameState.WAITING && gameState != GameState.GO) {
            Column(
                modifier = Modifier
                    .background(
                        color = Color(240, 240, 240),
                        shape = RoundedCornerShape(22.dp)
                    )
                    .width(230.dp)
                    .height(160.dp)
                    .padding(10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Trial Results",
                    fontSize = 22.sp,
                    color = Color(34, 117, 211),
                    fontWeight = FontWeight.Bold
                )

                Row {
                    Text(
                        text = "1",
                        modifier = Modifier.padding(horizontal = 20.dp),
                        color = Color(34, 117, 211),
                        fontSize = 18.sp
                    )

                    Text(
                        text = "2",
                        modifier = Modifier.padding(horizontal = 20.dp),
                        color = Color.Gray,
                        fontSize = 18.sp
                    )

                    Text(
                        text = "3",
                        modifier = Modifier.padding(horizontal = 20.dp),
                        color = Color.Gray,
                        fontSize = 18.sp
                    )
                }

                Row {
                    Text(
                        text = if (timeTrial1 > 0) "$timeTrial1 ms" else "-",
                        modifier = Modifier.padding(horizontal = 10.dp),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = if (timeTrial2 > 0) "$timeTrial2 ms" else "-",
                        modifier = Modifier.padding(horizontal = 10.dp),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = if (timeTrial3 > 0) "$timeTrial3 ms" else "-",
                        modifier = Modifier.padding(horizontal = 10.dp),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
fun calculateAverage( time1: Long, time2: Long, time3: Long): Long {
    return (time1 + time2 + time3) / 3
}

@Preview(showBackground = true)
@Composable
fun TestPreview() {
    Week3Theme{
        com.eric.week3.Test()
    }
}

@Composable
fun TestTheme(content: @Composable () -> Unit) {
    TODO("Not yet implemented")
}
