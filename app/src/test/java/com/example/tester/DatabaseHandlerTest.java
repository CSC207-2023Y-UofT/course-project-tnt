package com.example.tester;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tester.Model.TaskModel;
import com.example.tester.Utils.DatabaseHandler;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

/**
 * Unit tests for the {@link DatabaseHandler} class.
 */
public class DatabaseHandlerTest {

    @Mock
    SQLiteDatabase mockDatabase;

    @Mock
    Cursor mockCursor;

    private DatabaseHandler databaseHandler;

    @Before
    public void setUp() {
        // Initialize the mock objects and the class under test
        MockitoAnnotations.openMocks(this);
        databaseHandler = Mockito.spy(new DatabaseHandler(null));
        doReturn(mockDatabase).when(databaseHandler).getWritableDatabase();
    }

    /**
     * Test case for inserting a task into the database.
     */
    @Test
    public void testInsertTask() {
        // Prepare test data
        TaskModel task = new TaskModel();
        task.setTask("Sample task");

        // Mock the behavior of the SQLiteDatabase insert method
        doNothing().when(mockDatabase).insert(any(), any(), any(ContentValues.class));

        // Call the method under test
        databaseHandler.insertTask(task);

        // Verify that the insert method was called on the mockDatabase object
        verify(mockDatabase).insert(any(), any(), any(ContentValues.class));
    }

    /**
     * Test case for retrieving all tasks from the database.
     */
    @Test
    public void testGetAllTasks() {
        // Prepare expected test data
        List<TaskModel> expectedTaskList = new ArrayList<>();
        TaskModel task1 = new TaskModel();
        task1.setId(1);
        task1.setTask("Task 1");
        task1.setStatus(0);
        expectedTaskList.add(task1);

        TaskModel task2 = new TaskModel();
        task2.setId(2);
        task2.setTask("Task 2");
        task2.setStatus(1);
        expectedTaskList.add(task2);

        // Mock the behavior of the SQLiteDatabase query method
        doReturn(mockCursor).when(mockDatabase).query(any(), any(), any(), any(), any(), any(), any(), any());

        // Mock cursor behavior
        doReturn(true).doReturn(false).when(mockCursor).moveToNext();
        doReturn(2).when(mockCursor).getColumnIndexOrThrow(DatabaseHandler.getID());
        doReturn(1).when(mockCursor).getInt(2);
        doReturn(1).when(mockCursor).getColumnIndexOrThrow(DatabaseHandler.getTASK());
        doReturn("Task 1").when(mockCursor).getString(1);
        doReturn(2).when(mockCursor).getColumnIndexOrThrow(DatabaseHandler.getSTATUS());
        doReturn(0).when(mockCursor).getInt(2);

        // Call the method under test
        List<TaskModel> actualTaskList = databaseHandler.getAllTasks();

        // Perform assertions to validate the test results
        assertEquals(expectedTaskList.size(), actualTaskList.size());
        assertEquals(expectedTaskList.get(0).getTask(), actualTaskList.get(0).getTask());
        assertEquals(expectedTaskList.get(0).getStatus(), actualTaskList.get(0).getStatus());
        assertEquals(expectedTaskList.get(1).getTask(), actualTaskList.get(1).getTask());
        assertEquals(expectedTaskList.get(1).getStatus(), actualTaskList.get(1).getStatus());
    }

    /**
     * Test case for updating the status of a task in the database.
     */
    @Test
    public void testUpdateStatus() {
        // Prepare test data
        int taskId = 1;
        int newStatus = 1;

        // Mock the behavior of the SQLiteDatabase update method
        doNothing().when(mockDatabase).update(any(), any(ContentValues.class), any(), any(String[].class));

        // Call the method under test
        databaseHandler.updateStatus(taskId, newStatus);

        // Verify that the update method was called on the mockDatabase object
        verify(mockDatabase).update(any(), any(ContentValues.class), any(), any(String[].class));
    }

    /**
     * Test case for updating the description of a task in the database.
     */
    @Test
    public void testUpdateTask() {
        // Prepare test data
        int taskId = 1;
        String newTaskDescription = "Updated task description";

        // Mock the behavior of the SQLiteDatabase update method
        doNothing().when(mockDatabase).update(any(), any(ContentValues.class), any(), any(String[].class));

        // Call the method under test
        databaseHandler.updateTask(taskId, newTaskDescription);

        // Verify that the update method was called on the mockDatabase object
        verify(mockDatabase).update(any(), any(ContentValues.class), any(), any(String[].class));
    }

    /**
     * Test case for deleting a task from the database.
     */
    @Test
    public void testDeleteTask() {
        // Prepare test data
        int taskId = 1;

        // Mock the behavior of the SQLiteDatabase delete method
        doNothing().when(mockDatabase).delete(any(), any(), any(String[].class));

        // Call the method under test
        databaseHandler.deleteTask(taskId);

        // Verify that the delete method was called on the mockDatabase object
        verify(mockDatabase).delete(any(), any(), any(String[].class));
    }

    /**
     * Test case for deleting all tasks from the database.
     */
    @Test
    public void testDeleteAllTasks() {
        // Mock the behavior of the SQLiteDatabase delete method
        doNothing().when(mockDatabase).delete(any(), any(), any(String[].class));

        // Call the method under test
        DatabaseHandler.deleteAllTasks();

        // Verify that the delete method was called on the mockDatabase object
        verify(mockDatabase).delete(any(), any(), any(String[].class));
    }}
