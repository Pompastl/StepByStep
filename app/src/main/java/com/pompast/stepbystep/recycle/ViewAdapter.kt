package com.pompast.stepbystep.recycle

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.pompast.stepbystep.R
import com.pompast.stepbystep.sql.SqlAction
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class ViewAdapter(private val dates : MutableList<String>, private val steps : MutableList<String>,
                  private val calories : MutableList<String>, private val sql: SqlAction) :
    RecyclerView.Adapter<ViewHolder>()  {

    companion object {
        @SuppressLint("SimpleDateFormat")
        val FORMAT: SimpleDateFormat = SimpleDateFormat("dd.MM.yy")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycle_view_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        if (dates.size == calories.size && dates.size == steps.size) {
            return dates.size
        } else {
            throw IllegalStateException("Dates, steps, and calories lists must have the same size")
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (itemCount > 0) {
            val dateNow = Date()
            val datePass = FORMAT.parse(dates[position])

            val diffInMillis : Long = dateNow.time - datePass!!.time
            val daysDiff = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS)
            var passDays = "Ошибка"

            if (daysDiff == 0L) {
                passDays = "Сегодня"
            }
            if (daysDiff > 0L) {
                passDays = "$daysDiff дней назад"
            }

            holder.textViewDayPassed.text = passDays
            holder.textViewDate.text = dates[position]
            holder.textViewSteps.text = steps[position]
            holder.textViewCalories.text = calories[position]
        }

    }

    fun deleteItem(position: Int) {
        val id = position + 1
        sql.delete(id.toString())

        dates.removeAt(position)
        steps.removeAt(position)
        calories.removeAt(position)
        notifyItemRemoved(position)
    }
}