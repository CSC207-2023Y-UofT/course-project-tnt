package com.example.tester;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;
//
//public class DatabaseHelper extends SQLiteOpenHelper{
//
//    public static final String databaseName = "Signup.db";
//    public static final String tableName = "users";
//    private Context context;
//    public static final String CREATE_TABLE= "create table "+ tableName;
//    public static final String DROP_TABLE= "DROP TABLE IF EXISTS"+ tableName;
//
//    public DatabaseHelper(@Nullable Context context){
//        super(context, databaseName, null, 1);
//        SQLiteDatabase db= this.getWritableDatabase();
//        this.context= context;
//    }
//
//
//    @Override
//    public void onCreate(SQLiteDatabase MyDatabase) {
//        MyDatabase.execSQL("create Table users(username TEXT primary key, password TEXT)");
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase MyDatabase, int oldversion, int newVersion) {
//        MyDatabase.execSQL("drop Table if exists users");
//    }
//
//    public Boolean insertData(String username, String password){
//        SQLiteDatabase MyDatabase = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("username", username);
//        contentValues.put("password", password);
//        long result = MyDatabase.insert("uesrs", null, contentValues);
//
//        if (result == -1){
//            return false;
//        } else {
//            return true;
//        }
//    }

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="ep";
    public static final String TABLE_NAME= "users";
    public static final String ID= "ID";
    public static final String MESSAGE= "MESSAGE";
    public static final String password = "password";
    public static final String CREATE_TABLE= "create table "+ TABLE_NAME + " ( "+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+MESSAGE+" TEXT)";
    public static final String DROP_TABLE= "DROP TABLE IF EXISTS"+ TABLE_NAME;
    private Context context;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null , 1);
        SQLiteDatabase db= this.getWritableDatabase();
        this.context= context;
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
    public void onUpgrade(SQLiteDatabase MyDatabase, int oldversion, int newVersion) {
        MyDatabase.execSQL("drop Table if exists users");
    }

    public boolean insertData(DatabaseHelper dbHelper , String Message, String password){

        SQLiteDatabase db= dbHelper.getWritableDatabase();

        ContentValues contentValues= new ContentValues();
        contentValues.put(MESSAGE, Message);
        contentValues.put(password, password);
        db.insert(TABLE_NAME, null, contentValues);

        Log.d("Insert Successfull ", "Value : "+Message + " :Data Inserted");

        return true;
    }

    public Boolean checkUsername(String username){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from users where username = ?", new String[]{username});

        if (cursor.getCount() > 0){
            return true;
        } else {
            return false;
        }
    }

    public Boolean checkPassword(String username, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from allusers where username = ? and password = ?", new String[]{username, password});

        if (cursor.getCount() > 0){
            return true;
        } else {
            return false;
        }
    }

}

