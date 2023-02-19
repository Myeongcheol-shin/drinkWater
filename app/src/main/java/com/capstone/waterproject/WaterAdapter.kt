package com.capstone.waterproject

import android.annotation.SuppressLint
import android.provider.ContactsContract.Profile
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView

class WaterAdapter(var data : List<Water>) : RecyclerView.Adapter<WaterAdapter.MyViewHolder>() {

    interface OnItemClickListener{
        fun onItemCLick(v:View, pos : Int)
    }
    private var listener : OnItemClickListener? = null

    fun setOnItemClickListener(listener : OnItemClickListener) {
        this.listener = listener
    }

    inner class MyViewHolder constructor(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_recyclerview, parent, false)
    ) {
        val image : AppCompatImageView = itemView.findViewById(R.id.image)
        fun bind(item: Water){
            if(adapterPosition != RecyclerView.NO_POSITION) {
                itemView.setOnClickListener { listener?.onItemCLick(itemView,adapterPosition) }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WaterAdapter.MyViewHolder {
        return MyViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(data[position])
        data[position].let {
            with(holder) {
                if(it.status) image.setImageResource(R.drawable.icon_fill_water)
                else image.setImageResource(R.drawable.icon_empty_water)
            }
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setDatas(newData : List<Water>){
        data = newData
        notifyDataSetChanged()
    }
}
