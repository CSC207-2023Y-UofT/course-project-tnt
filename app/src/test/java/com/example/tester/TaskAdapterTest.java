package com.example.tester;

import com.example.tester.Adapters.TaskAdapter;
import com.example.tester.Model.TaskModel;
import com.example.tester.Utils.DatabaseHandler;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit tests for the TaskAdapter class.
 */
public class TaskAdapterTest {

    private TaskAdapter taskAdapter;
    private DatabaseHandler mockDbHandler;
    private Context context;

    @Before
    public void setUp() {
        context = mock(Context.class);
        mockDbHandler = mock(DatabaseHandler.class);
        TaskListFragment mockActivity = mock(TaskListFragment.class);

        taskAdapter = new TaskAdapter(mockDbHandler, mockActivity);
    }

    /**
     * Test the onCreateViewHolder method.
     * The method should create a ViewHolder and return it.
     */
    @Test
    public void onCreateViewHolderTest() {
        ViewGroup parent = mock(ViewGroup.class);
        LayoutInflater mockInflater = mock(LayoutInflater.class);
        View mockView = mock(View.class);

        when(mockInflater.inflate(anyInt(), eq(parent), anyBoolean())).thenReturn(mockView);
        when(context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).thenReturn(mockInflater);

        TaskAdapter.ViewHolder viewHolder = taskAdapter.onCreateViewHolder(parent, 0);

        // Assertions for viewHolder
        assertNotNull(viewHolder);
        assertNotNull(viewHolder.getTask()); // Assuming task is a member in your ViewHolder class
    }

    /**
     * Test the onBindViewHolder method.
     * The method should bind data to the ViewHolder based on the provided position.
     */
    @Test
    public void onBindViewHolderTest() {
        // Prepare mock data
        TaskModel mockTaskModel = new TaskModel();
        mockTaskModel.setTask("Test Task");
        mockTaskModel.setStatus(0);

        List<TaskModel> mockTaskList = new ArrayList<>();
        mockTaskList.add(mockTaskModel);

        when(mockDbHandler.getAllTasks()).thenReturn(mockTaskList);

        TaskAdapter.ViewHolder mockViewHolder = mock(TaskAdapter.ViewHolder.class);
        RecyclerView mockRecyclerView = mock(RecyclerView.class);
        when(mockViewHolder.getBindingAdapterPosition()).thenReturn(0);

        // Call onBindViewHolder
        taskAdapter.onBindViewHolder(mockViewHolder, 0);

        // Assertions for mockViewHolder
        verify(mockViewHolder, times(1)).getTask().setText("Test Task"); // Assuming 'task' is a TextView in your ViewHolder
        verify(mockViewHolder, times(1)).getTask().setChecked(eq(false)); // Assuming the initial status is 0

        // Assertions for mockRecyclerView
        verify(mockRecyclerView, never()).invalidate(); // For example, assert that invalidate() is not called
    }

    /**
     * Test the getItemCount method.
     * The method should return the correct count of items in the adapter.
     */
    @Test
    public void getItemCountTest() {
        // Prepare mock data
        List<TaskModel> mockTaskList = new ArrayList<>();
        mockTaskList.add(new TaskModel());

        when(mockDbHandler.getAllTasks()).thenReturn(mockTaskList);

        // Call getItemCount
        int itemCount = taskAdapter.getItemCount();

        // Assertions for itemCount
        assertEquals(1, itemCount); // Assuming you have added one item in the mockTaskList
    }

    /**
     * Test the setTasks method.
     * The method should set the provided list of tasks to the adapter.
     */
    @Test
    public void setTasksTest() {
        List<TaskModel> mockTaskList = new ArrayList<>();
        mockTaskList.add(new TaskModel());

        taskAdapter.setTasks(mockTaskList);

        assertEquals(mockTaskList.size(), taskAdapter.getItemCount());
    }

    /**
     * Test the deleteItem method.
     * The method should delete the specified item from the adapter and database.
     */
    @Test
    public void deleteItemTest() {
        List<TaskModel> mockTaskList = new ArrayList<>();
        TaskModel taskToDelete = new TaskModel();
        taskToDelete.setId(1);
        mockTaskList.add(taskToDelete);

        when(mockDbHandler.getAllTasks()).thenReturn(mockTaskList);

        taskAdapter.deleteItem(0);

        verify(mockDbHandler, times(1)).deleteTask(1);
        assertEquals(mockTaskList.size() - 1, taskAdapter.getItemCount());
    }

    /**
     * Test the editItem method.
     * The method should edit the specified item using a dialog fragment.
     */
    @Test
    public void editItemTest() {
        TaskModel mockTaskModel = new TaskModel();
        mockTaskModel.setId(1);
        mockTaskModel.setTask("Test Task");

        Bundle mockBundle = mock(Bundle.class);
        when(mockBundle.getInt("id")).thenReturn(1);
        when(mockBundle.getString("task")).thenReturn("Edited Task");

        TaskListFragment mockActivity = mock(TaskListFragment.class);
        when(mockActivity.getChildFragmentManager()).thenReturn(mock(FragmentManager.class));

        AddTaskUseCase mockAddTaskUseCase = mock(AddTaskUseCase.class);
        when(mockActivity.getChildFragmentManager()).thenReturn(mock(FragmentManager.class));

        // Modify the method invocation for void method using doNothing()
        doNothing().when(mockAddTaskUseCase).setArguments(mockBundle);
        doNothing().when(mockAddTaskUseCase).show(any(FragmentManager.class), eq(AddTaskUseCase.TAG));

        taskAdapter.editItem(0);

        verify(mockBundle, times(1)).putInt("id", 1);
        verify(mockBundle, times(1)).putString("task", "Test Task");
        verify(mockAddTaskUseCase, times(1)).setArguments(mockBundle);
        verify(mockAddTaskUseCase, times(1)).show(any(FragmentManager.class), eq(AddTaskUseCase.TAG));
    }

    /**
     * Test the getContext method.
     * The method should return the context associated with the adapter.
     */
    @Test
    public void getContextTest() {
        TaskListFragment mockActivity = mock(TaskListFragment.class);
        taskAdapter = new TaskAdapter(mockDbHandler, mockActivity);

        Context context = taskAdapter.getContext();

        assertNotNull(context);
        assertEquals(mockActivity.requireContext(), context);
    }
}