package com.example.tester;

import com.example.tester.entities.TaskModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A mock implementation of the IDatabaseHandler interface for testing purposes.
 */
public class MockDatabaseHandler implements IDatabaseHandler {

    private List<TaskModel> taskList = new ArrayList<>();

    /**
     * Mock implementation of opening the database.
     */
    @Override
    public void openDatabase() {
        // Mock implementation
    }

    /**
     * Mock implementation of inserting a task into the mock database.
     *
     * @param task The task to be inserted.
     */
    @Override
    public void insertTask(TaskModel task) {
        taskList.add(task);
    }

    /**
     * Mock implementation of updating the description of a task in the mock database.
     *
     * @param id   The ID of the task to be updated.
     * @param task The new description of the task.
     */
    @Override
    public void updateTask(int id, String task) {
        // Mock implementation
    }

    /**
     * Mock implementation of deleting a task from the mock database.
     *
     * @param id The ID of the task to be deleted.
     */
    @Override
    public void deleteTask(int id) {
        taskList.removeIf(task -> task.getId() == id);
    }

    /**
     * Mock implementation of retrieving all tasks from the mock database.
     *
     * @return A list of all tasks in the mock database.
     */
    @Override
    public List<TaskModel> getAllTasks() {
        return taskList;
    }
}
