package com.example.team7_sql

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

//https://angangmoddi.tistory.com/263
class MovieDatabase(context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
): SQLiteOpenHelper(context, name, factory, version){
    override fun onCreate(db: SQLiteDatabase?) {
        var sql = "create table movie (Registration_Number integer primary key, sGenre text, Rating text, movie_name text, production_country text)"
        //sql문 작성
        db?.execSQL(sql)//실행
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //
    }
}