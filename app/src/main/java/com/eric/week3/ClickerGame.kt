package com.eric.week3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.eric.week3.ui.theme.Week3Theme

class ClickerGame : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Week3Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Test2(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Test2(modifier: Modifier = Modifier) {
    var textYourCoins by rememberSaveable { mutableStateOf("Your Coins") }
    var count by rememberSaveable { mutableDoubleStateOf(1.0) }
    var isPressed by rememberSaveable { mutableStateOf(false) }
    var coins by rememberSaveable { mutableDoubleStateOf(0.0) }
    var quotaTarget by rememberSaveable { mutableIntStateOf(10) }


    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.a273765cdfe8bd1bdb0b028c71bf5aae),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.tint(
                color = Color.Black.copy(alpha = 0.5f),
                blendMode = BlendMode.Darken
            )
        )
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(90.dp, alignment = Alignment.CenterVertically),
        modifier = modifier.fillMaxSize().padding(30.dp)
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.background(
                color = Color.White.copy(alpha = 0.35f),
                shape = RoundedCornerShape(16.dp)
            ).padding(vertical = 16.dp, horizontal = 24.dp)
        ) {
            Text(
                textYourCoins, fontSize = 30.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
            Text(
                "${coins.toInt()}", fontSize = 45.sp,
                color = Color.Green,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
            Text(
                "${count.toInt()} coins per tap", fontSize = 20.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Tap the Cat!",
                fontSize = 20.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold)

            Image(
                painter = painterResource(
                    id = if (isPressed) R.drawable.catmeow
                    else R.drawable.cat1
                ),
                contentDescription = null,
                modifier = Modifier
                    .size(177.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onPress = {
                                coins = (coins + count)
                                isPressed = true
                                tryAwaitRelease()
                                isPressed = false
                            }
                        )
                    }
            )
            Text(
                text = if (isPressed) "Meow!" else "Purr~",
                modifier = Modifier.padding(vertical = 18.dp),
                fontSize = 20.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp, alignment = Alignment.CenterVertically),
                modifier = modifier.fillMaxWidth().background(Color(214,214,214),
                    shape = RoundedCornerShape(14.dp)).padding(vertical = 24.dp, horizontal = 24.dp)
            ) {
                    Text(
                        "Give Me Your Coin",
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )

                Text(
                    "Next upgrade: +${count*1.5} coins per tap",
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center
                )

                FloatingActionButton(

                    onClick = {
                        if (coins >= quotaTarget){
                            count *= 1.5
                            coins = coins - quotaTarget
                            quotaTarget = quotaTarget * 2
                        }
                    },containerColor = if (coins >= quotaTarget) {
                        Color(76, 176, 80)
                    } else {
                        Color.LightGray
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Find $quotaTarget more coins",
                        fontSize = 20.sp,
                        color =  if (coins >= quotaTarget){
                            Color.Black
                        } else {
                            Color.Gray
                        }
                    )
                }

            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun TestPreview2() {
    Week3Theme{
        com.eric.week3.Test2()
    }
}

@Composable
fun Test2Theme(content: @Composable () -> Unit) {
    TODO("Not yet implemented")
}
