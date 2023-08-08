package com.example.tester;

// import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.content.Context;

import net.penguincoders.doit.R;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.example.tester.Adapters.TaskAdapter;
import com.example.tester.Model.TaskModel;
import com.example.tester.Utils.DatabaseHandler;

import java.util.Collections;
import java.util.List;
// import java.util.Objects;

/**
 * A Fragment that displays the list of tasks.
 */
public class TaskListFragment extends Fragment implements DialogCloseListener{

    private DatabaseHandler db;

    private TaskAdapter tasksAdapter;

    private List<TaskModel> taskList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //public void onCreate(Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //Objects.requireNonNull(getSupportActionBar()).hide();
        View rootView = inflater.inflate(R.layout.fragment_task_list, container, false);


        Context context = requireContext();
        db = new DatabaseHandler(context);
        db.openDatabase();

        RecyclerView tasksRecyclerView = rootView.findViewById(R.id.tasksRecyclerView);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        tasksAdapter = new TaskAdapter(db, TaskListFragment.this);
        tasksRecyclerView.setAdapter(tasksAdapter);

        // Set up swipe-to-delete and swipe-to-edit functionality for tasks
        ItemTouchHelper itemTouchHelper = new
                ItemTouchHelper(new RemoveTaskUseCase(tasksAdapter));
        itemTouchHelper.attachToRecyclerView(tasksRecyclerView);

        FloatingActionButton fab = rootView.findViewById(R.id.fab);

        // Retrieve tasks from the database and display them in reverse order (newest first)
        taskList = db.getAllTasks();
        Collections.reverse(taskList);

        tasksAdapter.setTasks(taskList);

        // Open the AddTaskUseCase dialog when the FloatingActionButton is clicked
        fab.setOnClickListener(v -> AddTaskUseCase.newInstance().show(requireActivity().getSupportFragmentManager(), AddTaskUseCase.TAG));
        return rootView;
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void handleDialogClose(DialogInterface dialog){
        // This method is called when the AddTaskUseCase dialog is closed
        // Refresh the task list and update the RecyclerView when a new task is added or edited
        taskList = db.getAllTasks();
        Collections.reverse(taskList);
        tasksAdapter.setTasks(taskList);
        tasksAdapter.notifyDataSetChanged();
    }
}