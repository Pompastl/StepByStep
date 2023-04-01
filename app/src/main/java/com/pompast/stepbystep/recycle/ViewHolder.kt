package com.pompast.stepbystep.recycle

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pompast.stepbystep.R

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val textViewDate: TextView = view.findViewById(R.id.text_view_date)
    val textViewSteps: TextView = view.findViewById(R.id.text_view_steps)
    val textViewCalories : TextView = view.findViewById(R.id.text_view_calories)
    val textViewDayPassed: TextView = view.findViewById(R.id.text_view_day_passed)
}