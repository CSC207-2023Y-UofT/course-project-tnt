package com.example.tester.entities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * A utility class for managing the database operations related to tasks.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String NAME = "toDoListDatabase";
    private static final String TODO_TABLE = "todo";
    private static final String ID = "id";
    private static final String TASK = "task";
    private static final String STATUS = "status";
    private static final String CREATE_TODO_TABLE = "CREATE TABLE " + TODO_TABLE + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TASK + " TEXT, "
            + STATUS + " INTEGER)";

    private static SQLiteDatabase db;

    public DatabaseHandler(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TODO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TODO_TABLE);
        // Create tables again
        onCreate(db);
    }

    /**
     * Opens the database for writing.
     */
    public void openDatabase() {
        db = this.getWritableDatabase();
    }

    /**
     * Returns the column name for the ID field.
     *
     * @return The column name for the ID field.
     */
    public static String getID() {
        return ID;
    }

    /**
     * Returns the column name for the TASK field.
     *
     * @return The column name for the TASK field.
     */
    public static String getTASK() {
        return TASK;
    }

    /**
     * Returns the column name for the STATUS field.
     *
     * @return The column name for the STATUS field.
     */
    public static String getSTATUS() {
        return STATUS;
    }

    /**
     * Inserts a new task into the database.
     *
     * @param task The task to be inserted.
     */
    public void insertTask(TaskModel task){
        ContentValues cv = new ContentValues();
        cv.put(TASK, task.getTask());
        cv.put(STATUS, 0);
        db.insert(TODO_TABLE, null, cv);
    }

    /**
     * Retrieves all tasks from the database.
     *
     * @return A list of all tasks in the database.
     */
    public List<TaskModel> getAllTasks(){
        List<TaskModel> taskList = new ArrayList<>();
        Cursor cur = null;
        db.beginTransaction();
        try{
            cur = db.query(TODO_TABLE, null, null, null, null, null, null, null);
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
    }

    /**
     * Updates the status of a task.
     *
     * @param id The ID of the task to be updated.
     * @param status The new status of the task.
     */
    public void updateStatus(int id, int status){
        ContentValues cv = new ContentValues();
        cv.put(STATUS, status);
        db.update(TODO_TABLE, cv, ID + "= ?", new String[] {String.valueOf(id)});
    }

    /**
     * Updates the description of a task.
     *
     * @param id The ID of the task to be updated.
     * @param task The new description of the task.
     */
    public void updateTask(int id, String task) {
        ContentValues cv = new ContentValues();
        cv.put(TASK, task);
        db.update(TODO_TABLE, cv, ID + "= ?", new String[] {String.valueOf(id)});
    }

    /**
     * Deletes a task from the database.
     *
     * @param id The ID of the task to be deleted.
     */
    public void deleteTask(int id){
        db.delete(TODO_TABLE, ID + "= ?", new String[] {String.valueOf(id)});
    }

    /**
     * Deletes all tasks from the database.
     */
    public static void deleteAllTasks() {
        db.delete(TODO_TABLE, null, null);
    }
}
