package com.pompast.stepbystep.fragment

import android.annotation.SuppressLint
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.pompast.stepbystep.R
import com.pompast.stepbystep.sql.SqlAction
import com.pompast.stepbystep.sql.SqlHelper
import java.text.SimpleDateFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddDayFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddDayFragment(private val sql: SqlAction) : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_add_day, container, false)

        val editTextSteps: EditText = view.findViewById(R.id.edit_text_steps)
        val editTextCalories: EditText = view.findViewById(R.id.edit_text_callories)
        val calendarView: CalendarView = view.findViewById(R.id.calendar_view)
//        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
//            val calendar = Calendar.getInstance()
//            calendar.set(year, month, dayOfMonth)
//            date = Date(calendar.timeInMillis)
//        }

        val pushButton: Button = view.findViewById(R.id.button_new_date)
        pushButton.setOnClickListener {
            val selectedDate = calendarView.date
            val date = Date(selectedDate)
            val dateString : String = FORMAT.format(date)

            sql.write(SqlHelper.COLUMN_NAME_STEPS, "Шагов: ${editTextSteps.text}")
            sql.write(SqlHelper.COLUMN_NAME_CALORIES, "Вес: ${editTextCalories.text} кг.")
            sql.write(SqlHelper.COLUMN_NAME_DATE, dateString)
            sql.invest()
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(sql: SqlAction, param1: String, param2: String) =
            AddDayFragment(sql).apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

        @SuppressLint("SimpleDateFormat")
        val FORMAT: SimpleDateFormat = SimpleDateFormat("dd.MM.yy")
    }
}