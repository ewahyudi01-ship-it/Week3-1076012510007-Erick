package com.eric.week3

import android.R.attr.color
import android.widget.Toast
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.delay
import androidx.compose.material3.ButtonDefaults
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eric.week3.ui.theme.Week3Theme
import kotlin.random.Random

class CoffeOrder_BonusSoal : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Week3Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    viewUi(Modifier.padding(innerPadding))
                }
            }
        }
    }
}
@Composable
fun viewUi (modifier: Modifier = Modifier) {

    var count by rememberSaveable { mutableIntStateOf(0) }
    var subTotal by rememberSaveable { mutableIntStateOf(0) }
    var caramelLattePrice by rememberSaveable { mutableIntStateOf(25000) }
    var pajakRestoPersentase by rememberSaveable { mutableIntStateOf(10) }
    var pajakResto by rememberSaveable { mutableIntStateOf(750) }

    var isLarge by rememberSaveable { mutableStateOf(false) }
    var largeCost by rememberSaveable { mutableIntStateOf(0) }

    var btnRegular by remember { mutableStateOf(Color(0xFFDF692F))}
    var btnLarge by remember { mutableStateOf(Color(0xFFfae5ca))}
    var btnRegularTextColor by remember { mutableStateOf(Color(0xFFDF692F))}
    var btnLargeTextColor by remember { mutableStateOf(Color(0xFF785539))}

    val context = LocalContext.current


    Column(
        Modifier.fillMaxSize(   ).background(Color(252,248,246)).padding(   horizontal = 27.dp, vertical = 50.dp)
    ) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text ("Kopi Kenangan Senja", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color(61,23,11))

            Image(painter = painterResource(id = R.drawable.outline_coffee_24),
                contentDescription = null
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        Column(
            modifier = Modifier.fillMaxWidth().background(color = Color.White, shape = RoundedCornerShape(16.dp)).border(2.dp, Color(240, 220, 205), RoundedCornerShape(16.dp)).padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.fillMaxWidth().height(180.dp).background(Color(139, 69, 19), RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.outline_coffee_24),
                    contentDescription = null,
                    tint = Color(240, 220, 205),
                    modifier = Modifier.size(50.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                "Caramel Latte",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color(61, 23, 11)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text("Espresso shot, steamed milk & caramel syrup",
                fontSize = 12.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text("Rp 25.000 / cup",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color(223, 105, 47)
            )

        }

        Column() {
            Text(
                "PILIHAN UKURAN:",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color(117, 57, 26),
                modifier = Modifier.padding(top = 13.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button (
                    onClick = {
                        btnRegular = Color(0xFFDF692F)
                        btnLarge = Color(0xFFfae5ca)
                        btnRegularTextColor = Color(0xFFDF692F)
                        btnLargeTextColor = Color(0xFF785539)

                        isLarge = false
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFCF8F6)),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .border(
                            width = 2.dp,
                            color = btnRegular,
                            shape = RoundedCornerShape(8.dp)
                    ).weight(1f)
                )
                {
                    Text(
                        "Regular (+0)",
                        color = btnRegularTextColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                }
                Button (
                    onClick = {
                        btnRegular = Color(0xFFfae5ca)
                        btnLarge = Color(0xFFDF692F)
                        btnRegularTextColor = Color(0xFF785539)
                        btnLargeTextColor = Color(0xFFDF692F)

                        if (count > 0) {
                            if (!isLarge) {
                                isLarge = true
                            }
                        }

                    },
                    colors = ButtonDefaults.buttonColors(containerColor =  Color(0xFFFCF8F6)),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .border(
                        width = 2.dp,
                        color = btnLarge,
                        shape = RoundedCornerShape(8.dp)
                    ).weight(1f)
                )  {
                    Text(
                        "Large (+6k)",
                        color = btnLargeTextColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(18.dp))

        Column(
            Modifier.weight(1f).height(60.dp)
        ) {
            Row(
                modifier = Modifier.weight(1f).background(Color.White,RoundedCornerShape(8.dp)).border(2.dp, Color(240, 220, 205), RoundedCornerShape(8.dp)).padding( 10.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Jumlah Pesanan: ",
                    color = Color(61, 23, 11),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                Button(
                    onClick = {
                        if (count > 0) {
                            count--
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.border(
                        width = 4.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(12.dp)
                    )
                ) {
                    Text(
                        "-",
                        fontWeight = FontWeight.Bold,
                        color = Color(61, 23, 11),
                        fontSize = 25.sp
                    )
                }
                Text(
                    count.toString(),
                    fontWeight = FontWeight.Bold,
                    color = Color(61, 23, 11),
                    fontSize = 25.sp
                )
                Button(
                    onClick = {
                        count++
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(223, 105, 47)),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.border(
                        width = 4.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(12.dp)
                    )
                ) {
                    Text(
                        "+",
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 25.sp
                    )
                }
            }

        }
        Spacer(modifier = Modifier.height(18.dp))

        subTotal = caramelLattePrice * count + largeCost
        val pajakResto = subTotal * pajakRestoPersentase / 100
        val totalTagihan = subTotal + pajakResto

        if (isLarge && count > 0) {
            largeCost = 6000
        } else {
            largeCost = 0
        }

        Column(
            modifier = Modifier.fillMaxWidth()
                .drawBehind {
                drawRoundRect(
                    color =  Color(240, 220, 205),
                    style = Stroke(
                        width = 2.dp.toPx(),
                        pathEffect = PathEffect.dashPathEffect(
                            floatArrayOf(5.dp.toPx(), 5.dp.toPx())
                        )
                    )
                )
            },
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Column (
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Subtotal: ", fontSize = 13.sp, color = Color(100, 70, 60))
                    Text("Rp. $subTotal".toString(), fontSize = 13.sp, color = Color(100, 70, 60))
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Pajak Resto ($pajakRestoPersentase%):", fontSize = 13.sp, color = Color(100, 70, 60))
                    Text("Rp $pajakResto", fontSize = 13.sp, color = Color(100, 70, 60))
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        "Total Tagihan:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Color(61, 23, 11)
                    )
                    Text(
                        "Rp $totalTagihan",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color(223, 105, 47)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.weight(1f))

        Column {
            Button(
                onClick = {
                    if (count > 0) {
                        Toast.makeText(
                            context,
                            "Pesanan berhasil ditambahkan ke keranjang!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(223, 105, 47)),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth().height(48.dp)
            ) {
                Text(
                    "TAMBAH KE KERANJANG",
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 17.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun previewUi() {
    Week3Theme{
        com.eric.week3.viewUi()
    }
}