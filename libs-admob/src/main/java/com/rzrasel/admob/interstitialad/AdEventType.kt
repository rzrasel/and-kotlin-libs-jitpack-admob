package com.rzrasel.admob.interstitialad

enum class AdEventType(val event: String) {
    CLOSE("close"),
    ERROR("error"),
    LOADED("loaded"),
    SHOW("show"),
    EMPTY("empty");
    //
    companion object {
        fun find(value: String): AdEventType = entries.find {
            it.event.equals(value, ignoreCase = true)
        } ?: EMPTY
    }
}