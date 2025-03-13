package com.example.hangman.game

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import java.util.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hangman.R



@Composable
private fun GallowsImage(resId: Int, modifier: Modifier = Modifier, tint: Color = Color.Black) {
    Image(
        painter = painterResource(resId),
        contentDescription = null,
        colorFilter = ColorFilter.tint(tint),
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
private fun GuessWordText(word: String, modifier: Modifier = Modifier) {
    Text(
        text = word.uppercase(Locale.getDefault()),
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold, // The font weight of the text.
        letterSpacing = 8.sp, // The spacing between the letters.
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
private fun UsedLettersText(usedLetters: String, modifier: Modifier) {
    Column {
        Text(
            text = stringResource(R.string.used_letters),
            textAlign = TextAlign.Left, // make the text left-aligned
            fontWeight = FontWeight.Light,
            color = colorScheme.primary, // set the color of the text to the primary color defined in Theme.kt
            modifier = modifier.fillMaxWidth()
        )
        Text(
            text = usedLetters.uppercase(Locale.getDefault()),
            textAlign = TextAlign.Left,
            fontWeight = FontWeight.Light,
            color = colorScheme.error, // set the color of the text to the error color. By default it is not specified in the Theme.kt
            modifier = modifier.fillMaxWidth()
        )
    }
}

@Composable
fun LetterInputField(
    buttonText: String,
    buttonEnabled: Boolean,
    isError: Boolean,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier,

) {
    var textFieldValue by remember { mutableStateOf("") }
    Log.d("LetterInputField", "textFieldValue: $textFieldValue")
    // The LetterInputField composable is a Row that contains a TextField and a Button.
    // The Row is a container that lays out its children in a horizontal line.
    Row(
        modifier = modifier.fillMaxWidth(), // The Row fills the available width
        horizontalArrangement = Arrangement.Center, // The children are centered horizontally
    ) {
        TextField(
// The value of the TextField. The label is displayed when the value
// is empty and not in focus.
            value = "",
// The callback that is called when the value changes.
            onValueChange = { },
            singleLine = true,
            // The shape can be modified.
            shape = RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp),
            // hint for the user what should be input in the TextField.
            label = { Text(text = stringResource(R.string.enter_letter)) },
            isError = isError
        )
        // The Button is displayed to the right of the TextField.
        Button(
            onClick = onButtonClick, // The callback that is called when the Button is clicked.
            enabled = buttonEnabled, // The Button can be enabled/disabled.
            shape = RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 8.dp,
                bottomStart = 0.dp,
                bottomEnd = 8.dp
            ), // we can modify the shape of the Button.
            modifier = Modifier
                .defaultMinSize(minHeight = TextFieldDefaults.MinHeight) // Match TextField height
        ) {
            Text(text = buttonText)
        }
    }
}