package com.pompast.stepbystep.sql

import android.annotation.SuppressLint
import android.content.ContentValues

class SqlAction(helper: SqlHelper) : SqlEntity(helper) {

    companion object {
        private var VALUES: ContentValues = ContentValues()
    }

    fun write(column: String, value: String, column1: String, value1: String) {
        write(column, value)
        write(column1, value1)
    }

    override fun write(column: String, value: String) {
        super.write(column, value)
        VALUES.put(column, value)
    }

    fun invest() {
        sql.insert(SqlHelper.TABLE_NAME, null, VALUES)
        VALUES.clear()
    }

    fun readAll(columnName: String) : List<String> {
        val out: MutableList<String> = mutableListOf()
        var data : String? = read("1", columnName)

        for (i in 2..Int.MAX_VALUE) {
            if (data == null)
                break
            out.add(data)
            data = read(i.toString(), columnName)
        }

        return out
    }

    @SuppressLint("Recycle", "Range")
    override fun read(key: String, value: String): String? {
        super.read(key, value)

        val cursor = sql.rawQuery(
            "SELECT ${SqlHelper.COLUMN_NAME_DATE}, " +
                    "${SqlHelper.COLUMN_NAME_STEPS}, ${SqlHelper.COLUMN_NAME_CALORIES} FROM " +
                    "${SqlHelper.TABLE_NAME} WHERE _id = ?", arrayOf(key)
        )

        if (cursor.moveToNext()) {
            return cursor.getString(cursor.getColumnIndex(value))
        }

        return null
    }


//    @SuppressLint("Range")
//    override fun read(key: String, value: String): String? {
//        super.read(key)
//
//
//        val cursor = sql.rawQuery("SELECT ${SqlHelper.COLUMN_NAME_DATE}, " +
//                "${SqlHelper.COLUMN_NAME_STEPS}, ${SqlHelper.COLUMN_NAME_CALORIES} FROM " +
//                "${SqlHelper.TABLE_NAME} WHERE _id = ?", arrayOf(key))
//
//        if (cursor.moveToNext()) {
//            return cursor.getString(cursor.getColumnIndex(value))
//        }
//
//        return null
//    }

}