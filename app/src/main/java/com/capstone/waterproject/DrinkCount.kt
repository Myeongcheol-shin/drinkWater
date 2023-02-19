package com.capstone.waterproject

import androidx.lifecycle.MutableLiveData

object DrinkCount {
    private val drinkCount = MutableLiveData<Int>()
    init {
        var count = 0
        MyApplication.prefs.getWater().toCharArray().forEach {
            if(it == '1') count++
        }
        drinkCount.value = count
    }
    fun getDrink() = drinkCount
}