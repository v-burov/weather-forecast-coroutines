package com.example.weather.presentation.forecastlist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import java.util.ArrayList

internal class ForecastRecyclerAdapter(private val onItemClickListener: (Long) -> Unit) :
    RecyclerView.Adapter<ForecastRecyclerAdapter.ForecastViewHolder>() {

    private val forecastList = ArrayList<ForecastListItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_forecast, parent, false)
        return ForecastViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val forecastListItem = forecastList[position]
        holder.tvWeatherCondition.text = forecastListItem.weatherCondition
        holder.tvTemperature.text = forecastListItem.temperature.toString()

        //todo find another way to set listener
        holder.itemView.setOnClickListener {
            onItemClickListener.invoke(forecastListItem.date)
        }
    }

    override fun getItemCount() = forecastList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<ForecastListItem>) = forecastList.apply {
        clear()
        addAll(list)
        //todo dynamic updates are not supported so we use notifyDataSetChanged instead of diff utils
        notifyDataSetChanged()
    }

    inner class ForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvWeatherCondition: TextView = itemView.findViewById(R.id.tvWeatherCondition)
        val tvTemperature: TextView = itemView.findViewById(R.id.tvTemperature)
    }


}