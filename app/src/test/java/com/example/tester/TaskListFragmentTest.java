package com.example.tester;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.content.DialogInterface;
import android.view.MenuItem;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tester.UI.TaskAdapter;
import com.example.tester.entities.TaskModel;
import com.example.tester.UI.TaskListFragment;
import com.example.tester.entities.DatabaseHandler;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit tests for the TaskListFragment class.
 */
@RunWith(MockitoJUnitRunner.class)
public class TaskListFragmentTest {

    @Mock
    private DatabaseHandler mockDb;

    @Mock
    private TaskAdapter mockAdapter;

    @Mock
    private RecyclerView mockRecyclerView;

    @Mock
    private FloatingActionButton mockFab;

    @Mock
    private BottomNavigationView mockBottomNavigationView;

    @Mock
    private MenuItem mockMenuItem;

    @Mock
    private FragmentManager mockFragmentManager;

    @Mock
    private FragmentTransaction mockFragmentTransaction;

    @Mock
    private Fragment mockFragment;

    @Mock
    private DialogInterface mockDialogInterface;

    private TaskListFragment taskListFragment;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        taskListFragment = spy(new TaskListFragment());
        doReturn(mockFragmentManager).when(taskListFragment).getSupportFragmentManager();
        doReturn(mockFragmentTransaction).when(mockFragmentManager).beginTransaction();
        doReturn(mockFragmentTransaction).when(mockFragmentTransaction).replace(anyInt(), any(Fragment.class));
        doReturn(mockRecyclerView).when(taskListFragment).findViewById(R.id.tasksRecyclerView);
        doReturn(mockAdapter).when(mockRecyclerView).getAdapter();
        doReturn(mockFab).when(taskListFragment).findViewById(R.id.fab);
        doReturn(mockBottomNavigationView).when(taskListFragment).findViewById(R.id.bottomNavigationView);
        doReturn(true).when(mockMenuItem).isChecked();
    }

    @After
    public void tearDown() {
        taskListFragment = null;
    }

    @Test
    public void onCreate_shouldInitializeViewsAndSetListeners() {
        taskListFragment.onCreate(null);

        // Assertions and verifications
        // Verify that expected methods were called on the mocked objects
        verify(mockDb).openDatabase();
        verify(mockRecyclerView).setLayoutManager(any(RecyclerView.LayoutManager.class));
        verify(mockRecyclerView).setAdapter(mockAdapter);
        verify(mockFab).setOnClickListener(any());
        verify(mockBottomNavigationView).setOnItemSelectedListener(any());
    }

    @Test
    public void handleDialogClose_shouldRefreshTaskListAndNotifyAdapter() {
        List<TaskModel> fakeTaskList = createFakeTaskList();
        when(mockDb.getAllTasks()).thenReturn(fakeTaskList);

        taskListFragment.handleDialogClose(mockDialogInterface);

        // Assertions and verifications
        // Verify that expected methods were called on the mocked objects
        verify(mockDb).getAllTasks();
        verify(mockAdapter).setTasks(fakeTaskList);
        verify(mockAdapter).notifyDataSetChanged();
    }

    @Test
    public void replaceFragment_shouldReplaceFragmentAndFinishActivity() {
        when(mockFragmentManager.beginTransaction()).thenReturn(mockFragmentTransaction);

        taskListFragment.replaceFragment(mockFragment);

        // Assertions and verifications
        // Verify that expected methods were called on the mocked objects
        verify(mockFragmentTransaction).replace(anyInt(), eq(mockFragment));
        verify(mockFragmentTransaction).commit();
        verify(taskListFragment).finish();
    }

    // Helper method to create a fake task list
    private List<TaskModel> createFakeTaskList() {
        List<TaskModel> taskList = new ArrayList<>();
        TaskModel task1 = new TaskModel();
        task1.setId(1);
        task1.setTask("Task 1");
        task1.setStatus(0);
        taskList.add(task1);
        TaskModel task2 = new TaskModel();
        task2.setId(2);
        task2.setTask("Task 2");
        task2.setStatus(1);
        taskList.add(task2);
        return taskList;
    }
}
