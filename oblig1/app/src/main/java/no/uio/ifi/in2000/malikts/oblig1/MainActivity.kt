package no.uio.ifi.in2000.malikts.oblig1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import no.uio.ifi.in2000.malikts.oblig1.ui.palindrome.PalindromeChecker
import no.uio.ifi.in2000.malikts.oblig1.ui.theme.Malikts_oblig1Theme
import no.uio.ifi.in2000.malikts.oblig1.ui.unitconverter.UnitConverter


sealed class Screens(val route: String) {
    data object PalindromScreen: Screens("ui.palindrome.PalindromeScreen.kt")
    data object UnitConverterScreen: Screens("ui.unitconverter.UnitConverterScreen.kt")
}
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Malikts_oblig1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()

                }

            }
        }
    }

    @Composable
    fun MyApp(){
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Screens.PalindromScreen.route,
        ) {
            composable(Screens.PalindromScreen.route) { PalindromeChecker(navController)}
            composable(Screens.UnitConverterScreen.route) { UnitConverter(navController)}
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun Preview(){
        Malikts_oblig1Theme {
            MyApp()
        }
    }

}



