package com.example.mymidterm_4_4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mymidterm_4_4.ui.theme.MyMidTerm44Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyMidTerm44Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    myAppScreen()
                }
            }
        }
    }
}

@Composable
fun myAppScreen() {
    var doC by remember {
        mutableStateOf("")
    }
    var doCValue = doC.toIntOrNull() ?: 0

    var colorToFill: Color
    var notification: String

    if (doCValue < 25) {
        colorToFill = Color.Blue
        notification = "Thời tiết lạnh"
    } else if (doCValue >= 25 && doCValue <= 28) {
        colorToFill = Color.Green
        notification = "Thời tiết ôn hòa"
    } else if (doCValue >= 29 && doCValue <= 35) {
        colorToFill = Color.Green
        notification = "Thời tiết nóng"
    } else {
        colorToFill = Color.Red
        notification = "Thời tiết rất nóng"
    }

    Column(
        modifier = Modifier
            .padding(8.dp)
            .verticalScroll(rememberScrollState())
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier) {
            EditNumberField(
                label ="Enter Celsius",
                value = doC,
                onValueChange = { if(it.length <= 3) doC = it },
                modifier = Modifier.fillMaxWidth()
            )
        }
        if(doCValue >= 70){
            warningNoty()
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .border(1.dp, Color(0))
                    .clip(shape = CircleShape)
                    .background(color = colorToFill),
                contentAlignment = Alignment.Center
            ) {
                Text(text = notification, color = Color.Black)
            }

        }
    }
}

@Composable
fun EditNumberField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier
) {
    TextField(
        label = { Text(text = label) },
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        modifier = modifier
    )
}

@Composable
fun warningNoty(){
    Text(text = "Cảnh báo thời tiết diễn biến cực kì nóng", color = Color.Red)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyMidTerm44Theme {
        myAppScreen()
    }
}