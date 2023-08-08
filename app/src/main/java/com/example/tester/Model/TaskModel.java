package com.example.tester.Model;

/**
 * Represents a task in the application.
 */
public class TaskModel {
    private int id, status;
    private String task;

    /**
     * Get the unique identifier of the task.
     *
     * @return The task's unique identifier.
     */
    public int getId() {
        return id;
    }

    /**
     * Set the unique identifier of the task.
     *
     * @param id The task's unique identifier.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the status of the task.
     *
     * @return The status of the task (0 for incomplete, 1 for complete).
     */
    public int getStatus() {
        return status;
    }

    /**
     * Set the status of the task.
     *
     * @param status The status of the task (0 for incomplete, 1 for complete).
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Get the description of the task.
     *
     * @return The description of the task.
     */
    public String getTask() {
        return task;
    }

    /**
     * Set the description of the task.
     *
     * @param task The description of the task.
     */
    public void setTask(String task) {
        this.task = task;
    }
}
