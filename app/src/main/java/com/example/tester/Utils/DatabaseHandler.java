package com.example.tester.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.tester.Model.TaskModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.lang.Integer;
import com.google.gson;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String NAME = "TaskListDatabase";
    private static final String TASK_TABLE = "task";
    private static final String USERNAME = "username";
    private static final String ID = "id";
//    private static final String TASK = "task";
    private static final String TASK_DICT = "task_dict";

//    private static final String STATUS = "status";
/**    private static final String CREATE_TASK_TABLE = "CREATE TABLE " + TASK_TABLE + "("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + USERNAME + " USERNAME, "
            + TASK + " TEXT, "
            + STATUS + " INTEGER)";*/
    private static final String CREATE_TASK_TABLE = "CREATE TABLE " + TASK_TABLE + "("
        + USERNAME + " USERNAME, "
        + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
        + TASK_DICT + " MAP)";
// Create database with columns: USERNAME, ID, TASK_DICT = {Order: (Task_ID, Task_Name, Status)}
    private SQLiteDatabase db;

    public DatabaseHandler(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TASK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TASK_TABLE);
        // Create tables again
        onCreate(db);
    }

    public void openDatabase() {
        db = this.getWritableDatabase();
    }
// Insert task into TASK_DICT
/**    public void insertTask(TaskModel task){
        ContentValues cv = new ContentValues();
        cv.put(TASK, task.getTask());
        cv.put(STATUS, 0);
        db.insert(TASK_TABLE, null, cv);
    }*/
    public boolean CheckValueInDB(String field, String value) {
        String Query = "SELECT * FROM " + TASK_TABLE + " WHERE " + field + " = " + value;
        Cursor cursor = db.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }
    public void insertTask(String username, TaskModel task){
        if(CheckValueInDB(USERNAME, username)){
            String task_dict = getAllTasks(username);
            task_dict.put(task_dict.size(), task);
            ContentValues cv = new ContentValues();
            cv.put(TASK_DICT, task_dict.toString());
            db.update(TASK_TABLE, cv, ID + "= ?", new String[] {String.valueOf(id)});
        } else{
            Map<Integer, TaskModel> task_dict = new HashMap<Integer, TaskModel>();
            task_dict.put(1, task);
            Gson gson = new Gson();
            ContentValues cv = new ContentValues();
            cv.put(USERNAME, username);
            cv.put(TASK_DICT, task_dict.toString());
            db.insert(TASK_TABLE, null, cv);
        }
    }
// Get TASK_DICT by user
/**    public List<TaskModel> getAllTasks(){
        List<TaskModel> taskList = new ArrayList<>();
        Cursor cur = null;
        db.beginTransaction();
        try{
            cur = db.query(TASK_TABLE, null, null, null, null, null, null, null);
            if(cur != null){
                if(cur.moveToFirst()){
                    do{
                        TaskModel task = new TaskModel();
                        task.setId(cur.getInt(cur.getColumnIndexOrThrow(ID)));
                        task.setTask(cur.getString(cur.getColumnIndexOrThrow(TASK)));
                        task.setStatus(cur.getInt(cur.getColumnIndexOrThrow(STATUS)));
                        taskList.add(task);
                    }
                    while(cur.moveToNext());
                }
            }
        }
        finally {
            db.endTransaction();
            assert cur != null;
            cur.close();
        }
        return taskList;
    }*/
    public List<TaskModel> getAllTasks(String username){
        List<TaskModel> taskList = new ArrayList<>();
        Cursor cur = null;
        db.beginTransaction();
    }
// Update Status directly on the task inside TASK_DICT
    public void updateStatus(int id, int status){
        ContentValues cv = new ContentValues();
        cv.put(STATUS, status);
        db.update(TASK_TABLE, cv, ID + "= ?", new String[] {String.valueOf(id)});
    }
// Update Task_Name directly on the task inside TASK_DICT
    public void updateTask(int id, String task) {
        ContentValues cv = new ContentValues();
        cv.put(TASK, task);
        db.update(TASK_TABLE, cv, ID + "= ?", new String[] {String.valueOf(id)});
    }
// Delete task based on Task_ID inside TASK_LIST
    public void deleteTask(int id){
        db.delete(TASK_TABLE, ID + "= ?", new String[] {String.valueOf(id)});
    }
}
