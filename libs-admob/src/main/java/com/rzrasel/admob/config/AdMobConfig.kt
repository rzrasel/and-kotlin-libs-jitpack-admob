package com.rzrasel.admob.config

import android.content.Context
import androidx.annotation.StringRes
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration

object AdMobConfig {

    // ---------------------------------------
    // External Config Passed by Application Layer
    // ---------------------------------------
    var IS_FORCE_BANNER_AD_OFF: Boolean = false
        private set

    var IS_FORCE_INTERSTITIAL_AD_OFF: Boolean = false
        private set

    var IS_DEBUG_MODE: Boolean = false
        private set

    // IDs are now fully controlled by the Application layer
    @StringRes
    private var debugBannerAdId: Int = 0

    @StringRes
    private var debugInterstitialAdId: Int = 0

    @StringRes
    private var liveBannerAdIds: List<Int> = emptyList()

    @StringRes
    private var liveInterstitialAdIds: List<Int> = emptyList()

    // ---------------------------------------
    // INITIALIZE FROM APPLICATION LAYER
    // ---------------------------------------
    fun init(
        context: Context,
        isDebugMode: Boolean,
        isForceBannerAdOff: Boolean = false,
        isForceInterstitialAdOff: Boolean = false,

        // Ad IDs passed from App Layer
        @StringRes debugBannerAdId: Int,
        @StringRes debugInterstitialAdId: Int,
        @StringRes liveBannerAdIds: List<Int>,
        @StringRes liveInterstitialAdIds: List<Int>,
    ) {
        // assign flags
        IS_DEBUG_MODE = isDebugMode
        IS_FORCE_BANNER_AD_OFF = isForceBannerAdOff
        IS_FORCE_INTERSTITIAL_AD_OFF = isForceInterstitialAdOff

        // assign ad IDs
        this.debugBannerAdId = debugBannerAdId
        this.debugInterstitialAdId = debugInterstitialAdId
        this.liveBannerAdIds = liveBannerAdIds
        this.liveInterstitialAdIds = liveInterstitialAdIds

        // initialize SDK
        if (!IS_FORCE_BANNER_AD_OFF || !IS_FORCE_INTERSTITIAL_AD_OFF) {
            MobileAds.setRequestConfiguration(getRequestConfiguration())
            MobileAds.initialize(context)
        }
    }

    // ---------------------------------------
    // Request Configuration
    // ---------------------------------------
    fun getRequestConfiguration(): RequestConfiguration {
        return MobileAds.getRequestConfiguration()
            .toBuilder()
            .setTagForChildDirectedTreatment(
                RequestConfiguration.TAG_FOR_CHILD_DIRECTED_TREATMENT_TRUE
            )
            .setMaxAdContentRating(RequestConfiguration.MAX_AD_CONTENT_RATING_G)
            .build()
    }

    // ---------------------------------------
    // Banner Ad
    // ---------------------------------------
    fun getBannerAdId(index: Int = 0): Int {
        return if (IS_DEBUG_MODE) {
            debugBannerAdId
        } else {
            liveBannerAdIds.getOrElse(index) { liveBannerAdIds.first() }
        }
    }

    // ---------------------------------------
    // Interstitial Ad
    // ---------------------------------------
    fun getInterstitialAdId(index: Int = 0): Int {
        return if (IS_DEBUG_MODE) {
            debugInterstitialAdId
        } else {
            liveInterstitialAdIds.getOrElse(index) { liveInterstitialAdIds.first() }
        }
    }
}