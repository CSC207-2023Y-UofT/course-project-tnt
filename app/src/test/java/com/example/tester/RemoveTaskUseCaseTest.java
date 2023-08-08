package com.example.tester;

import android.graphics.Canvas;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.tester.Adapters.TaskAdapter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Unit tests for the RemoveTaskUseCase class.
 */
@RunWith(MockitoJUnitRunner.class)
public class RemoveTaskUseCaseTest {

    @Mock
    private TaskAdapter mockAdapter;

    private RemoveTaskUseCase removeTaskUseCase;

    @Before
    public void setUp() {
        removeTaskUseCase = new RemoveTaskUseCase(mockAdapter);
    }

    /**
     * Test the onChildDraw method when swiping right.
     * The method should draw the appropriate icon and background color when swiping to the right.
     */
    @Test
    public void testOnChildDraw_SwipeRight() {
        Canvas mockCanvas = Mockito.mock(Canvas.class);
        RecyclerView mockRecyclerView = Mockito.mock(RecyclerView.class);
        RecyclerView.ViewHolder mockViewHolder = Mockito.mock(RecyclerView.ViewHolder.class);
        View mockItemView = Mockito.mock(View.class);

        Mockito.when(mockViewHolder.itemView).thenReturn(mockItemView);
        Mockito.when(mockItemView.getHeight()).thenReturn(100); // Adjust height as needed
        Mockito.when(mockItemView.getLeft()).thenReturn(0);
        Mockito.when(mockItemView.getRight()).thenReturn(200); // Adjust width as needed

        removeTaskUseCase.onChildDraw(
                mockCanvas,
                mockRecyclerView,
                mockViewHolder,
                50, // Swipe distance (positive for right)
                0,
                0,
                false
        );

        // Verify that certain actions have occurred as expected.
        Mockito.verify(mockCanvas).drawRect(
                Mockito.anyInt(), Mockito.anyInt(),
                Mockito.eq(50), Mockito.anyInt(),
                Mockito.any()
        );
    }

    /**
     * Test the onChildDraw method when swiping left.
     * The method should draw the appropriate icon and background color when swiping to the left.
     */
    @Test
    public void testOnChildDraw_SwipeLeft() {
        Canvas mockCanvas = Mockito.mock(Canvas.class);
        RecyclerView mockRecyclerView = Mockito.mock(RecyclerView.class);
        RecyclerView.ViewHolder mockViewHolder = Mockito.mock(RecyclerView.ViewHolder.class);
        View mockItemView = Mockito.mock(View.class);

        Mockito.when(mockViewHolder.itemView).thenReturn(mockItemView);
        Mockito.when(mockItemView.getHeight()).thenReturn(100); // Adjust height as needed
        Mockito.when(mockItemView.getLeft()).thenReturn(0);
        Mockito.when(mockItemView.getRight()).thenReturn(200); // Adjust width as needed

        removeTaskUseCase.onChildDraw(
                mockCanvas,
                mockRecyclerView,
                mockViewHolder,
                -50, // Swipe distance (negative for left)
                0,
                0,
                false
        );

        // Verify that certain actions have occurred as expected.
        // Verify that drawRect method is called with the correct arguments
        Mockito.verify(mockCanvas).drawRect(
                Mockito.eq(0), Mockito.eq(0), // Left and top coordinates
                Mockito.eq(50), Mockito.anyInt(), // Right coordinate
                Mockito.any() // Paint (color) argument
        );
    }
}