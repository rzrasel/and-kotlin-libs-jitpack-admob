package com.rzrasel.admob.composable

import AdMobInterstitialAd
import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.rzrasel.admob.config.AdMobConfig
import com.rzrasel.admob.interstitialad.AdEventListener

@Composable
fun AdMobInterstitialAdView(activity: Activity, index: Int = 0) {
    val context: Context = LocalContext.current
    val adMobInterstitialAd: AdMobInterstitialAd = AdMobInterstitialAd.getInstance(activity, context)
    var isShowInterstitialAd by remember { mutableStateOf(AdMobConfig.IS_FORCE_INTERSTITIAL_AD_OFF) }
    //
    if(!isShowInterstitialAd) {
        //Log.i("DEBUG_LOG", "AdMob: $isShowInterstitialAd line ${Exception().stackTrace.firstOrNull()?.lineNumber}")
        val interstitialAdId = AdMobConfig.getInterstitialAdId(index = 0)
        val interstitialAdUnitId = context.getString(interstitialAdId)
        adMobInterstitialAd.setAdResponseEventListener(object: AdEventListener {
                override fun onAdClosed() {
                    Log.i("DEBUG_LOG", "DEBUG_LOG AdMob: close() line ${Exception().stackTrace.firstOrNull()?.lineNumber}")
                }

                override fun onAdError() {
                    Log.i("DEBUG_LOG", "DEBUG_LOG AdMob: error() line ${Exception().stackTrace.firstOrNull()?.lineNumber}")
                }

                override fun onAdLoaded() {
                    Log.i("DEBUG_LOG", "DEBUG_LOG AdMob: loaded() line ${Exception().stackTrace.firstOrNull()?.lineNumber}")
                }

                override fun onAdShown() {
                    Log.i("DEBUG_LOG", "DEBUG_LOG AdMob: show() line ${Exception().stackTrace.firstOrNull()?.lineNumber}")
                }
            })
            .onLoadAd(adMobUnitId = interstitialAdUnitId)
        isShowInterstitialAd = true
    }
}