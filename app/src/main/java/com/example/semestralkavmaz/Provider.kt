package com.example.semestralkavmaz

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri


class Provider : ContentProvider() {
    /* aby sa mohlo lahko pristupovat k premennym cez nazov
  */
    companion object {
        val PROVIDE_NAME = "com.example.semestralkavmaz/Provider"
        val URL = "content://$PROVIDE_NAME/WINNERS"

        //tvorim url na zaklade mojho URLka
        val CONTENT_URL = Uri.parse(URL)

        //chcem aby user mal pristup k column name
        val _ID = "_id"
        val NAME = "name"
        val COUNTRY = "country"
    }

    lateinit var db: SQLiteDatabase


    /* spravi sa raz pri vytvarani databazy ak sa vytvori return true
      */
    override fun onCreate(): Boolean {
        var helper = MyHelper(getContext())
        db = helper.writableDatabase
        return if (db == null) false else true
    }

    /* ked pouzivatel prejde uri insertnem hodnoty a rovnako vratim uri pouzivatelovy potom ako spravi insert
      */
    override fun insert(uri: Uri, cv: ContentValues?): Uri? {
        db.insert("WINNERS", null, cv)
        context?.contentResolver?.notifyChange(uri, null)
        return uri
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        TODO("Not yet implemented")
    }


    override fun query(
        uri: Uri,
        cols: Array<out String>?,
        condition: String?,
        condition_val: Array<out String>?,
        order: String?
    ): Cursor? {
        return db.query("WINNERS", cols, condition, condition_val, null, null, order)
    }

    override fun getType(uri: Uri): String? {
        return "vnd.android.cursor.dir/vnd.example.WINNERS"
    }
}