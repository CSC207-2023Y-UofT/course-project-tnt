package com.example.tester;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.graphics.Canvas;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tester.UI.TaskAdapter;
import com.example.tester.useCase.RemoveTaskUseCase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Unit tests for the RemoveTaskUseCase class.
 */
public class RemoveTaskUseCaseTest {

    @Mock
    private TaskAdapter mockAdapter;
    @Mock
    private AlertDialog.Builder mockDialogBuilder;
    @Mock
    private AlertDialog mockDialog;
    @Mock
    private View mockView;
    @Mock

    private RemoveTaskUseCase removeTaskUseCase;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(mockDialogBuilder.setTitle(any())).thenReturn(mockDialogBuilder);
        when(mockDialogBuilder.setMessage(any())).thenReturn(mockDialogBuilder);
        when(mockDialogBuilder.setPositiveButton(any(), any())).thenReturn(mockDialogBuilder);
        when(mockDialogBuilder.setNegativeButton(any(), any())).thenReturn(mockDialogBuilder);
        when(mockDialogBuilder.create()).thenReturn(mockDialog);

        removeTaskUseCase = new RemoveTaskUseCase(mockAdapter);
    }

    @Test
    public void onSwiped_directionLeft_deleteItem() {
        int position = 0;
        RecyclerView.ViewHolder mockViewHolder = mock(RecyclerView.ViewHolder.class);
        when(mockAdapter.getContext()).thenReturn(mockView.getContext());
        when(mockViewHolder.getAdapterPosition()).thenReturn(position);
        doNothing().when(mockAdapter).deleteItem(anyInt()); // Mocking the deleteItem method

        removeTaskUseCase.onSwiped(mockViewHolder, ItemTouchHelper.LEFT);

        verify(mockAdapter).deleteItem(position);
    }

    @Test
    public void onSwiped_directionRight_editItem() {
        int position = 1;
        RecyclerView.ViewHolder mockViewHolder = mock(RecyclerView.ViewHolder.class);
        when(mockAdapter.getContext()).thenReturn(mockView.getContext());
        when(mockViewHolder.getAdapterPosition()).thenReturn(position);
        doNothing().when(mockAdapter).editItem(anyInt()); // Mocking the editItem method

        removeTaskUseCase.onSwiped(mockViewHolder, ItemTouchHelper.RIGHT);

        verify(mockAdapter).editItem(position);
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
