package com.example.tester.UI;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tester.useCase.AddTaskUseCase;
import com.example.tester.util.DialogCloseListener;
import com.example.tester.R;
import com.example.tester.useCase.RemoveTaskUseCase;
import com.example.tester.entities.TaskModel;
import com.example.tester.entities.DatabaseHandler;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Collections;
import java.util.List;

/**
 * The TaskListFragment class represents the main activity for displaying the list of tasks.
 * This activity handles the task list UI, including adding, editing, and deleting tasks.
 */
public class TaskListFragment extends AppCompatActivity implements DialogCloseListener {

    private DatabaseHandler db;

    private TaskAdapter tasksAdapter;

    private List<TaskModel> taskList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_task_list);

        db = new DatabaseHandler(this);
        db.openDatabase();

        RecyclerView tasksRecyclerView = findViewById(R.id.tasksRecyclerView);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tasksAdapter = new TaskAdapter(db, TaskListFragment.this);
        tasksRecyclerView.setAdapter(tasksAdapter);

        ItemTouchHelper itemTouchHelper = new
                ItemTouchHelper(new RemoveTaskUseCase(tasksAdapter));
        itemTouchHelper.attachToRecyclerView(tasksRecyclerView);

        FloatingActionButton fab = findViewById(R.id.fab);

        taskList = db.getAllTasks();
        Collections.reverse(taskList);

        tasksAdapter.setTasks(taskList);

        fab.setOnClickListener(v -> AddTaskUseCase.newInstance().show(getSupportFragmentManager(), AddTaskUseCase.TAG));

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.timer) {
                replaceFragment(new PomodoroTimer());
                return true;
            } else if (itemId == R.id.tasks) {
                return true;
            } else if (itemId == R.id.logout) {
                DatabaseHandler.deleteAllTasks();
                Intent intent = new Intent(TaskListFragment.this, MainActivity.class);
                startActivity(intent);
                return true;
            }
            return false;
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void handleDialogClose(DialogInterface dialog){
        runOnUiThread(() -> {
            taskList = db.getAllTasks();
            Collections.reverse(taskList);
            tasksAdapter.setTasks(taskList);
            tasksAdapter.notifyDataSetChanged();
        });
    }

    /**
     * Replaces the current fragment with the provided fragment and finishes the current activity.
     *
     * @param fragment The fragment to replace the current fragment with.
     */
    public void replaceFragment(Fragment fragment) {
        runOnUiThread(() -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout, fragment);
            fragmentTransaction.commit();

            // Close the current activity
            finish();
        });
    }
}