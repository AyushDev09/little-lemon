package com.example.androidcapstone

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@Composable
fun OnboardingScreen(navController: NavController){

    var text = remember { mutableStateOf("") }

    Column(
        Modifier.fillMaxWidth().fillMaxHeight()
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            Image(painter = painterResource(id=R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.size(width = 200.dp, height = 100.dp)
            )
        }
        Box(
            modifier = Modifier.size(width = 500.dp, height = 150.dp)
                .background(Color.DarkGray)
                .padding(20.dp)
        ){
            Text(text = "Let's get to know you",
                fontSize = 30.sp,
                color = Color.White,
                modifier = Modifier.align(Alignment.Center))
        }

        Text(text = "Personal information",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(20.dp))

        Column(
            modifier = Modifier.weight(1f))
        {
            OutlinedTextField(
                value = text.value,
                onValueChange = { text.value = it },
                placeholder = { Text("Tilly ")},
                label = { Text("First name") },
                modifier = Modifier.padding(30.dp).size(width = 400.dp, height = 68.dp)
            )
            OutlinedTextField(
                value = text.value,
                onValueChange = { text.value = it },
                placeholder = { Text("Jameson ")},
                label = { Text("Last name") },
                modifier = Modifier.padding(30.dp).size(width = 400.dp, height = 68.dp)
            )
            OutlinedTextField(
                value = text.value,
                onValueChange = { text.value = it },
                placeholder = { Text("T@gmail.com ")},
                label = { Text("Email") },
                modifier = Modifier.padding(30.dp).size(width = 400.dp, height = 68.dp)
            )
        }

        Button(onClick = {
            navController.navigate(Home.route)
        },
            modifier = Modifier.fillMaxWidth()
                .padding(20.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Yellow, // Background color
                contentColor = Color.Black))
            {
            Text("Register")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    val navController = rememberNavController()
    OnboardingScreen(navController)
}

