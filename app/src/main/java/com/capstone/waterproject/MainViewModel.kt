package com.capstone.waterproject

import android.database.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel(){
    private val _count : MutableLiveData<Int> = DrinkCount.getDrink()
    private val _waterList = MutableLiveData<List<Water>>()
    val count : LiveData<Int> get() = _count
    val waterList : LiveData<List<Water>> get() = _waterList

    private fun countMinus(){
        if(_count.value != 0) _count.value = _count.value!! - 1
    }
    private fun countPlus(){
        if(_count.value != 10) _count.value = _count.value!! + 1
    }
    fun clickDrink(pos : Int) {
        val nItems = _waterList.value!!
        if(nItems[pos].status) {
            countMinus()
            nItems[pos].status = false
            val ch = MyApplication.prefs.getWater().toCharArray()
            ch[pos] = '0'
            MyApplication.prefs.setWater(String(ch))
        }
        else{
            countPlus()
            nItems[pos].status = true
            val ch = MyApplication.prefs.getWater().toCharArray()
            ch[pos] = '1'
            MyApplication.prefs.setWater(String(ch))
        }
        _waterList.value = nItems
    }
    init {
        val water = MyApplication.prefs.getWater().toCharArray()
        this._waterList.value = water.map { it ->
            if(it == '0') Water()
            else Water(status = true)
        }
    }
}