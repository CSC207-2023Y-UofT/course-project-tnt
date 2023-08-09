package com.example.tester.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.tester.Model.TaskModel;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;

import java.util.ArrayList;
import java.util.List;
import java.lang.Integer;

/**
 * Helper class for managing the SQLite database for tasks.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String NAME = "TaskListDatabase";
    private static final String TASK_TABLE = "task";
    private static final String USERNAME = "username";
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

    /**
     * Creates a new instance of the DatabaseHandler class.
     *
     * @param context The context of the application.
     */
    public DatabaseHandler(Context context) {
        super(context, NAME, null, VERSION);
        //USERNAME = DatabaseHelper.getUsername(); // Initialize USERNAME properly
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        allTasks = new ArrayList<>();
        db.execSQL(CREATE_TASK_TABLE);
        //USERNAME = DatabaseHelper.getUsername();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TASK_TABLE);
        // Create tables again
        onCreate(db);
    }

    /**
     * Opens the database for writing and reading.
     */
    public void openDatabase() {
        db = this.getWritableDatabase();
    }

    /**
     * Retrieves the task dictionary for the given username.
     *
     * @param username The username of the user.
     * @return The task dictionary as a HashMap with TaskModel objects.
     */
    public HashMap<Integer, TaskModel> getTaskDict(String username) {
        String result = null;
        HashMap<Integer, TaskModel> taskMap = new HashMap<>();
        Cursor cursor = null;

        try {
            SQLiteDatabase MyDatabase = this.getReadableDatabase();

            String[] columns = {TASK_DICT};
            String selection = USERNAME + "= ?";
            String[] selectionArgs = {username};

            String selectQuery = "SELECT " + TASK_DICT + " FROM " + TASK_TABLE + " WHERE " + selection;

            cursor = MyDatabase.rawQuery(selectQuery, selectionArgs);

            if (cursor != null && cursor.moveToFirst()) {
                result = cursor.getString(cursor.getColumnIndexOrThrow(TASK_DICT));
            }

            if (result != null) {
                taskMap = retrieveTasks(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return taskMap;
    }

    private static HashMap<Integer, TaskModel> retrieveTasks(String jsonString) {
        String realJson = convertHashMapToJsonString(jsonString);
        if (realJson.isEmpty()) {
            return new HashMap<>(); // Return an empty map if realJson is null or empty
        }

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            HashMap<Integer, TaskModel> map = objectMapper.readValue(realJson, new TypeReference<HashMap<Integer, TaskModel>>() {});

            ArrayList<TaskModel> taskList = new ArrayList<>();
            for (TaskModel task : map.values()) {
                taskList.add(task);
            }
            allTasks = taskList;
            return map;
        } catch (JsonParseException e) {
            e.printStackTrace();
            // Handle invalid JSON string
            System.out.println("Invalid JSON string: " + realJson);
            return new HashMap<>(); // Return an empty map in case of parsing error
        } catch (IOException e) {
            e.printStackTrace();
            return new HashMap<>(); // Return an empty map in case of parsing error
        }
    }

    /**
     * Inserts a new task into the task dictionary.
     *
     * @param taskNumber The number of the task.
     * @param task       The TaskModel object representing the task.
     * @return True if the insertion was successful, false otherwise.
     */
    public boolean insertTask(Integer taskNumber, TaskModel task) {
        ContentValues contentValues = new ContentValues();
        HashMap<Integer, TaskModel> taskList = getTaskDict(USERNAME);
        taskList.put(taskNumber, task);
        contentValues.put(USERNAME, USERNAME); // You might want to store the username
        contentValues.put(TASK_DICT, convertHashMapToJson(taskList)); // Use "task_dict" as the column name

        long result = db.insert(TASK_TABLE, null, contentValues);

        return result != -1;
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
        cv.put(TASK_DICT, convertHashMapToJson(taskList));
        db.update(TASK_TABLE, cv, "username = ?", new String[]{USERNAME});
    }

    // Update Task_Name directly on the task inside TASK_DICT
    public boolean updateTask(int id, String description) {
        SQLiteDatabase database = this.getWritableDatabase();
        HashMap<Integer, TaskModel> taskList = getTaskDict(USERNAME);
        ContentValues cv = new ContentValues();

        try {
            database.beginTransaction();

            TaskModel task = taskList.get(id);
            if (task != null) {
                task.setTask(description);
                taskList.put(id, task);
                cv.put(TASK_DICT, convertHashMapToJson(taskList));

                int rowsAffected = database.update(
                        TASK_TABLE,
                        cv,
                        USERNAME + " = ?",
                        new String[]{USERNAME}
                );

                database.setTransactionSuccessful();
                return rowsAffected > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.endTransaction();
            database.close();
        }

        return false;
    }


    /**
     * Deletes a task from the task dictionary.
     *
     * @param id The ID of the task to be deleted.
     * @return True if the deletion was successful, false otherwise.
     */
    public boolean deleteTask(int id) {
        HashMap<Integer, TaskModel> taskList = getTaskDict(USERNAME);
        taskList.remove(id); // Remove the task from the map
        ContentValues cv = new ContentValues();
        cv.put(TASK_DICT, convertHashMapToJson(taskList));
        int result = db.update(TASK_TABLE, cv, "username = ?", new String[]{USERNAME});
        return result != -1; // Return true if the update was successful, false otherwise
    }


    /**
     * Retrieves all tasks from the task dictionary.
     *
     * @return A list of TaskModel objects representing all tasks.
     */
    public List<TaskModel> getAllTasks(){
        return allTasks;
    }

    /**
     * Gets the underlying SQLiteDatabase instance for direct access.
     *
     * @return The SQLiteDatabase instance.
     */
    public SQLiteDatabase getDB(){
        return db;
    }

    public void closeDatabase() {
        if (db != null && db.isOpen()) {
            db.close();
        }
    }

    public static String convertHashMapToJson(HashMap<Integer, TaskModel> taskMap) {
        StringBuilder jsonBuilder = new StringBuilder("{");

        // Iterate over the entries in the HashMap
        boolean firstEntry = true;
        for (Integer key : taskMap.keySet()) {
            TaskModel task = taskMap.get(key);
            if (!firstEntry) {
                jsonBuilder.append(",");
            }
            assert task != null;
            jsonBuilder.append("\"").append(key).append("\":").append(taskModelToJsonString(task));
            firstEntry = false;
        }

        jsonBuilder.append("}");
        return jsonBuilder.toString();
    }

    public static String convertHashMapToJsonString(String hashMapString) {
        if (hashMapString == null || hashMapString.isEmpty()) {
            return "{}"; // Return empty JSON object if input is empty or null
        }

        // Create a StringBuilder to build the JSON string
        StringBuilder jsonStringBuilder = new StringBuilder();
        jsonStringBuilder.append("{");

        // Remove leading and trailing curly braces from the input string
        hashMapString = hashMapString.trim();
        if (hashMapString.startsWith("{") && hashMapString.endsWith("}")) {
            hashMapString = hashMapString.substring(1, hashMapString.length() - 1);
        }

        // Split the input string into key-value pairs
        String[] keyValuePairs = hashMapString.split(",");
        for (String keyValuePair : keyValuePairs) {
            String[] keyValue = keyValuePair.trim().split("=");
            if (keyValue.length == 2) {
                String key = keyValue[0].trim();
                String value = keyValue[1].trim();

                // Convert the value string into a TaskModel object (you need to implement this part)
                TaskModel taskModel = convertValueToTaskModel(value);

                // Convert the key and value to JSON format and append to the JSON string
                jsonStringBuilder.append("\"").append(key).append("\":").append(taskModelToJsonString(taskModel)).append(",");
            }
        }

        // Remove the trailing comma and close the JSON object
        jsonStringBuilder.deleteCharAt(jsonStringBuilder.length() - 1);
        jsonStringBuilder.append("}");

        return jsonStringBuilder.toString();
    }

    // Convert TaskModel object to its JSON representation
    private static String taskModelToJsonString(TaskModel taskModel) {
        if (taskModel != null) {
            return "{\"id\":" + taskModel.getId() + ", \"task\":\"" + taskModel.getTask() + "\", \"status\":" + taskModel.getStatus() + "}";
        } else {
            return null;
        }
    }

    // Convert the value string to a TaskModel object
    private static TaskModel convertValueToTaskModel(String value) {
        try {
            // Split the value string into its components
            String[] components = value.split(",");

            // Extract id, name, and status from components
            int id = -1; // Default value if parsing fails
            String name = "";
            int status = 0; // Default value

            for (String component : components) {
                String[] keyValue = component.split(":");
                if (keyValue.length == 2) {
                    String key = keyValue[0].trim();
                    String val = keyValue[1].trim();

                    if ("id".equals(key)) {
                        id = Integer.parseInt(val);
                    } else if ("task".equals(key)) {
                        name = val.replace("\"", ""); // Remove surrounding quotes
                    } else if ("status".equals(key)) {
                        status = Integer.parseInt(val);
                    }
                }
            }

            TaskModel taskModel = new TaskModel();
            taskModel.setId(id);
            taskModel.setTask(name);
            taskModel.setStatus(status);

            return taskModel;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
