package com.example.hangman

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hangman.ui.theme.HangmanTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen()
        }
    }
}


@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val wordsArray = stringArrayResource(id = R.array.words)
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            GameStartButton(
                stringResource(R.string.start_text),
                true,
                textColor = colorScheme.primary,
            ) {
                val mysteryWord = getMysteryWord(words = wordsArray)
                Log.d("MainActivity", "Mystery word: $mysteryWord")

            }
        }
    }
}

@Composable
fun GameStartButton(
    text: String,
    enabled: Boolean,
    textColor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    TextButton(onClick = onClick, enabled = enabled) {
        Text(
            text = text,
            textAlign = TextAlign.Center, // Center the text
            // Change the text color when disabled. Note that if the text color wasn't changeable,
            // the text would be greyed out when disabled by default.
            color = if (enabled) textColor else textColor.copy(alpha = 0.5f),
            fontFamily = FontFamily.Cursive, // Add some style to the text
            fontSize = 36.sp,
            modifier = Modifier
                .padding(16.dp)
        )
    }
}

private fun getMysteryWord(words: Array<String>): String {
    return words.random().uppercase()
}

@Preview(showBackground = false)
@Composable
fun GameStartButtonPreview() {
    HangmanTheme {
        MainScreen()
    }
}