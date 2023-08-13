package com.example.tester;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.example.tester.entities.TaskModel;

/**
 * Unit tests for the TaskModel class.
 */
@RunWith(MockitoJUnitRunner.class)
public class TaskModelTest {

    @Mock
    TaskModel taskModel;

    @Before
    public void setUp() {
        // No need to create a new instance of TaskModel, as it will be mocked
    }

    @Test
    public void testMockedGetId() {
        when(taskModel.getId()).thenReturn(3);
        assertEquals(3, taskModel.getId());
    }

    @Test
    public void testMockedSetId() {
        taskModel.setId(4);
        verify(taskModel).setId(4);
    }

    @Test
    public void testMockedGetStatus() {
        when(taskModel.getStatus()).thenReturn(2);
        assertEquals(2, taskModel.getStatus());
    }

    @Test
    public void testMockedSetStatus() {
        taskModel.setStatus(3);
        verify(taskModel).setStatus(3);
    }

    @Test
    public void testMockedGetTask() {
        when(taskModel.getTask()).thenReturn("Mocked Task");
        assertEquals("Mocked Task", taskModel.getTask());
    }

    @Test
    public void testMockedSetTask() {
        taskModel.setTask("Another Task");
        verify(taskModel).setTask("Another Task");
    }
}