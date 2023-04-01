package com.pompast.stepbystep

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pompast.stepbystep.fragment.AddDayFragment
import com.pompast.stepbystep.fragment.AllDaysFragment
import com.pompast.stepbystep.fragment.StatisticFragment
import com.pompast.stepbystep.sql.SqlAction
import com.pompast.stepbystep.sql.SqlHelper

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "CommitTransaction")
    companion object {
        const val FRAGMENT_VIEW_ID: Int = R.id.fragment_container_view
    }
    private var lastWindow: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationMenu: BottomNavigationView = findViewById(R.id.b)
        bottomNavigationMenu.setOnItemSelectedListener { menuItem ->
            onNavigationMenuChange(menuItem.itemId)
            true
        }
        onNavigationMenuChange(R.id.all_days)
    }

    private fun onNavigationMenuChange(view: Int) {
        if (lastWindow != null && lastWindow == view)
            return

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(R.anim.frame_change_in, R.anim.frame_change_out)
        val helper = SqlHelper(this)
        val sqlAction = SqlAction(helper)

        when (view) {
            R.id.all_days -> fragmentTransaction.replace(
                    FRAGMENT_VIEW_ID,
                    AllDaysFragment(sqlAction)
                )


            R.id.add_days -> fragmentTransaction.replace(
                FRAGMENT_VIEW_ID,
                AddDayFragment(sqlAction)
            )

            R.id.statistic -> fragmentTransaction.replace(
                FRAGMENT_VIEW_ID,
                StatisticFragment(sqlAction)
            )
        }
        fragmentTransaction.commit()
        lastWindow = view
    }
}
