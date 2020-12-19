package com.example.contactbook

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.text.Editable
import android.util.Log

class SqliteHelper(context: Context, name: String, version: Int) :
    SQLiteOpenHelper(context, name, null, version) {
    override fun onCreate(db: SQLiteDatabase?) {
        val create = "create table user (" +
                "no integer primary key, " +
                "name text, " +
                "description text" +
                ")"

        db?.execSQL(create)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun insertUser(name: String, description: String) {
        val values = ContentValues()
        values.put("name", name)
        values.put("description", description)

        val wd = writableDatabase
        wd.insert("user", null, values)
        wd.close()
    }

    fun selectUser(): MutableList<User> {
        val list = mutableListOf<User>()

        val select = "select * from user"
        val rd = readableDatabase
        val cursor = rd.rawQuery(select, null)

        while (cursor.moveToNext()) {
            val no = cursor.getLong(cursor.getColumnIndex("no")).toInt()
            val content = cursor.getString(cursor.getColumnIndex("name"))
            val description = cursor.getString(cursor.getColumnIndex("description"))

            list.add(User(no, content, description))
        }

        cursor.close()
        rd.close()

        return list
    }

    fun updateUser(user: User) {
        val values = ContentValues()
        values.put("name", user.name)
        values.put("description", user.description)

        val wd = writableDatabase
        wd.update("user", values, "no = ${user.no}", null)
        wd.close()
    }

    fun deleteUser(user: User) {
        val delete = "delete from user where no = ${user.no}"

        val db = writableDatabase
        db.execSQL(delete)
        db.close()
    }

    fun deleteAll() {
        val delete = "delete from user"

        val db = writableDatabase
        db.execSQL(delete)
        db.close()
    }
}