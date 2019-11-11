package com.tt.nicklas.vmau.ui

import android.content.Context
import android.content.SharedPreferences

class SharedPreferences(val context: Context) {
    private val PREFS_NAME = "kotlin"
    val sharedPref: SharedPreferences? = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)


    fun save(KEY_NAME: String, status: Boolean) {

        val editor: SharedPreferences.Editor = sharedPref!!.edit()

        editor.putBoolean(KEY_NAME, status)

        editor.apply()
    }

    fun getValueBoolean(KEY_NAME: String, defaultValue: Boolean): Boolean {

        return sharedPref!!.getBoolean(KEY_NAME, defaultValue)

    }
}