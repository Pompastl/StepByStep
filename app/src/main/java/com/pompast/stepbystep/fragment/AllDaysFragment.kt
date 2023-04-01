package com.pompast.stepbystep.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pompast.stepbystep.R
import com.pompast.stepbystep.fragment.touch.TouchHelper
import com.pompast.stepbystep.recycle.ViewAdapter
import com.pompast.stepbystep.sql.SqlAction
import com.pompast.stepbystep.sql.SqlHelper

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class AllDaysFragment(private val sql: SqlAction) : Fragment() {
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
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_all_days, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.recycle_view)
        recyclerView.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(this.context)
        recyclerView.layoutManager = layoutManager

        val dates: List<String> = sql.readAll(SqlHelper.COLUMN_NAME_DATE)
        val steps: List<String> = sql.readAll(SqlHelper.COLUMN_NAME_STEPS)
        val calories: List<String> = sql.readAll(SqlHelper.COLUMN_NAME_CALORIES)


        val adapter = ViewAdapter(dates as MutableList<String>, steps as MutableList<String>
            , calories as MutableList<String>, sql)
        recyclerView.adapter = adapter
        val itemTouchHelper = ItemTouchHelper(TouchHelper(adapter))
        itemTouchHelper.attachToRecyclerView(recyclerView)


        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(sql: SqlAction, param2: String) =
            AllDaysFragment(sql).apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}