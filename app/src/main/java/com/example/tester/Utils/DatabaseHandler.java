package com.example.tester.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.tester.Model.TaskModel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.lang.Integer;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String NAME = "TaskListDatabase";
    private static final String TASK_TABLE = "task";
    private static String USERNAME = "username";
    private static final String ID = "id";
    //    private static final String TASK = "task";
    private static final String TASK_DICT = "task_dict";
    private static ArrayList<TaskModel> allTasks;

//    private static final String STATUS = "status";
    /**    private static final String CREATE_TASK_TABLE = "CREATE TABLE " + TASK_TABLE + "("
     + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
     + USERNAME + " USERNAME, "
     + TASK + " TEXT, "
     + STATUS + " INTEGER)";*/
    private static final String CREATE_TASK_TABLE = "CREATE TABLE " + TASK_TABLE + "("
            + USERNAME + " TEXT, "
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TASK_DICT + " TEXT)";
    // Create database with columns: USERNAME, ID, TASK_DICT = {Order: (Task_ID, Task_Name, Status)}
    private SQLiteDatabase db;

    public DatabaseHandler(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        allTasks = new ArrayList<TaskModel>();
        db.execSQL(CREATE_TASK_TABLE);
        USERNAME = DatabaseHelper.getUsername();
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

//// Insert task into TASK_DICT
//    /**    public void insertTask(TaskModel task){
//     ContentValues cv = new ContentValues();
//     cv.put(TASK, task.getTask());
//     cv.put(STATUS, 0);
//     db.insert(TASK_TABLE, null, cv);
//     }*/
//    public boolean CheckValueInDB(String field, String value) {
//        String Query = "SELECT * FROM " + TASK_TABLE + " WHERE " + field + " = " + value;
//        Cursor cursor = db.rawQuery(Query, null);
//        if(cursor.getCount() <= 0){
//            cursor.close();
//            return false;
//        }
//        cursor.close();
//        return true;
//    }

    public HashMap<Integer, TaskModel> getTaskDict(String username) {
        String result = null;
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cur;
        String strquery = "SELECT * FROM " + TASK_TABLE + " WHERE " + USERNAME + " = " + username + "'";
        cur = MyDatabase.rawQuery(strquery,null);
        if(cur != null&&cur.moveToFirst()) {
            do {
                result = cur.getString(0);
            } while(cur.moveToNext());
        }
        return retrieveTasks(result);
    }

    private static HashMap<Integer, TaskModel> retrieveTasks(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            HashMap<Integer, TaskModel> map = objectMapper.readValue(jsonString, new TypeReference<HashMap<Integer, TaskModel>>() {});

            ArrayList<TaskModel> taskList = new ArrayList<>();
            for (TaskModel task : map.values()) {
                taskList.add(task);
            }
            allTasks = taskList;
            return map;
        } catch (IOException e) {
            e.printStackTrace();
            return new HashMap<>(); // Return an empty dictionary in case of parsing error
        }
    }

    public List<TaskModel> getAllTasks(){
        return allTasks;
    }


    public boolean insertTask(Integer taskNumber, TaskModel task) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        HashMap<Integer, TaskModel> taskList = getTaskDict(USERNAME);
        taskList.put(taskNumber, task);
        contentValues.put("taskList", String.valueOf(taskList));
        long result = MyDatabase.update(TASK_TABLE, contentValues, "username = ?", new String[]{USERNAME});

        return result != 1;
    }

    public void updateStatus(int id, int status){
        HashMap<Integer, TaskModel> taskList = getTaskDict(USERNAME);
        ContentValues cv = new ContentValues();
        // Get the TaskModel at the position id, update its status
        TaskModel task = taskList.get(id);
        assert task != null;
        task.setStatus(status);
        //Put the task into the map, then put the map into the database
        taskList.put(id, task);
        cv.put(TASK_DICT, String.valueOf(taskList));
        db.update(TASK_TABLE, cv, "username = ?", new String[]{USERNAME});
    }

    // Update Task_Name directly on the task inside TASK_DICT
    public void updateTask(int id, String description) {
        HashMap<Integer, TaskModel> taskList = getTaskDict(USERNAME);
        ContentValues cv = new ContentValues();
        // Get the TaskModel at the position id, update its description
        TaskModel task = taskList.get(id);
        assert task != null;
        task.setTask(description);
        //Put the task into the map, then put the map into the database
        taskList.put(id, task);
        cv.put(TASK_DICT, String.valueOf(taskList));
        db.update(TASK_TABLE, cv, "username = ?", new String[]{USERNAME});
    }

    // Delete task based on Task_ID inside TASK_LIST
    public void deleteTask(int id){
        HashMap<Integer, TaskModel> taskList = getTaskDict(USERNAME);
        ContentValues cv = new ContentValues();
        TaskModel task = new TaskModel();
        taskList.put(id, task);
        cv.put(TASK_DICT, String.valueOf(taskList));
        db.update(TASK_TABLE, cv, "username = ?", new String[]{USERNAME});
    }

//        if(CheckValueInDB(USERNAME, username)){
//            String task_dict = getAllTasks(username);
//            task_dict.put(task_dict.size(), task);
//            ContentValues cv = new ContentValues();
//            cv.put(TASK_DICT, task_dict.toString());
//            db.update(TASK_TABLE, cv, ID + "= ?", new String[] {String.valueOf(id)});
//        } else{
//            Map<Integer, TaskModel> task_dict = new HashMap<Integer, TaskModel>();
//            task_dict.put(1, task);
//            Gson gson = new Gson();
//            ContentValues cv = new ContentValues();
//            cv.put(USERNAME, username);
//            cv.put(TASK_DICT, task_dict.toString());
//            db.insert(TASK_TABLE, null, cv);
}

// Get TASK_DICT by user
/*    public List<TaskModel> getAllTasks(){
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
 //     }*/

//    // Update Status directly on the task inside TASK_DICT

//}