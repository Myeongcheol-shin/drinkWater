package com.capstone.waterproject

import android.annotation.SuppressLint
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.capstone.waterproject.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override var layoutResourceId: Int = R.layout.activity_main
    private lateinit var mainViewModel : MainViewModel
    @SuppressLint("NotifyDataSetChanged")
    override fun initBinding() {
        mainViewModel = MainViewModel()
        binding.main = mainViewModel

        val gridLayoutManager = GridLayoutManager(this,5)
        binding.recycler.layoutManager = gridLayoutManager

        mainViewModel.waterList.observe(this, Observer {
            val adapter = WaterAdapter(it)
            adapter.setOnItemClickListener(object : WaterAdapter.OnItemClickListener{
                override fun onItemCLick(v: View, pos: Int) {
                    mainViewModel.clickDrink(pos = pos)
                }
            })
            binding.recycler.adapter = adapter
            binding.recycler.adapter!!.notifyDataSetChanged()
        })

        mainViewModel.count.observe(this, Observer {
            binding.waveView.setProgress(it * 10)
        })

        mainViewModel.waterList.observe(this, Observer {
            (binding.recycler.adapter as WaterAdapter).setDatas(it)
        })
    }
}