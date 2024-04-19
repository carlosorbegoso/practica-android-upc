package com.katty.tarea_semana2.util

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper (context:Context): SQLiteOpenHelper(
    context, DATA_BASE_NAME, null, DATA_VERSION) {
    companion object {
       private const val DATA_BASE_NAME = "biblioteca.db"
         private const val DATA_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
      var sql = "CREATE TABLE IF NOT EXISTS libro (" +
              "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT, autor TEXT, anio INTEGER)"

        db?.execSQL(sql)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS libro")
        onCreate(db)
    }
}