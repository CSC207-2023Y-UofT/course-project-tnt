package com.example.tester;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.example.tester.Model.TaskModel;
import com.example.tester.Utils.DatabaseHandler;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

/**
 * Unit tests for the DatabaseHandler class.
 */
public class DatabaseHandlerTest {

    private DatabaseHandler dbHandler;
    private SQLiteDatabase mockDb;

    @Before
    public void setUp() {
        Context context = mock(Context.class); // Mock the Context
        dbHandler = new DatabaseHandler(context);

        // Mock SQLiteDatabase
        mockDb = mock(SQLiteDatabase.class);
        when(dbHandler.getDB()).thenReturn(mockDb);

        // Ensure that the database is open before tests
        dbHandler.openDatabase();
    }

    @After
    public void tearDown() {
        // Close the database after tests
        dbHandler.close();
    }

    /**
     * Test the getTaskDict method of the DatabaseHandler class.
     * The method should retrieve a HashMap of TaskModel objects from the database.
     */
    @Test
    public void testGetTaskDict() {
        // Define expected values
        String username = "testUser";
        HashMap<Integer, TaskModel> expectedTaskDict = new HashMap<>();
        // Add expected tasks to the map

        // Mock cursor and database behavior
        Cursor mockCursor = mock(Cursor.class);
        when(mockDb.rawQuery(anyString(), any())).thenReturn(mockCursor);
        when(mockCursor.moveToFirst()).thenReturn(true);
        when(mockCursor.getString(0)).thenReturn("JSON_STRING");

        // Call the method
        HashMap<Integer, TaskModel> taskDict = dbHandler.getTaskDict(username);

        // Assertions
        assertEquals(expectedTaskDict.size(), taskDict.size());
    }

    /**
     * Test the insertTask method of the DatabaseHandler class.
     * The method should insert a TaskModel object into the database.
     */
    @Test
    public void testInsertTask() {
        // Define test data
        int taskNumber = 1;
        TaskModel task = new TaskModel();
        task.setTask("Test Task");
        task.setStatus(0);

        // Mock ContentValues and database behavior
        ContentValues contentValues = new ContentValues();
        when(mockDb.update(anyString(), any(ContentValues.class), anyString(), any())).thenReturn(1);

        // Call the method
        boolean result = dbHandler.insertTask(taskNumber, task);

        // Assertions
        assertTrue(result);
    }

    /**
     * Test the updateStatus method of the DatabaseHandler class.
     * The method should update the status of a TaskModel object in the database.
     */
    @Test
    public void testUpdateStatus() {
        // Define test data
        int taskId = 1;
        int status = 1;

        // Mock ContentValues and database behavior
        ContentValues contentValues = new ContentValues();
        when(mockDb.update(anyString(), any(ContentValues.class), anyString(), any())).thenReturn(1);

        // Call the method
        dbHandler.updateStatus(taskId, status);

        // Verify the method was called with the correct arguments
        verify(mockDb).update(anyString(), any(ContentValues.class), anyString(), any());
    }

    /**
     * Test the updateTask method of the DatabaseHandler class.
     * The method should update the description of a TaskModel object in the database.
     */
    @Test
    public void testUpdateTask() {
        // Define test data
        int taskId = 1;
        String updatedTaskDescription = "Updated Task Description";
        int updatedTaskStatus = 1;

        // Mock ContentValues and database behavior
        ContentValues contentValues = new ContentValues();
        when(mockDb.update(anyString(), any(ContentValues.class), anyString(), any())).thenReturn(1);

        // Call the method
        boolean result = dbHandler.updateTask(taskId, updatedTaskDescription);

        // Assertions
        assertTrue(result);
    }

    /**
     * Test the deleteTask method of the DatabaseHandler class.
     * The method should delete a TaskModel object from the database.
     */
    @Test
    public void testDeleteTask() {
        // Define test data
        int taskId = 1;

        // Mock database behavior
        when(mockDb.delete(anyString(), anyString(), any())).thenReturn(1);

        // Call the method
        boolean result = dbHandler.deleteTask(taskId);

        // Assertions
        assertTrue(result);
    }

    /**
     * Test the getAllTasks method of the DatabaseHandler class.
     * The method should retrieve a List of all TaskModel objects from the database.
     */
    @Test
    public void testGetAllTasks() {
        // Define expected values
        List<TaskModel> expectedTaskList = new ArrayList<>();
        // Add expected tasks to the list

        // Mock cursor and database behavior
        Cursor mockCursor = mock(Cursor.class);
        when(mockDb.rawQuery(anyString(), any())).thenReturn(mockCursor);
        when(mockCursor.moveToFirst()).thenReturn(true);
        when(mockCursor.getString(0)).thenReturn("JSON_STRING");

        // Call the method
        List<TaskModel> taskList = dbHandler.getAllTasks();

        // Assertions
        assertEquals(expectedTaskList.size(), taskList.size());
    }
}