package com.findmore.reelraves

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Surface
import com.findmore.reelraves.ui.home.HomeScreen
import com.findmore.reelraves.ui.navigation.NavigationGraph
import com.findmore.reelraves.ui.theme.ReelRavesTheme
import com.findmore.reelraves.ui.utils.Screens
import com.findmore.reelraves.ui.welcome.WelcomeScreen
import com.findmore.reelraves.viewmodel.GenreViewModel
import com.findmore.reelraves.viewmodel.MainViewModel
import com.findmore.reelraves.viewmodel.MovieViewModel
import com.findmore.reelraves.viewmodel.SummaryViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReelRavesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    shape = RectangleShape
                ) {
                    ReelRavesApp()

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReelRavesApp() {
    ReelRavesTheme {
         NavigationGraph()
    }

}


