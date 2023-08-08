package com.example.tester.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tester.AddTaskUseCase;
import com.example.tester.TaskListFragment;
import com.example.tester.Model.TaskModel;
import com.example.tester.Utils.DatabaseHandler;

import java.util.List;
import com.example.tester.R;

/**
 * Adapter class for the RecyclerView used to display tasks in the TaskListFragment.
 */
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private List<TaskModel> taskList;
    private final DatabaseHandler db;
    private final TaskListFragment activity;

    /**
     * Constructor for the TaskAdapter.
     *
     * @param db       The database handler used to interact with the task database.
     * @param activity The TaskListFragment instance to which this adapter is attached.
     */
    public TaskAdapter(DatabaseHandler db, TaskListFragment activity) {
        this.db = db;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_layout, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        db.openDatabase();

        final TaskModel item = taskList.get(position);
        holder.task.setText(item.getTask());
        holder.task.setChecked(toBoolean(item.getStatus()));
        holder.task.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                db.updateStatus(item.getId(), 1);
            } else {
                db.updateStatus(item.getId(), 0);
            }
        });
    }

    private boolean toBoolean(int n) {
        return n != 0;
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    /**
     * Get the context associated with the TaskListFragment.
     *
     * @return The context associated with the TaskListFragment.
     */
    public Context getContext() {
        return activity.requireContext();
    }

    /**
     * Set the task list and notify the adapter of the data change.
     *
     * @param taskList The new task list to be displayed.
     */
    @SuppressLint("NotifyDataSetChanged")
    public void setTasks(List<TaskModel> taskList) {
        this.taskList = taskList;
        notifyDataSetChanged();
    }

    /**
     * Delete a task item at the specified position.
     *
     * @param position The position of the task item to be deleted.
     */
    public void deleteItem(int position) {
        TaskModel item = taskList.get(position);
        db.deleteTask(item.getId());
        taskList.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * Edit a task item at the specified position.
     *
     * @param position The position of the task item to be edited.
     */
    public void editItem(int position) {
        TaskModel item = taskList.get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("id", item.getId());
        bundle.putString("task", item.getTask());
        AddTaskUseCase fragment = new AddTaskUseCase();
        fragment.setArguments(bundle);
        fragment.show(activity.getChildFragmentManager(), AddTaskUseCase.TAG);
    }

    /**
     * ViewHolder class to hold references to the views for each task item in the RecyclerView.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox task;

        ViewHolder(View view) {
            super(view);
            task = view.findViewById(R.id.todoCheckBox);
        }

        /**
         * Get the CheckBox view representing the task.
         *
         * @return The CheckBox view representing the task.
         */
        public CheckBox getTask() {
            return task;
        }
    }
}
