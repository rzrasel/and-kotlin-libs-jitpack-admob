package com.rzrasel.libs

import android.content.Context
import com.rzrasel.admob.config.AdMobConfig
import com.rzrasel.usagesapplication.BuildConfig
import com.rzrasel.usagesapplication.R

object AdMobInitializer {

    fun init(context: Context) {

        AdMobConfig.init(
            context = context,
            isDebugMode = BuildConfig.DEBUG,

            isForceBannerAdOff = false,
            isForceInterstitialAdOff = false,

            // Debug Ad IDs from app resources
            debugBannerAdId = R.string.admob_debug_banner_ad_id,
            debugInterstitialAdId = R.string.admob_debug_interstitial_ad_id,

            // Live Ad IDs (multiple supported)
            liveBannerAdIds = listOf(
                R.string.admob_live_banner_ad_id,
            ),

            liveInterstitialAdIds = listOf(
                R.string.admob_live_interstitial_ad_id,
            )
        )
    }
}