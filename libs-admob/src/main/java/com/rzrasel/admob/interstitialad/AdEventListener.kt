package com.rzrasel.admob.interstitialad

interface AdEventListener {
    fun onAdClosed()
    fun onAdError()
    fun onAdLoaded()
    fun onAdShown()
}