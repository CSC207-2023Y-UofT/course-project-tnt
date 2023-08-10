package com.example.tester;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tester.Adapters.TaskAdapter;
import com.example.tester.Model.TaskModel;
import com.example.tester.Utils.DatabaseHandler;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit tests for the TaskAdapter class.
 */
public class TaskAdapterTest {

    private TaskAdapter taskAdapter;

    @Mock
    private DatabaseHandler mockDb;
    @Mock
    private TaskListFragment mockActivity;
    @Mock
    private Context mockContext;
    @Mock
    private RecyclerView.ViewHolder mockViewHolder;
    @Mock
    private View mockView;
    @Mock
    private CheckBox mockCheckBox;
    @Mock
    private FragmentManager mockFragmentManager;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        when(mockActivity.getSupportFragmentManager()).thenReturn(mockFragmentManager);
        when(mockViewHolder.itemView).thenReturn(mockView);
        when(mockView.findViewById(R.id.todoCheckBox)).thenReturn(mockCheckBox);

        taskAdapter = new TaskAdapter(mockDb, mockActivity);
    }

    @Test
    public void onCreateViewHolder_createsViewHolder() {
        ViewGroup mockParent = mock(ViewGroup.class);
        when(mockParent.getContext()).thenReturn(mockContext);

        RecyclerView.ViewHolder viewHolder = taskAdapter.onCreateViewHolder(mockParent, 0);

        assertEquals(TaskAdapter.ViewHolder.class, viewHolder.getClass());
    }

    @Test
    public void onBindViewHolder_bindsTaskData() {
        List<TaskModel> mockTodoList = new ArrayList<>();
        TaskModel task = new TaskModel();
        task.setId(1);
        task.setTask("Task 1");
        task.setStatus(1);
        mockTodoList.add(task);
        taskAdapter.setTasks(mockTodoList);

        when(mockDb.getAllTasks()).thenReturn(mockTodoList);

        TaskAdapter.ViewHolder viewHolder = new TaskAdapter.ViewHolder(mockView);
        taskAdapter.onBindViewHolder(viewHolder, 0);

        // Verify that the checkbox is checked and its text is set correctly
        verify(mockCheckBox).setChecked(true);
        verify(mockCheckBox).setText("Task 1");
    }

    @Test
    public void deleteItem_deletesTask() {
        List<TaskModel> mockTodoList = new ArrayList<>();
        TaskModel task = new TaskModel();
        task.setId(1);
        task.setTask("Task 1");
        task.setStatus(1);
        mockTodoList.add(task);
        taskAdapter.setTasks(mockTodoList);

        taskAdapter.deleteItem(0);

        // Verify that deleteTask method was called with the correct ID
        verify(mockDb).deleteTask(1);

        // Verify that the list size is updated
        assertEquals(0, mockTodoList.size());
    }

    @Test
    public void editItem_showsEditDialog() {
        List<TaskModel> mockTodoList = new ArrayList<>();
        TaskModel task = new TaskModel();
        task.setId(1);
        task.setTask("Task 1");
        task.setStatus(1);
        mockTodoList.add(task);
        taskAdapter.setTasks(mockTodoList);

        taskAdapter.editItem(0);

        // Verify that beginTransaction method was called on the fragment manager
        verify(mockFragmentManager).beginTransaction();
    }
}
