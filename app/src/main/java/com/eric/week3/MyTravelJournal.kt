package com.eric.week3

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.benchmark.traceprocessor.Row
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eric.week3.ui.theme.Week3Theme


class MyTravelJournal : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Week3Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    viewTravelJournal(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun viewTravelJournal (modifier: Modifier = Modifier) {

    var textSpotWisata by rememberSaveable { mutableStateOf("") }
    var textPalingNikmati by rememberSaveable { mutableStateOf("") }
    var textCatatan by rememberSaveable { mutableStateOf("") }
    var tersimpan by rememberSaveable { mutableStateOf("") }


    var selectedRating by rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        Modifier.fillMaxSize(   ).background(Color(17,23,41)).padding(   horizontal = 20.dp, vertical = 40.dp)
    ) {
        Column(
            Modifier.fillMaxWidth().heightIn(120.dp,150.dp).
            background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(118,70,0),
                        Color(94,0,79),
                        Color(0,67,118)
                    )
                ),
                shape = RoundedCornerShape(16.dp)
            )
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    text = "LOG PERJALANAN",
                    color = Color.White
                )

                Text(
                    text = "Tromsø, Norway ❄️",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Ekspedisi Aurora Borealis",
                    color = Color.White
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = textSpotWisata,
            onValueChange = {
                textSpotWisata = it
            },
            placeholder = {
                Text("SPOT WISATA FAVORIT",
                    fontSize = 13.sp,
                    color = Color(151,162,182),
                    fontWeight = FontWeight.Bold
                )
            },
            modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp).clip(RoundedCornerShape(16.dp))
                .background(Color(32, 41, 50)),
            shape = RoundedCornerShape(16.dp),
            minLines = 1,
            maxLines = 2
        )

        OutlinedTextField(
            value = textPalingNikmati,
            onValueChange = {
                textPalingNikmati = it
            },
            placeholder = {
                Text("APA YANG PALING KAMU NIKMATI",
                    fontSize = 13.sp,
                    color = Color(151,162,182),
                    fontWeight = FontWeight.Bold
                )
            },
            modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp).clip(RoundedCornerShape(16.dp))
                .background(Color(32, 41, 50)),
            shape = RoundedCornerShape(16.dp),
            minLines = 3
        )

        OutlinedTextField(
            value = textCatatan,
            onValueChange = {
                textCatatan = it
            },
            placeholder = {
                Text("CATATAN TAMBAHAN / PERLENGKAPAN\n",
                    fontSize = 13.sp,
                    color = Color(151,162,182),
                    fontWeight = FontWeight.Bold
                )
                Text("\n(Ketik perlengkapan ekstra di sini..)",
                    fontSize = 13.sp,
                    color = Color(101,112,132)
                )
            },
            modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp).clip(RoundedCornerShape(16.dp))
                .background(Color(32, 41, 50)),
            shape = RoundedCornerShape(16.dp)
        )

        Text(
            "TINGKAT KEPUASAN:",
            fontSize = 13.sp,
            color = Color(151,162,182),
            fontWeight = FontWeight.Bold
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Button(
                onClick = {
                    selectedRating = "Biasa"
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor =
                        if (selectedRating == "Biasa")
                            Color(98, 186, 243)
                        else
                            Color.Transparent
                ),
                border = BorderStroke(1.dp, Color(80, 100, 130)),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text("Biasa",
                    color = if (selectedRating == "Biasa")
                        Color.Black
                    else
                        Color.White)
            }

            Button(
                onClick = {
                    selectedRating = "Seru"
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor =
                        if (selectedRating == "Seru")
                            Color(98, 186, 243)
                        else
                            Color.Transparent
                ),
                border = BorderStroke(1.dp, Color(80, 100, 130)),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text("Seru",
                    color = if (selectedRating == "Seru")
                        Color.Black
                    else
                        Color.White)
            }

            Button(
                onClick = {
                    selectedRating = "Luar Biasa ★"
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor =
                        if (selectedRating == "Luar Biasa ★")
                            Color(98, 186, 243)
                        else
                            Color.Transparent
                ),
                border = BorderStroke(1.dp, Color(80, 100, 130)),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text("Luar Biasa ★",
                    color = if (selectedRating == "Luar Biasa ★")
                        Color.Black
                    else
                        Color.White)

            }
        }
        Box (
            modifier = Modifier.fillMaxSize().padding(bottom = 3.dp)
        ){
            Text(
                "Status: ",
                fontSize = 13.sp,
                color = Color(151, 162, 182),
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .offset(y = 10.dp)
            )
            if (
                selectedRating.isNotEmpty() ||
                textPalingNikmati.isNotEmpty() ||
                textCatatan.isNotEmpty() ||
                textSpotWisata.isNotEmpty()
            ) {
                Text(
                    "Tersimpan",
                    fontSize = 13.sp,
                    color = Color(167, 177, 243),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .offset(45.dp, 10.dp)
                )
            }

            FloatingActionButton(
                onClick = {
                    if (!selectedRating.isEmpty()) {
                        Toast.makeText(
                            context,
                            "Review telah berhasil dikirimkan!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                containerColor = Color(167,177,243),
                modifier = Modifier.align(Alignment.BottomEnd).offset(0.dp,10.dp)
            ) {
                Text("+",
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun previewTravelJournal() {
    Week3Theme{
        com.eric.week3.viewTravelJournal()
    }
}