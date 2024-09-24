package io.github.rahman.githubsearch.base

import android.content.SharedPreferences

class Cache (
    val sharedPref: SharedPreferences,
){

    enum class KeyName {
        DeviceId,
        FcmId,
        AuthToken,
    }
    var deviceId: String
        get() = sharedPref.getString(KeyName.DeviceId.name, "") ?: ""
        set(value) = sharedPref.edit().putString(KeyName.DeviceId.name, value).apply()

    var fcmId: String
        get() = sharedPref.getString(KeyName.FcmId.name, "") ?: ""
        set(value) = sharedPref.edit().putString(KeyName.FcmId.name, value).apply()

    var authToken: String
        get() = sharedPref.getString(KeyName.AuthToken.name, "") ?: ""
        set(value) = sharedPref.edit().putString(KeyName.AuthToken.name, value).apply()
}