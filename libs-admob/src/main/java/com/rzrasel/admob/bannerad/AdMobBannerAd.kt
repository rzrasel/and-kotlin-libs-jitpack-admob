package com.rzrasel.admob.bannerad

/*import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.viewinterop.NoOpUpdate
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.rzrasel.admob.config.AdMobConfig

object AdMobBannerAd {

    @Composable
    fun AdMobBannerLayout(
        modifier: Modifier = Modifier,
        bannerAdIndex: Int = 0,
    ) {
        if (AdMobConfig.IS_FORCE_BANNER_AD_OFF) {
            Column(modifier = modifier) {}
            return
        }

        val bannerAdIdRes = AdMobConfig.getBannerAdId(index = bannerAdIndex)

        AndroidView(
            modifier = modifier.fillMaxWidth(),
            factory = { context ->
                AdView(context).apply {
                    setAdSize(AdSize.BANNER)
                    adUnitId = context.getString(bannerAdIdRes)
                    loadAd(AdRequest.Builder().build())
                }
            },
            update = { NoOpUpdate }
        )
    }
}*/

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.rzrasel.admob.config.AdMobConfig

object AdMobBannerAd {

    @Composable
    fun AdMobBannerLayout(
        modifier: Modifier = Modifier,
        bannerAdIndex: Int = 0,
    ) {
        /*Column(
            modifier = modifier
        ) {
        }
        return*/
        if(AdMobConfig.IS_FORCE_BANNER_AD_OFF) {
            Column(
                modifier = modifier
            ) {
            }
            return
        }
        /*if(AdMobConfig.getSetBannerAdOnOff()) {
            Column(
                modifier = modifier
            ) {
            }
            return
        }*/
        val bannerAdId = AdMobConfig.getBannerAdId(index = bannerAdIndex)
        /*if(!AdMobConfig.IS_DEBUG_MODE) {
            bannerAdId = AdMobConfig.getBannerAdId(index = index)
        }*/
        AndroidView(
            modifier = modifier.fillMaxWidth(),
            factory = { context ->
                // on below line specifying ad view.
                AdView(context).apply {
                    // on below line specifying ad size
                    //adSize = AdSize.BANNER
                    // on below line specifying ad unit id
                    // currently added a test ad unit id.
                    setAdSize(AdSize.BANNER)
                    //adUnitId = "ca-app-pub-3940256099942544/6300978111"
                    adUnitId = context.getString(bannerAdId)
                    //adUnitId = bannerAdUnitId
                    // calling load ad to load our ad.
                    loadAd(AdRequest.Builder().build())
                    /*layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )*/
                }
            }
        )
    }
}