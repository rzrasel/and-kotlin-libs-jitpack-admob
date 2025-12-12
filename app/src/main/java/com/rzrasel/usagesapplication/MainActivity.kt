package com.rzrasel.usagesapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rzrasel.admob.composable.AdMobBannerAdLayout
import com.rzrasel.admob.composable.AdMobInterstitialAdView
import com.rzrasel.libs.AdMobInitializer
import com.rzrasel.usagesapplication.ui.theme.UsagesApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AdMobInitializer.init(this)
        enableEdgeToEdge()
        setContent {
            UsagesApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                    val activity = this
                    AdMobInterstitialAdView(activity)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        //|-----|DISPLAY ADMOB BANNER AD LAYOUT|-----|
        Spacer(modifier = Modifier.height(46.dp))
        //|----|Load and display AdMob banner ad|----|
        val context = LocalContext.current
        AdMobBannerAdLayout(context)
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UsagesApplicationTheme {
        Greeting("Android")
    }
}