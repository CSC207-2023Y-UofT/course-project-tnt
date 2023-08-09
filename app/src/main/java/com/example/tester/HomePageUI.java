package com.example.tester;
import com.example.tester.databinding.HomepageBinding;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * The HomePageUI class represents the main activity for the home page user interface.
 * This activity handles bottom navigation menu for controlling timer, task list, and user log-out
 * fragments on the home page.
 */
public class HomePageUI extends AppCompatActivity {

    /**
     * Called when the back button is pressed.
     * This method is overridden to perform nothing to prevent unintended log-out
     * (i.e. going back to main page)
     */
    @Override
    public void onBackPressed() {
        // Do nothing so that user does not log out by pressing the back button
        // of the Android device
    }

    /**
     * The binding for the home page UI layout.
     */
    HomepageBinding binding;

    /**
     * Called when the activity is created. Sets up the UI and initializes the initial fragment.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously
     *                           being shut down, this Bundle contains the data it most recently
     *                           supplied.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = HomepageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new PomodoroTimer());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemID = item.getItemId();

            if (itemID == R.id.timer) {
                replaceFragment(new PomodoroTimer());
            } else if (itemID == R.id.tasks) {
                replaceFragment(new TaskListFragment());
            } else if (itemID == R.id.logout) {
                Intent intent = new Intent(HomePageUI.this, MainActivity.class);
                startActivity(intent);
            }

            return true;
        });
    }

    /**
     * Replaces the current fragment with the provided fragment.
     *
     * @param fragment The fragment to replace the current fragment with.
     */
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}
