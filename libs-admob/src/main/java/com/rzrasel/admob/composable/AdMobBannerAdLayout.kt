package com.rzrasel.admob.composable

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.rzrasel.admob.bannerad.AdMobBannerAd

@Composable
fun AdMobBannerAdLayout(
    context: Context,
    index: Int = 0,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            //.defaultMinSize(minHeight = 40.dp)
            .wrapContentHeight()
            //.weight(1f)
            .background(Color("#00000fff".toColorInt())),
    ) {
        //Text("Layer 1.1", fontSize = 40.sp)
        AdMobBannerAd.AdMobBannerLayout(
            context = context,
            modifier = Modifier.fillMaxWidth(),
            bannerAdIndex = index,
        )
        Spacer(modifier = Modifier.height(0.dp))
    }
}