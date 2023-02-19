package com.capstone.waterproject

import android.content.Context
import android.content.SharedPreferences

class PreferenceUtil(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("Water", Context.MODE_PRIVATE)

    fun setWater(water : String){
        prefs.edit().putString("Water",water).apply()
    }
    fun getWater() : String {
        return prefs.getString("Water", "0000000000").toString()
    }
}