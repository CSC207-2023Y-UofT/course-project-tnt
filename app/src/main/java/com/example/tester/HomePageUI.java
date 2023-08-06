package com.example.tester;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.tester.databinding.HomepageBinding;
import com.google.android.material.button.MaterialButton;

public class HomePageUI extends AppCompatActivity {

    @Override
    public void onBackPressed() {

    }

    HomepageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = HomepageBinding.inflate(getLayoutInflater());
//        setContentView(R.layout.homepage);
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

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}