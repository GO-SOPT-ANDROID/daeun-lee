package org.android.go.sopt.data.datasource.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import org.android.go.sopt.BuildConfig

class GSDataStore(context: Context) {
    private val masterKey = MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val dataStore: SharedPreferences =
        if (BuildConfig.DEBUG) context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        else EncryptedSharedPreferences.create(
            context,
            FILE_NAME,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

    var isLogin: Boolean
        set(value) = dataStore.edit { putBoolean(IS_LOGIN, value) }
        get() = dataStore.getBoolean(IS_LOGIN, false)

    var userName: String
        set(value) = dataStore.edit { putString(USER_NAME, value) }
        get() = dataStore.getString(USER_NAME, "") ?: ""

    var userFavoriteSong: String
        set(value) = dataStore.edit { putString(USER_FAVORITE_SONG, value) }
        get() = dataStore.getString(USER_FAVORITE_SONG, "") ?: ""


    companion object {
        const val FILE_NAME = "GoSoptSharedPreferences"
        const val IS_LOGIN = "IsLogin"
        const val USER_NAME = "name"
        const val USER_FAVORITE_SONG = "favoriteSong"
    }
}