package com.example.tester;

import com.example.tester.entities.TaskModel;

import java.util.List;

/**
 * Interface for handling database operations related to tasks.
 */
public interface IDatabaseHandler {
    /**
     * Opens the database for writing.
     */
    void openDatabase();
    /**
     * Inserts a new task into the database.
     *
     * @param task The task to be inserted.
     */
    void insertTask(TaskModel task);
    /**
     * Updates the description of a task in the database.
     *
     * @param id   The ID of the task to be updated.
     * @param task The new description of the task.
     */
    void updateTask(int id, String task);
    /**
     * Deletes a task from the database.
     *
     * @param id The ID of the task to be deleted.
     */
    void deleteTask(int id);
    /**
     * Retrieves all tasks from the database.
     *
     * @return A list of all tasks in the database.
     */
    List<TaskModel> getAllTasks();
}
