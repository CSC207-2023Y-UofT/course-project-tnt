package com.example.tester;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Unit tests for the AddTaskUseCase class.
 */
public class AddTaskUseCaseTest {

    @Mock
    private FragmentActivity mockActivity;
    @Mock
    private FragmentManager mockFragmentManager;
    @Mock
    private DialogInterface mockDialogInterface;

    private AddTaskUseCase addTaskUseCase;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        when(mockActivity.getSupportFragmentManager()).thenReturn(mockFragmentManager);
        when(mockFragmentManager.beginTransaction()).thenReturn(null);

        addTaskUseCase = AddTaskUseCase.newInstance();
    }

    /**
     * Test that the newInstance method returns a non-null instance of AddTaskUseCase.
     */
    @Test
    public void testNewInstanceNotNull() {
        assertNotNull(addTaskUseCase.newInstance());
    }

    /**
     * Test the onCreate method of the AddTaskUseCase fragment when a Bundle with task data is provided.
     * The text field in the fragment should be correctly populated with the task text from the Bundle.
     */
    @Test
    public void testOnCreateDialogFragment() {
        Bundle mockBundle = mock(Bundle.class);
        when(mockBundle.getString("task")).thenReturn("Sample Task");

        addTaskUseCase.setArguments(mockBundle);

        addTaskUseCase.onCreate(mock(Bundle.class));

        // Verify that the text field is correctly populated
        assertEquals("Sample Task", addTaskUseCase.getNewTaskText().getText().toString());
    }

    /**
     * Test the handleDialogClose method of the AddTaskUseCase fragment.
     * The method should call the handleDialogClose method of the set DialogCloseListener.
     */
    @Test
    public void testHandleDialogClose() {
        DialogCloseListener mockListener = mock(DialogCloseListener.class);
        addTaskUseCase.setDialogCloseListener();

        addTaskUseCase.onDismiss(mockDialogInterface);

        verify(mockListener, times(1)).handleDialogClose(mockDialogInterface);
    }
}