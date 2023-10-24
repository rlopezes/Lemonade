package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}

@Composable
fun LemonadeApp(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Text(
            text = stringResource(R.string.app_name),
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF9E44C))
                .padding(16.dp)
        )

        //Pintar el resto de la pantalla
        LemonadeStep(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        )
    }
}


@Composable
fun LemonadeStep(modifier: Modifier = Modifier) {

    //Variable con el paso actual
    var step by remember { mutableStateOf(0) }
    //Variable pulsaciones aleatorias
    var randomTap by remember { mutableStateOf(0) }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        //0 ->
        var imagen = R.drawable.lemon_tree
        var textoImagen = R.string.lemon_tree
        var textoPie = R.string.select_lemon
        when (step) {
            1 -> {
                imagen = R.drawable.lemon_squeeze
                textoImagen = R.string.lemon
                textoPie = R.string.squeeze_lemon
            }
            2 -> {
                imagen = R.drawable.lemon_drink
                textoImagen = R.string.glass_lemonade
                textoPie = R.string.drink_lemon
            }
            3 -> {
                imagen = R.drawable.lemon_restart
                textoImagen = R.string.empty_glass
                textoPie = R.string.start_again
            }
        }

        Image(
            painter = painterResource(id = imagen),
            contentDescription = stringResource( textoImagen ),
            modifier = Modifier
                .size(250.dp)
                .background(Color(0xFFC3ECD2))
                .clip(RoundedCornerShape(4.dp))
                .border(
                    BorderStroke(2.dp, Color(105, 205, 216 ))
                )
                .clickable {
                    if (randomTap == 0) {
                        step = (step + 1) % 4
                        if (step == 1) {
                            randomTap = (1..3).random()
                        }
                    }
                    else {
                        randomTap--
                    }


                }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource( textoPie )
        )

        Text(
            text = step.toString() + "-" + randomTap.toString()

        )
    }
}


@Preview(showBackground = true)
@Composable
fun LemonadeAppPreview() {
    LemonadeTheme {
        LemonadeApp()
    }
}