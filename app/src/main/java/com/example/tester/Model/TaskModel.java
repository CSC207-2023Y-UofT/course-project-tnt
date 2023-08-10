package com.example.tester.Model;

/**
 * A class representing a task item.
 */
public class TaskModel {
    private int id, status;
    private String task;

    /**
     * Gets the ID of the task.
     *
     * @return The ID of the task.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the task.
     *
     * @param id The ID to set for the task.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the status of the task.
     *
     * @return The status of the task.
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets the status of the task.
     *
     * @param status The status to set for the task.
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getTask() {
        return task;
    }

    /**
     * Sets the description of the task.
     *
     * @param task The description to set for the task.
     */
    public void setTask(String task) {
        this.task = task;
    }
}
