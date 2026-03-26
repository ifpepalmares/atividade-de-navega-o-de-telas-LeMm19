package com.example.composenavigationapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.*
import com.example.composenavigationapp.ui.theme.ComposeNavigationAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ComposeNavigationAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFFFF8FB) // off-white rosado
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "screen_a") {
        composable("screen_a") {
            ScreenA(navController)
        }
        composable("screen_b?message={message}") {
            val message = it.arguments?.getString("message")
            ScreenB(navController, message)
        }
    }
}

@Composable
fun ScreenA(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Momento de leveza 🌷",
            color = Color(0xFF7E57C2) // lilás suave
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.navigate("screen_b?message=Respire fundo... você está indo bem 💜")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFD1C4E9) // lilás claro
            )
        ) {
            Text("Receber mensagem positiva", color = Color(0xFF4A148C))
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = { navController.navigate("screen_b") },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFEDE7F6) // lilás bem clarinho
            )
        ) {
            Text("Ir sem mensagem", color = Color(0xFF6A1B9A))
        }
    }
}

@Composable
fun ScreenB(navController: NavController, message: String?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Pequena pausa 🌙",
            color = Color(0xFF5E35B1)
        )

        Spacer(modifier = Modifier.height(16.dp))

        message?.let {
            Text(
                text = it,
                color = Color(0xFF7E57C2)
            )
        } ?: Text(
            text = "Às vezes, só parar já é suficiente 🤍",
            color = Color(0xFF9575CD)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.popBackStack() },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFD1C4E9)
            )
        ) {
            Text("Voltar", color = Color(0xFF4A148C))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenAPreview() {
    ComposeNavigationAppTheme {
        ScreenA(rememberNavController())
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenBPreview() {
    ComposeNavigationAppTheme {
        ScreenB(rememberNavController(), "Respire fundo... você está indo bem 💜")
    }
}