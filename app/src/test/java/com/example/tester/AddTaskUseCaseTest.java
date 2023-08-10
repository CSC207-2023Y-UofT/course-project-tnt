package com.example.tester;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.example.tester.entities.DatabaseHandler;
import com.example.tester.useCase.AddTaskUseCase;
import com.example.tester.util.DialogCloseListener;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Unit tests for the AddTaskUseCase class.
 */
public class AddTaskUseCaseTest {

    @Mock
    private Context mockContext;

    @Mock
    private FragmentActivity mockActivity;

    @Mock
    private FragmentManager mockFragmentManager;

    @Mock
    private View mockView;

    @Mock
    private EditText mockEditText;

    @Mock
    private Button mockButton;

    private AddTaskUseCase addTaskUseCase;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Simulate color resource
        when(mockContext.getColor(R.color.colorPrimaryDark)).thenReturn(Color.parseColor("#FF0000"));

        when(mockActivity.getSupportFragmentManager()).thenReturn(mockFragmentManager);
        when(mockActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).thenReturn(mockView);
        when(mockView.findViewById(R.id.newTaskText)).thenReturn(mockEditText);
        when(mockView.findViewById(R.id.newTaskButton)).thenReturn(mockButton);

        addTaskUseCase = AddTaskUseCase.newInstance();
        addTaskUseCase.setArguments(new Bundle());
    }

    @Test
    public void testOnCreateView() {
        // Mocking required objects and actions

        // Call the method to be tested
        View resultView = addTaskUseCase.onCreateView(
                mock(LayoutInflater.class), // Non-null LayoutInflater
                mock(ViewGroup.class), // Non-null ViewGroup
                null // Passing null as the Bundle argument for this test case
        );

        // Assertions and verifications
        assertNotNull(resultView); // Check if the view is not null
    }

    @Test
    public void testOnViewCreated() {
        // Mocking required objects and actions
        when(mockEditText.getText()).thenReturn(mock(Editable.class)); // Mock the Editable
        when(mockEditText.getText().toString()).thenReturn("Sample Task");
        when(mockEditText.getContext()).thenReturn(mockContext);

        // Simulate color resource
        when(mockContext.getColor(R.color.colorPrimaryDark)).thenReturn(Color.parseColor("#FF0000"));

        // Call the method to be tested
        addTaskUseCase.onViewCreated(mockView, null);

        // Assertions and verifications
        // Assert that the button's enabled state is as expected
        verify(mockButton).setEnabled(true);
    }


    @Test
    public void testOnViewCreated_UpdateTask() {
        // Mocking required objects and actions
        Bundle mockBundle = mock(Bundle.class);
        when(mockBundle.getString("task")).thenReturn("Updated Task");
        when(mockButton.getText()).thenReturn("Save"); // Simulate a button click
        when(mockEditText.getText().toString()).thenReturn("Updated Task");
        when(mockEditText.getContext()).thenReturn(mockContext);

        // Mock the DatabaseHandler
        DatabaseHandler mockDatabaseHandler = mock(DatabaseHandler.class);
        addTaskUseCase.db = mockDatabaseHandler; // Inject the mock DatabaseHandler

        addTaskUseCase.setArguments(mockBundle);

        // Simulate color resource
        when(mockContext.getColor(R.color.colorPrimaryDark)).thenReturn(Color.parseColor("#FF0000"));

        // Call the method to be tested
        addTaskUseCase.onViewCreated(mockView, null);

        // Verifications
        // Verify that updateTask method was called with the expected parameters
        verify(mockDatabaseHandler).updateTask(anyInt(), eq("Updated Task"));
    }

    @Test
    public void testOnViewCreated_SaveNewTask() {
        // Mocking required objects and actions
        when(mockButton.getText()).thenReturn("Save"); // Simulate a button click
        when(mockEditText.getText().toString()).thenReturn("New Task");
        when(mockEditText.getContext()).thenReturn(mockContext);

        // Simulate color resource
        when(mockContext.getColor(R.color.colorPrimaryDark)).thenReturn(Color.parseColor("#FF0000"));

        // Call the method to be tested
        addTaskUseCase.onViewCreated(mockView, null);

        // Assertions and verifications
        verify(mockButton).setTextColor(Color.parseColor("#FF0000"));
        verify(mockButton).setEnabled(true);
        verify(mockButton).performClick(); // Simulate button click
    }

    @Test
    public void testOnViewCreated_Dismiss() {
        // Mocking required objects and actions
        when(mockButton.getText()).thenReturn("Dismiss"); // Simulate a button click
        when(mockEditText.getText().toString()).thenReturn("New Task");
        when(mockEditText.getContext()).thenReturn(mockContext);

        // Simulate color resource
        when(mockContext.getColor(R.color.colorPrimaryDark)).thenReturn(Color.parseColor("#FF0000"));

        // Call the method to be tested
        addTaskUseCase.onViewCreated(mockView, null);

        // Assertions and verifications
        verify(mockButton).setTextColor(Color.GRAY);
        verify(mockButton).setEnabled(false);
        verify(mockButton).performClick(); // Simulate button click
    }

    @Test
    public void testOnDismiss() {
        // Mocking required objects and actions
        DialogInterface mockDialogInterface = mock(DialogInterface.class);
        DialogCloseListener mockListener = mock(DialogCloseListener.class);
        addTaskUseCase = spy(addTaskUseCase);
        doReturn(mockActivity).when(addTaskUseCase).getActivity();
        when(mockActivity instanceof DialogCloseListener).thenReturn(true);
        when(mockActivity).thenReturn((FragmentActivity) mockListener);

        // Call the method to be tested
        addTaskUseCase.onDismiss(mockDialogInterface);

        // Assertions and verifications
        verify(mockListener).handleDialogClose(mockDialogInterface);
    }
}
