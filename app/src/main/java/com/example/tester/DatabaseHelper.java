package com.example.tester;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="ep";
    public static final String TABLE_NAME= "users";
    public static final String ID= "ID";
    public static final String USERNAME= "username";
    public static final String PASSWORD = "password";
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( "
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + USERNAME + " TEXT, "
            + PASSWORD + " TEXT)";

    public static final String DROP_TABLE= "DROP TABLE IF EXISTS"+ TABLE_NAME;
//    private Context context;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null , 1);
//        SQLiteDatabase db= this.getWritableDatabase();
//        this.context= context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        @Override
    public void onUpgrade(SQLiteDatabase myDatabase, int oldversion, int newVersion) {
        myDatabase.execSQL("drop Table if exists users");
        onCreate(myDatabase);
    }

    public boolean addUser(DatabaseHelper dbHelper, String username, String password){

        SQLiteDatabase db= dbHelper.getWritableDatabase();

        ContentValues contentValues= new ContentValues();
        contentValues.put(USERNAME, username);
        contentValues.put(PASSWORD, password);
        db.insert(TABLE_NAME, null, contentValues);

        return true;
    }

    public Boolean checkUsername(String username){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + USERNAME + " = ?", new String[]{username});
        int count = cursor.getCount();
        cursor.close();
        if (count > 0){
            return true;
        } else {
            return false;
        }

    }

    public Boolean checkPassword(String username, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        String whereclause = "USERNAME=? and PASSWORD=?";
        String[] whereargs = new String[]{username,password};
//        @SuppressLint("Recycle") Cursor cursor = MyDatabase.rawQuery("Select * from allusers where username = ? and password = ?", new String[]{username, password});
        Cursor cursor = MyDatabase.query(
                TABLE_NAME,
                new String[]{"USERNAME","PASSWORD"},
                whereclause,
                whereargs,
                null,null,null
        );
        int count = cursor.getCount();
        cursor.close();
        if (count > 0){
            return true;
        } else {
            return false;
        }
    }

}

