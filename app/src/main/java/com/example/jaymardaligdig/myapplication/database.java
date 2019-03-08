package com.example.jaymardaligdig.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class database extends SQLiteOpenHelper {

    private static String table_name = "player";
    private static String table_name2 = "scores";
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + table_name +"(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT)";
        db.execSQL(createTable);
        createTable = "CREATE TABLE " + table_name2 +"(id INTEGER PRIMARY KEY AUTOINCREMENT, remarks INTEGER, dif TEXT, category, uniqid TEXT, name TEXT)";
        db.execSQL(createTable);
    }
    public database(Context context){
        super(context,table_name,null,1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXIST "+ table_name);
        onCreate(db);
        db.execSQL("DROP IF TABLE EXIST "+ table_name2);
        onCreate(db);
    }

    public boolean addName(String value){
        SQLiteDatabase db = this.getWritableDatabase();
        String q = "INSERT INTO "+table_name+"(name) VALUES('"+ value +"')";
        db.execSQL(q);
        return true;
    }

    public boolean addScore(int value){
        SQLiteDatabase db = this.getWritableDatabase();
        String q = "INSERT INTO "+table_name2+"(remarks,dif,category,uniqid,name) VALUES("+ value +",'"+ dificulty.dificulties +"','"+ category.category +"','98987','"+ player_info.playerName +"')";
        db.execSQL(q);
        return true;
    }

    public Cursor data(String q){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor data = db.rawQuery(q,null);
        return data;
    }

}
