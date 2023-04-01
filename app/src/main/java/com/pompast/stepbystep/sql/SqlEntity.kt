package com.pompast.stepbystep.sql

import android.database.sqlite.SQLiteDatabase

open class SqlEntity(private val helper : SqlHelper) {
    protected open lateinit var sql : SQLiteDatabase
    open fun write(column : String, value : String) {
        sql = helper.writableDatabase
    }

    open fun read(key : String, value : String) : String? {
        sql = helper.readableDatabase
        return null
    }

    open fun delete(key : String) {
        sql = helper.writableDatabase
        sql.delete(SqlHelper.TABLE_NAME, "_id=?", arrayOf(key))
    }

    fun update() {
        if (::sql.isInitialized)
            helper.onUpgrade(sql, 0, 1)
        else {
            sql = helper.readableDatabase
            update()
        }
    }
}