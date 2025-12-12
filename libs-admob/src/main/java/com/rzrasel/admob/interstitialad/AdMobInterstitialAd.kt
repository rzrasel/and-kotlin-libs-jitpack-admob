import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.rzrasel.admob.interstitialad.AdEventListener
import com.rzrasel.admob.interstitialad.AdEventType


class AdMobInterstitialAd private constructor(
    val activity: Activity,
    val context: Context
) {
    //
    private var adEventListener: AdEventListener? = null
    private var interstitialAd: InterstitialAd? = null
    private lateinit var adRequest: AdRequest
    private lateinit var adMobUnitId: String
    //
    companion object {

        @SuppressLint("StaticFieldLeak")
        @Volatile private var instance: AdMobInterstitialAd? = null // Volatile modifier is necessary

        fun getInstance(activity: Activity, context: Context) =
            instance ?: synchronized(this) { // synchronized to avoid concurrency problem
                instance ?: AdMobInterstitialAd(activity, context).also { instance = it }
            }
    }

    /*fun getInstance(activity: Activity, context: Context): AdMobInterstitialAd {
        if (instance == null) {
            instance = AdMobInterstitialAd(activity, context)
        }
        return instance as AdMobInterstitialAd
    }*/

    fun getAdRequest(): AdRequest {
        return AdRequest.Builder().build()
    }

    fun setAdResponseEventListener(adEventListener: AdEventListener): AdMobInterstitialAd {
        this.adEventListener = adEventListener
        return this
    }

    fun onLoadAd(adRequest: AdRequest = AdRequest.Builder().build(), adMobUnitId: String) {
        this.adRequest = adRequest
        this.adMobUnitId = adMobUnitId
        //Log.i("DEBUG_LOG", "AdMob: onLoadAd line ${Exception().stackTrace.firstOrNull()?.lineNumber}")
        //callAdResponseEventListener(AdEvent.LOADED);
        onPrepareAd(adRequest, adMobUnitId)
    }

    private fun onPrepareAd(adRequest: AdRequest, adMobUnitId: String) {
        this.adRequest = adRequest
        this.adMobUnitId = adMobUnitId
        //
        InterstitialAd.load(
            context,
            adMobUnitId,
            adRequest,
            object: InterstitialAdLoadCallback() {
                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    interstitialAd.show(activity)
                    onResponseAdEventListener(AdEventType.LOADED);
                    interstitialAd.fullScreenContentCallback = FullScreenContentCallbackImpl()
                }

                override fun onAdFailedToLoad(adError: LoadAdError) {
                    onResponseAdEventListener(AdEventType.ERROR);
                }
            }
        )
    }
    //
    private inner class FullScreenContentCallbackImpl: FullScreenContentCallback() {
        override fun onAdShowedFullScreenContent() {
            onResponseAdEventListener(AdEventType.SHOW);
            //interstitialAd = null;
        }
        override fun onAdDismissedFullScreenContent() {
            onResponseAdEventListener(AdEventType.CLOSE);
        }
        override fun onAdFailedToShowFullScreenContent(adError: AdError) {
            onResponseAdEventListener(AdEventType.ERROR);
        }
    }
    //
    private fun onResponseAdEventListener(adEventType: AdEventType) {
        if(adEventListener == null) {
            return
        }
        when(adEventType) {
            AdEventType.CLOSE -> {
                adEventListener!!.onAdClosed();
            }
            AdEventType.ERROR -> {
                adEventListener!!.onAdError();
            }
            AdEventType.LOADED -> {
                adEventListener!!.onAdLoaded();
            }
            AdEventType.SHOW -> {
                adEventListener!!.onAdShown();
            }
            else -> {}
        }
    }
}