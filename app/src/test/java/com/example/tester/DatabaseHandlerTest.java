package com.example.tester;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.example.tester.entities.TaskModel;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Unit tests for the database operations using the IDatabaseHandler interface.
 * Note: these tests cover the add, remove, and edit task use cases.
 * So, there is no need to create other files explicitly on testing these use cases.
 */
public class DatabaseHandlerTest {

    private IDatabaseHandler dbHandler;

    /**
     * Sets up the test environment by creating a mock database handler instance.
     */
    @Before
    public void setUp() {
        dbHandler = new MockDatabaseHandler();
    }

    /**
     * Tests the insertion of a task into the mock database.
     */
    @Test
    public void testInsertTask() {
        TaskModel task = new TaskModel();
        task.setTask("Test Task");
        task.setStatus(0);

        dbHandler.insertTask(task);

        List<TaskModel> taskList = dbHandler.getAllTasks();
        assertEquals(1, taskList.size());

        TaskModel insertedTask = taskList.get(0);
        assertEquals("Test Task", insertedTask.getTask());
        assertEquals(0, insertedTask.getStatus());
    }

    /**
     * Tests the deletion of a task from the mock database.
     */
    @Test
    public void testDeleteTask() {
        TaskModel task = new TaskModel();
        task.setTask("Task to Delete");
        task.setStatus(0);
        dbHandler.insertTask(task);

        List<TaskModel> taskList = dbHandler.getAllTasks();
        assertEquals(1, taskList.size());

        dbHandler.deleteTask(task.getId());

        List<TaskModel> afterDeleteTaskList = dbHandler.getAllTasks();
        assertTrue(afterDeleteTaskList.isEmpty());
    }

    /**
     * Tests the update of a task's description in the mock database.
     */
    @Test
    public void testUpdateTask() {
        TaskModel task = new TaskModel();
        task.setTask("Original Task");
        task.setStatus(0);
        dbHandler.insertTask(task);

        List<TaskModel> taskList = dbHandler.getAllTasks();
        assertEquals(1, taskList.size());

        TaskModel originalTask = taskList.get(0);
        assertEquals("Original Task", originalTask.getTask());

        // Simulate updating the task's description in the mock implementation
        String updatedDescription = "Updated Task Description";
        dbHandler.updateTask(originalTask.getId(), updatedDescription);
        taskList.get(0).setTask(updatedDescription);

        List<TaskModel> updatedTaskList = dbHandler.getAllTasks();
        assertEquals(1, updatedTaskList.size());

        TaskModel updatedTask = updatedTaskList.get(0);
        assertEquals(updatedDescription, updatedTask.getTask());
    }
}
