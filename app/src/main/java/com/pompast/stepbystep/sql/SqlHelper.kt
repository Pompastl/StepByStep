package com.pompast.stepbystep.sql

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SqlHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    companion object {
        const val DATABASE_NAME : String = "DATABASE_FILE"
        const val TABLE_NAME : String = "fit_table"
        const val COLUMN_NAME_DATE : String = "date"
        const val COLUMN_NAME_STEPS : String = "steps"
        const val COLUMN_NAME_CALORIES : String = "calories"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(
            "CREATE TABLE IF NOT EXISTS $TABLE_NAME (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$COLUMN_NAME_DATE TEXT, $COLUMN_NAME_STEPS TEXT, $COLUMN_NAME_CALORIES TEXT)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }


}