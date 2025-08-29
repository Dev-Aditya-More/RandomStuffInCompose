package com.example.randomstuffincompose

import android.R.attr.progress
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.randomstuffincompose.ui.theme.Purple40
import com.example.randomstuffincompose.ui.theme.RandomStuffInComposeTheme
import kotlinx.serialization.Serializable
import java.util.Collections.rotate
import kotlin.math.PI
import kotlin.math.atan2

sealed interface Root : NavKey {
    @Serializable
    data object List : Root
    @Serializable
    data class Details(val text: String) : Root
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RandomStuffInComposeTheme {

                val backstack = rememberNavBackStack<Root>(Root.List)
                NavDisplay(
                    backstack,
                    onBack = {
                        backstack.removeLastOrNull()
                    },
                    entryProvider = entryProvider{
                        entry<Root.List>{
                            ListScreen(
                                onClick = {
                                    backstack.add(Root.Details(it))
                                }
                            )
                        }
                        entry<Root.Details>{ key ->
                            DetailsScreen(text = key.text)
                        }
                    }
                )
            }
        }
    }
}


