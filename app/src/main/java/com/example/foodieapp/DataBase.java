package com.example.foodieapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DataBase extends SQLiteOpenHelper {
//    private final String db = "LA.db";
    public DataBase(Context context) {
        super(context,"LA.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE Users(username TEXT primary key, email TEXT, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists USERS");
    }


    public boolean insertData(String name, String email, String password){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",name);
        contentValues.put("email", email);
        contentValues.put("password",password);
        long result = DB.insert("USERS",null, contentValues);
        if(result == -1){return false;}
        return true;
    }

    public boolean checkUser(String name){

        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from USERS where username = ?", new String[]{name});
        int count = cursor.getCount();
        if(count > 0){
            return true;
        }
        return false;
    }

    public boolean checkUsername_Password(String name, String password){

        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from USERS where username = ? and password = ?", new String[]{name,password});
        int count = cursor.getCount();
        if(count > 0){
            return true;
        }
        return false;
    }

    public boolean resetPassword(String name, String password){

            SQLiteDatabase DB = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("password", password);
            long result = DB.update("USERS",contentValues,"username = ?", new String[]{name});

            if(result != -1){
                return true;
            }
            return false;

    }
}//class end
