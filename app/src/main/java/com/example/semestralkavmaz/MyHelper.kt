package com.example.semestralkavmaz

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/* vytvori databazu a urci atributy v nej
 */
class MyHelper(context: Context?) : SQLiteOpenHelper(context, "WINNERS", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE WINNERS(_id INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, COUNTRY TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

}